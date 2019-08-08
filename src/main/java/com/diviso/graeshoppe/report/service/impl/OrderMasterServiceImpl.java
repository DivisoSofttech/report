package com.diviso.graeshoppe.report.service.impl;

import com.diviso.graeshoppe.report.service.OrderMasterService;
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

import java.util.Optional;

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
}
