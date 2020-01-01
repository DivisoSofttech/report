package com.diviso.graeshoppe.report.web.rest;

import com.diviso.graeshoppe.report.ReportApp;
import com.diviso.graeshoppe.report.config.TestSecurityConfiguration;
import com.diviso.graeshoppe.report.domain.OfferLine;
import com.diviso.graeshoppe.report.repository.OfferLineRepository;
import com.diviso.graeshoppe.report.repository.search.OfferLineSearchRepository;
import com.diviso.graeshoppe.report.service.OfferLineService;
import com.diviso.graeshoppe.report.service.dto.OfferLineDTO;
import com.diviso.graeshoppe.report.service.mapper.OfferLineMapper;
import com.diviso.graeshoppe.report.web.rest.errors.ExceptionTranslator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import java.util.Collections;
import java.util.List;

import static com.diviso.graeshoppe.report.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.elasticsearch.index.query.QueryBuilders.queryStringQuery;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link OfferLineResource} REST controller.
 */
@SpringBootTest(classes = {ReportApp.class, TestSecurityConfiguration.class})
public class OfferLineResourceIT {

    private static final String DEFAULT_OFFER_REF = "AAAAAAAAAA";
    private static final String UPDATED_OFFER_REF = "BBBBBBBBBB";

    private static final Double DEFAULT_DISCOUNT_AMOUNT = 1D;
    private static final Double UPDATED_DISCOUNT_AMOUNT = 2D;

    @Autowired
    private OfferLineRepository offerLineRepository;

    @Autowired
    private OfferLineMapper offerLineMapper;

    @Autowired
    private OfferLineService offerLineService;

    /**
     * This repository is mocked in the com.diviso.graeshoppe.report.repository.search test package.
     *
     * @see com.diviso.graeshoppe.report.repository.search.OfferLineSearchRepositoryMockConfiguration
     */
    @Autowired
    private OfferLineSearchRepository mockOfferLineSearchRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    @Autowired
    private Validator validator;

    private MockMvc restOfferLineMockMvc;

    private OfferLine offerLine;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final OfferLineResource offerLineResource = new OfferLineResource(offerLineService);
        this.restOfferLineMockMvc = MockMvcBuilders.standaloneSetup(offerLineResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter)
            .setValidator(validator).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static OfferLine createEntity(EntityManager em) {
        OfferLine offerLine = new OfferLine()
            .offerRef(DEFAULT_OFFER_REF)
            .discountAmount(DEFAULT_DISCOUNT_AMOUNT);
        return offerLine;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static OfferLine createUpdatedEntity(EntityManager em) {
        OfferLine offerLine = new OfferLine()
            .offerRef(UPDATED_OFFER_REF)
            .discountAmount(UPDATED_DISCOUNT_AMOUNT);
        return offerLine;
    }

    @BeforeEach
    public void initTest() {
        offerLine = createEntity(em);
    }

    @Test
    @Transactional
    public void createOfferLine() throws Exception {
        int databaseSizeBeforeCreate = offerLineRepository.findAll().size();

        // Create the OfferLine
        OfferLineDTO offerLineDTO = offerLineMapper.toDto(offerLine);
        restOfferLineMockMvc.perform(post("/api/offer-lines")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(offerLineDTO)))
            .andExpect(status().isCreated());

        // Validate the OfferLine in the database
        List<OfferLine> offerLineList = offerLineRepository.findAll();
        assertThat(offerLineList).hasSize(databaseSizeBeforeCreate + 1);
        OfferLine testOfferLine = offerLineList.get(offerLineList.size() - 1);
        assertThat(testOfferLine.getOfferRef()).isEqualTo(DEFAULT_OFFER_REF);
        assertThat(testOfferLine.getDiscountAmount()).isEqualTo(DEFAULT_DISCOUNT_AMOUNT);

        // Validate the OfferLine in Elasticsearch
        verify(mockOfferLineSearchRepository, times(1)).save(testOfferLine);
    }

    @Test
    @Transactional
    public void createOfferLineWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = offerLineRepository.findAll().size();

        // Create the OfferLine with an existing ID
        offerLine.setId(1L);
        OfferLineDTO offerLineDTO = offerLineMapper.toDto(offerLine);

