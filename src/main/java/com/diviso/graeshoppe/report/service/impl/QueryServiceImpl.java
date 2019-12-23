package com.diviso.graeshoppe.report.service.impl;

import static org.elasticsearch.index.query.QueryBuilders.termQuery;

import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;

import java.util.Map;
import javax.sql.DataSource;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.data.elasticsearch.core.query.StringQuery;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import com.diviso.graeshoppe.report.client.customer.model.Customer;
import com.diviso.graeshoppe.report.client.order.api.ReportQueryResourceApi;
import com.diviso.graeshoppe.report.client.payment.api.PaymentResourceApi;
import com.diviso.graeshoppe.report.client.payment.model.PaymentDTO;
import com.diviso.graeshoppe.report.client.product.model.ComboLineItem;
import com.diviso.graeshoppe.report.client.product.model.Product;
import com.diviso.graeshoppe.report.client.store.model.Store;
import com.diviso.graeshoppe.report.domain.AuxItem;
import com.diviso.graeshoppe.report.domain.ComboItem;
import com.diviso.graeshoppe.report.domain.OrderAggregator;
import com.diviso.graeshoppe.report.domain.OrderLine;
import com.diviso.graeshoppe.report.domain.OrderMaster;
import com.diviso.graeshoppe.report.service.dto.OrderMasterDTO;
import com.diviso.graeshoppe.report.domain.ReportSummary;
import com.diviso.graeshoppe.report.repository.OrderMasterRepository;
import com.diviso.graeshoppe.report.service.QueryService;
import com.github.vanroy.springdata.jest.JestElasticsearchTemplate;

import io.searchbox.client.JestClient;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import com.diviso.graeshoppe.report.repository.OrderMasterRepository;
import com.diviso.graeshoppe.report.service.OrderMasterService;
@Service
@Transactional
public class QueryServiceImpl implements QueryService {
	@Autowired
	DataSource dataSource;
	
	@Autowired
	OrderMasterService orderMasterService;

	@Autowired
	OrderMasterRepository orderMasterRepository;
	private static List<ReportSummary> reportSummaryList = new ArrayList<ReportSummary>();
	
	/*private final JestClient jestClient;
	private final JestElasticsearchTemplate elasticsearchTemplate;

	int i = 0;
	Long count = 0L;*/
	private final Logger log = LoggerFactory.getLogger(QueryServiceImpl.class);

	

	@Autowired
	ReportQueryResourceApi ReportQueryResourceApi;
	@Autowired
	PaymentResourceApi paymentResourceApi;
	
	
	

	@Override
	public ReportSummary createReportSummary(LocalDate date,String storeId) {

		
		 Instant dateBegin = Instant.parse(date.toString() + "T00:00:00Z");
		  Instant dateEnd = Instant.parse(date.toString() + "T23:59:59Z");
		 

		/*Instant dateBegin = Instant.parse("2019-08-27T00:00:00Z");
		Instant dateEnd = Instant.parse("2019-08-27T23:59:59Z");*/
		ReportSummary reportSummary = new ReportSummary();
		
		reportSummary.setDate(date);
		reportSummary.setStoreId(storeId);
		reportSummary.setTypeAllCount(
				ReportQueryResourceApi.countAllOrdersByDateAndStoreIdUsingGET(dateBegin, dateEnd, storeId).getBody());

		List<String> allReference = ReportQueryResourceApi
				.findAllPaymentReferenceByDateAndStoreIdUsingGET(dateBegin, dateEnd, storeId).getBody();

		reportSummary.setTypeAllTotal(calculateTotal(convertStringToLong(getPaymentReference(allReference))));

		List<Long> paymentIdByCard = findByPaymentType("card", getPaymentReference(allReference));

		reportSummary.setTypeCardTotal(calculateTotal(paymentIdByCard));
		reportSummary.setTypeCardCount(paymentIdByCard.size());

		List<Long> paymentIdByCash = findByPaymentType("cash",getPaymentReference(allReference));
		reportSummary.setTypeCashCount(paymentIdByCash.size());
		reportSummary.setTypeCashTotal(calculateTotal(paymentIdByCash));
		reportSummary.setTypeCardCount(paymentIdByCash.size());
		reportSummary.setTypeDeliveryCount(ReportQueryResourceApi
				.countOrdersByStoreIdAndDeliveryTypeUsingGET(dateBegin, dateEnd, storeId, "delivery").getBody());

		List<String> deleveryReference = ReportQueryResourceApi
				.findAllPaymentRefByDeliveryTypeUsingGET(dateBegin, dateEnd, "delivery", storeId).getBody();
		reportSummary.setTypeDeliveryTotal(calculateTotal(convertStringToLong(getPaymentReference(deleveryReference))));
		reportSummary.setTypeCollectionCount(ReportQueryResourceApi
				.countOrdersByStoreIdAndDeliveryTypeUsingGET(dateBegin, dateEnd, storeId, "collection").getBody());
		List<String> collectionReference = ReportQueryResourceApi
				.findAllPaymentRefByDeliveryTypeUsingGET(dateBegin, dateEnd, "collection", storeId).getBody();
		reportSummary
				.setTypeCollectionTotal(calculateTotal(convertStringToLong(getPaymentReference(collectionReference))));
		return reportSummary;
	}

