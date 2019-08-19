package com.diviso.graeshoppe.report.web.rest;

import com.diviso.graeshoppe.report.ReportApp;

import com.diviso.graeshoppe.report.domain.AuxItem;
import com.diviso.graeshoppe.report.repository.AuxItemRepository;
import com.diviso.graeshoppe.report.repository.search.AuxItemSearchRepository;
import com.diviso.graeshoppe.report.service.AuxItemService;
import com.diviso.graeshoppe.report.service.dto.AuxItemDTO;
import com.diviso.graeshoppe.report.service.mapper.AuxItemMapper;
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
 * Test class for the AuxItemResource REST controller.
 *
 * @see AuxItemResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ReportApp.class)
public class AuxItemResourceIntTest {

    private static final String DEFAULT_AUX_ITEM = "AAAAAAAAAA";
    private static final String UPDATED_AUX_ITEM = "BBBBBBBBBB";

    private static final Integer DEFAULT_QUANTITY = 1;
    private static final Integer UPDATED_QUANTITY = 2;

    private static final Double DEFAULT_TOTAL = 1D;
    private static final Double UPDATED_TOTAL = 2D;

    @Autowired
    private AuxItemRepository auxItemRepository;

    @Autowired
    private AuxItemMapper auxItemMapper;

    @Autowired
    private AuxItemService auxItemService;

    /**
     * This repository is mocked in the com.diviso.graeshoppe.report.repository.search test package.
     *
     * @see com.diviso.graeshoppe.report.repository.search.AuxItemSearchRepositoryMockConfiguration
     */
    @Autowired
    private AuxItemSearchRepository mockAuxItemSearchRepository;

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

    private MockMvc restAuxItemMockMvc;

    private AuxItem auxItem;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final AuxItemResource auxItemResource = new AuxItemResource(auxItemService);
        this.restAuxItemMockMvc = MockMvcBuilders.standaloneSetup(auxItemResource)
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
    public static AuxItem createEntity(EntityManager em) {
        AuxItem auxItem = new AuxItem()
            .auxItem(DEFAULT_AUX_ITEM)
            .quantity(DEFAULT_QUANTITY)
            .total(DEFAULT_TOTAL);
        return auxItem;
    }

    @Before
    public void initTest() {
        auxItem = createEntity(em);
    }

    @Test
    @Transactional
    public void createAuxItem() throws Exception {
        int databaseSizeBeforeCreate = auxItemRepository.findAll().size();

        // Create the AuxItem
        AuxItemDTO auxItemDTO = auxItemMapper.toDto(auxItem);
        restAuxItemMockMvc.perform(post("/api/aux-items")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(auxItemDTO)))
            .andExpect(status().isCreated());

        // Validate the AuxItem in the database
        List<AuxItem> auxItemList = auxItemRepository.findAll();
        assertThat(auxItemList).hasSize(databaseSizeBeforeCreate + 1);
        AuxItem testAuxItem = auxItemList.get(auxItemList.size() - 1);
        assertThat(testAuxItem.getAuxItem()).isEqualTo(DEFAULT_AUX_ITEM);
        assertThat(testAuxItem.getQuantity()).isEqualTo(DEFAULT_QUANTITY);
        assertThat(testAuxItem.getTotal()).isEqualTo(DEFAULT_TOTAL);

