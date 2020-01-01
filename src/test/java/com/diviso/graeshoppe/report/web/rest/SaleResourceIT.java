package com.diviso.graeshoppe.report.web.rest;

import com.diviso.graeshoppe.report.ReportApp;
import com.diviso.graeshoppe.report.config.TestSecurityConfiguration;
import com.diviso.graeshoppe.report.domain.Sale;
import com.diviso.graeshoppe.report.repository.SaleRepository;
import com.diviso.graeshoppe.report.repository.search.SaleSearchRepository;
import com.diviso.graeshoppe.report.service.SaleService;
import com.diviso.graeshoppe.report.service.dto.SaleDTO;
import com.diviso.graeshoppe.report.service.mapper.SaleMapper;
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
import java.time.Instant;
import java.time.temporal.ChronoUnit;
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
 * Integration tests for the {@link SaleResource} REST controller.
 */
@SpringBootTest(classes = {ReportApp.class, TestSecurityConfiguration.class})
public class SaleResourceIT {

    private static final String DEFAULT_STORE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_STORE_NAME = "BBBBBBBBBB";

    private static final Long DEFAULT_STORE_PHONE = 1L;
    private static final Long UPDATED_STORE_PHONE = 2L;

    private static final String DEFAULT_STOREIDPCODE = "AAAAAAAAAA";
    private static final String UPDATED_STOREIDPCODE = "BBBBBBBBBB";

    private static final Long DEFAULT_CUSTOMER_ID = 1L;
    private static final Long UPDATED_CUSTOMER_ID = 2L;

    private static final Instant DEFAULT_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Double DEFAULT_GRAND_TOTAL = 1D;
    private static final Double UPDATED_GRAND_TOTAL = 2D;

    @Autowired
    private SaleRepository saleRepository;

    @Autowired
    private SaleMapper saleMapper;

    @Autowired
    private SaleService saleService;

    /**
     * This repository is mocked in the com.diviso.graeshoppe.report.repository.search test package.
     *
     * @see com.diviso.graeshoppe.report.repository.search.SaleSearchRepositoryMockConfiguration
     */
    @Autowired
    private SaleSearchRepository mockSaleSearchRepository;

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

    private MockMvc restSaleMockMvc;

    private Sale sale;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final SaleResource saleResource = new SaleResource(saleService);
        this.restSaleMockMvc = MockMvcBuilders.standaloneSetup(saleResource)
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
    public static Sale createEntity(EntityManager em) {
        Sale sale = new Sale()
            .storeName(DEFAULT_STORE_NAME)
            .storePhone(DEFAULT_STORE_PHONE)
            .storeidpcode(DEFAULT_STOREIDPCODE)
            .customerId(DEFAULT_CUSTOMER_ID)
            .date(DEFAULT_DATE)
            .grandTotal(DEFAULT_GRAND_TOTAL);
        return sale;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Sale createUpdatedEntity(EntityManager em) {
        Sale sale = new Sale()
            .storeName(UPDATED_STORE_NAME)
            .storePhone(UPDATED_STORE_PHONE)
            .storeidpcode(UPDATED_STOREIDPCODE)
            .customerId(UPDATED_CUSTOMER_ID)
            .date(UPDATED_DATE)
            .grandTotal(UPDATED_GRAND_TOTAL);
        return sale;
    }

    @BeforeEach
    public void initTest() {
        sale = createEntity(em);
    }

    @Test
    @Transactional
    public void createSale() throws Exception {
        int databaseSizeBeforeCreate = saleRepository.findAll().size();

        // Create the Sale
        SaleDTO saleDTO = saleMapper.toDto(sale);
        restSaleMockMvc.perform(post("/api/sales")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(saleDTO)))
            .andExpect(status().isCreated());

        // Validate the Sale in the database
        List<Sale> saleList = saleRepository.findAll();
        assertThat(saleList).hasSize(databaseSizeBeforeCreate + 1);
        Sale testSale = saleList.get(saleList.size() - 1);
        assertThat(testSale.getStoreName()).isEqualTo(DEFAULT_STORE_NAME);
        assertThat(testSale.getStorePhone()).isEqualTo(DEFAULT_STORE_PHONE);
        assertThat(testSale.getStoreidpcode()).isEqualTo(DEFAULT_STOREIDPCODE);
        assertThat(testSale.getCustomerId()).isEqualTo(DEFAULT_CUSTOMER_ID);
        assertThat(testSale.getDate()).isEqualTo(DEFAULT_DATE);
        assertThat(testSale.getGrandTotal()).isEqualTo(DEFAULT_GRAND_TOTAL);

        // Validate the Sale in Elasticsearch
        verify(mockSaleSearchRepository, times(1)).save(testSale);
    }

