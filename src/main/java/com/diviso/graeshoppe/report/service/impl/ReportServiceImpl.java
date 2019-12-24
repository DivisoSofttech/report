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

	public ReportServiceImpl(JestClient jestClient, JestElasticsearchTemplate elasticsearchTemplate) {
		// TODO Auto-generated constructor stub
		this.jestClient = jestClient;
		this.elasticsearchTemplate = elasticsearchTemplate;
	}

	/*

	@Override
	public Store findStoreByStoreId(String storeId) {
		StringQuery stringQuery = new StringQuery(termQuery("regNo", storeId).toString());
		return elasticsearchOperations.queryForObject(stringQuery, Store.class);
	}

	@Override
	public Customer findCustomerByReference(String reference) {
		StringQuery stringQuery = new StringQuery(termQuery("idpCode", reference).toString());
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

*/

	
}
