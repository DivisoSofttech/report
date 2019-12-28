package com.diviso.graeshoppe.report.service;

import java.time.LocalDate;
import java.util.List;

import com.diviso.graeshoppe.report.client.customer.model.Customer;
import com.diviso.graeshoppe.report.client.product.model.ComboLineItem;
import com.diviso.graeshoppe.report.client.product.model.Product;
import com.diviso.graeshoppe.report.client.store.model.Store;
import com.diviso.graeshoppe.report.domain.ReportSummary;

import net.sf.jasperreports.engine.JRException;

public interface ReportService {

	/**
	 * @param storeId
	 * @return
	 */
	//Store findStoreByStoreId(String storeId);
	
	/**
	 * @param productId
	 * @return
	 */
	//Product findProductByProductId(Long productId);
	
	/**
	 * @param id
	 * @return
	 */
	//List<ComboLineItem> findCombosByProductId(Long id);

	

	//Customer findCustomerByReference(String reference);
	
	
	
}