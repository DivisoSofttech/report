package com.diviso.graeshoppe.report.service.impl;

import com.diviso.graeshoppe.report.service.AuxItemService;
import com.diviso.graeshoppe.report.service.ComboItemService;
import com.diviso.graeshoppe.report.service.OrderLineService;
import com.diviso.graeshoppe.report.service.OrderMasterService;
import com.diviso.graeshoppe.report.service.ReportService;
import com.diviso.graeshoppe.order.avro.AuxilaryOrderLine;
import com.diviso.graeshoppe.order.avro.Order;
import com.diviso.graeshoppe.report.client.customer.api.CustomerResourceApi;
import com.diviso.graeshoppe.report.client.customer.model.Customer;
import com.diviso.graeshoppe.report.client.product.model.ComboLineItem;
import com.diviso.graeshoppe.report.client.product.model.Product;
import com.diviso.graeshoppe.report.client.store.model.Store;
import com.diviso.graeshoppe.report.domain.AuxItem;
import com.diviso.graeshoppe.report.domain.ComboItem;
import com.diviso.graeshoppe.report.domain.OrderLine;
import com.diviso.graeshoppe.report.domain.OrderMaster;
import com.diviso.graeshoppe.report.repository.OrderMasterRepository;
import com.diviso.graeshoppe.report.repository.search.OrderMasterSearchRepository;
import com.diviso.graeshoppe.report.service.dto.AuxItemDTO;
import com.diviso.graeshoppe.report.service.dto.ComboItemDTO;
import com.diviso.graeshoppe.report.service.dto.OrderLineDTO;
import com.diviso.graeshoppe.report.service.dto.OrderMasterDTO;
import com.diviso.graeshoppe.report.service.mapper.AuxItemMapper;
import com.diviso.graeshoppe.report.service.mapper.ComboItemMapper;
import com.diviso.graeshoppe.report.service.mapper.OrderLineMapper;
import com.diviso.graeshoppe.report.service.mapper.OrderMasterMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
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

	@Autowired
	private OrderLineMapper orderLineMapper;
	@Autowired
	private AuxItemMapper auxItemMapper;
	
	@Autowired
	private ComboItemMapper comboItemMapper;
	
	@Autowired
	private ReportService reportService;
	@Autowired
	private OrderLineService orderLineService;
	@Autowired
	private AuxItemService auxItemService;
	@Autowired
	private ComboItemService comboItemService;
	private final OrderMasterMapper orderMasterMapper;

	@Autowired
	private CustomerResourceApi customerResourceApi;
	private final OrderMasterSearchRepository orderMasterSearchRepository;

	public OrderMasterServiceImpl(OrderMasterRepository orderMasterRepository, OrderMasterMapper orderMasterMapper,
			OrderMasterSearchRepository orderMasterSearchRepository) {
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
		return orderMasterRepository.findAll(pageable).map(orderMasterMapper::toDto);
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
		return orderMasterRepository.findById(id).map(orderMasterMapper::toDto);
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
	 * @param query    the query of the search
	 * @param pageable the pagination information
	 * @return the list of entities
	 */
	@Override
	@Transactional(readOnly = true)
	public Page<OrderMasterDTO> search(String query, Pageable pageable) {
		log.debug("Request to search for a page of OrderMasters for query {}", query);
		return orderMasterSearchRepository.search(queryStringQuery(query), pageable).map(orderMasterMapper::toDto);
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
		return orderMasterRepository.findByOrderNumber(orderNumber).map(orderMasterMapper::toDto).get();

	}

	@Override
	public void convertAndSaveOrderMaster(Order order) {
		if(order.getDeliveryInfo().getDeliveryNotes() == null) {
			order.getDeliveryInfo().setDeliveryNotes("**notes not provided**");
		}
		OrderMaster orderMaster=new OrderMaster();
		Store store = reportService.findStoreByStoreId(order.getStoreId());
		Customer customer=reportService.findCustomerByReference(order.getCustomerId());
		orderMaster.setStoreName(store.getName());
		orderMaster.setStorePhone(store.getContactNo());
		orderMaster.setMethodOfOrder(order.getDeliveryInfo().getDeliveryType().toUpperCase());
		orderMaster.setOrderNumber(order.getOrderId());
		orderMaster.setDeliveryCharge(order.getDeliveryInfo().getDeliveryCharge());
		orderMaster.setPhone(order.getCustomerPhone());
		orderMaster.setAllergyNote(order.getAllergyNote());
		orderMaster.setPreOrderDate(Instant.ofEpochMilli(order.getPreOrderDate()));
		if (order.getDeliveryInfo().getDeliveryAddress() != null) {
			orderMaster.setRoadNameAreaOrStreet(order.getDeliveryInfo().getDeliveryAddress().getRoadNameAreaOrStreet());
			orderMaster.setEmail(order.getDeliveryInfo().getDeliveryAddress().getEmail());
			orderMaster
					.setHouseNoOrBuildingName(order.getDeliveryInfo().getDeliveryAddress().getHouseNoOrBuildingName());
			orderMaster.setCity(order.getDeliveryInfo().getDeliveryAddress().getCity());
			orderMaster.setLandmark(order.getDeliveryInfo().getDeliveryAddress().getLandmark());
			// orderMaster.setPhone(order.getDeliveryInfo().getDeliveryAddress().getPhone());
			orderMaster.setAlternatePhone(order.getDeliveryInfo().getDeliveryAddress().getAlternatePhone());
			orderMaster.setPincode(order.getDeliveryInfo().getDeliveryAddress().getPincode());
			orderMaster.setState(order.getDeliveryInfo().getDeliveryAddress().getState());
			orderMaster.setAddressType(order.getDeliveryInfo().getDeliveryAddress().getAddressType());
			orderMaster.setName(order.getDeliveryInfo().getDeliveryAddress().getName());
		}
		orderMaster.setCustomerId(customer.getCustomerUniqueId());
		orderMaster.setNotes(order.getDeliveryInfo().getDeliveryNotes());
		orderMaster.setOrderFromCustomer(order.getOrderCountRestaurant());
		orderMaster.setTotalDue(order.getGrandTotal());
		orderMaster.storelocationName(store.getStoreAddress().getLandmark());
		Instant expectedDelivery = Instant.ofEpochMilli(order.getApprovalDetails().getExpectedDelivery());
		orderMaster.setExpectedDelivery(expectedDelivery);
		orderMaster.setCustomerOrder(order.getOrderCountgraeshoppe());
		Instant orderDate = Instant.ofEpochMilli(order.getDate());
		orderMaster.setOrderPlaceAt(orderDate);
		if (order.getApprovalDetails() != null) {
			Instant acceptedDate = Instant.ofEpochMilli(order.getApprovalDetails().getAcceptedAt());
			orderMaster.setOrderAcceptedAt(acceptedDate);
		}

		log.info("The order master going to persist is ^^^^^^^^^^^^^^^^^ " + orderMaster);
		OrderMaster updatedResult = orderMasterRepository.save(orderMaster);
		order.getOrderLines().stream().map(this::toOrderLine).collect(Collectors.toSet())
		.forEach(orderline -> {
			// saving orderlines
			OrderLineDTO lineDTO = orderLineMapper.toDto(orderline);
			lineDTO.setOrderMasterId(updatedResult.getId());
			OrderLineDTO resultLineDTO = orderLineService.save(lineDTO);
			
			//saving the combos of orderline
			orderline.getComboItems().forEach(comboItem->{
				ComboItemDTO comboItemDTO=comboItemMapper.toDto(comboItem);
				comboItemDTO.setOrderLineId(resultLineDTO.getId());
				comboItemService.save(comboItemDTO);
			});
			
			//saving the aux items of the particular line
			orderline.getAuxItems()
			.forEach(auxItem -> {
				AuxItemDTO auxItemDTO=auxItemMapper.toDto(auxItem);
				auxItemDTO.setOrderLineId(resultLineDTO.getId());
				auxItemService.save(auxItemDTO);
			});
		});
	}

	private OrderLine toOrderLine(com.diviso.graeshoppe.order.avro.OrderLine orderLine) {
		OrderLine line = new OrderLine();
		Product product = reportService.findProductByProductId(orderLine.getProductId());
		if(product!=null) {
		line.setItem(product.getName());
		}								// query to get productname
		line.setQuantity(orderLine.getQuantity());
		line.setTotal(orderLine.getTotal());
		line.setAuxItems(orderLine.getAuxilaryOrderLines().stream().map(this::toAuxItem).collect(Collectors.toSet()));
		List<ComboLineItem> comboItems=reportService.findCombosByProductId(orderLine.getProductId());
		line.setComboItems(comboItems.stream().map(this::toComboItem).collect(Collectors.toSet()));
		return line;

	}
	
	private ComboItem toComboItem(ComboLineItem lineitem) {
		ComboItem comboItem=new ComboItem();
		comboItem.setComboItem(lineitem.getProduct().getName());
		comboItem.setQuantity(lineitem.getQuantity());
		return comboItem;
	}

	private AuxItem toAuxItem(AuxilaryOrderLine aux) {
		AuxItem auxItem = new AuxItem();
		Product product = reportService.findProductByProductId(aux.getProductId());
		auxItem.setAuxItem(product.getName()); // query to get aux name
		auxItem.setQuantity(aux.getQuantity());
		auxItem.setTotal(aux.getTotal());
		return auxItem;
	}

	@Override
	public Optional<OrderMasterDTO> findByOrderNumber(String orderNumber) {

		return orderMasterRepository.findByOrderNumber(orderNumber).map(orderMasterMapper::toDto);
	}
}
