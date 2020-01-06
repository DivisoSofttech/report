package com.diviso.graeshoppe.report.web.rest;

import com.diviso.graeshoppe.report.ReportApp;
import com.diviso.graeshoppe.report.config.TestSecurityConfiguration;
import com.diviso.graeshoppe.report.domain.OrderLine;
import com.diviso.graeshoppe.report.repository.OrderLineRepository;
import com.diviso.graeshoppe.report.repository.search.OrderLineSearchRepository;
import com.diviso.graeshoppe.report.service.OrderLineService;
import com.diviso.graeshoppe.report.service.dto.OrderLineDTO;
import com.diviso.graeshoppe.report.service.mapper.OrderLineMapper;
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
 * Integration tests for the {@link OrderLineResource} REST controller.
 */
@SpringBootTest(classes = {ReportApp.class, TestSecurityConfiguration.class})
public class OrderLineResourceIT {

    private static final String DEFAULT_ITEM = "AAAAAAAAAA";
    private static final String UPDATED_ITEM = "BBBBBBBBBB";

    private static final Integer DEFAULT_QUANTITY = 1;
    private static final Integer UPDATED_QUANTITY = 2;

    private static final Double DEFAULT_TOTAL = 1D;
    private static final Double UPDATED_TOTAL = 2D;

    private static final Long DEFAULT_PRODUCT_ID = 1L;
    private static final Long UPDATED_PRODUCT_ID = 2L;

    @Autowired
    private OrderLineRepository orderLineRepository;

    @Autowired
    private OrderLineMapper orderLineMapper;

    @Autowired
    private OrderLineService orderLineService;

    /**
     * This repository is mocked in the com.diviso.graeshoppe.report.repository.search test package.
     *
     * @see com.diviso.graeshoppe.report.repository.search.OrderLineSearchRepositoryMockConfiguration
     */
    @Autowired
    private OrderLineSearchRepository mockOrderLineSearchRepository;

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

    private MockMvc restOrderLineMockMvc;

    private OrderLine orderLine;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final OrderLineResource orderLineResource = new OrderLineResource(orderLineService);
        this.restOrderLineMockMvc = MockMvcBuilders.standaloneSetup(orderLineResource)
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
    public static OrderLine createEntity(EntityManager em) {
        OrderLine orderLine = new OrderLine()
            .item(DEFAULT_ITEM)
            .quantity(DEFAULT_QUANTITY)
            .total(DEFAULT_TOTAL)
            .productId(DEFAULT_PRODUCT_ID);
        return orderLine;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static OrderLine createUpdatedEntity(EntityManager em) {
        OrderLine orderLine = new OrderLine()
            .item(UPDATED_ITEM)
            .quantity(UPDATED_QUANTITY)
            .total(UPDATED_TOTAL)
            .productId(UPDATED_PRODUCT_ID);
        return orderLine;
    }

    @BeforeEach
    public void initTest() {
        orderLine = createEntity(em);
    }

    @Test
    @Transactional
    public void createOrderLine() throws Exception {
        int databaseSizeBeforeCreate = orderLineRepository.findAll().size();

        // Create the OrderLine
        OrderLineDTO orderLineDTO = orderLineMapper.toDto(orderLine);
        restOrderLineMockMvc.perform(post("/api/order-lines")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(orderLineDTO)))
            .andExpect(status().isCreated());

        // Validate the OrderLine in the database
        List<OrderLine> orderLineList = orderLineRepository.findAll();
        assertThat(orderLineList).hasSize(databaseSizeBeforeCreate + 1);
        OrderLine testOrderLine = orderLineList.get(orderLineList.size() - 1);
        assertThat(testOrderLine.getItem()).isEqualTo(DEFAULT_ITEM);
        assertThat(testOrderLine.getQuantity()).isEqualTo(DEFAULT_QUANTITY);
        assertThat(testOrderLine.getTotal()).isEqualTo(DEFAULT_TOTAL);
        assertThat(testOrderLine.getProductId()).isEqualTo(DEFAULT_PRODUCT_ID);

        // Validate the OrderLine in Elasticsearch
        verify(mockOrderLineSearchRepository, times(1)).save(testOrderLine);
    }

    @Test
    @Transactional
    public void createOrderLineWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = orderLineRepository.findAll().size();

        // Create the OrderLine with an existing ID
        orderLine.setId(1L);
        OrderLineDTO orderLineDTO = orderLineMapper.toDto(orderLine);

