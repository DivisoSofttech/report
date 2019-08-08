package com.diviso.graeshoppe.report.web.rest;

import com.diviso.graeshoppe.report.ReportApp;

import com.diviso.graeshoppe.report.domain.OrderLine;
import com.diviso.graeshoppe.report.repository.OrderLineRepository;
import com.diviso.graeshoppe.report.repository.search.OrderLineSearchRepository;
import com.diviso.graeshoppe.report.service.OrderLineService;
import com.diviso.graeshoppe.report.service.dto.OrderLineDTO;
import com.diviso.graeshoppe.report.service.mapper.OrderLineMapper;
import com.diviso.graeshoppe.report.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

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
 * Test class for the OrderLineResource REST controller.
 *
 * @see OrderLineResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ReportApp.class)
public class OrderLineResourceIntTest {

    private static final String DEFAULT_ITEM = "AAAAAAAAAA";
    private static final String UPDATED_ITEM = "BBBBBBBBBB";

    private static final Integer DEFAULT_QUANTITY = 1;
    private static final Integer UPDATED_QUANTITY = 2;

    private static final Double DEFAULT_TOTAL = 1D;
    private static final Double UPDATED_TOTAL = 2D;

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

    private MockMvc restOrderLineMockMvc;

    private OrderLine orderLine;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final OrderLineResource orderLineResource = new OrderLineResource(orderLineService);
        this.restOrderLineMockMvc = MockMvcBuilders.standaloneSetup(orderLineResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();
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
            .total(DEFAULT_TOTAL);
        return orderLine;
    }

    @Before
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
            .andExpect(jsonPath("$.[*].item").value(hasItem(DEFAULT_ITEM.toString())))
            .andExpect(jsonPath("$.[*].quantity").value(hasItem(DEFAULT_QUANTITY)))
            .andExpect(jsonPath("$.[*].total").value(hasItem(DEFAULT_TOTAL.doubleValue())));
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
            .andExpect(jsonPath("$.item").value(DEFAULT_ITEM.toString()))
            .andExpect(jsonPath("$.quantity").value(DEFAULT_QUANTITY))
            .andExpect(jsonPath("$.total").value(DEFAULT_TOTAL.doubleValue()));
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
            .total(UPDATED_TOTAL);
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

        // Get the orderLine
        restOrderLineMockMvc.perform(delete("/api/order-lines/{id}", orderLine.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
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
            .andExpect(jsonPath("$.[*].total").value(hasItem(DEFAULT_TOTAL.doubleValue())));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(OrderLine.class);
        OrderLine orderLine1 = new OrderLine();
        orderLine1.setId(1L);
        OrderLine orderLine2 = new OrderLine();
        orderLine2.setId(orderLine1.getId());
        assertThat(orderLine1).isEqualTo(orderLine2);
        orderLine2.setId(2L);
        assertThat(orderLine1).isNotEqualTo(orderLine2);
        orderLine1.setId(null);
        assertThat(orderLine1).isNotEqualTo(orderLine2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(OrderLineDTO.class);
        OrderLineDTO orderLineDTO1 = new OrderLineDTO();
        orderLineDTO1.setId(1L);
        OrderLineDTO orderLineDTO2 = new OrderLineDTO();
        assertThat(orderLineDTO1).isNotEqualTo(orderLineDTO2);
        orderLineDTO2.setId(orderLineDTO1.getId());
        assertThat(orderLineDTO1).isEqualTo(orderLineDTO2);
        orderLineDTO2.setId(2L);
        assertThat(orderLineDTO1).isNotEqualTo(orderLineDTO2);
        orderLineDTO1.setId(null);
        assertThat(orderLineDTO1).isNotEqualTo(orderLineDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(orderLineMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(orderLineMapper.fromId(null)).isNull();
    }
}
