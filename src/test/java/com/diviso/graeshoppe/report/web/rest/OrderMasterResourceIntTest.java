package com.diviso.graeshoppe.report.web.rest;

import com.diviso.graeshoppe.report.ReportApp;

import com.diviso.graeshoppe.report.domain.OrderMaster;
import com.diviso.graeshoppe.report.repository.OrderMasterRepository;
import com.diviso.graeshoppe.report.repository.search.OrderMasterSearchRepository;
import com.diviso.graeshoppe.report.service.OrderMasterService;
import com.diviso.graeshoppe.report.service.dto.OrderMasterDTO;
import com.diviso.graeshoppe.report.service.mapper.OrderMasterMapper;
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
 * Test class for the OrderMasterResource REST controller.
 *
 * @see OrderMasterResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ReportApp.class)
public class OrderMasterResourceIntTest {

    private static final String DEFAULT_STORE_IDPCODE = "AAAAAAAAAA";
    private static final String UPDATED_STORE_IDPCODE = "BBBBBBBBBB";

    private static final String DEFAULT_STORE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_STORE_NAME = "BBBBBBBBBB";

    private static final Long DEFAULT_STORE_PHONE = 1L;
    private static final Long UPDATED_STORE_PHONE = 2L;

    private static final String DEFAULT_STORELOCATION_NAME = "AAAAAAAAAA";
    private static final String UPDATED_STORELOCATION_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_METHOD_OF_ORDER = "AAAAAAAAAA";
    private static final String UPDATED_METHOD_OF_ORDER = "BBBBBBBBBB";

    private static final Instant DEFAULT_EXPECTED_DELIVERY = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_EXPECTED_DELIVERY = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_ORDER_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_ORDER_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_NOTES = "AAAAAAAAAA";
    private static final String UPDATED_NOTES = "BBBBBBBBBB";

    private static final Double DEFAULT_SUB_TOTAL = 1D;
    private static final Double UPDATED_SUB_TOTAL = 2D;

    private static final Double DEFAULT_ORDER_DISCOUNT_AMOUNT = 1D;
    private static final Double UPDATED_ORDER_DISCOUNT_AMOUNT = 2D;

    private static final Double DEFAULT_DELIVERY_CHARGE = 1D;
    private static final Double UPDATED_DELIVERY_CHARGE = 2D;

    private static final Double DEFAULT_SERVICE_CHARGE = 1D;
    private static final Double UPDATED_SERVICE_CHARGE = 2D;

    private static final Double DEFAULT_TOTAL_DUE = 1D;
    private static final Double UPDATED_TOTAL_DUE = 2D;

    private static final String DEFAULT_ORDER_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_ORDER_STATUS = "BBBBBBBBBB";

    private static final String DEFAULT_CUSTOMER_ID = "AAAAAAAAAA";
    private static final String UPDATED_CUSTOMER_ID = "BBBBBBBBBB";

    private static final String DEFAULT_CUSTOMER_NAME = "AAAAAAAAAA";
    private static final String UPDATED_CUSTOMER_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_PINCODE = "AAAAAAAAAA";
    private static final String UPDATED_PINCODE = "BBBBBBBBBB";

    private static final String DEFAULT_HOUSE_NO_OR_BUILDING_NAME = "AAAAAAAAAA";
    private static final String UPDATED_HOUSE_NO_OR_BUILDING_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_ROAD_NAME_AREA_OR_STREET = "AAAAAAAAAA";
    private static final String UPDATED_ROAD_NAME_AREA_OR_STREET = "BBBBBBBBBB";

    private static final String DEFAULT_CITY = "AAAAAAAAAA";
    private static final String UPDATED_CITY = "BBBBBBBBBB";

    private static final String DEFAULT_STATE = "AAAAAAAAAA";
    private static final String UPDATED_STATE = "BBBBBBBBBB";

    private static final String DEFAULT_LANDMARK = "AAAAAAAAAA";
    private static final String UPDATED_LANDMARK = "BBBBBBBBBB";

    private static final Long DEFAULT_PHONE = 1L;
    private static final Long UPDATED_PHONE = 2L;

    private static final Long DEFAULT_ALTERNATE_PHONE = 1L;
    private static final Long UPDATED_ALTERNATE_PHONE = 2L;

    private static final String DEFAULT_ADDRESS_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_ADDRESS_TYPE = "BBBBBBBBBB";

    private static final Long DEFAULT_ORDER_FROM_CUSTOMER = 1L;
    private static final Long UPDATED_ORDER_FROM_CUSTOMER = 2L;

    private static final Long DEFAULT_CUSTOMER_ORDER = 1L;
    private static final Long UPDATED_CUSTOMER_ORDER = 2L;

    private static final Instant DEFAULT_ORDER_PLACE_AT = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_ORDER_PLACE_AT = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_ORDER_ACCEPTED_AT = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_ORDER_ACCEPTED_AT = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_ALLERGY_NOTE = "AAAAAAAAAA";
    private static final String UPDATED_ALLERGY_NOTE = "BBBBBBBBBB";

    private static final Instant DEFAULT_PRE_ORDER_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_PRE_ORDER_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_EMAIL = "BBBBBBBBBB";

    private static final String DEFAULT_PAYMENT_REF = "AAAAAAAAAA";
    private static final String UPDATED_PAYMENT_REF = "BBBBBBBBBB";

    private static final String DEFAULT_PAYMENT_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_PAYMENT_STATUS = "BBBBBBBBBB";

