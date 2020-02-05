package com.diviso.graeshoppe.report.service.impl;

import com.diviso.graeshoppe.report.service.AuxItemService;
import com.diviso.graeshoppe.report.domain.AuxItem;
import com.diviso.graeshoppe.report.domain.ComboItem;
import com.diviso.graeshoppe.report.repository.AuxItemRepository;
import com.diviso.graeshoppe.report.repository.search.AuxItemSearchRepository;
import com.diviso.graeshoppe.report.service.dto.AuxItemDTO;
import com.diviso.graeshoppe.report.service.dto.ComboItemDTO;
import com.diviso.graeshoppe.report.service.mapper.AuxItemMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing {@link AuxItem}.
 */
@Service
@Transactional
public class AuxItemServiceImpl implements AuxItemService {

    private final Logger log = LoggerFactory.getLogger(AuxItemServiceImpl.class);

    private final AuxItemRepository auxItemRepository;

    private final AuxItemMapper auxItemMapper;

    private final AuxItemSearchRepository auxItemSearchRepository;

    public AuxItemServiceImpl(AuxItemRepository auxItemRepository, AuxItemMapper auxItemMapper, AuxItemSearchRepository auxItemSearchRepository) {
        this.auxItemRepository = auxItemRepository;
        this.auxItemMapper = auxItemMapper;
        this.auxItemSearchRepository = auxItemSearchRepository;
    }

    /**
     * Save a auxItem.
     *
     * @param auxItemDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public AuxItemDTO save(AuxItemDTO auxItemDTO) {
        log.debug("Request to save AuxItem : {}", auxItemDTO);
        AuxItem auxItem = auxItemMapper.toEntity(auxItemDTO);
        auxItem = auxItemRepository.save(auxItem);
        AuxItemDTO result = auxItemMapper.toDto(auxItem);
        auxItemSearchRepository.save(auxItem);
        updateToEs(result);
        return result;
    }
    private void updateToEs(AuxItemDTO auxItemDTO) {
    	AuxItem auxItem = auxItemMapper.toEntity(auxItemDTO);
    	auxItem = auxItemRepository.save(auxItem);
    	auxItemSearchRepository.save(auxItem);
    }
    /**
     * Get all the auxItems.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<AuxItemDTO> findAll(Pageable pageable) {
        log.debug("Request to get all AuxItems");
        return auxItemRepository.findAll(pageable)
            .map(auxItemMapper::toDto);
    }


    /**
     * Get one auxItem by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<AuxItemDTO> findOne(Long id) {
        log.debug("Request to get AuxItem : {}", id);
        return auxItemRepository.findById(id)
            .map(auxItemMapper::toDto);
    }

    /**
     * Delete the auxItem by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete AuxItem : {}", id);
        auxItemRepository.deleteById(id);
        auxItemSearchRepository.deleteById(id);
    }

    /**
     * Search for the auxItem corresponding to the query.
     *
     * @param query the query of the search.
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<AuxItemDTO> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of AuxItems for query {}", query);
        return auxItemSearchRepository.search(queryStringQuery(query), pageable)
            .map(auxItemMapper::toDto);
    }
    
	@Override
	public List<AuxItem> findAuxItemByid(Long id) {
		log.debug("<<<<<<<<<findByOrderLine_id >>>>>>>{}",id);
		
		List<AuxItem> auxItems =  auxItemRepository.findByOrderLine_id(id);
		return auxItems;
	}
}
