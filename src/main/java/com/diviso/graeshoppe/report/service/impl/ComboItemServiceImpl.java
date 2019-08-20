package com.diviso.graeshoppe.report.service.impl;

import com.diviso.graeshoppe.report.service.ComboItemService;
import com.diviso.graeshoppe.report.domain.ComboItem;
import com.diviso.graeshoppe.report.repository.ComboItemRepository;
import com.diviso.graeshoppe.report.repository.search.ComboItemSearchRepository;
import com.diviso.graeshoppe.report.service.dto.ComboItemDTO;
import com.diviso.graeshoppe.report.service.mapper.ComboItemMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing ComboItem.
 */
@Service
@Transactional
public class ComboItemServiceImpl implements ComboItemService {

    private final Logger log = LoggerFactory.getLogger(ComboItemServiceImpl.class);

    private final ComboItemRepository comboItemRepository;

    private final ComboItemMapper comboItemMapper;

    private final ComboItemSearchRepository comboItemSearchRepository;

    public ComboItemServiceImpl(ComboItemRepository comboItemRepository, ComboItemMapper comboItemMapper, ComboItemSearchRepository comboItemSearchRepository) {
        this.comboItemRepository = comboItemRepository;
        this.comboItemMapper = comboItemMapper;
        this.comboItemSearchRepository = comboItemSearchRepository;
    }

    /**
     * Save a comboItem.
     *
     * @param comboItemDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public ComboItemDTO save(ComboItemDTO comboItemDTO) {
        log.debug("Request to save ComboItem : {}", comboItemDTO);
        ComboItem comboItem = comboItemMapper.toEntity(comboItemDTO);
        comboItem = comboItemRepository.save(comboItem);
        ComboItemDTO result = comboItemMapper.toDto(comboItem);
        comboItemSearchRepository.save(comboItem);
        return result;
    }

    /**
     * Get all the comboItems.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<ComboItemDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ComboItems");
        return comboItemRepository.findAll(pageable)
            .map(comboItemMapper::toDto);
    }


    /**
     * Get one comboItem by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<ComboItemDTO> findOne(Long id) {
        log.debug("Request to get ComboItem : {}", id);
        return comboItemRepository.findById(id)
            .map(comboItemMapper::toDto);
    }

    /**
     * Delete the comboItem by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete ComboItem : {}", id);        comboItemRepository.deleteById(id);
        comboItemSearchRepository.deleteById(id);
    }

    /**
     * Search for the comboItem corresponding to the query.
     *
     * @param query the query of the search
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<ComboItemDTO> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of ComboItems for query {}", query);
        return comboItemSearchRepository.search(queryStringQuery(query), pageable)
            .map(comboItemMapper::toDto);
    }
}
