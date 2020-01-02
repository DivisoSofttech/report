package com.diviso.graeshoppe.report.web.rest;

import com.diviso.graeshoppe.report.service.OfferLineService;
import com.diviso.graeshoppe.report.web.rest.errors.BadRequestAlertException;
import com.diviso.graeshoppe.report.service.dto.OfferLineDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.diviso.graeshoppe.report.domain.OfferLine}.
 */
@RestController
@RequestMapping("/api")
public class OfferLineResource {

    private final Logger log = LoggerFactory.getLogger(OfferLineResource.class);

    private static final String ENTITY_NAME = "reportOfferLine";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final OfferLineService offerLineService;

    public OfferLineResource(OfferLineService offerLineService) {
        this.offerLineService = offerLineService;
    }

    /**
     * {@code POST  /offer-lines} : Create a new offerLine.
     *
     * @param offerLineDTO the offerLineDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new offerLineDTO, or with status {@code 400 (Bad Request)} if the offerLine has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/offer-lines")
    public ResponseEntity<OfferLineDTO> createOfferLine(@RequestBody OfferLineDTO offerLineDTO) throws URISyntaxException {
        log.debug("REST request to save OfferLine : {}", offerLineDTO);
        if (offerLineDTO.getId() != null) {
            throw new BadRequestAlertException("A new offerLine cannot already have an ID", ENTITY_NAME, "idexists");
        }
        OfferLineDTO result = offerLineService.save(offerLineDTO);
        return ResponseEntity.created(new URI("/api/offer-lines/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /offer-lines} : Updates an existing offerLine.
     *
     * @param offerLineDTO the offerLineDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated offerLineDTO,
     * or with status {@code 400 (Bad Request)} if the offerLineDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the offerLineDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/offer-lines")
    public ResponseEntity<OfferLineDTO> updateOfferLine(@RequestBody OfferLineDTO offerLineDTO) throws URISyntaxException {
        log.debug("REST request to update OfferLine : {}", offerLineDTO);
        if (offerLineDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        OfferLineDTO result = offerLineService.save(offerLineDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, offerLineDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /offer-lines} : get all the offerLines.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of offerLines in body.
     */
    @GetMapping("/offer-lines")
    public ResponseEntity<List<OfferLineDTO>> getAllOfferLines(Pageable pageable) {
        log.debug("REST request to get a page of OfferLines");
        Page<OfferLineDTO> page = offerLineService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /offer-lines/:id} : get the "id" offerLine.
     *
     * @param id the id of the offerLineDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the offerLineDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/offer-lines/{id}")
    public ResponseEntity<OfferLineDTO> getOfferLine(@PathVariable Long id) {
        log.debug("REST request to get OfferLine : {}", id);
        Optional<OfferLineDTO> offerLineDTO = offerLineService.findOne(id);
        return ResponseUtil.wrapOrNotFound(offerLineDTO);
    }

    /**
     * {@code DELETE  /offer-lines/:id} : delete the "id" offerLine.
     *
     * @param id the id of the offerLineDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/offer-lines/{id}")
    public ResponseEntity<Void> deleteOfferLine(@PathVariable Long id) {
        log.debug("REST request to delete OfferLine : {}", id);
        offerLineService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }

    /**
     * {@code SEARCH  /_search/offer-lines?query=:query} : search for the offerLine corresponding
     * to the query.
     *
     * @param query the query of the offerLine search.
     * @param pageable the pagination information.
     * @return the result of the search.
     */
    @GetMapping("/_search/offer-lines")
    public ResponseEntity<List<OfferLineDTO>> searchOfferLines(@RequestParam String query, Pageable pageable) {
        log.debug("REST request to search for a page of OfferLines for query {}", query);
        Page<OfferLineDTO> page = offerLineService.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }
}
