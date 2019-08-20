package com.diviso.graeshoppe.report.service;

import com.diviso.graeshoppe.report.service.dto.ComboItemDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing ComboItem.
 */
public interface ComboItemService {

    /**
     * Save a comboItem.
     *
     * @param comboItemDTO the entity to save
     * @return the persisted entity
     */
    ComboItemDTO save(ComboItemDTO comboItemDTO);

    /**
     * Get all the comboItems.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<ComboItemDTO> findAll(Pageable pageable);


    /**
     * Get the "id" comboItem.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<ComboItemDTO> findOne(Long id);

    /**
     * Delete the "id" comboItem.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the comboItem corresponding to the query.
     *
     * @param query the query of the search
     * 
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<ComboItemDTO> search(String query, Pageable pageable);
}
