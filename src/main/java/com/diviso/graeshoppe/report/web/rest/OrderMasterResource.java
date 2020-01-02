package com.diviso.graeshoppe.report.web.rest;

import com.diviso.graeshoppe.report.service.OrderMasterService;
import com.diviso.graeshoppe.report.web.rest.errors.BadRequestAlertException;
import com.diviso.graeshoppe.report.service.dto.OrderMasterDTO;

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
 * REST controller for managing {@link com.diviso.graeshoppe.report.domain.OrderMaster}.
 */
@RestController
@RequestMapping("/api")
public class OrderMasterResource {

    private final Logger log = LoggerFactory.getLogger(OrderMasterResource.class);

    private static final String ENTITY_NAME = "reportOrderMaster";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final OrderMasterService orderMasterService;

    public OrderMasterResource(OrderMasterService orderMasterService) {
        this.orderMasterService = orderMasterService;
    }

    /**
     * {@code POST  /order-masters} : Create a new orderMaster.
     *
     * @param orderMasterDTO the orderMasterDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new orderMasterDTO, or with status {@code 400 (Bad Request)} if the orderMaster has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/order-masters")
    public ResponseEntity<OrderMasterDTO> createOrderMaster(@RequestBody OrderMasterDTO orderMasterDTO) throws URISyntaxException {
        log.debug("REST request to save OrderMaster : {}", orderMasterDTO);
        if (orderMasterDTO.getId() != null) {
            throw new BadRequestAlertException("A new orderMaster cannot already have an ID", ENTITY_NAME, "idexists");
        }
        OrderMasterDTO result = orderMasterService.save(orderMasterDTO);
        return ResponseEntity.created(new URI("/api/order-masters/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /order-masters} : Updates an existing orderMaster.
     *
     * @param orderMasterDTO the orderMasterDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated orderMasterDTO,
     * or with status {@code 400 (Bad Request)} if the orderMasterDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the orderMasterDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/order-masters")
    public ResponseEntity<OrderMasterDTO> updateOrderMaster(@RequestBody OrderMasterDTO orderMasterDTO) throws URISyntaxException {
        log.debug("REST request to update OrderMaster : {}", orderMasterDTO);
        if (orderMasterDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        OrderMasterDTO result = orderMasterService.save(orderMasterDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, orderMasterDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /order-masters} : get all the orderMasters.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of orderMasters in body.
     */
    @GetMapping("/order-masters")
    public ResponseEntity<List<OrderMasterDTO>> getAllOrderMasters(Pageable pageable) {
        log.debug("REST request to get a page of OrderMasters");
        Page<OrderMasterDTO> page = orderMasterService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /order-masters/:id} : get the "id" orderMaster.
     *
     * @param id the id of the orderMasterDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the orderMasterDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/order-masters/{id}")
    public ResponseEntity<OrderMasterDTO> getOrderMaster(@PathVariable Long id) {
        log.debug("REST request to get OrderMaster : {}", id);
        Optional<OrderMasterDTO> orderMasterDTO = orderMasterService.findOne(id);
        return ResponseUtil.wrapOrNotFound(orderMasterDTO);
    }

    /**
     * {@code DELETE  /order-masters/:id} : delete the "id" orderMaster.
     *
     * @param id the id of the orderMasterDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/order-masters/{id}")
    public ResponseEntity<Void> deleteOrderMaster(@PathVariable Long id) {
        log.debug("REST request to delete OrderMaster : {}", id);
        orderMasterService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }

    /**
     * {@code SEARCH  /_search/order-masters?query=:query} : search for the orderMaster corresponding
     * to the query.
     *
     * @param query the query of the orderMaster search.
     * @param pageable the pagination information.
     * @return the result of the search.
     */
    @GetMapping("/_search/order-masters")
    public ResponseEntity<List<OrderMasterDTO>> searchOrderMasters(@RequestParam String query, Pageable pageable) {
        log.debug("REST request to search for a page of OrderMasters for query {}", query);
        Page<OrderMasterDTO> page = orderMasterService.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }
}
