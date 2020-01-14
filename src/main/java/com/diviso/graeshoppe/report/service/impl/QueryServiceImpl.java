package com.diviso.graeshoppe.report.service.impl;

import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;

import java.util.Map;
import javax.sql.DataSource;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.diviso.graeshoppe.report.client.payment.api.PaymentResourceApi;
import com.diviso.graeshoppe.report.client.payment.model.PaymentDTO;
import com.diviso.graeshoppe.report.service.dto.OrderMasterDTO;
import com.diviso.graeshoppe.report.domain.OrderMaster;
import com.diviso.graeshoppe.report.domain.ReportOrderModel;
import com.diviso.graeshoppe.report.domain.ReportSummary;
import com.diviso.graeshoppe.report.repository.OrderMasterRepository;
import com.diviso.graeshoppe.report.service.QueryService;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
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

	private static List<OrderMaster> orderMasterList = new ArrayList<OrderMaster>();


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
	public byte[] getReportSummaryAsPdf(LocalDate fromDate,LocalDate toDate, String storeId) throws JRException {
		JasperReport jr = JasperCompileManager.compileReport("src/main/resources/report/reportSummary.jrxml");

		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("from_date", fromDate);
		parameters.put("to_date", toDate);
		parameters.put("store_idpcode", storeId);
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
		parameters.put("store_idpcode", storeId);
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
		parameters.put("store_idpcode", storeId);
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
		parameters.put("store_idpcode", storeId);
		
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
		parameters.put("store_idpcode", storeId);
		
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

	@Override
	public byte[] getAllOrdersBetweenDatesAndStoreIdAsPdf(LocalDate fromDate, LocalDate toDate, String storeId )throws JRException {
		JasperReport jr = JasperCompileManager.compileReport("src/main/resources/report/ordersbetweendatesandstorename.jrxml");

		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("from_date", fromDate);
		parameters.put("to_date", toDate);
		parameters.put("store_idpcode", storeId);
		
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
	@Override
	public ReportSummary createReportSummary(String fromDate,String toDate, String storeName) {
		Instant dateBegin = Instant.parse(fromDate.toString() + "T00:00:00Z");
		Instant dateEnd = Instant.parse(toDate.toString() + "T23:59:59Z");
		ReportSummary reportSummary = new ReportSummary();
		reportSummary.setFromDate(LocalDate.parse(fromDate));
		reportSummary.setToDate(LocalDate.parse(toDate));
		reportSummary.setStoreId(storeName);

		reportSummary.setTypeAllCount(
				orderMasterRepository.countByOrderPlaceAtBetweenAndStoreName(dateBegin, dateEnd, storeName));
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
	public static List<OrderMaster> getOrderMasterList() {
		return orderMasterList;
	}

	/***
	 * @author neeraja
	 */
	public static void setOrderMasterList(List<OrderMaster> orderMasterList) {
		QueryServiceImpl.orderMasterList = orderMasterList;
	}
	

	@Override
	public Page<OrderMaster> getOrdersViewByMethodOfOrder(String storeIdpcode, String fromDate,String toDate, String methodOfOrder,Pageable pageable) {
		
		Instant dateBegin = Instant.parse(fromDate.toString() + "T00:00:00Z");
		Instant dateEnd = Instant.parse(toDate.toString() + "T23:59:59Z");
		return orderMasterRepository.findByOrderPlaceAtBetweenAndStoreIdpcodeAndMethodOfOrder(dateBegin, dateEnd,storeIdpcode, methodOfOrder, pageable);
		
		
	}

	@Override
	public Page<OrderMaster> getOrdersViewByPaymentStatus(String storeIdpcode, String fromDate, String toDate, String paymentStatus, Pageable pageable) {
		
		Instant dateBegin = Instant.parse(fromDate.toString() + "T00:00:00Z");
		Instant dateEnd = Instant.parse(toDate.toString() + "T23:59:59Z");
		return orderMasterRepository.findByOrderPlaceAtBetweenAndStoreIdpcodeAndPaymentStatus(dateBegin, dateEnd,storeIdpcode, paymentStatus, pageable);
		
		
	}

	@Override
	public Page<OrderMaster> getOrdersViewBetweenDates(String fromDate, String toDate, Pageable pageable) {
	
	
		Instant dateBegin = Instant.parse(fromDate.toString() + "T00:00:00Z");
		Instant dateEnd = Instant.parse(toDate.toString() + "T23:59:59Z");
		 return orderMasterRepository.findByOrderPlaceAtBetween(dateBegin, dateEnd, pageable);
		

		
	}

	@Override
	public Page<OrderMaster> getOrdersViewBetweenDatesAndStoreIdpcode(String fromDate, String toDate, String storeId, Pageable pageable) {
		
		Instant dateBegin = Instant.parse(fromDate.toString() + "T00:00:00Z");
		Instant dateEnd = Instant.parse(toDate.toString() + "T23:59:59Z");
		return orderMasterRepository.findByOrderPlaceAtBetweenAndStoreIdpcode(dateBegin, dateEnd,storeId, pageable);
		
		
	}

	@Override
	public Page<OrderMaster> getOrdersViewBetweenDatesAndPaymentStatus(String fromDate, String toDate,
			String paymentStatus, Pageable pageable) {
		System.out.println("service impl>>>>>>>>>"+fromDate+">>>>>>>>>>>>>>>>"+toDate+">>>>>>>>>>>"+paymentStatus);
		Instant dateBegin = Instant.parse(fromDate.toString() + "T00:00:00Z");
		Instant dateEnd = Instant.parse(toDate.toString() + "T23:59:59Z");
		return orderMasterRepository.findByOrderPlaceAtBetweenAndPaymentStatus(dateBegin, dateEnd,paymentStatus,  pageable);
		
	}
	
	@Override
	public Page<OrderMaster> getOrdersViewBetweenDatesAndMethodOfOrder(String fromDate, String toDate,
			String methodOfOrder, Pageable pageable) {
		

		Instant dateBegin = Instant.parse(fromDate.toString() + "T00:00:00Z");
		Instant dateEnd = Instant.parse(toDate.toString() + "T23:59:59Z");
		return orderMasterRepository.findByOrderPlaceAtBetweenAndMethodOfOrder(dateBegin, dateEnd,methodOfOrder,  pageable);
		
	}

	@Override
	public Page<OrderMaster> getOrdersViewBetweenDatesAndPaymentStatusAndMethodOfOrder(String fromDate, String toDate,
			String paymentStatus, String methodOfOrder, Pageable pageable) {
		
		Instant dateBegin = Instant.parse(fromDate.toString() + "T00:00:00Z");
		Instant dateEnd = Instant.parse(toDate.toString() + "T23:59:59Z");
		return orderMasterRepository.findByOrderPlaceAtBetweenAndPaymentStatusAndMethodOfOrder(dateBegin, dateEnd,paymentStatus, methodOfOrder,  pageable);
	}

	@Override
	public Page<OrderMaster> getOrdersViewBetweenDatesAndStoreIdpcodeAndPaymentStatusAndMethodOfOrder(String fromDate,
			String toDate, String storeIdpcode, String paymentStatus, String methodOfOrder, Pageable pageable) {
		
		
		Instant dateBegin = Instant.parse(fromDate.toString() + "T00:00:00Z");
		Instant dateEnd = Instant.parse(toDate.toString() + "T23:59:59Z");
		return orderMasterRepository.findByOrderPlaceAtBetweenAndStoreIdpcodeAndPaymentStatusAndMethodOfOrder(dateBegin, dateEnd, storeIdpcode,paymentStatus, methodOfOrder,  pageable);
	
	}

	@Override
	public byte[] getAllOrdersBetweenDatesByPaymentStatusAsPdf(LocalDate fromDate, LocalDate toDate,
			String paymentStatus) throws JRException {
		

		JasperReport jr = JasperCompileManager.compileReport("src/main/resources/report/ordersByDateAndPaymentStatusOnly.jrxml");

		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("from_date", fromDate);
		parameters.put("to_date", toDate);
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
	public byte[] getAllOrdersBetweenDatesByMethodOfOrderAsPdf(LocalDate fromDate, LocalDate toDate,
			String methodOfOrder) throws JRException {
		

		JasperReport jr = JasperCompileManager.compileReport("src/main/resources/report/ordersByDateAndMethodOfOrderOnly.jrxml");

		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("from_date", fromDate);
		parameters.put("to_date", toDate);
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
	public byte[] getAllOrdersBetweenDatesByPaymentStatusAndMethodOfOrderAsPdf(LocalDate fromDate, LocalDate toDate,
			String paymentStatus, String methodOfOrder) throws JRException{
		
		JasperReport jr = JasperCompileManager.compileReport("src/main/resources/report/ordersByDatePaymentAndMethod.jrxml");

		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("from_date", fromDate);
		parameters.put("to_date", toDate);
		parameters.put("payment_status", paymentStatus);
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
	public byte[] getAllOrdersBetweenDatesByStoreIdAndPaymentStatusAndMethodOfOrderAsPdf(LocalDate fromDate,
			LocalDate toDate, String storeId, String paymentStatus, String methodOfOrder) throws JRException {
		JasperReport jr = JasperCompileManager.compileReport("src/main/resources/report/ordersByDatePaymentAndMethod.jrxml");

		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("from_date", fromDate);
		parameters.put("to_date", toDate);
		parameters.put("store_id", storeId);
		parameters.put("payment_status", paymentStatus);
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
	public ReportSummary createReportSummaryBetweenTwoDates(String fromDate, String toDate) {
	
		Instant dateBegin = Instant.parse(fromDate.toString() + "T00:00:00Z");
		Instant dateEnd = Instant.parse(toDate.toString() + "T23:59:59Z");
		ReportSummary reportSummary = new ReportSummary();
		reportSummary.setFromDate(LocalDate.parse(fromDate));
		reportSummary.setToDate(LocalDate.parse(toDate));

		reportSummary.setTypeAllCount(
				orderMasterRepository.countByOrderPlaceAtBetween(dateBegin, dateEnd));
		reportSummary.setTypeAllTotal(orderMasterRepository.sumOfTotalDue(dateBegin, dateEnd));
		reportSummary.setTypeDeliveryCount(
				orderMasterRepository.countByMethodOfOrder(dateBegin, dateEnd, "delivery"));
		reportSummary.setTypeCollectionCount(
				orderMasterRepository.countByMethodOfOrder(dateBegin, dateEnd, "collection"));
		reportSummary.setTypeDeliveryTotal(
				orderMasterRepository.sumOfTotalByOrderType(dateBegin, dateEnd, "delivery"));
		reportSummary.setTypeCollectionTotal(
				orderMasterRepository.sumOfTotalByOrderType(dateBegin, dateEnd, "collection"));
		reportSummary.setTypeCardCount(
				orderMasterRepository.countByPaymentStatus(dateBegin, dateEnd, "order paid"));
		reportSummary.setTypeCashCount(
				orderMasterRepository.countByPaymentStatus(dateBegin, dateEnd, "order not paid"));
		reportSummary.setTypeCardTotal(
				orderMasterRepository.sumOftotalByPaymentStatus(dateBegin, dateEnd, "order paid"));
		reportSummary.setTypeCashTotal(
				orderMasterRepository.sumOftotalByPaymentStatus(dateBegin, dateEnd, "order not paid"));

		return reportSummary;
		
		
	}

	/*
	 * @Override public List<OrderMaster> getOrdersViewByDateAndStoreIdpcode(String
	 * fromDate,String toDate, String storeId) {
	 * 
	 * Instant dateBegin = Instant.parse(fromDate.toString() + "T00:00:00Z");
	 * Instant dateEnd = Instant.parse(toDate.toString() + "T23:59:59Z");
	 * setOrderMasterList(orderMasterRepository.
	 * findByOrderPlaceAtBetweenAndStoreIdpcode(dateBegin, dateEnd,storeId));
	 * 
	 * return getOrderMasterList();
	 * 
	 * }
	 */
	
	
}
