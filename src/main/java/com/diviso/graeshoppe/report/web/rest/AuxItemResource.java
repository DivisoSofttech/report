package com.diviso.graeshoppe.report.web.rest;

import com.diviso.graeshoppe.report.service.AuxItemService;
import com.diviso.graeshoppe.report.web.rest.errors.BadRequestAlertException;
import com.diviso.graeshoppe.report.service.dto.AuxItemDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * REST controller for managing {@link com.diviso.graeshoppe.report.domain.AuxItem}.
 */
@RestController
@RequestMapping("/api")
public class AuxItemResource {

    private final Logger log = LoggerFactory.getLogger(AuxItemResource.class);

    private static final String ENTITY_NAME = "reportAuxItem";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final AuxItemService auxItemService;

    public AuxItemResource(AuxItemService auxItemService) {
        this.auxItemService = auxItemService;
    }

    /**
     * {@code POST  /aux-items} : Create a new auxItem.
     *
     * @param auxItemDTO the auxItemDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new auxItemDTO, or with status {@code 400 (Bad Request)} if the auxItem has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/aux-items")
    public ResponseEntity<AuxItemDTO> createAuxItem(@RequestBody AuxItemDTO auxItemDTO) throws URISyntaxException {
        log.debug("REST request to save AuxItem : {}", auxItemDTO);
        if (auxItemDTO.getId() != null) {
            throw new BadRequestAlertException("A new auxItem cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AuxItemDTO result = auxItemService.save(auxItemDTO);
        return ResponseEntity.created(new URI("/api/aux-items/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /aux-items} : Updates an existing auxItem.
     *
     * @param auxItemDTO the auxItemDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated auxItemDTO,
     * or with status {@code 400 (Bad Request)} if the auxItemDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the auxItemDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/aux-items")
    public ResponseEntity<AuxItemDTO> updateAuxItem(@RequestBody AuxItemDTO auxItemDTO) throws URISyntaxException {
        log.debug("REST request to update AuxItem : {}", auxItemDTO);
        if (auxItemDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        AuxItemDTO result = auxItemService.save(auxItemDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, auxItemDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /aux-items} : get all the auxItems.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of auxItems in body.
     */
    @GetMapping("/aux-items")
    public ResponseEntity<List<AuxItemDTO>> getAllAuxItems(Pageable pageable) {
        log.debug("REST request to get a page of AuxItems");
        Page<AuxItemDTO> page = auxItemService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /aux-items/:id} : get the "id" auxItem.
     *
     * @param id the id of the auxItemDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the auxItemDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/aux-items/{id}")
    public ResponseEntity<AuxItemDTO> getAuxItem(@PathVariable Long id) {
        log.debug("REST request to get AuxItem : {}", id);
        Optional<AuxItemDTO> auxItemDTO = auxItemService.findOne(id);
        return ResponseUtil.wrapOrNotFound(auxItemDTO);
    }

    /**
     * {@code DELETE  /aux-items/:id} : delete the "id" auxItem.
     *
     * @param id the id of the auxItemDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/aux-items/{id}")
    public ResponseEntity<Void> deleteAuxItem(@PathVariable Long id) {
        log.debug("REST request to delete AuxItem : {}", id);
        auxItemService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }

    /**
     * {@code SEARCH  /_search/aux-items?query=:query} : search for the auxItem corresponding
     * to the query.
     *
     * @param query the query of the auxItem search.
     * @param pageable the pagination information.
     * @return the result of the search.
     */
    @GetMapping("/_search/aux-items")
    public ResponseEntity<List<AuxItemDTO>> searchAuxItems(@RequestParam String query, Pageable pageable) {
        log.debug("REST request to search for a page of AuxItems for query {}", query);
        Page<AuxItemDTO> page = auxItemService.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }
}