        // An entity with an existing ID cannot be created, so this API call must fail
        restOrderLineMockMvc.perform(post("/api/order-lines")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(orderLineDTO)))
            .andExpect(status().isBadRequest());

        // Validate the OrderLine in the database
        List<OrderLine> orderLineList = orderLineRepository.findAll();
        assertThat(orderLineList).hasSize(databaseSizeBeforeCreate);

        // Validate the OrderLine in Elasticsearch
        verify(mockOrderLineSearchRepository, times(0)).save(orderLine);
    }


    @Test
    @Transactional
    public void getAllOrderLines() throws Exception {
        // Initialize the database
        orderLineRepository.saveAndFlush(orderLine);

        // Get all the orderLineList
        restOrderLineMockMvc.perform(get("/api/order-lines?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(orderLine.getId().intValue())))
            .andExpect(jsonPath("$.[*].item").value(hasItem(DEFAULT_ITEM)))
            .andExpect(jsonPath("$.[*].quantity").value(hasItem(DEFAULT_QUANTITY)))
            .andExpect(jsonPath("$.[*].total").value(hasItem(DEFAULT_TOTAL.doubleValue())))
            .andExpect(jsonPath("$.[*].productId").value(hasItem(DEFAULT_PRODUCT_ID.intValue())));
    }
    
    @Test
    @Transactional
    public void getOrderLine() throws Exception {
        // Initialize the database
        orderLineRepository.saveAndFlush(orderLine);

        // Get the orderLine
        restOrderLineMockMvc.perform(get("/api/order-lines/{id}", orderLine.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(orderLine.getId().intValue()))
            .andExpect(jsonPath("$.item").value(DEFAULT_ITEM))
            .andExpect(jsonPath("$.quantity").value(DEFAULT_QUANTITY))
            .andExpect(jsonPath("$.total").value(DEFAULT_TOTAL.doubleValue()))
            .andExpect(jsonPath("$.productId").value(DEFAULT_PRODUCT_ID.intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingOrderLine() throws Exception {
        // Get the orderLine
        restOrderLineMockMvc.perform(get("/api/order-lines/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateOrderLine() throws Exception {
        // Initialize the database
        orderLineRepository.saveAndFlush(orderLine);

        int databaseSizeBeforeUpdate = orderLineRepository.findAll().size();

        // Update the orderLine
        OrderLine updatedOrderLine = orderLineRepository.findById(orderLine.getId()).get();
        // Disconnect from session so that the updates on updatedOrderLine are not directly saved in db
        em.detach(updatedOrderLine);
        updatedOrderLine
            .item(UPDATED_ITEM)
            .quantity(UPDATED_QUANTITY)
            .total(UPDATED_TOTAL)
            .productId(UPDATED_PRODUCT_ID);
        OrderLineDTO orderLineDTO = orderLineMapper.toDto(updatedOrderLine);

        restOrderLineMockMvc.perform(put("/api/order-lines")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(orderLineDTO)))
            .andExpect(status().isOk());

        // Validate the OrderLine in the database
        List<OrderLine> orderLineList = orderLineRepository.findAll();
        assertThat(orderLineList).hasSize(databaseSizeBeforeUpdate);
        OrderLine testOrderLine = orderLineList.get(orderLineList.size() - 1);
        assertThat(testOrderLine.getItem()).isEqualTo(UPDATED_ITEM);
        assertThat(testOrderLine.getQuantity()).isEqualTo(UPDATED_QUANTITY);
        assertThat(testOrderLine.getTotal()).isEqualTo(UPDATED_TOTAL);
        assertThat(testOrderLine.getProductId()).isEqualTo(UPDATED_PRODUCT_ID);

        // Validate the OrderLine in Elasticsearch
        verify(mockOrderLineSearchRepository, times(1)).save(testOrderLine);
    }

    @Test
    @Transactional
    public void updateNonExistingOrderLine() throws Exception {
        int databaseSizeBeforeUpdate = orderLineRepository.findAll().size();

        // Create the OrderLine
        OrderLineDTO orderLineDTO = orderLineMapper.toDto(orderLine);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOrderLineMockMvc.perform(put("/api/order-lines")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(orderLineDTO)))
            .andExpect(status().isBadRequest());

        // Validate the OrderLine in the database
        List<OrderLine> orderLineList = orderLineRepository.findAll();
        assertThat(orderLineList).hasSize(databaseSizeBeforeUpdate);

        // Validate the OrderLine in Elasticsearch
        verify(mockOrderLineSearchRepository, times(0)).save(orderLine);
    }

    @Test
    @Transactional
    public void deleteOrderLine() throws Exception {
        // Initialize the database
        orderLineRepository.saveAndFlush(orderLine);

        int databaseSizeBeforeDelete = orderLineRepository.findAll().size();

        // Delete the orderLine
        restOrderLineMockMvc.perform(delete("/api/order-lines/{id}", orderLine.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<OrderLine> orderLineList = orderLineRepository.findAll();
        assertThat(orderLineList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the OrderLine in Elasticsearch
        verify(mockOrderLineSearchRepository, times(1)).deleteById(orderLine.getId());
    }

    @Test
    @Transactional
    public void searchOrderLine() throws Exception {
        // Initialize the database
        orderLineRepository.saveAndFlush(orderLine);
        when(mockOrderLineSearchRepository.search(queryStringQuery("id:" + orderLine.getId()), PageRequest.of(0, 20)))
            .thenReturn(new PageImpl<>(Collections.singletonList(orderLine), PageRequest.of(0, 1), 1));
        // Search the orderLine
        restOrderLineMockMvc.perform(get("/api/_search/order-lines?query=id:" + orderLine.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(orderLine.getId().intValue())))
            .andExpect(jsonPath("$.[*].item").value(hasItem(DEFAULT_ITEM)))
            .andExpect(jsonPath("$.[*].quantity").value(hasItem(DEFAULT_QUANTITY)))
            .andExpect(jsonPath("$.[*].total").value(hasItem(DEFAULT_TOTAL.doubleValue())))
            .andExpect(jsonPath("$.[*].productId").value(hasItem(DEFAULT_PRODUCT_ID.intValue())));
    }
}
