package com.diviso.graeshoppe.report.service;

import com.diviso.graeshoppe.report.domain.AuxItem;
import com.diviso.graeshoppe.report.service.dto.AuxItemDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing AuxItem.
 */
public interface AuxItemService {

    /**
     * Save a auxItem.
     *
     * @param auxItemDTO the entity to save
     * @return the persisted entity
     */
    AuxItemDTO save(AuxItemDTO auxItemDTO);

    /**
     * Get all the auxItems.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<AuxItemDTO> findAll(Pageable pageable);


    /**
     * Get the "id" auxItem.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<AuxItemDTO> findOne(Long id);

    /**
     * Delete the "id" auxItem.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the auxItem corresponding to the query.
     *
     * @param query the query of the search
     * 
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<AuxItemDTO> search(String query, Pageable pageable);

	//List<AuxItem> findByOrderLine_id(Long id);
}
