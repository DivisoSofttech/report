package com.diviso.graeshoppe.report.service.impl;

import static org.elasticsearch.index.query.QueryBuilders.termQuery;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.data.elasticsearch.core.query.StringQuery;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.diviso.graeshoppe.report.client.customer.model.Customer;
import com.diviso.graeshoppe.report.client.product.model.ComboLineItem;
import com.diviso.graeshoppe.report.client.product.model.Product;
import com.diviso.graeshoppe.report.client.store.model.Store;
import com.diviso.graeshoppe.report.domain.ReportSummary;
import com.diviso.graeshoppe.report.repository.OrderMasterRepository;
import com.diviso.graeshoppe.report.service.OrderMasterService;
import com.diviso.graeshoppe.report.service.QueryService;
import com.diviso.graeshoppe.report.service.ReportService;
import com.diviso.graeshoppe.report.service.dto.OrderMasterDTO;
import com.github.vanroy.springdata.jest.JestElasticsearchTemplate;

import io.searchbox.client.JestClient;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

@Service
@Transactional

public class ReportServiceImpl implements ReportService {

	private final JestClient jestClient;
	private final JestElasticsearchTemplate elasticsearchTemplate;

	@Autowired
	ElasticsearchOperations elasticsearchOperations;
	@Autowired
	DataSource dataSource;

	@Autowired
	QueryService queryService;

	@Autowired
	OrderMasterService orderMasterService;
	
	@Autowired
	OrderMasterRepository orderMasterRepository;

	private static List<ReportSummary> reportSummaryList = new ArrayList<ReportSummary>();

	public ReportServiceImpl(JestClient jestClient, JestElasticsearchTemplate elasticsearchTemplate) {
		// TODO Auto-generated constructor stub
		this.jestClient = jestClient;
		this.elasticsearchTemplate = elasticsearchTemplate;
	}

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
	public byte[] getReportSummaryAsPdf(LocalDate date, String storeId) throws JRException {
		//JasperReport jr = JasperCompileManager.compileReport("ordersummary.jrxml");

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
	
		JasperPrint jp = JasperFillManager.fillReport("src/main/resources/report/reportSummary.jasper", parameters, conn);
		return JasperExportManager.exportReportToPdf(jp);

	}

	public static List<ReportSummary> getReportSummaryList() {
		return reportSummaryList;
	}

	public static void setReportSummaryList(List<ReportSummary> reportSummaryList) {
		ReportServiceImpl.reportSummaryList = reportSummaryList;
	}

	@Override
	public Store findStoreByStoreId(String storeId) {
		StringQuery stringQuery = new StringQuery(termQuery("regNo", storeId).toString());
		return elasticsearchOperations.queryForObject(stringQuery, Store.class);
	}

	@Override
	public Customer findCustomerByReference(String reference) {
		StringQuery stringQuery = new StringQuery(termQuery("reference", reference).toString());
		return elasticsearchOperations.queryForObject(stringQuery, Customer.class);
	}
	
	@Override
	public Product findProductByProductId(Long productId) {
		StringQuery stringQuery = new StringQuery(termQuery("id", productId).toString());
		return elasticsearchOperations.queryForObject(stringQuery, Product.class);
	}

	@Override
	public List<ComboLineItem> findCombosByProductId(Long id) {
		SearchQuery searchQuery = new NativeSearchQueryBuilder().withQuery(termQuery("product.id", id)).build();
		return elasticsearchOperations.queryForList(searchQuery, ComboLineItem.class);
	}

	@Override
	public byte[] getReportWithAuxAndComboAsPdf(String orderNumber) throws JRException {

		
	  JasperReport jr = null; 
	  OrderMasterDTO orderMasterDto =orderMasterService.findOrderMasterByOrderNumber(orderNumber);
	 
	  // Preparing parameters
	  if(orderMasterDto.getMethodOfOrder().equalsIgnoreCase("delivery"))
	  {
		  jr=JasperCompileManager.compileReport("src/main/resources/report/reportdelivery.jrxml"); 
	  } 
	  else if(orderMasterDto.getMethodOfOrder().equalsIgnoreCase("collection")) {
		   
		  jr=JasperCompileManager.compileReport("src/main/resources/report/reportcollection.jrxml"); 
	  }
		 
		//JasperReport jr = JasperCompileManager.compileReport("src/main/resources/report/reportcomboaux.jrxml");
		
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("order_number", orderNumber);
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JasperPrint jp=JasperFillManager.fillReport(jr, parameters, conn);
		
		/*OrderMasterDTO orderMasterDto =orderMasterService.findOrderMasterByOrderNumber(orderNumber);
		if(orderMasterDto.getMethodOfOrder().equalsIgnoreCase("delivery"))
		  {
			  jp = JasperFillManager.fillReport(jr, parameters, conn);
		  } 
		  else if(orderMasterDto.getMethodOfOrder().equalsIgnoreCase("collection")) {
			  jp = JasperFillManager.fillReport(jr, parameters, conn); 
			  
		  }*/
	
		return JasperExportManager.exportReportToPdf(jp);

	}

	

	@Override
	public ReportSummary createReportSummary(String expectedDelivery, String storeName) {
		Instant dateBegin = Instant.parse(expectedDelivery.toString() + "T00:00:00Z"); 
		Instant dateEnd = Instant.parse(expectedDelivery.toString() + "T23:59:59Z");
		ReportSummary reportSummary = new ReportSummary();
	    reportSummary.setDate(LocalDate.parse(expectedDelivery)); 
	    reportSummary.setStoreId(storeName);
		
		 reportSummary.setTypeAllCount(orderMasterRepository.countByExpectedDeliveryBetweenAndStoreName(dateBegin,dateEnd,storeName));
		reportSummary.setTypeAllTotal(orderMasterRepository.sumOfTotalDue(dateBegin,dateEnd,storeName));
		reportSummary.setTypeDeliveryCount(orderMasterRepository.countByMethodOfOrderAndStoreName(dateBegin,dateEnd,storeName,"delivery"));
		reportSummary.setTypeCollectionCount(orderMasterRepository.countByMethodOfOrderAndStoreName(dateBegin,dateEnd,storeName,"collection"));
		reportSummary.setTypeDeliveryTotal(orderMasterRepository.sumOfTotalByOrderType(dateBegin,dateEnd,storeName,"delivery"));
		reportSummary.setTypeCollectionTotal(orderMasterRepository.sumOfTotalByOrderType(dateBegin,dateEnd,storeName,"collection"));
		reportSummary.setTypeCardCount(orderMasterRepository.countByOrderStatusAndStoreName(dateBegin,dateEnd,storeName,"order paid"));
		reportSummary.setTypeCashCount(orderMasterRepository.countByOrderStatusAndStoreName(dateBegin,dateEnd,storeName,"order not paid"));
		reportSummary.setTypeCardTotal(orderMasterRepository.sumOftotalByOrderStatus(dateBegin,dateEnd,storeName,"order paid"));
		reportSummary.setTypeCashTotal(orderMasterRepository.sumOftotalByOrderStatus(dateBegin,dateEnd,storeName,"order not paid"));
		
		
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