	Double calculateTotal(List<Long> paymentRefList) {
		Double amount = 0.0;
		for (Long payRef : paymentRefList) {
			PaymentDTO payment = paymentResourceApi.getPaymentUsingGET(payRef).getBody();

			amount = amount + payment.getAmount();
		}

		return amount;
	}

	List<String> getPaymentReference(List<String> payRef) {
		List<String> result = new ArrayList<String>();
		for (String reference : payRef) {

			if (reference != null) {
				result.add(reference);
			}

		}
		return result;
	}

	List<Long> convertStringToLong(List<String> list) {
		List<Long> longList = new ArrayList<>();
		for (String ref : list) {
			longList.add(Long.parseLong(ref));
		}

		return longList;
	}

	List<Long> findByPaymentType(String paymentType, List<String> paymentRefList) {
		List<Long> paymentIdList = new ArrayList<Long>();
		PaymentDTO payment = null;
		for (String payRef : paymentRefList) {
			payment = paymentResourceApi.getPaymentUsingGET(Long.parseLong(payRef)).getBody();
			if (payment.getPaymentType().equalsIgnoreCase(paymentType)) {
				paymentIdList.add(payment.getId());
			}

		}

		return paymentIdList;
	}

	/*@Override
	public OrderMaster findOrderMasterByOrderNumber(@PathVariable String orderNumber) {
	  
		StringQuery searchQuery = new StringQuery(termQuery("orderNumber.keyword", orderNumber).toString());
		return elasticsearchOperations.queryForObject(searchQuery, OrderMaster.class);
	  
	  }*/
	
	/*@Override
	public List<OrderLine> findOrderLineByOrderMaster(@PathVariable Long orderMasterId) {
		log.info("orderMaster Id is " + orderMasterId);
		SearchQuery searchQuery = new NativeSearchQueryBuilder().withQuery(termQuery("orderMaster.id", orderMasterId))
				.withIndices("reportorderline").build();
		return elasticsearchOperations.queryForList(searchQuery, OrderLine.class);
	  
	  }
	 
	@Override
	public List<ComboItem> findComboItemByOrderLine(@PathVariable Long orderLineId) {
		log.info("orderLine Id is " + orderLineId);
		SearchQuery searchQuery = new NativeSearchQueryBuilder().withQuery(termQuery("orderLine.id", orderLineId))
		.build();
		return elasticsearchOperations.queryForList(searchQuery, ComboItem.class);
	  
	  }
	*/
/*	@Override
	public List<AuxItem> findAuxItemByOrderLine(@PathVariable Long orderLineId) {
		log.info("orderLine Id is " + orderLineId);
		SearchQuery searchQuery = new NativeSearchQueryBuilder().withQuery(termQuery("orderLine.id", orderLineId))
		.build();
		return elasticsearchOperations.queryForList(searchQuery, AuxItem.class);
	  }
	
	@Override
	public OrderAggregator getOrderAggregator(String orderNumber) {

		OrderAggregator orderAggregator= new OrderAggregator();
		OrderMaster master=findOrderMasterByOrderNumber(orderNumber);
		orderAggregator.setOrderMaster(master);
		Set<OrderLine> orderLines=new HashSet<OrderLine>(findOrderLineByOrderMaster(master.getId()));
		orderAggregator.getOrderMaster().setOrderLines(orderLines);
		
		System.out.println("+++++++++++++++++++++++++++++++++++++++"+orderAggregator.getOrderMaster());;
		
		
		for(OrderLine orderLine : orderAggregator.getOrderMaster().getOrderLines()) {
			
			List<ComboItem> combos=findComboItemByOrderLine(orderLine.getId());
			List<AuxItem> auxes=findAuxItemByOrderLine(orderLine.getId());
			orderLine.setComboItems(new HashSet<>(combos));
			orderLine.setAuxItems(new HashSet<>(auxes));
			
		}
		return orderAggregator;
	}*/