    private static final String DEFAULT_ZONE_ID = "AAAAAAAAAA";
    private static final String UPDATED_ZONE_ID = "BBBBBBBBBB";

    private static final Long DEFAULT_LOYALTY_POINT = 1L;
    private static final Long UPDATED_LOYALTY_POINT = 2L;

    private static final Double DEFAULT_REFUNDED_AMOUNT = 1D;
    private static final Double UPDATED_REFUNDED_AMOUNT = 2D;

    private static final Long DEFAULT_CANCELLATION_REF = 1L;
    private static final Long UPDATED_CANCELLATION_REF = 2L;

    private static final String DEFAULT_NEXT_TASK_ID = "AAAAAAAAAA";
    private static final String UPDATED_NEXT_TASK_ID = "BBBBBBBBBB";

    @Autowired
    private OrderMasterRepository orderMasterRepository;

    @Autowired
    private OrderMasterMapper orderMasterMapper;

    @Autowired
    private OrderMasterService orderMasterService;

    /**
     * This repository is mocked in the com.diviso.graeshoppe.report.repository.search test package.
     *
     * @see com.diviso.graeshoppe.report.repository.search.OrderMasterSearchRepositoryMockConfiguration
     */
    @Autowired
    private OrderMasterSearchRepository mockOrderMasterSearchRepository;

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

    private MockMvc restOrderMasterMockMvc;

    private OrderMaster orderMaster;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final OrderMasterResource orderMasterResource = new OrderMasterResource(orderMasterService);
        this.restOrderMasterMockMvc = MockMvcBuilders.standaloneSetup(orderMasterResource)
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
    public static OrderMaster createEntity(EntityManager em) {
        OrderMaster orderMaster = new OrderMaster()
            .storeIdpcode(DEFAULT_STORE_IDPCODE)
            .storeName(DEFAULT_STORE_NAME)
            .storePhone(DEFAULT_STORE_PHONE)
            .storelocationName(DEFAULT_STORELOCATION_NAME)
            .methodOfOrder(DEFAULT_METHOD_OF_ORDER)
            .expectedDelivery(DEFAULT_EXPECTED_DELIVERY)
            .orderNumber(DEFAULT_ORDER_NUMBER)
            .notes(DEFAULT_NOTES)
            .subTotal(DEFAULT_SUB_TOTAL)
            .orderDiscountAmount(DEFAULT_ORDER_DISCOUNT_AMOUNT)
            .deliveryCharge(DEFAULT_DELIVERY_CHARGE)
            .serviceCharge(DEFAULT_SERVICE_CHARGE)
            .totalDue(DEFAULT_TOTAL_DUE)
            .orderStatus(DEFAULT_ORDER_STATUS)
            .customerId(DEFAULT_CUSTOMER_ID)
            .customerName(DEFAULT_CUSTOMER_NAME)
            .pincode(DEFAULT_PINCODE)
            .houseNoOrBuildingName(DEFAULT_HOUSE_NO_OR_BUILDING_NAME)
            .roadNameAreaOrStreet(DEFAULT_ROAD_NAME_AREA_OR_STREET)
            .city(DEFAULT_CITY)
            .state(DEFAULT_STATE)
            .landmark(DEFAULT_LANDMARK)
            .phone(DEFAULT_PHONE)
            .alternatePhone(DEFAULT_ALTERNATE_PHONE)
            .addressType(DEFAULT_ADDRESS_TYPE)
            .orderFromCustomer(DEFAULT_ORDER_FROM_CUSTOMER)
            .customerOrder(DEFAULT_CUSTOMER_ORDER)
            .orderPlaceAt(DEFAULT_ORDER_PLACE_AT)
            .orderAcceptedAt(DEFAULT_ORDER_ACCEPTED_AT)
            .allergyNote(DEFAULT_ALLERGY_NOTE)
            .preOrderDate(DEFAULT_PRE_ORDER_DATE)
            .email(DEFAULT_EMAIL)
            .paymentRef(DEFAULT_PAYMENT_REF)
            .paymentStatus(DEFAULT_PAYMENT_STATUS)
            .zoneId(DEFAULT_ZONE_ID)
            .loyaltyPoint(DEFAULT_LOYALTY_POINT)
            .refundedAmount(DEFAULT_REFUNDED_AMOUNT)
            .cancellationRef(DEFAULT_CANCELLATION_REF)
            .nextTaskId(DEFAULT_NEXT_TASK_ID);
        return orderMaster;
    }

    @Before
    public void initTest() {
        orderMaster = createEntity(em);
    }

    @Test
    @Transactional
    public void createOrderMaster() throws Exception {
        int databaseSizeBeforeCreate = orderMasterRepository.findAll().size();

        // Create the OrderMaster
        OrderMasterDTO orderMasterDTO = orderMasterMapper.toDto(orderMaster);
        restOrderMasterMockMvc.perform(post("/api/order-masters")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(orderMasterDTO)))
            .andExpect(status().isCreated());

