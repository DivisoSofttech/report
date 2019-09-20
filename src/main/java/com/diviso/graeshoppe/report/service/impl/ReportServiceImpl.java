package com.diviso.graeshoppe.report.service.impl;

import static org.elasticsearch.index.query.QueryBuilders.termQuery;

import java.sql.Connection;
import java.sql.SQLException;
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

import com.diviso.graeshoppe.report.client.order.model.Reportsummary;
import com.diviso.graeshoppe.report.client.product.model.ComboLineItem;
import com.diviso.graeshoppe.report.client.product.model.Product;
import com.diviso.graeshoppe.report.client.store.model.Store;
import com.diviso.graeshoppe.report.domain.ReportSummary;

import com.diviso.graeshoppe.report.service.QueryService;
import com.diviso.graeshoppe.report.service.ReportService;
import com.github.vanroy.springdata.jest.JestElasticsearchTemplate;

import io.searchbox.client.JestClient;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

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

	private static List<ReportSummary> reportSummaryList = new ArrayList<ReportSummary>();

	public ReportServiceImpl(JestClient jestClient, JestElasticsearchTemplate elasticsearchTemplate) {
		// TODO Auto-generated constructor stub
		this.jestClient=jestClient;
		this.elasticsearchTemplate=elasticsearchTemplate;
	}

	@Override
	public byte[] getReportAsPdf(String orderNumber) throws JRException {
		//JasperReport jr = JasperCompileManager.compileReport("report.jrxml");

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
		JasperPrint jp = JasperFillManager.fillReport("src/main/resources/report/report.jasper", parameters, conn);

		return JasperExportManager.exportReportToPdf(jp);

	}

	@Override
	public byte[] getReportSummaryAsPdf(LocalDate date, String storeId) throws JRException {
		//JasperReport jr = JasperCompileManager.compileReport("ordersummary.jrxml");

		ReportSummary reportSummary = queryService.createReportSummary(date, storeId);
		reportSummaryList.add(reportSummary);
		JRBeanCollectionDataSource collectionDatasource = new JRBeanCollectionDataSource(
				ReportServiceImpl.getReportSummaryList());

//Preparing parameters
		Map<String, Object> parameters = new HashMap<String, Object>();

		JasperPrint jp = JasperFillManager.fillReport("src/main/resources/report/reportsummary.jasper", parameters, collectionDatasource);

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
		//JasperReport jr = JasperCompileManager.compileReport("reportcomboaux.jrxml");

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
		JasperPrint jp = JasperFillManager.fillReport("src/main/resources/report/reportcomboaux.jasper", parameters, conn);

		return JasperExportManager.exportReportToPdf(jp);

	}


}