	@Override
	public byte[] getReportAsPdf(String orderNumber) throws JRException {
		JasperReport jr = JasperCompileManager.compileReport("report.jrxml");

		// Preparing parameters
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("order_master_id", orderNumber);
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JasperPrint jp = JasperFillManager.fillReport(jr, parameters, conn);

		return JasperExportManager.exportReportToPdf(jp);

	}

	@Override
	public byte[] getReportSummaryAsPdf(LocalDate date, String storeName) throws JRException {
		// JasperReport jr = JasperCompileManager.compileReport("ordersummary.jrxml");

		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("date", date);
		parameters.put("store_name", storeName);
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		JasperPrint jp = JasperFillManager.fillReport("src/main/resources/report/reportsummary1.jasper", parameters,
				conn);
		return JasperExportManager.exportReportToPdf(jp);

	}

	public static List<ReportSummary> getReportSummaryList() {
		return reportSummaryList;
	}

	public static void setReportSummaryList(List<ReportSummary> reportSummaryList) {
		QueryServiceImpl.reportSummaryList = reportSummaryList;
	}
	
	
	@Override
	public byte[] getReportWithAuxAndComboAsPdf(String orderNumber) throws JRException {

		OrderMasterDTO orderMasterDto = orderMasterService.findOrderMasterByOrderNumber(orderNumber);

		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("order_number", orderNumber);
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JasperPrint jp = null;

		if (orderMasterDto.getMethodOfOrder().equalsIgnoreCase("delivery")) {
			jp = JasperFillManager.fillReport("src/main/resources/report/reportdelivery.jasper", parameters, conn);
		} else if (orderMasterDto.getMethodOfOrder().equalsIgnoreCase("collection")) {
			jp = JasperFillManager.fillReport("src/main/resources/report/reportcollection.jasper", parameters, conn);

		}
	
		return JasperExportManager.exportReportToPdf(jp);

	}

	@Override
	public ReportSummary createReportSummary(String expectedDelivery, String storeName) {
		Instant dateBegin = Instant.parse(expectedDelivery.toString() + "T00:00:00Z");
		Instant dateEnd = Instant.parse(expectedDelivery.toString() + "T23:59:59Z");
		ReportSummary reportSummary = new ReportSummary();
		reportSummary.setDate(LocalDate.parse(expectedDelivery));
		reportSummary.setStoreId(storeName);

		reportSummary.setTypeAllCount(
				orderMasterRepository.countByExpectedDeliveryBetweenAndStoreName(dateBegin, dateEnd, storeName));
		reportSummary.setTypeAllTotal(orderMasterRepository.sumOfTotalDue(dateBegin, dateEnd, storeName));
		reportSummary.setTypeDeliveryCount(
				orderMasterRepository.countByMethodOfOrderAndStoreName(dateBegin, dateEnd, storeName, "delivery"));
		reportSummary.setTypeCollectionCount(
				orderMasterRepository.countByMethodOfOrderAndStoreName(dateBegin, dateEnd, storeName, "collection"));
		reportSummary.setTypeDeliveryTotal(
				orderMasterRepository.sumOfTotalByOrderType(dateBegin, dateEnd, storeName, "delivery"));
		reportSummary.setTypeCollectionTotal(
				orderMasterRepository.sumOfTotalByOrderType(dateBegin, dateEnd, storeName, "collection"));
		reportSummary.setTypeCardCount(
				orderMasterRepository.countByOrderStatusAndStoreName(dateBegin, dateEnd, storeName, "order paid"));
		reportSummary.setTypeCashCount(
				orderMasterRepository.countByOrderStatusAndStoreName(dateBegin, dateEnd, storeName, "order not paid"));
		reportSummary.setTypeCardTotal(
				orderMasterRepository.sumOftotalByOrderStatus(dateBegin, dateEnd, storeName, "order paid"));
		reportSummary.setTypeCashTotal(
				orderMasterRepository.sumOftotalByOrderStatus(dateBegin, dateEnd, storeName, "order not paid"));

		return reportSummary;
	}

	@Override
	public byte[] getSaleReportAsPdf(String storeidpcode) throws JRException {
		JasperReport jr = JasperCompileManager.compileReport("src/main/resources/report/sale.jrxml");
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("store_i_d_pcode", storeidpcode);
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		JasperPrint jp = JasperFillManager.fillReport(jr, parameters, conn);
		return JasperExportManager.exportReportToPdf(jp);
	}

	


	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
