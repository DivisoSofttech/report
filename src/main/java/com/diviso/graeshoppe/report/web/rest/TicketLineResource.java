package com.diviso.graeshoppe.report.web.rest;

import com.diviso.graeshoppe.report.service.TicketLineService;
import com.diviso.graeshoppe.report.web.rest.errors.BadRequestAlertException;
import com.diviso.graeshoppe.report.service.dto.TicketLineDTO;

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
 * REST controller for managing {@link com.diviso.graeshoppe.report.domain.TicketLine}.
 */
@RestController
@RequestMapping("/api")
public class TicketLineResource {

    private final Logger log = LoggerFactory.getLogger(TicketLineResource.class);

    private static final String ENTITY_NAME = "reportTicketLine";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TicketLineService ticketLineService;

    public TicketLineResource(TicketLineService ticketLineService) {
        this.ticketLineService = ticketLineService;
    }

    /**
     * {@code POST  /ticket-lines} : Create a new ticketLine.
     *
     * @param ticketLineDTO the ticketLineDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new ticketLineDTO, or with status {@code 400 (Bad Request)} if the ticketLine has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/ticket-lines")
    public ResponseEntity<TicketLineDTO> createTicketLine(@RequestBody TicketLineDTO ticketLineDTO) throws URISyntaxException {
        log.debug("REST request to save TicketLine : {}", ticketLineDTO);
        if (ticketLineDTO.getId() != null) {
            throw new BadRequestAlertException("A new ticketLine cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TicketLineDTO result = ticketLineService.save(ticketLineDTO);
        return ResponseEntity.created(new URI("/api/ticket-lines/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /ticket-lines} : Updates an existing ticketLine.
     *
     * @param ticketLineDTO the ticketLineDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated ticketLineDTO,
     * or with status {@code 400 (Bad Request)} if the ticketLineDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the ticketLineDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/ticket-lines")
    public ResponseEntity<TicketLineDTO> updateTicketLine(@RequestBody TicketLineDTO ticketLineDTO) throws URISyntaxException {
        log.debug("REST request to update TicketLine : {}", ticketLineDTO);
        if (ticketLineDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TicketLineDTO result = ticketLineService.save(ticketLineDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, ticketLineDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /ticket-lines} : get all the ticketLines.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of ticketLines in body.
     */
    @GetMapping("/ticket-lines")
    public ResponseEntity<List<TicketLineDTO>> getAllTicketLines(Pageable pageable) {
        log.debug("REST request to get a page of TicketLines");
        Page<TicketLineDTO> page = ticketLineService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /ticket-lines/:id} : get the "id" ticketLine.
     *
     * @param id the id of the ticketLineDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the ticketLineDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/ticket-lines/{id}")
    public ResponseEntity<TicketLineDTO> getTicketLine(@PathVariable Long id) {
        log.debug("REST request to get TicketLine : {}", id);
        Optional<TicketLineDTO> ticketLineDTO = ticketLineService.findOne(id);
        return ResponseUtil.wrapOrNotFound(ticketLineDTO);
    }

    /**
     * {@code DELETE  /ticket-lines/:id} : delete the "id" ticketLine.
     *
     * @param id the id of the ticketLineDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/ticket-lines/{id}")
    public ResponseEntity<Void> deleteTicketLine(@PathVariable Long id) {
        log.debug("REST request to delete TicketLine : {}", id);
        ticketLineService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }

    /**
     * {@code SEARCH  /_search/ticket-lines?query=:query} : search for the ticketLine corresponding
     * to the query.
     *
     * @param query the query of the ticketLine search.
     * @param pageable the pagination information.
     * @return the result of the search.
     */
    @GetMapping("/_search/ticket-lines")
    public ResponseEntity<List<TicketLineDTO>> searchTicketLines(@RequestParam String query, Pageable pageable) {
        log.debug("REST request to search for a page of TicketLines for query {}", query);
        Page<TicketLineDTO> page = ticketLineService.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }
}
