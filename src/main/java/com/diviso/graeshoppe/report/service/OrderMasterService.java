package com.diviso.graeshoppe.report.service;

import com.diviso.graeshoppe.report.service.dto.OrderMasterDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing OrderMaster.
 */
public interface OrderMasterService {

    /**
     * Save a orderMaster.
     *
     * @param orderMasterDTO the entity to save
     * @return the persisted entity
     */
    OrderMasterDTO save(OrderMasterDTO orderMasterDTO);

    /**
     * Get all the orderMasters.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<OrderMasterDTO> findAll(Pageable pageable);


    /**
     * Get the "id" orderMaster.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<OrderMasterDTO> findOne(Long id);

    /**
     * Delete the "id" orderMaster.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the orderMaster corresponding to the query.
     *
     * @param query the query of the search
     * 
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<OrderMasterDTO> search(String query, Pageable pageable);

    
    /**
     * Search for the orderMaster corresponding to the order id.
     *
     * @param order id
     * 
     * 
     * @return the entity
     */
	OrderMasterDTO findOrderMasterByOrderNumber(String orderNumber);
}
