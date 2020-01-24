package com.diviso.graeshoppe.report.web.rest;

import com.diviso.graeshoppe.report.ReportApp;

import com.diviso.graeshoppe.report.domain.ComboItem;
import com.diviso.graeshoppe.report.repository.ComboItemRepository;
import com.diviso.graeshoppe.report.repository.search.ComboItemSearchRepository;
import com.diviso.graeshoppe.report.service.ComboItemService;
import com.diviso.graeshoppe.report.service.dto.ComboItemDTO;
import com.diviso.graeshoppe.report.service.mapper.ComboItemMapper;
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
 * Test class for the ComboItemResource REST controller.
 *
 * @see ComboItemResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ReportApp.class)
public class ComboItemResourceIntTest {

    private static final String DEFAULT_COMBO_ITEM = "AAAAAAAAAA";
    private static final String UPDATED_COMBO_ITEM = "BBBBBBBBBB";

    private static final Double DEFAULT_QUANTITY = 1D;
    private static final Double UPDATED_QUANTITY = 2D;

    private static final Long DEFAULT_PRODUCT_ID = 1L;
    private static final Long UPDATED_PRODUCT_ID = 2L;

    @Autowired
    private ComboItemRepository comboItemRepository;

    @Autowired
    private ComboItemMapper comboItemMapper;

    @Autowired
    private ComboItemService comboItemService;

    /**
     * This repository is mocked in the com.diviso.graeshoppe.report.repository.search test package.
     *
     * @see com.diviso.graeshoppe.report.repository.search.ComboItemSearchRepositoryMockConfiguration
     */
    @Autowired
    private ComboItemSearchRepository mockComboItemSearchRepository;

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

    private MockMvc restComboItemMockMvc;

    private ComboItem comboItem;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ComboItemResource comboItemResource = new ComboItemResource(comboItemService);
        this.restComboItemMockMvc = MockMvcBuilders.standaloneSetup(comboItemResource)
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
    public static ComboItem createEntity(EntityManager em) {
        ComboItem comboItem = new ComboItem()
            .comboItem(DEFAULT_COMBO_ITEM)
            .quantity(DEFAULT_QUANTITY)
            .productId(DEFAULT_PRODUCT_ID);
        return comboItem;
    }

    @Before
    public void initTest() {
        comboItem = createEntity(em);
    }

    @Test
    @Transactional
    public void createComboItem() throws Exception {
        int databaseSizeBeforeCreate = comboItemRepository.findAll().size();

        // Create the ComboItem
        ComboItemDTO comboItemDTO = comboItemMapper.toDto(comboItem);
        restComboItemMockMvc.perform(post("/api/combo-items")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(comboItemDTO)))
            .andExpect(status().isCreated());

        // Validate the ComboItem in the database
        List<ComboItem> comboItemList = comboItemRepository.findAll();
        assertThat(comboItemList).hasSize(databaseSizeBeforeCreate + 1);
        ComboItem testComboItem = comboItemList.get(comboItemList.size() - 1);
        assertThat(testComboItem.getComboItem()).isEqualTo(DEFAULT_COMBO_ITEM);
        assertThat(testComboItem.getQuantity()).isEqualTo(DEFAULT_QUANTITY);
        assertThat(testComboItem.getProductId()).isEqualTo(DEFAULT_PRODUCT_ID);