        // Validate the AuxItem in Elasticsearch
        verify(mockAuxItemSearchRepository, times(1)).save(testAuxItem);
    }

    @Test
    @Transactional
    public void createAuxItemWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = auxItemRepository.findAll().size();

        // Create the AuxItem with an existing ID
        auxItem.setId(1L);
        AuxItemDTO auxItemDTO = auxItemMapper.toDto(auxItem);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAuxItemMockMvc.perform(post("/api/aux-items")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(auxItemDTO)))
            .andExpect(status().isBadRequest());

        // Validate the AuxItem in the database
        List<AuxItem> auxItemList = auxItemRepository.findAll();
        assertThat(auxItemList).hasSize(databaseSizeBeforeCreate);

        // Validate the AuxItem in Elasticsearch
        verify(mockAuxItemSearchRepository, times(0)).save(auxItem);
    }

    @Test
    @Transactional
    public void getAllAuxItems() throws Exception {
        // Initialize the database
        auxItemRepository.saveAndFlush(auxItem);

        // Get all the auxItemList
        restAuxItemMockMvc.perform(get("/api/aux-items?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(auxItem.getId().intValue())))
            .andExpect(jsonPath("$.[*].auxItem").value(hasItem(DEFAULT_AUX_ITEM.toString())))
            .andExpect(jsonPath("$.[*].quantity").value(hasItem(DEFAULT_QUANTITY)))
            .andExpect(jsonPath("$.[*].total").value(hasItem(DEFAULT_TOTAL.doubleValue())));
    }
    
    @Test
    @Transactional
    public void getAuxItem() throws Exception {
        // Initialize the database
        auxItemRepository.saveAndFlush(auxItem);

        // Get the auxItem
        restAuxItemMockMvc.perform(get("/api/aux-items/{id}", auxItem.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(auxItem.getId().intValue()))
            .andExpect(jsonPath("$.auxItem").value(DEFAULT_AUX_ITEM.toString()))
            .andExpect(jsonPath("$.quantity").value(DEFAULT_QUANTITY))
            .andExpect(jsonPath("$.total").value(DEFAULT_TOTAL.doubleValue()));
    }

    @Test
    @Transactional
    public void getNonExistingAuxItem() throws Exception {
        // Get the auxItem
        restAuxItemMockMvc.perform(get("/api/aux-items/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAuxItem() throws Exception {
        // Initialize the database
        auxItemRepository.saveAndFlush(auxItem);

        int databaseSizeBeforeUpdate = auxItemRepository.findAll().size();

        // Update the auxItem
        AuxItem updatedAuxItem = auxItemRepository.findById(auxItem.getId()).get();
        // Disconnect from session so that the updates on updatedAuxItem are not directly saved in db
        em.detach(updatedAuxItem);
        updatedAuxItem
            .auxItem(UPDATED_AUX_ITEM)
            .quantity(UPDATED_QUANTITY)
            .total(UPDATED_TOTAL);
        AuxItemDTO auxItemDTO = auxItemMapper.toDto(updatedAuxItem);

        restAuxItemMockMvc.perform(put("/api/aux-items")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(auxItemDTO)))
            .andExpect(status().isOk());

        // Validate the AuxItem in the database
        List<AuxItem> auxItemList = auxItemRepository.findAll();
        assertThat(auxItemList).hasSize(databaseSizeBeforeUpdate);
        AuxItem testAuxItem = auxItemList.get(auxItemList.size() - 1);
        assertThat(testAuxItem.getAuxItem()).isEqualTo(UPDATED_AUX_ITEM);
        assertThat(testAuxItem.getQuantity()).isEqualTo(UPDATED_QUANTITY);
        assertThat(testAuxItem.getTotal()).isEqualTo(UPDATED_TOTAL);

        // Validate the AuxItem in Elasticsearch
        verify(mockAuxItemSearchRepository, times(1)).save(testAuxItem);
    }

    @Test
    @Transactional
    public void updateNonExistingAuxItem() throws Exception {
        int databaseSizeBeforeUpdate = auxItemRepository.findAll().size();

        // Create the AuxItem
        AuxItemDTO auxItemDTO = auxItemMapper.toDto(auxItem);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAuxItemMockMvc.perform(put("/api/aux-items")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(auxItemDTO)))
            .andExpect(status().isBadRequest());

        // Validate the AuxItem in the database
        List<AuxItem> auxItemList = auxItemRepository.findAll();
        assertThat(auxItemList).hasSize(databaseSizeBeforeUpdate);

        // Validate the AuxItem in Elasticsearch
        verify(mockAuxItemSearchRepository, times(0)).save(auxItem);
    }

    @Test
    @Transactional
    public void deleteAuxItem() throws Exception {
        // Initialize the database
        auxItemRepository.saveAndFlush(auxItem);

        int databaseSizeBeforeDelete = auxItemRepository.findAll().size();

        // Delete the auxItem
        restAuxItemMockMvc.perform(delete("/api/aux-items/{id}", auxItem.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<AuxItem> auxItemList = auxItemRepository.findAll();
        assertThat(auxItemList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the AuxItem in Elasticsearch
        verify(mockAuxItemSearchRepository, times(1)).deleteById(auxItem.getId());
    }

    @Test
    @Transactional
    public void searchAuxItem() throws Exception {
        // Initialize the database
        auxItemRepository.saveAndFlush(auxItem);
        when(mockAuxItemSearchRepository.search(queryStringQuery("id:" + auxItem.getId()), PageRequest.of(0, 20)))
            .thenReturn(new PageImpl<>(Collections.singletonList(auxItem), PageRequest.of(0, 1), 1));
        // Search the auxItem
        restAuxItemMockMvc.perform(get("/api/_search/aux-items?query=id:" + auxItem.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(auxItem.getId().intValue())))
            .andExpect(jsonPath("$.[*].auxItem").value(hasItem(DEFAULT_AUX_ITEM)))
            .andExpect(jsonPath("$.[*].quantity").value(hasItem(DEFAULT_QUANTITY)))
            .andExpect(jsonPath("$.[*].total").value(hasItem(DEFAULT_TOTAL.doubleValue())));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(AuxItem.class);
        AuxItem auxItem1 = new AuxItem();
        auxItem1.setId(1L);
        AuxItem auxItem2 = new AuxItem();
        auxItem2.setId(auxItem1.getId());
        assertThat(auxItem1).isEqualTo(auxItem2);
        auxItem2.setId(2L);
        assertThat(auxItem1).isNotEqualTo(auxItem2);
        auxItem1.setId(null);
        assertThat(auxItem1).isNotEqualTo(auxItem2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(AuxItemDTO.class);
        AuxItemDTO auxItemDTO1 = new AuxItemDTO();
        auxItemDTO1.setId(1L);
        AuxItemDTO auxItemDTO2 = new AuxItemDTO();
        assertThat(auxItemDTO1).isNotEqualTo(auxItemDTO2);
        auxItemDTO2.setId(auxItemDTO1.getId());
        assertThat(auxItemDTO1).isEqualTo(auxItemDTO2);
        auxItemDTO2.setId(2L);
        assertThat(auxItemDTO1).isNotEqualTo(auxItemDTO2);
        auxItemDTO1.setId(null);
        assertThat(auxItemDTO1).isNotEqualTo(auxItemDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(auxItemMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(auxItemMapper.fromId(null)).isNull();
    }
}
