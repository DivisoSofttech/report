package com.diviso.graeshoppe.report.service.impl;

import com.diviso.graeshoppe.report.service.OrderMasterService;
import com.diviso.graeshoppe.order.avro.AuxilaryOrderLine;
import com.diviso.graeshoppe.order.avro.Order;
import com.diviso.graeshoppe.report.domain.AuxItem;
import com.diviso.graeshoppe.report.domain.OrderLine;
import com.diviso.graeshoppe.report.domain.OrderMaster;
import com.diviso.graeshoppe.report.repository.OrderMasterRepository;
import com.diviso.graeshoppe.report.repository.search.OrderMasterSearchRepository;
import com.diviso.graeshoppe.report.service.dto.OrderMasterDTO;
import com.diviso.graeshoppe.report.service.mapper.OrderMasterMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Date;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing OrderMaster.
 */
@Service
@Transactional
public class OrderMasterServiceImpl implements OrderMasterService {

    private final Logger log = LoggerFactory.getLogger(OrderMasterServiceImpl.class);

    private final OrderMasterRepository orderMasterRepository;

    private final OrderMasterMapper orderMasterMapper;

    private final OrderMasterSearchRepository orderMasterSearchRepository;

    public OrderMasterServiceImpl(OrderMasterRepository orderMasterRepository, OrderMasterMapper orderMasterMapper, OrderMasterSearchRepository orderMasterSearchRepository) {
        this.orderMasterRepository = orderMasterRepository;
        this.orderMasterMapper = orderMasterMapper;
        this.orderMasterSearchRepository = orderMasterSearchRepository;
    }

    /**
     * Save a orderMaster.
     *
     * @param orderMasterDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public OrderMasterDTO save(OrderMasterDTO orderMasterDTO) {
        log.debug("Request to save OrderMaster : {}", orderMasterDTO);

        OrderMaster orderMaster = orderMasterMapper.toEntity(orderMasterDTO);
        orderMaster = orderMasterRepository.save(orderMaster);
        OrderMasterDTO result = orderMasterMapper.toDto(orderMaster);
        orderMasterSearchRepository.save(orderMaster);
        return result;
    }

    /**
     * Get all the orderMasters.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<OrderMasterDTO> findAll(Pageable pageable) {
        log.debug("Request to get all OrderMasters");
        return orderMasterRepository.findAll(pageable)
            .map(orderMasterMapper::toDto);
    }


    /**
     * Get one orderMaster by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<OrderMasterDTO> findOne(Long id) {
        log.debug("Request to get OrderMaster : {}", id);
        return orderMasterRepository.findById(id)
            .map(orderMasterMapper::toDto);
    }

    /**
     * Delete the orderMaster by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete OrderMaster : {}", id);
        orderMasterRepository.deleteById(id);
        orderMasterSearchRepository.deleteById(id);
    }

    /**
     * Search for the orderMaster corresponding to the query.
     *
     * @param query the query of the search
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<OrderMasterDTO> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of OrderMasters for query {}", query);
        return orderMasterSearchRepository.search(queryStringQuery(query), pageable)
            .map(orderMasterMapper::toDto);
    }

    /**
     * Search for the orderMaster corresponding to the order id.
     *
     *
     * @param order id
     * @return the list of entities
     */
    
	@Override
	public OrderMasterDTO findOrderMasterByOrderNumber(String orderNumber) {
		 log.debug("Request to get OrderMaster by order id : {}", orderNumber);
	        return orderMasterMapper.toDto(orderMasterRepository.findOrderMasterByOrderNumber(orderNumber));
	          

	}
	
	
	
	@Override
	public void convertAndSaveOrderMaster(Order order) {
		OrderMaster orderMaster=new OrderMaster();
		orderMaster.setStoreName(null);
		orderMaster.setStorePhone(0l);
		orderMaster.setMethodOfOrder(order.getDeliveryInfo().getDeliveryType().toUpperCase());
		orderMaster.setOrderNumber(order.getOrderId());
		orderMaster.setServiceCharge(order.getDeliveryInfo().getDeliveryCharge());
		if(order.getDeliveryInfo().getDeliveryAddress()!=null) {
			orderMaster.setRoadNameAreaOrStreet(order.getDeliveryInfo().getDeliveryAddress().getRoadNameAreaOrStreet());
			orderMaster.setHouseNoOrBuildingName(order.getDeliveryInfo().getDeliveryAddress().getHouseNoOrBuildingName());
			orderMaster.setCity(order.getDeliveryInfo().getDeliveryAddress().getCity());
			orderMaster.setLandmark(order.getDeliveryInfo().getDeliveryAddress().getLandmark());
			orderMaster.setPhone(order.getDeliveryInfo().getDeliveryAddress().getPhone());
			orderMaster.setAlternatePhone(order.getDeliveryInfo().getDeliveryAddress().getAlternatePhone());
			orderMaster.setPincode(order.getDeliveryInfo().getDeliveryAddress().getPincode());
			orderMaster.setState(order.getDeliveryInfo().getDeliveryAddress().getState());
			orderMaster.setAddressType(order.getDeliveryInfo().getDeliveryAddress().getAddressType());
			orderMaster.setName(order.getDeliveryInfo().getDeliveryAddress().getName());
		}
		orderMaster.setCustomerId(order.getCustomerId());
		orderMaster.setNotes(order.getDeliveryInfo().getDeliveryNotes());
		orderMaster.setOrderFromCustomer(order.getOrderCountRestaurant());
		orderMaster.setTotalDue(order.getGrandTotal());
		Instant expectedDelivery =Instant.ofEpochMilli(order.getApprovalDetails().getExpectedDelivery());
		String dueDate = Date.from(expectedDelivery).toString().substring(4, 10);
		String dueTime = Date.from(expectedDelivery).toString().substring(11, 16);
		orderMaster.setDueDate(dueDate);
		orderMaster.setDueTime(dueTime);
		orderMaster.setCustomerOrder(order.getOrderCountgraeshoppe());
		Instant orderDate =Instant.ofEpochMilli(order.getDate());
		String orderPlacedAt = Date.from(orderDate).toString().substring(4, 10);
		orderMaster.setOrderPlaceAt(orderPlacedAt);
		if(order.getApprovalDetails()!=null) {
			Instant acceptedDate =Instant.ofEpochMilli(order.getApprovalDetails().getAcceptedAt());
			String orderAcceptedAt = Date.from(acceptedDate).toString().substring(4, 10);
			orderMaster.setOrderAcceptedAt(orderAcceptedAt);

		}
		orderMaster.setOrderLines(order.getOrderLines().stream().map(this::toOrderLine).collect(Collectors.toSet()));
		
	}
	
	private OrderLine toOrderLine(com.diviso.graeshoppe.order.avro.OrderLine orderLine) {
		OrderLine line=new OrderLine();
		line.setItem(null); //query to get productname
		line.setQuantity(orderLine.getQuantity());
		line.setTotal(orderLine.getTotal());
		line.setAuxItems(orderLine.getAuxilaryOrderLines().stream().map(this::toAuxItem).collect(Collectors.toSet()));
		return line;
		
	}
	
	private AuxItem toAuxItem(AuxilaryOrderLine aux) {
		AuxItem auxItem=new AuxItem();
		auxItem.setAuxItem(null); //query to get aux name
		auxItem.setQuantity(aux.getQuantity());
		auxItem.setTotal(aux.getTotal());
		return auxItem;
	}

	@Override
	public Optional<OrderMasterDTO> findByOrderNumber(String orderNumber) {

		return orderMasterRepository.findByOrderNumber(orderNumber).map(orderMasterMapper::toDto);
	}
}
