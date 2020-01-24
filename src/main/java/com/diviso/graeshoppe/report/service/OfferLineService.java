package com.diviso.graeshoppe.report.service;

import com.diviso.graeshoppe.report.domain.OfferLine;
import com.diviso.graeshoppe.report.service.dto.OfferLineDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.diviso.graeshoppe.report.domain.OfferLine}.
 */
public interface OfferLineService {

    /**
     * Save a offerLine.
     *
     * @param offerLineDTO the entity to save.
     * @return the persisted entity.
     */
    OfferLineDTO save(OfferLineDTO offerLineDTO);

    /**
     * Get all the offerLines.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<OfferLineDTO> findAll(Pageable pageable);


    /**
     * Get the "id" offerLine.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<OfferLineDTO> findOne(Long id);

    /**
     * Delete the "id" offerLine.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);

    /**
     * Search for the offerLine corresponding to the query.
     *
     * @param query the query of the search.
     * 
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<OfferLineDTO> search(String query, Pageable pageable);
    
    List<OfferLine> findOfferLinesByOrderNumber(String orderId);
}
