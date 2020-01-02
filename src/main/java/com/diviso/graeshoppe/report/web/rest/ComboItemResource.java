package com.diviso.graeshoppe.report.web.rest;

import com.diviso.graeshoppe.report.service.ComboItemService;
import com.diviso.graeshoppe.report.web.rest.errors.BadRequestAlertException;
import com.diviso.graeshoppe.report.service.dto.ComboItemDTO;

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
 * REST controller for managing {@link com.diviso.graeshoppe.report.domain.ComboItem}.
 */
@RestController
@RequestMapping("/api")
public class ComboItemResource {

    private final Logger log = LoggerFactory.getLogger(ComboItemResource.class);

    private static final String ENTITY_NAME = "reportComboItem";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ComboItemService comboItemService;

    public ComboItemResource(ComboItemService comboItemService) {
        this.comboItemService = comboItemService;
    }

    /**
     * {@code POST  /combo-items} : Create a new comboItem.
     *
     * @param comboItemDTO the comboItemDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new comboItemDTO, or with status {@code 400 (Bad Request)} if the comboItem has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/combo-items")
    public ResponseEntity<ComboItemDTO> createComboItem(@RequestBody ComboItemDTO comboItemDTO) throws URISyntaxException {
        log.debug("REST request to save ComboItem : {}", comboItemDTO);
        if (comboItemDTO.getId() != null) {
            throw new BadRequestAlertException("A new comboItem cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ComboItemDTO result = comboItemService.save(comboItemDTO);
        return ResponseEntity.created(new URI("/api/combo-items/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /combo-items} : Updates an existing comboItem.
     *
     * @param comboItemDTO the comboItemDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated comboItemDTO,
     * or with status {@code 400 (Bad Request)} if the comboItemDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the comboItemDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/combo-items")
    public ResponseEntity<ComboItemDTO> updateComboItem(@RequestBody ComboItemDTO comboItemDTO) throws URISyntaxException {
        log.debug("REST request to update ComboItem : {}", comboItemDTO);
        if (comboItemDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ComboItemDTO result = comboItemService.save(comboItemDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, comboItemDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /combo-items} : get all the comboItems.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of comboItems in body.
     */
    @GetMapping("/combo-items")
    public ResponseEntity<List<ComboItemDTO>> getAllComboItems(Pageable pageable) {
        log.debug("REST request to get a page of ComboItems");
        Page<ComboItemDTO> page = comboItemService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /combo-items/:id} : get the "id" comboItem.
     *
     * @param id the id of the comboItemDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the comboItemDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/combo-items/{id}")
    public ResponseEntity<ComboItemDTO> getComboItem(@PathVariable Long id) {
        log.debug("REST request to get ComboItem : {}", id);
        Optional<ComboItemDTO> comboItemDTO = comboItemService.findOne(id);
        return ResponseUtil.wrapOrNotFound(comboItemDTO);
    }

    /**
     * {@code DELETE  /combo-items/:id} : delete the "id" comboItem.
     *
     * @param id the id of the comboItemDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/combo-items/{id}")
    public ResponseEntity<Void> deleteComboItem(@PathVariable Long id) {
        log.debug("REST request to delete ComboItem : {}", id);
        comboItemService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }

    /**
     * {@code SEARCH  /_search/combo-items?query=:query} : search for the comboItem corresponding
     * to the query.
     *
     * @param query the query of the comboItem search.
     * @param pageable the pagination information.
     * @return the result of the search.
     */
    @GetMapping("/_search/combo-items")
    public ResponseEntity<List<ComboItemDTO>> searchComboItems(@RequestParam String query, Pageable pageable) {
        log.debug("REST request to search for a page of ComboItems for query {}", query);
        Page<ComboItemDTO> page = comboItemService.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }
}