        // An entity with an existing ID cannot be created, so this API call must fail
        restOfferLineMockMvc.perform(post("/api/offer-lines")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(offerLineDTO)))
            .andExpect(status().isBadRequest());

        // Validate the OfferLine in the database
        List<OfferLine> offerLineList = offerLineRepository.findAll();
        assertThat(offerLineList).hasSize(databaseSizeBeforeCreate);

        // Validate the OfferLine in Elasticsearch
        verify(mockOfferLineSearchRepository, times(0)).save(offerLine);
    }


    @Test
    @Transactional
    public void getAllOfferLines() throws Exception {
        // Initialize the database
        offerLineRepository.saveAndFlush(offerLine);

        // Get all the offerLineList
        restOfferLineMockMvc.perform(get("/api/offer-lines?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(offerLine.getId().intValue())))
            .andExpect(jsonPath("$.[*].offerRef").value(hasItem(DEFAULT_OFFER_REF)))
            .andExpect(jsonPath("$.[*].discountAmount").value(hasItem(DEFAULT_DISCOUNT_AMOUNT.doubleValue())));
    }
    
    @Test
    @Transactional
    public void getOfferLine() throws Exception {
        // Initialize the database
        offerLineRepository.saveAndFlush(offerLine);

        // Get the offerLine
        restOfferLineMockMvc.perform(get("/api/offer-lines/{id}", offerLine.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(offerLine.getId().intValue()))
            .andExpect(jsonPath("$.offerRef").value(DEFAULT_OFFER_REF))
            .andExpect(jsonPath("$.discountAmount").value(DEFAULT_DISCOUNT_AMOUNT.doubleValue()));
    }

    @Test
    @Transactional
    public void getNonExistingOfferLine() throws Exception {
        // Get the offerLine
        restOfferLineMockMvc.perform(get("/api/offer-lines/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateOfferLine() throws Exception {
        // Initialize the database
        offerLineRepository.saveAndFlush(offerLine);

        int databaseSizeBeforeUpdate = offerLineRepository.findAll().size();

        // Update the offerLine
        OfferLine updatedOfferLine = offerLineRepository.findById(offerLine.getId()).get();
        // Disconnect from session so that the updates on updatedOfferLine are not directly saved in db
        em.detach(updatedOfferLine);
        updatedOfferLine
            .offerRef(UPDATED_OFFER_REF)
            .discountAmount(UPDATED_DISCOUNT_AMOUNT);
        OfferLineDTO offerLineDTO = offerLineMapper.toDto(updatedOfferLine);

        restOfferLineMockMvc.perform(put("/api/offer-lines")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(offerLineDTO)))
            .andExpect(status().isOk());

        // Validate the OfferLine in the database
        List<OfferLine> offerLineList = offerLineRepository.findAll();
        assertThat(offerLineList).hasSize(databaseSizeBeforeUpdate);
        OfferLine testOfferLine = offerLineList.get(offerLineList.size() - 1);
        assertThat(testOfferLine.getOfferRef()).isEqualTo(UPDATED_OFFER_REF);
        assertThat(testOfferLine.getDiscountAmount()).isEqualTo(UPDATED_DISCOUNT_AMOUNT);

        // Validate the OfferLine in Elasticsearch
        verify(mockOfferLineSearchRepository, times(1)).save(testOfferLine);
    }

    @Test
    @Transactional
    public void updateNonExistingOfferLine() throws Exception {
        int databaseSizeBeforeUpdate = offerLineRepository.findAll().size();

        // Create the OfferLine
        OfferLineDTO offerLineDTO = offerLineMapper.toDto(offerLine);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOfferLineMockMvc.perform(put("/api/offer-lines")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(offerLineDTO)))
            .andExpect(status().isBadRequest());

        // Validate the OfferLine in the database
        List<OfferLine> offerLineList = offerLineRepository.findAll();
        assertThat(offerLineList).hasSize(databaseSizeBeforeUpdate);

        // Validate the OfferLine in Elasticsearch
        verify(mockOfferLineSearchRepository, times(0)).save(offerLine);
    }

    @Test
    @Transactional
    public void deleteOfferLine() throws Exception {
        // Initialize the database
        offerLineRepository.saveAndFlush(offerLine);

        int databaseSizeBeforeDelete = offerLineRepository.findAll().size();

        // Delete the offerLine
        restOfferLineMockMvc.perform(delete("/api/offer-lines/{id}", offerLine.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<OfferLine> offerLineList = offerLineRepository.findAll();
        assertThat(offerLineList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the OfferLine in Elasticsearch
        verify(mockOfferLineSearchRepository, times(1)).deleteById(offerLine.getId());
    }

    @Test
    @Transactional
    public void searchOfferLine() throws Exception {
        // Initialize the database
        offerLineRepository.saveAndFlush(offerLine);
        when(mockOfferLineSearchRepository.search(queryStringQuery("id:" + offerLine.getId()), PageRequest.of(0, 20)))
            .thenReturn(new PageImpl<>(Collections.singletonList(offerLine), PageRequest.of(0, 1), 1));
        // Search the offerLine
        restOfferLineMockMvc.perform(get("/api/_search/offer-lines?query=id:" + offerLine.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(offerLine.getId().intValue())))
            .andExpect(jsonPath("$.[*].offerRef").value(hasItem(DEFAULT_OFFER_REF)))
            .andExpect(jsonPath("$.[*].discountAmount").value(hasItem(DEFAULT_DISCOUNT_AMOUNT.doubleValue())));
    }
}