        // Validate the ComboItem in Elasticsearch
        verify(mockComboItemSearchRepository, times(1)).save(testComboItem);
    }

    @Test
    @Transactional
    public void createComboItemWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = comboItemRepository.findAll().size();

        // Create the ComboItem with an existing ID
        comboItem.setId(1L);
        ComboItemDTO comboItemDTO = comboItemMapper.toDto(comboItem);

        // An entity with an existing ID cannot be created, so this API call must fail
        restComboItemMockMvc.perform(post("/api/combo-items")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(comboItemDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ComboItem in the database
        List<ComboItem> comboItemList = comboItemRepository.findAll();
        assertThat(comboItemList).hasSize(databaseSizeBeforeCreate);

        // Validate the ComboItem in Elasticsearch
        verify(mockComboItemSearchRepository, times(0)).save(comboItem);
    }

    @Test
    @Transactional
    public void getAllComboItems() throws Exception {
        // Initialize the database
        comboItemRepository.saveAndFlush(comboItem);

        // Get all the comboItemList
        restComboItemMockMvc.perform(get("/api/combo-items?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(comboItem.getId().intValue())))
            .andExpect(jsonPath("$.[*].comboItem").value(hasItem(DEFAULT_COMBO_ITEM.toString())))
            .andExpect(jsonPath("$.[*].quantity").value(hasItem(DEFAULT_QUANTITY.doubleValue())))
            .andExpect(jsonPath("$.[*].productId").value(hasItem(DEFAULT_PRODUCT_ID.intValue())));
    }
    
    @Test
    @Transactional
    public void getComboItem() throws Exception {
        // Initialize the database
        comboItemRepository.saveAndFlush(comboItem);

        // Get the comboItem
        restComboItemMockMvc.perform(get("/api/combo-items/{id}", comboItem.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(comboItem.getId().intValue()))
            .andExpect(jsonPath("$.comboItem").value(DEFAULT_COMBO_ITEM.toString()))
            .andExpect(jsonPath("$.quantity").value(DEFAULT_QUANTITY.doubleValue()))
            .andExpect(jsonPath("$.productId").value(DEFAULT_PRODUCT_ID.intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingComboItem() throws Exception {
        // Get the comboItem
        restComboItemMockMvc.perform(get("/api/combo-items/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateComboItem() throws Exception {
        // Initialize the database
        comboItemRepository.saveAndFlush(comboItem);

        int databaseSizeBeforeUpdate = comboItemRepository.findAll().size();

        // Update the comboItem
        ComboItem updatedComboItem = comboItemRepository.findById(comboItem.getId()).get();
        // Disconnect from session so that the updates on updatedComboItem are not directly saved in db
        em.detach(updatedComboItem);
        updatedComboItem
            .comboItem(UPDATED_COMBO_ITEM)
            .quantity(UPDATED_QUANTITY)
            .productId(UPDATED_PRODUCT_ID);
        ComboItemDTO comboItemDTO = comboItemMapper.toDto(updatedComboItem);

        restComboItemMockMvc.perform(put("/api/combo-items")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(comboItemDTO)))
            .andExpect(status().isOk());

        // Validate the ComboItem in the database
        List<ComboItem> comboItemList = comboItemRepository.findAll();
        assertThat(comboItemList).hasSize(databaseSizeBeforeUpdate);
        ComboItem testComboItem = comboItemList.get(comboItemList.size() - 1);
        assertThat(testComboItem.getComboItem()).isEqualTo(UPDATED_COMBO_ITEM);
        assertThat(testComboItem.getQuantity()).isEqualTo(UPDATED_QUANTITY);
        assertThat(testComboItem.getProductId()).isEqualTo(UPDATED_PRODUCT_ID);

        // Validate the ComboItem in Elasticsearch
        verify(mockComboItemSearchRepository, times(1)).save(testComboItem);
    }

    @Test
    @Transactional
    public void updateNonExistingComboItem() throws Exception {
        int databaseSizeBeforeUpdate = comboItemRepository.findAll().size();

        // Create the ComboItem
        ComboItemDTO comboItemDTO = comboItemMapper.toDto(comboItem);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restComboItemMockMvc.perform(put("/api/combo-items")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(comboItemDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ComboItem in the database
        List<ComboItem> comboItemList = comboItemRepository.findAll();
        assertThat(comboItemList).hasSize(databaseSizeBeforeUpdate);

        // Validate the ComboItem in Elasticsearch
        verify(mockComboItemSearchRepository, times(0)).save(comboItem);
    }

    @Test
    @Transactional
    public void deleteComboItem() throws Exception {
        // Initialize the database
        comboItemRepository.saveAndFlush(comboItem);

        int databaseSizeBeforeDelete = comboItemRepository.findAll().size();

        // Delete the comboItem
        restComboItemMockMvc.perform(delete("/api/combo-items/{id}", comboItem.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<ComboItem> comboItemList = comboItemRepository.findAll();
        assertThat(comboItemList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the ComboItem in Elasticsearch
        verify(mockComboItemSearchRepository, times(1)).deleteById(comboItem.getId());
    }

    @Test
    @Transactional
    public void searchComboItem() throws Exception {
        // Initialize the database
        comboItemRepository.saveAndFlush(comboItem);
        when(mockComboItemSearchRepository.search(queryStringQuery("id:" + comboItem.getId()), PageRequest.of(0, 20)))
            .thenReturn(new PageImpl<>(Collections.singletonList(comboItem), PageRequest.of(0, 1), 1));
        // Search the comboItem
        restComboItemMockMvc.perform(get("/api/_search/combo-items?query=id:" + comboItem.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(comboItem.getId().intValue())))
            .andExpect(jsonPath("$.[*].comboItem").value(hasItem(DEFAULT_COMBO_ITEM)))
            .andExpect(jsonPath("$.[*].quantity").value(hasItem(DEFAULT_QUANTITY.doubleValue())))
            .andExpect(jsonPath("$.[*].productId").value(hasItem(DEFAULT_PRODUCT_ID.intValue())));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ComboItem.class);
        ComboItem comboItem1 = new ComboItem();
        comboItem1.setId(1L);
        ComboItem comboItem2 = new ComboItem();
        comboItem2.setId(comboItem1.getId());
        assertThat(comboItem1).isEqualTo(comboItem2);
        comboItem2.setId(2L);
        assertThat(comboItem1).isNotEqualTo(comboItem2);
        comboItem1.setId(null);
        assertThat(comboItem1).isNotEqualTo(comboItem2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ComboItemDTO.class);
        ComboItemDTO comboItemDTO1 = new ComboItemDTO();
        comboItemDTO1.setId(1L);
        ComboItemDTO comboItemDTO2 = new ComboItemDTO();
        assertThat(comboItemDTO1).isNotEqualTo(comboItemDTO2);
        comboItemDTO2.setId(comboItemDTO1.getId());
        assertThat(comboItemDTO1).isEqualTo(comboItemDTO2);
        comboItemDTO2.setId(2L);
        assertThat(comboItemDTO1).isNotEqualTo(comboItemDTO2);
        comboItemDTO1.setId(null);
        assertThat(comboItemDTO1).isNotEqualTo(comboItemDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(comboItemMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(comboItemMapper.fromId(null)).isNull();
    }
}