        // Validate the OrderMaster in the database
        List<OrderMaster> orderMasterList = orderMasterRepository.findAll();
        assertThat(orderMasterList).hasSize(databaseSizeBeforeCreate + 1);
        OrderMaster testOrderMaster = orderMasterList.get(orderMasterList.size() - 1);
        assertThat(testOrderMaster.getStoreIdpcode()).isEqualTo(DEFAULT_STORE_IDPCODE);
        assertThat(testOrderMaster.getStoreName()).isEqualTo(DEFAULT_STORE_NAME);
        assertThat(testOrderMaster.getStorePhone()).isEqualTo(DEFAULT_STORE_PHONE);
        assertThat(testOrderMaster.getStorelocationName()).isEqualTo(DEFAULT_STORELOCATION_NAME);
        assertThat(testOrderMaster.getMethodOfOrder()).isEqualTo(DEFAULT_METHOD_OF_ORDER);
        assertThat(testOrderMaster.getExpectedDelivery()).isEqualTo(DEFAULT_EXPECTED_DELIVERY);
        assertThat(testOrderMaster.getOrderNumber()).isEqualTo(DEFAULT_ORDER_NUMBER);
        assertThat(testOrderMaster.getNotes()).isEqualTo(DEFAULT_NOTES);
        assertThat(testOrderMaster.getSubTotal()).isEqualTo(DEFAULT_SUB_TOTAL);
        assertThat(testOrderMaster.getOrderDiscountAmount()).isEqualTo(DEFAULT_ORDER_DISCOUNT_AMOUNT);
        assertThat(testOrderMaster.getDeliveryCharge()).isEqualTo(DEFAULT_DELIVERY_CHARGE);
        assertThat(testOrderMaster.getServiceCharge()).isEqualTo(DEFAULT_SERVICE_CHARGE);
        assertThat(testOrderMaster.getTotalDue()).isEqualTo(DEFAULT_TOTAL_DUE);
        assertThat(testOrderMaster.getOrderStatus()).isEqualTo(DEFAULT_ORDER_STATUS);
        assertThat(testOrderMaster.getCustomerId()).isEqualTo(DEFAULT_CUSTOMER_ID);
        assertThat(testOrderMaster.getCustomerName()).isEqualTo(DEFAULT_CUSTOMER_NAME);
        assertThat(testOrderMaster.getPincode()).isEqualTo(DEFAULT_PINCODE);
        assertThat(testOrderMaster.getHouseNoOrBuildingName()).isEqualTo(DEFAULT_HOUSE_NO_OR_BUILDING_NAME);
        assertThat(testOrderMaster.getRoadNameAreaOrStreet()).isEqualTo(DEFAULT_ROAD_NAME_AREA_OR_STREET);
        assertThat(testOrderMaster.getCity()).isEqualTo(DEFAULT_CITY);
        assertThat(testOrderMaster.getState()).isEqualTo(DEFAULT_STATE);
        assertThat(testOrderMaster.getLandmark()).isEqualTo(DEFAULT_LANDMARK);
        assertThat(testOrderMaster.getPhone()).isEqualTo(DEFAULT_PHONE);
        assertThat(testOrderMaster.getAlternatePhone()).isEqualTo(DEFAULT_ALTERNATE_PHONE);
        assertThat(testOrderMaster.getAddressType()).isEqualTo(DEFAULT_ADDRESS_TYPE);
        assertThat(testOrderMaster.getOrderFromCustomer()).isEqualTo(DEFAULT_ORDER_FROM_CUSTOMER);
        assertThat(testOrderMaster.getCustomerOrder()).isEqualTo(DEFAULT_CUSTOMER_ORDER);
        assertThat(testOrderMaster.getOrderPlaceAt()).isEqualTo(DEFAULT_ORDER_PLACE_AT);
        assertThat(testOrderMaster.getOrderAcceptedAt()).isEqualTo(DEFAULT_ORDER_ACCEPTED_AT);
        assertThat(testOrderMaster.getAllergyNote()).isEqualTo(DEFAULT_ALLERGY_NOTE);
        assertThat(testOrderMaster.getPreOrderDate()).isEqualTo(DEFAULT_PRE_ORDER_DATE);
        assertThat(testOrderMaster.getEmail()).isEqualTo(DEFAULT_EMAIL);
        assertThat(testOrderMaster.getPaymentRef()).isEqualTo(DEFAULT_PAYMENT_REF);
        assertThat(testOrderMaster.getPaymentStatus()).isEqualTo(DEFAULT_PAYMENT_STATUS);
        assertThat(testOrderMaster.getZoneId()).isEqualTo(DEFAULT_ZONE_ID);
        assertThat(testOrderMaster.getLoyaltyPoint()).isEqualTo(DEFAULT_LOYALTY_POINT);
        assertThat(testOrderMaster.getRefundedAmount()).isEqualTo(DEFAULT_REFUNDED_AMOUNT);
        assertThat(testOrderMaster.getCancellationRef()).isEqualTo(DEFAULT_CANCELLATION_REF);
        assertThat(testOrderMaster.getNextTaskId()).isEqualTo(DEFAULT_NEXT_TASK_ID);

