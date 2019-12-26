package com.diviso.graeshoppe.report.service.impl;

import com.diviso.graeshoppe.report.service.OfferLineService;
import com.diviso.graeshoppe.report.domain.OfferLine;
import com.diviso.graeshoppe.report.domain.OrderMaster;
import com.diviso.graeshoppe.report.repository.OfferLineRepository;
import com.diviso.graeshoppe.report.repository.OrderMasterRepository;
import com.diviso.graeshoppe.report.repository.search.OfferLineSearchRepository;
import com.diviso.graeshoppe.report.service.dto.OfferLineDTO;
import com.diviso.graeshoppe.report.service.mapper.OfferLineMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing OfferLine.
 */
@Service
@Transactional
public class OfferLineServiceImpl implements OfferLineService {

	private final Logger log = LoggerFactory.getLogger(OfferLineServiceImpl.class);

	private final OfferLineRepository offerLineRepository;

	private final OfferLineMapper offerLineMapper;

	private final OfferLineSearchRepository offerLineSearchRepository;
	
	private final OrderMasterRepository orderMasterRepository;

	

	public OfferLineServiceImpl(OfferLineRepository offerLineRepository, OfferLineMapper offerLineMapper,
			OfferLineSearchRepository offerLineSearchRepository, OrderMasterRepository orderMasterRepository) {
		super();
		this.offerLineRepository = offerLineRepository;
		this.offerLineMapper = offerLineMapper;
		this.offerLineSearchRepository = offerLineSearchRepository;
		this.orderMasterRepository = orderMasterRepository;
	}

	/**
	 * Save a offerLine.
	 *
	 * @param offerLineDTO the entity to save
	 * @return the persisted entity
	 */
	@Override
	public OfferLineDTO save(OfferLineDTO offerLineDTO) {
		log.debug("Request to save OfferLine : {}", offerLineDTO);
		OfferLine offerLine = offerLineMapper.toEntity(offerLineDTO);
		offerLine = offerLineRepository.save(offerLine);
		OfferLineDTO result = offerLineMapper.toDto(offerLine);
		offerLineSearchRepository.save(offerLine);
		return result;
	}

	/**
	 * Get all the offerLines.
	 *
	 * @param pageable the pagination information
	 * @return the list of entities
	 */
	@Override
	@Transactional(readOnly = true)
	public Page<OfferLineDTO> findAll(Pageable pageable) {
		log.debug("Request to get all OfferLines");
		return offerLineRepository.findAll(pageable).map(offerLineMapper::toDto);
	}

	/**
	 * Get one offerLine by id.
	 *
	 * @param id the id of the entity
	 * @return the entity
	 */
	@Override
	@Transactional(readOnly = true)
	public Optional<OfferLineDTO> findOne(Long id) {
		log.debug("Request to get OfferLine : {}", id);
		return offerLineRepository.findById(id).map(offerLineMapper::toDto);
	}

	/**
	 * Delete the offerLine by id.
	 *
	 * @param id the id of the entity
	 */
	@Override
	public void delete(Long id) {
		log.debug("Request to delete OfferLine : {}", id);
		offerLineRepository.deleteById(id);
		offerLineSearchRepository.deleteById(id);
	}

	/**
	 * Search for the offerLine corresponding to the query.
	 *
	 * @param query    the query of the search
	 * @param pageable the pagination information
	 * @return the list of entities
	 */
	@Override
	@Transactional(readOnly = true)
	public Page<OfferLineDTO> search(String query, Pageable pageable) {
		log.debug("Request to search for a page of OfferLines for query {}", query);
		return offerLineSearchRepository.search(queryStringQuery(query), pageable).map(offerLineMapper::toDto);
	}

	@Override
	public List<OfferLine> findOfferLinesByOrderNumber(String orderId) {
		log.debug("<<<<<<<<<<findByOrderMaster_orderNumber impl >>>>>>>>>>{}", orderId);
		List<OfferLine> offerLines = new ArrayList<OfferLine>();
		Optional<OrderMaster> orderMaster=orderMasterRepository.findByOrderNumber(orderId);
		
		offerLines = offerLineRepository.findByOrderMasterId_OrderNumber(orderMaster.get().getId());
		return offerLines;
	}

}
