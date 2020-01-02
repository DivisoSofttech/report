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
	PaymentResourceApi paymentResourceApi;
	
	
	


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
	
	/***
	 * @author neeraja
	 */
	

	@Override
	public byte[] getReportSummaryAsPdf(LocalDate date, String storeId) throws JRException {
		JasperReport jr = JasperCompileManager.compileReport("src/main/resources/report/reportSummary.jrxml");

		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("date", date);
		parameters.put("store_name", storeId);
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

	/***
	 * @author neeraja
	 */
	public static List<ReportSummary> getReportSummaryList() {
		return reportSummaryList;
	}

	/***
	 * @author neeraja
	 */
	public static void setReportSummaryList(List<ReportSummary> reportSummaryList) {
		QueryServiceImpl.reportSummaryList = reportSummaryList;
	}
	
	/***
	 * @author neeraja
	 */
	@Override
	public byte[] getReportWithAuxAndComboAsPdf(String orderNumber) throws JRException {
		
		
		
		

		OrderMasterDTO orderMasterDto = orderMasterService.findOrderMasterByOrderNumber(orderNumber);
		
		JasperReport jr= null;
		
		
		  if (orderMasterDto.getMethodOfOrder().equalsIgnoreCase("delivery")) { jr =
		  JasperCompileManager.compileReport(
		  "src/main/resources/report/reportdeliveryv1.jrxml"); } else if
		  (orderMasterDto.getMethodOfOrder().equalsIgnoreCase("collection")) { jr =
		  JasperCompileManager.compileReport(
		  "src/main/resources/report/reportcollection.jrxml");
		  
		  }
		 
		
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("order_number", orderNumber);
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JasperPrint jp = JasperFillManager.fillReport(jr, parameters, conn);

		/*JasperPrint jp =null;
		if (orderMasterDto.getMethodOfOrder().equalsIgnoreCase("delivery")) {
			jp = JasperFillManager.fillReport("src/main/resources/report/reportdelivery.jasper", parameters, conn);
		} else if (orderMasterDto.getMethodOfOrder().equalsIgnoreCase("collection")) {
			jp = JasperFillManager.fillReport("src/main/resources/report/reportcollection.jasper", parameters, conn);

		}*/
	
		return JasperExportManager.exportReportToPdf(jp);

	}

	
	/***
	 * @author neeraja
	 */
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
				orderMasterRepository.countByPaymentStatusAndStoreName(dateBegin, dateEnd, storeName, "order paid"));
		reportSummary.setTypeCashCount(
				orderMasterRepository.countByPaymentStatusAndStoreName(dateBegin, dateEnd, storeName, "order not paid"));
		reportSummary.setTypeCardTotal(
				orderMasterRepository.sumOftotalByPaymentStatus(dateBegin, dateEnd, storeName, "order paid"));
		reportSummary.setTypeCashTotal(
				orderMasterRepository.sumOftotalByPaymentStatus(dateBegin, dateEnd, storeName, "order not paid"));

		return reportSummary;
	}

	
	/***
	 * @author neeraja
	 */
	
	@Override
	public byte[] getSaleReportAsPdf(String storeidpcode) throws JRException {
		
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("store_i_d_pcode", storeidpcode);
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		JasperPrint jp = JasperFillManager.fillReport("src/main/resources/report/sale.jasper", parameters, conn);
		return JasperExportManager.exportReportToPdf(jp);
	}

	@Override
	public byte[] getAllOrdersByMethodOfOrderAsPdf(LocalDate date, String storeId, String methodOfOrder) throws JRException {
		JasperReport jr = JasperCompileManager.compileReport("src/main/resources/report/orderbymethodoforder.jrxml");

		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("date", date);
		parameters.put("store_name", storeId);
		parameters.put("method_of_order", methodOfOrder);
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
	public byte[] getAllOrdersByPaymentStatusAsPdf(LocalDate date, String storeId, String paymentStatus) throws JRException {
		
		JasperReport jr = JasperCompileManager.compileReport("src/main/resources/report/ordersbypaymentstatus.jrxml");

		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("date", date);
		parameters.put("store_name", storeId);
		parameters.put("payment_status", paymentStatus);
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
	public byte[] getAllOrdersByDateAsPdf(LocalDate date)throws JRException {
	
		JasperReport jr = JasperCompileManager.compileReport("src/main/resources/report/datespecificorders.jrxml");

		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("date", date);
		
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
	public byte[] getAllOrdersByDateAndStoreNameAsPdf(LocalDate date, String storeId) throws JRException{
		
		JasperReport jr = JasperCompileManager.compileReport("src/main/resources/report/ordersbydateandstorename.jrxml");

		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("date", date);
		parameters.put("store_name", storeId);
		
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
	public byte[] getAllOrdersBetweenDatesAsPdf(LocalDate fromDate, LocalDate toDate) throws JRException{
		
		JasperReport jr = JasperCompileManager.compileReport("src/main/resources/report/ordersbetweendates.jrxml");

		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("from_date", fromDate);
		parameters.put("to_date", toDate);
		
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
	public byte[] getOrderSummaryBetweenDatesAsPdf(LocalDate fromDate, LocalDate toDate, String storeId) throws JRException {


		JasperReport jr = JasperCompileManager.compileReport("src/main/resources/report/ordersummarybetweendates.jrxml");

		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("from_date", fromDate);
		parameters.put("to_date", toDate);
		parameters.put("store_name", storeId);
		
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
	public byte[] getOrderSummaryByDateAndStoreNameAsPdf(LocalDate date, String storeId) throws JRException {
		JasperReport jr = JasperCompileManager.compileReport("src/main/resources/report/ordersummaryadmin.jrxml");

		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("date", date);
		parameters.put("store_name", storeId);
		
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