        // Validate the OrderMaster in Elasticsearch
        verify(mockOrderMasterSearchRepository, times(1)).save(testOrderMaster);
    }

    @Test
    @Transactional
    public void createOrderMasterWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = orderMasterRepository.findAll().size();

        // Create the OrderMaster with an existing ID
        orderMaster.setId(1L);
        OrderMasterDTO orderMasterDTO = orderMasterMapper.toDto(orderMaster);

        // An entity with an existing ID cannot be created, so this API call must fail
        restOrderMasterMockMvc.perform(post("/api/order-masters")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(orderMasterDTO)))
            .andExpect(status().isBadRequest());

        // Validate the OrderMaster in the database
        List<OrderMaster> orderMasterList = orderMasterRepository.findAll();
        assertThat(orderMasterList).hasSize(databaseSizeBeforeCreate);

        // Validate the OrderMaster in Elasticsearch
        verify(mockOrderMasterSearchRepository, times(0)).save(orderMaster);
    }

    @Test
    @Transactional
    public void getAllOrderMasters() throws Exception {
        // Initialize the database
        orderMasterRepository.saveAndFlush(orderMaster);

        // Get all the orderMasterList
        restOrderMasterMockMvc.perform(get("/api/order-masters?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(orderMaster.getId().intValue())))
            .andExpect(jsonPath("$.[*].storeIdpcode").value(hasItem(DEFAULT_STORE_IDPCODE.toString())))
            .andExpect(jsonPath("$.[*].storeName").value(hasItem(DEFAULT_STORE_NAME.toString())))
            .andExpect(jsonPath("$.[*].storePhone").value(hasItem(DEFAULT_STORE_PHONE.intValue())))
            .andExpect(jsonPath("$.[*].storelocationName").value(hasItem(DEFAULT_STORELOCATION_NAME.toString())))
            .andExpect(jsonPath("$.[*].methodOfOrder").value(hasItem(DEFAULT_METHOD_OF_ORDER.toString())))
            .andExpect(jsonPath("$.[*].expectedDelivery").value(hasItem(DEFAULT_EXPECTED_DELIVERY.toString())))
            .andExpect(jsonPath("$.[*].orderNumber").value(hasItem(DEFAULT_ORDER_NUMBER.toString())))
            .andExpect(jsonPath("$.[*].notes").value(hasItem(DEFAULT_NOTES.toString())))
            .andExpect(jsonPath("$.[*].subTotal").value(hasItem(DEFAULT_SUB_TOTAL.doubleValue())))
            .andExpect(jsonPath("$.[*].orderDiscountAmount").value(hasItem(DEFAULT_ORDER_DISCOUNT_AMOUNT.doubleValue())))
            .andExpect(jsonPath("$.[*].deliveryCharge").value(hasItem(DEFAULT_DELIVERY_CHARGE.doubleValue())))
            .andExpect(jsonPath("$.[*].serviceCharge").value(hasItem(DEFAULT_SERVICE_CHARGE.doubleValue())))
            .andExpect(jsonPath("$.[*].totalDue").value(hasItem(DEFAULT_TOTAL_DUE.doubleValue())))
            .andExpect(jsonPath("$.[*].orderStatus").value(hasItem(DEFAULT_ORDER_STATUS.toString())))
            .andExpect(jsonPath("$.[*].customerId").value(hasItem(DEFAULT_CUSTOMER_ID.toString())))
            .andExpect(jsonPath("$.[*].customerName").value(hasItem(DEFAULT_CUSTOMER_NAME.toString())))
            .andExpect(jsonPath("$.[*].pincode").value(hasItem(DEFAULT_PINCODE.toString())))
            .andExpect(jsonPath("$.[*].houseNoOrBuildingName").value(hasItem(DEFAULT_HOUSE_NO_OR_BUILDING_NAME.toString())))
            .andExpect(jsonPath("$.[*].roadNameAreaOrStreet").value(hasItem(DEFAULT_ROAD_NAME_AREA_OR_STREET.toString())))
            .andExpect(jsonPath("$.[*].city").value(hasItem(DEFAULT_CITY.toString())))
            .andExpect(jsonPath("$.[*].state").value(hasItem(DEFAULT_STATE.toString())))
            .andExpect(jsonPath("$.[*].landmark").value(hasItem(DEFAULT_LANDMARK.toString())))
            .andExpect(jsonPath("$.[*].phone").value(hasItem(DEFAULT_PHONE.intValue())))
            .andExpect(jsonPath("$.[*].alternatePhone").value(hasItem(DEFAULT_ALTERNATE_PHONE.intValue())))
            .andExpect(jsonPath("$.[*].addressType").value(hasItem(DEFAULT_ADDRESS_TYPE.toString())))
            .andExpect(jsonPath("$.[*].orderFromCustomer").value(hasItem(DEFAULT_ORDER_FROM_CUSTOMER.intValue())))
            .andExpect(jsonPath("$.[*].customerOrder").value(hasItem(DEFAULT_CUSTOMER_ORDER.intValue())))
            .andExpect(jsonPath("$.[*].orderPlaceAt").value(hasItem(DEFAULT_ORDER_PLACE_AT.toString())))
            .andExpect(jsonPath("$.[*].orderAcceptedAt").value(hasItem(DEFAULT_ORDER_ACCEPTED_AT.toString())))
            .andExpect(jsonPath("$.[*].allergyNote").value(hasItem(DEFAULT_ALLERGY_NOTE.toString())))
            .andExpect(jsonPath("$.[*].preOrderDate").value(hasItem(DEFAULT_PRE_ORDER_DATE.toString())))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL.toString())))
            .andExpect(jsonPath("$.[*].paymentRef").value(hasItem(DEFAULT_PAYMENT_REF.toString())))
            .andExpect(jsonPath("$.[*].paymentStatus").value(hasItem(DEFAULT_PAYMENT_STATUS.toString())))
            .andExpect(jsonPath("$.[*].zoneId").value(hasItem(DEFAULT_ZONE_ID.toString())))
            .andExpect(jsonPath("$.[*].loyaltyPoint").value(hasItem(DEFAULT_LOYALTY_POINT.intValue())))
            .andExpect(jsonPath("$.[*].refundedAmount").value(hasItem(DEFAULT_REFUNDED_AMOUNT.doubleValue())))
            .andExpect(jsonPath("$.[*].cancellationRef").value(hasItem(DEFAULT_CANCELLATION_REF.intValue())))
            .andExpect(jsonPath("$.[*].nextTaskId").value(hasItem(DEFAULT_NEXT_TASK_ID.toString())));
    }
    
    @Test
    @Transactional
    public void getOrderMaster() throws Exception {
        // Initialize the database
        orderMasterRepository.saveAndFlush(orderMaster);

        // Get the orderMaster
        restOrderMasterMockMvc.perform(get("/api/order-masters/{id}", orderMaster.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(orderMaster.getId().intValue()))
            .andExpect(jsonPath("$.storeIdpcode").value(DEFAULT_STORE_IDPCODE.toString()))
            .andExpect(jsonPath("$.storeName").value(DEFAULT_STORE_NAME.toString()))
            .andExpect(jsonPath("$.storePhone").value(DEFAULT_STORE_PHONE.intValue()))
            .andExpect(jsonPath("$.storelocationName").value(DEFAULT_STORELOCATION_NAME.toString()))
            .andExpect(jsonPath("$.methodOfOrder").value(DEFAULT_METHOD_OF_ORDER.toString()))
            .andExpect(jsonPath("$.expectedDelivery").value(DEFAULT_EXPECTED_DELIVERY.toString()))
            .andExpect(jsonPath("$.orderNumber").value(DEFAULT_ORDER_NUMBER.toString()))
            .andExpect(jsonPath("$.notes").value(DEFAULT_NOTES.toString()))
            .andExpect(jsonPath("$.subTotal").value(DEFAULT_SUB_TOTAL.doubleValue()))
            .andExpect(jsonPath("$.orderDiscountAmount").value(DEFAULT_ORDER_DISCOUNT_AMOUNT.doubleValue()))
            .andExpect(jsonPath("$.deliveryCharge").value(DEFAULT_DELIVERY_CHARGE.doubleValue()))
            .andExpect(jsonPath("$.serviceCharge").value(DEFAULT_SERVICE_CHARGE.doubleValue()))
            .andExpect(jsonPath("$.totalDue").value(DEFAULT_TOTAL_DUE.doubleValue()))
            .andExpect(jsonPath("$.orderStatus").value(DEFAULT_ORDER_STATUS.toString()))
            .andExpect(jsonPath("$.customerId").value(DEFAULT_CUSTOMER_ID.toString()))
            .andExpect(jsonPath("$.customerName").value(DEFAULT_CUSTOMER_NAME.toString()))
            .andExpect(jsonPath("$.pincode").value(DEFAULT_PINCODE.toString()))
            .andExpect(jsonPath("$.houseNoOrBuildingName").value(DEFAULT_HOUSE_NO_OR_BUILDING_NAME.toString()))
            .andExpect(jsonPath("$.roadNameAreaOrStreet").value(DEFAULT_ROAD_NAME_AREA_OR_STREET.toString()))
            .andExpect(jsonPath("$.city").value(DEFAULT_CITY.toString()))
            .andExpect(jsonPath("$.state").value(DEFAULT_STATE.toString()))
            .andExpect(jsonPath("$.landmark").value(DEFAULT_LANDMARK.toString()))
            .andExpect(jsonPath("$.phone").value(DEFAULT_PHONE.intValue()))
            .andExpect(jsonPath("$.alternatePhone").value(DEFAULT_ALTERNATE_PHONE.intValue()))
            .andExpect(jsonPath("$.addressType").value(DEFAULT_ADDRESS_TYPE.toString()))
            .andExpect(jsonPath("$.orderFromCustomer").value(DEFAULT_ORDER_FROM_CUSTOMER.intValue()))
            .andExpect(jsonPath("$.customerOrder").value(DEFAULT_CUSTOMER_ORDER.intValue()))
            .andExpect(jsonPath("$.orderPlaceAt").value(DEFAULT_ORDER_PLACE_AT.toString()))
            .andExpect(jsonPath("$.orderAcceptedAt").value(DEFAULT_ORDER_ACCEPTED_AT.toString()))
            .andExpect(jsonPath("$.allergyNote").value(DEFAULT_ALLERGY_NOTE.toString()))
            .andExpect(jsonPath("$.preOrderDate").value(DEFAULT_PRE_ORDER_DATE.toString()))
            .andExpect(jsonPath("$.email").value(DEFAULT_EMAIL.toString()))
            .andExpect(jsonPath("$.paymentRef").value(DEFAULT_PAYMENT_REF.toString()))
            .andExpect(jsonPath("$.paymentStatus").value(DEFAULT_PAYMENT_STATUS.toString()))
            .andExpect(jsonPath("$.zoneId").value(DEFAULT_ZONE_ID.toString()))
            .andExpect(jsonPath("$.loyaltyPoint").value(DEFAULT_LOYALTY_POINT.intValue()))
            .andExpect(jsonPath("$.refundedAmount").value(DEFAULT_REFUNDED_AMOUNT.doubleValue()))
            .andExpect(jsonPath("$.cancellationRef").value(DEFAULT_CANCELLATION_REF.intValue()))
            .andExpect(jsonPath("$.nextTaskId").value(DEFAULT_NEXT_TASK_ID.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingOrderMaster() throws Exception {
        // Get the orderMaster
        restOrderMasterMockMvc.perform(get("/api/order-masters/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateOrderMaster() throws Exception {
        // Initialize the database
        orderMasterRepository.saveAndFlush(orderMaster);

        int databaseSizeBeforeUpdate = orderMasterRepository.findAll().size();

        // Update the orderMaster
        OrderMaster updatedOrderMaster = orderMasterRepository.findById(orderMaster.getId()).get();
        // Disconnect from session so that the updates on updatedOrderMaster are not directly saved in db
        em.detach(updatedOrderMaster);
        updatedOrderMaster
            .storeIdpcode(UPDATED_STORE_IDPCODE)
            .storeName(UPDATED_STORE_NAME)
            .storePhone(UPDATED_STORE_PHONE)
            .storelocationName(UPDATED_STORELOCATION_NAME)
            .methodOfOrder(UPDATED_METHOD_OF_ORDER)
            .expectedDelivery(UPDATED_EXPECTED_DELIVERY)
            .orderNumber(UPDATED_ORDER_NUMBER)
            .notes(UPDATED_NOTES)
            .subTotal(UPDATED_SUB_TOTAL)
            .orderDiscountAmount(UPDATED_ORDER_DISCOUNT_AMOUNT)
            .deliveryCharge(UPDATED_DELIVERY_CHARGE)
            .serviceCharge(UPDATED_SERVICE_CHARGE)
            .totalDue(UPDATED_TOTAL_DUE)
            .orderStatus(UPDATED_ORDER_STATUS)
            .customerId(UPDATED_CUSTOMER_ID)
            .customerName(UPDATED_CUSTOMER_NAME)
            .pincode(UPDATED_PINCODE)
            .houseNoOrBuildingName(UPDATED_HOUSE_NO_OR_BUILDING_NAME)
            .roadNameAreaOrStreet(UPDATED_ROAD_NAME_AREA_OR_STREET)
            .city(UPDATED_CITY)
            .state(UPDATED_STATE)
            .landmark(UPDATED_LANDMARK)
            .phone(UPDATED_PHONE)
            .alternatePhone(UPDATED_ALTERNATE_PHONE)
            .addressType(UPDATED_ADDRESS_TYPE)
            .orderFromCustomer(UPDATED_ORDER_FROM_CUSTOMER)
            .customerOrder(UPDATED_CUSTOMER_ORDER)
            .orderPlaceAt(UPDATED_ORDER_PLACE_AT)
            .orderAcceptedAt(UPDATED_ORDER_ACCEPTED_AT)
            .allergyNote(UPDATED_ALLERGY_NOTE)
            .preOrderDate(UPDATED_PRE_ORDER_DATE)
            .email(UPDATED_EMAIL)
            .paymentRef(UPDATED_PAYMENT_REF)
            .paymentStatus(UPDATED_PAYMENT_STATUS)
            .zoneId(UPDATED_ZONE_ID)
            .loyaltyPoint(UPDATED_LOYALTY_POINT)
            .refundedAmount(UPDATED_REFUNDED_AMOUNT)
            .cancellationRef(UPDATED_CANCELLATION_REF)
            .nextTaskId(UPDATED_NEXT_TASK_ID);
        OrderMasterDTO orderMasterDTO = orderMasterMapper.toDto(updatedOrderMaster);

        restOrderMasterMockMvc.perform(put("/api/order-masters")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(orderMasterDTO)))
            .andExpect(status().isOk());

        // Validate the OrderMaster in the database
        List<OrderMaster> orderMasterList = orderMasterRepository.findAll();
        assertThat(orderMasterList).hasSize(databaseSizeBeforeUpdate);
        OrderMaster testOrderMaster = orderMasterList.get(orderMasterList.size() - 1);
        assertThat(testOrderMaster.getStoreIdpcode()).isEqualTo(UPDATED_STORE_IDPCODE);
        assertThat(testOrderMaster.getStoreName()).isEqualTo(UPDATED_STORE_NAME);
        assertThat(testOrderMaster.getStorePhone()).isEqualTo(UPDATED_STORE_PHONE);
        assertThat(testOrderMaster.getStorelocationName()).isEqualTo(UPDATED_STORELOCATION_NAME);
        assertThat(testOrderMaster.getMethodOfOrder()).isEqualTo(UPDATED_METHOD_OF_ORDER);
        assertThat(testOrderMaster.getExpectedDelivery()).isEqualTo(UPDATED_EXPECTED_DELIVERY);
        assertThat(testOrderMaster.getOrderNumber()).isEqualTo(UPDATED_ORDER_NUMBER);
        assertThat(testOrderMaster.getNotes()).isEqualTo(UPDATED_NOTES);
        assertThat(testOrderMaster.getSubTotal()).isEqualTo(UPDATED_SUB_TOTAL);
        assertThat(testOrderMaster.getOrderDiscountAmount()).isEqualTo(UPDATED_ORDER_DISCOUNT_AMOUNT);
        assertThat(testOrderMaster.getDeliveryCharge()).isEqualTo(UPDATED_DELIVERY_CHARGE);
        assertThat(testOrderMaster.getServiceCharge()).isEqualTo(UPDATED_SERVICE_CHARGE);
        assertThat(testOrderMaster.getTotalDue()).isEqualTo(UPDATED_TOTAL_DUE);
        assertThat(testOrderMaster.getOrderStatus()).isEqualTo(UPDATED_ORDER_STATUS);
        assertThat(testOrderMaster.getCustomerId()).isEqualTo(UPDATED_CUSTOMER_ID);
        assertThat(testOrderMaster.getCustomerName()).isEqualTo(UPDATED_CUSTOMER_NAME);
        assertThat(testOrderMaster.getPincode()).isEqualTo(UPDATED_PINCODE);
        assertThat(testOrderMaster.getHouseNoOrBuildingName()).isEqualTo(UPDATED_HOUSE_NO_OR_BUILDING_NAME);
        assertThat(testOrderMaster.getRoadNameAreaOrStreet()).isEqualTo(UPDATED_ROAD_NAME_AREA_OR_STREET);
        assertThat(testOrderMaster.getCity()).isEqualTo(UPDATED_CITY);
        assertThat(testOrderMaster.getState()).isEqualTo(UPDATED_STATE);
        assertThat(testOrderMaster.getLandmark()).isEqualTo(UPDATED_LANDMARK);
        assertThat(testOrderMaster.getPhone()).isEqualTo(UPDATED_PHONE);
        assertThat(testOrderMaster.getAlternatePhone()).isEqualTo(UPDATED_ALTERNATE_PHONE);
        assertThat(testOrderMaster.getAddressType()).isEqualTo(UPDATED_ADDRESS_TYPE);
        assertThat(testOrderMaster.getOrderFromCustomer()).isEqualTo(UPDATED_ORDER_FROM_CUSTOMER);
        assertThat(testOrderMaster.getCustomerOrder()).isEqualTo(UPDATED_CUSTOMER_ORDER);
        assertThat(testOrderMaster.getOrderPlaceAt()).isEqualTo(UPDATED_ORDER_PLACE_AT);
        assertThat(testOrderMaster.getOrderAcceptedAt()).isEqualTo(UPDATED_ORDER_ACCEPTED_AT);
        assertThat(testOrderMaster.getAllergyNote()).isEqualTo(UPDATED_ALLERGY_NOTE);
        assertThat(testOrderMaster.getPreOrderDate()).isEqualTo(UPDATED_PRE_ORDER_DATE);
        assertThat(testOrderMaster.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testOrderMaster.getPaymentRef()).isEqualTo(UPDATED_PAYMENT_REF);
        assertThat(testOrderMaster.getPaymentStatus()).isEqualTo(UPDATED_PAYMENT_STATUS);
        assertThat(testOrderMaster.getZoneId()).isEqualTo(UPDATED_ZONE_ID);
        assertThat(testOrderMaster.getLoyaltyPoint()).isEqualTo(UPDATED_LOYALTY_POINT);
        assertThat(testOrderMaster.getRefundedAmount()).isEqualTo(UPDATED_REFUNDED_AMOUNT);
        assertThat(testOrderMaster.getCancellationRef()).isEqualTo(UPDATED_CANCELLATION_REF);
        assertThat(testOrderMaster.getNextTaskId()).isEqualTo(UPDATED_NEXT_TASK_ID);

        // Validate the OrderMaster in Elasticsearch
        verify(mockOrderMasterSearchRepository, times(1)).save(testOrderMaster);
    }

    @Test
    @Transactional
    public void updateNonExistingOrderMaster() throws Exception {
        int databaseSizeBeforeUpdate = orderMasterRepository.findAll().size();

        // Create the OrderMaster
        OrderMasterDTO orderMasterDTO = orderMasterMapper.toDto(orderMaster);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOrderMasterMockMvc.perform(put("/api/order-masters")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(orderMasterDTO)))
            .andExpect(status().isBadRequest());

        // Validate the OrderMaster in the database
        List<OrderMaster> orderMasterList = orderMasterRepository.findAll();
        assertThat(orderMasterList).hasSize(databaseSizeBeforeUpdate);

        // Validate the OrderMaster in Elasticsearch
        verify(mockOrderMasterSearchRepository, times(0)).save(orderMaster);
    }

    @Test
    @Transactional
    public void deleteOrderMaster() throws Exception {
        // Initialize the database
        orderMasterRepository.saveAndFlush(orderMaster);

        int databaseSizeBeforeDelete = orderMasterRepository.findAll().size();

        // Delete the orderMaster
        restOrderMasterMockMvc.perform(delete("/api/order-masters/{id}", orderMaster.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<OrderMaster> orderMasterList = orderMasterRepository.findAll();
        assertThat(orderMasterList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the OrderMaster in Elasticsearch
        verify(mockOrderMasterSearchRepository, times(1)).deleteById(orderMaster.getId());
    }

    @Test
    @Transactional
    public void searchOrderMaster() throws Exception {
        // Initialize the database
        orderMasterRepository.saveAndFlush(orderMaster);
        when(mockOrderMasterSearchRepository.search(queryStringQuery("id:" + orderMaster.getId()), PageRequest.of(0, 20)))
            .thenReturn(new PageImpl<>(Collections.singletonList(orderMaster), PageRequest.of(0, 1), 1));
        // Search the orderMaster
        restOrderMasterMockMvc.perform(get("/api/_search/order-masters?query=id:" + orderMaster.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(orderMaster.getId().intValue())))
            .andExpect(jsonPath("$.[*].storeIdpcode").value(hasItem(DEFAULT_STORE_IDPCODE)))
            .andExpect(jsonPath("$.[*].storeName").value(hasItem(DEFAULT_STORE_NAME)))
            .andExpect(jsonPath("$.[*].storePhone").value(hasItem(DEFAULT_STORE_PHONE.intValue())))
            .andExpect(jsonPath("$.[*].storelocationName").value(hasItem(DEFAULT_STORELOCATION_NAME)))
            .andExpect(jsonPath("$.[*].methodOfOrder").value(hasItem(DEFAULT_METHOD_OF_ORDER)))
            .andExpect(jsonPath("$.[*].expectedDelivery").value(hasItem(DEFAULT_EXPECTED_DELIVERY.toString())))
            .andExpect(jsonPath("$.[*].orderNumber").value(hasItem(DEFAULT_ORDER_NUMBER)))
            .andExpect(jsonPath("$.[*].notes").value(hasItem(DEFAULT_NOTES)))
            .andExpect(jsonPath("$.[*].subTotal").value(hasItem(DEFAULT_SUB_TOTAL.doubleValue())))
            .andExpect(jsonPath("$.[*].orderDiscountAmount").value(hasItem(DEFAULT_ORDER_DISCOUNT_AMOUNT.doubleValue())))
            .andExpect(jsonPath("$.[*].deliveryCharge").value(hasItem(DEFAULT_DELIVERY_CHARGE.doubleValue())))
            .andExpect(jsonPath("$.[*].serviceCharge").value(hasItem(DEFAULT_SERVICE_CHARGE.doubleValue())))
            .andExpect(jsonPath("$.[*].totalDue").value(hasItem(DEFAULT_TOTAL_DUE.doubleValue())))
            .andExpect(jsonPath("$.[*].orderStatus").value(hasItem(DEFAULT_ORDER_STATUS)))
            .andExpect(jsonPath("$.[*].customerId").value(hasItem(DEFAULT_CUSTOMER_ID)))
            .andExpect(jsonPath("$.[*].customerName").value(hasItem(DEFAULT_CUSTOMER_NAME)))
            .andExpect(jsonPath("$.[*].pincode").value(hasItem(DEFAULT_PINCODE)))
            .andExpect(jsonPath("$.[*].houseNoOrBuildingName").value(hasItem(DEFAULT_HOUSE_NO_OR_BUILDING_NAME)))
            .andExpect(jsonPath("$.[*].roadNameAreaOrStreet").value(hasItem(DEFAULT_ROAD_NAME_AREA_OR_STREET)))
            .andExpect(jsonPath("$.[*].city").value(hasItem(DEFAULT_CITY)))
            .andExpect(jsonPath("$.[*].state").value(hasItem(DEFAULT_STATE)))
            .andExpect(jsonPath("$.[*].landmark").value(hasItem(DEFAULT_LANDMARK)))
            .andExpect(jsonPath("$.[*].phone").value(hasItem(DEFAULT_PHONE.intValue())))
            .andExpect(jsonPath("$.[*].alternatePhone").value(hasItem(DEFAULT_ALTERNATE_PHONE.intValue())))
            .andExpect(jsonPath("$.[*].addressType").value(hasItem(DEFAULT_ADDRESS_TYPE)))
            .andExpect(jsonPath("$.[*].orderFromCustomer").value(hasItem(DEFAULT_ORDER_FROM_CUSTOMER.intValue())))
            .andExpect(jsonPath("$.[*].customerOrder").value(hasItem(DEFAULT_CUSTOMER_ORDER.intValue())))
            .andExpect(jsonPath("$.[*].orderPlaceAt").value(hasItem(DEFAULT_ORDER_PLACE_AT.toString())))
            .andExpect(jsonPath("$.[*].orderAcceptedAt").value(hasItem(DEFAULT_ORDER_ACCEPTED_AT.toString())))
            .andExpect(jsonPath("$.[*].allergyNote").value(hasItem(DEFAULT_ALLERGY_NOTE)))
            .andExpect(jsonPath("$.[*].preOrderDate").value(hasItem(DEFAULT_PRE_ORDER_DATE.toString())))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL)))
            .andExpect(jsonPath("$.[*].paymentRef").value(hasItem(DEFAULT_PAYMENT_REF)))
            .andExpect(jsonPath("$.[*].paymentStatus").value(hasItem(DEFAULT_PAYMENT_STATUS)))
            .andExpect(jsonPath("$.[*].zoneId").value(hasItem(DEFAULT_ZONE_ID)))
            .andExpect(jsonPath("$.[*].loyaltyPoint").value(hasItem(DEFAULT_LOYALTY_POINT.intValue())))
            .andExpect(jsonPath("$.[*].refundedAmount").value(hasItem(DEFAULT_REFUNDED_AMOUNT.doubleValue())))
            .andExpect(jsonPath("$.[*].cancellationRef").value(hasItem(DEFAULT_CANCELLATION_REF.intValue())))
            .andExpect(jsonPath("$.[*].nextTaskId").value(hasItem(DEFAULT_NEXT_TASK_ID)));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(OrderMaster.class);
        OrderMaster orderMaster1 = new OrderMaster();
        orderMaster1.setId(1L);
        OrderMaster orderMaster2 = new OrderMaster();
        orderMaster2.setId(orderMaster1.getId());
        assertThat(orderMaster1).isEqualTo(orderMaster2);
        orderMaster2.setId(2L);
        assertThat(orderMaster1).isNotEqualTo(orderMaster2);
        orderMaster1.setId(null);
        assertThat(orderMaster1).isNotEqualTo(orderMaster2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(OrderMasterDTO.class);
        OrderMasterDTO orderMasterDTO1 = new OrderMasterDTO();
        orderMasterDTO1.setId(1L);
        OrderMasterDTO orderMasterDTO2 = new OrderMasterDTO();
        assertThat(orderMasterDTO1).isNotEqualTo(orderMasterDTO2);
        orderMasterDTO2.setId(orderMasterDTO1.getId());
        assertThat(orderMasterDTO1).isEqualTo(orderMasterDTO2);
        orderMasterDTO2.setId(2L);
        assertThat(orderMasterDTO1).isNotEqualTo(orderMasterDTO2);
        orderMasterDTO1.setId(null);
        assertThat(orderMasterDTO1).isNotEqualTo(orderMasterDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(orderMasterMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(orderMasterMapper.fromId(null)).isNull();
    }
}