    @Test
    @Transactional
    public void createSaleWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = saleRepository.findAll().size();

        // Create the Sale with an existing ID
        sale.setId(1L);
        SaleDTO saleDTO = saleMapper.toDto(sale);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSaleMockMvc.perform(post("/api/sales")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(saleDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Sale in the database
        List<Sale> saleList = saleRepository.findAll();
        assertThat(saleList).hasSize(databaseSizeBeforeCreate);

        // Validate the Sale in Elasticsearch
        verify(mockSaleSearchRepository, times(0)).save(sale);
    }


    @Test
    @Transactional
    public void getAllSales() throws Exception {
        // Initialize the database
        saleRepository.saveAndFlush(sale);

        // Get all the saleList
        restSaleMockMvc.perform(get("/api/sales?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(sale.getId().intValue())))
            .andExpect(jsonPath("$.[*].storeName").value(hasItem(DEFAULT_STORE_NAME)))
            .andExpect(jsonPath("$.[*].storePhone").value(hasItem(DEFAULT_STORE_PHONE.intValue())))
            .andExpect(jsonPath("$.[*].storeidpcode").value(hasItem(DEFAULT_STOREIDPCODE)))
            .andExpect(jsonPath("$.[*].customerId").value(hasItem(DEFAULT_CUSTOMER_ID.intValue())))
            .andExpect(jsonPath("$.[*].date").value(hasItem(DEFAULT_DATE.toString())))
            .andExpect(jsonPath("$.[*].grandTotal").value(hasItem(DEFAULT_GRAND_TOTAL.doubleValue())));
    }
    
    @Test
    @Transactional
    public void getSale() throws Exception {
        // Initialize the database
        saleRepository.saveAndFlush(sale);

        // Get the sale
        restSaleMockMvc.perform(get("/api/sales/{id}", sale.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(sale.getId().intValue()))
            .andExpect(jsonPath("$.storeName").value(DEFAULT_STORE_NAME))
            .andExpect(jsonPath("$.storePhone").value(DEFAULT_STORE_PHONE.intValue()))
            .andExpect(jsonPath("$.storeidpcode").value(DEFAULT_STOREIDPCODE))
            .andExpect(jsonPath("$.customerId").value(DEFAULT_CUSTOMER_ID.intValue()))
            .andExpect(jsonPath("$.date").value(DEFAULT_DATE.toString()))
            .andExpect(jsonPath("$.grandTotal").value(DEFAULT_GRAND_TOTAL.doubleValue()));
    }

    @Test
    @Transactional
    public void getNonExistingSale() throws Exception {
        // Get the sale
        restSaleMockMvc.perform(get("/api/sales/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSale() throws Exception {
        // Initialize the database
        saleRepository.saveAndFlush(sale);

        int databaseSizeBeforeUpdate = saleRepository.findAll().size();

        // Update the sale
        Sale updatedSale = saleRepository.findById(sale.getId()).get();
        // Disconnect from session so that the updates on updatedSale are not directly saved in db
        em.detach(updatedSale);
        updatedSale
            .storeName(UPDATED_STORE_NAME)
            .storePhone(UPDATED_STORE_PHONE)
            .storeidpcode(UPDATED_STOREIDPCODE)
            .customerId(UPDATED_CUSTOMER_ID)
            .date(UPDATED_DATE)
            .grandTotal(UPDATED_GRAND_TOTAL);
        SaleDTO saleDTO = saleMapper.toDto(updatedSale);

        restSaleMockMvc.perform(put("/api/sales")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(saleDTO)))
            .andExpect(status().isOk());

        // Validate the Sale in the database
        List<Sale> saleList = saleRepository.findAll();
        assertThat(saleList).hasSize(databaseSizeBeforeUpdate);
        Sale testSale = saleList.get(saleList.size() - 1);
        assertThat(testSale.getStoreName()).isEqualTo(UPDATED_STORE_NAME);
        assertThat(testSale.getStorePhone()).isEqualTo(UPDATED_STORE_PHONE);
        assertThat(testSale.getStoreidpcode()).isEqualTo(UPDATED_STOREIDPCODE);
        assertThat(testSale.getCustomerId()).isEqualTo(UPDATED_CUSTOMER_ID);
        assertThat(testSale.getDate()).isEqualTo(UPDATED_DATE);
        assertThat(testSale.getGrandTotal()).isEqualTo(UPDATED_GRAND_TOTAL);

        // Validate the Sale in Elasticsearch
        verify(mockSaleSearchRepository, times(1)).save(testSale);
    }

    @Test
    @Transactional
    public void updateNonExistingSale() throws Exception {
        int databaseSizeBeforeUpdate = saleRepository.findAll().size();

        // Create the Sale
        SaleDTO saleDTO = saleMapper.toDto(sale);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSaleMockMvc.perform(put("/api/sales")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(saleDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Sale in the database
        List<Sale> saleList = saleRepository.findAll();
        assertThat(saleList).hasSize(databaseSizeBeforeUpdate);

        // Validate the Sale in Elasticsearch
        verify(mockSaleSearchRepository, times(0)).save(sale);
    }

    @Test
    @Transactional
    public void deleteSale() throws Exception {
        // Initialize the database
        saleRepository.saveAndFlush(sale);

        int databaseSizeBeforeDelete = saleRepository.findAll().size();

        // Delete the sale
        restSaleMockMvc.perform(delete("/api/sales/{id}", sale.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Sale> saleList = saleRepository.findAll();
        assertThat(saleList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the Sale in Elasticsearch
        verify(mockSaleSearchRepository, times(1)).deleteById(sale.getId());
    }

    @Test
    @Transactional
    public void searchSale() throws Exception {
        // Initialize the database
        saleRepository.saveAndFlush(sale);
        when(mockSaleSearchRepository.search(queryStringQuery("id:" + sale.getId()), PageRequest.of(0, 20)))
            .thenReturn(new PageImpl<>(Collections.singletonList(sale), PageRequest.of(0, 1), 1));
        // Search the sale
        restSaleMockMvc.perform(get("/api/_search/sales?query=id:" + sale.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(sale.getId().intValue())))
            .andExpect(jsonPath("$.[*].storeName").value(hasItem(DEFAULT_STORE_NAME)))
            .andExpect(jsonPath("$.[*].storePhone").value(hasItem(DEFAULT_STORE_PHONE.intValue())))
            .andExpect(jsonPath("$.[*].storeidpcode").value(hasItem(DEFAULT_STOREIDPCODE)))
            .andExpect(jsonPath("$.[*].customerId").value(hasItem(DEFAULT_CUSTOMER_ID.intValue())))
            .andExpect(jsonPath("$.[*].date").value(hasItem(DEFAULT_DATE.toString())))
            .andExpect(jsonPath("$.[*].grandTotal").value(hasItem(DEFAULT_GRAND_TOTAL.doubleValue())));
    }
}
