package com.diviso.graeshoppe.report.service;

import java.time.LocalDate;
import java.util.List;

import com.diviso.graeshoppe.report.domain.AuxItem;
import com.diviso.graeshoppe.report.domain.ComboItem;
import com.diviso.graeshoppe.report.domain.OrderAggregator;
import com.diviso.graeshoppe.report.domain.OrderLine;
import com.diviso.graeshoppe.report.domain.OrderMaster;
import com.diviso.graeshoppe.report.domain.ReportSummary;

import net.sf.jasperreports.engine.JRException;
public interface QueryService {
		

	byte[] getReportSummaryAsPdf(LocalDate date,String storeId) throws JRException;

	/**
	 * @param orderNumber
	 * @return
	 */
	byte[] getReportWithAuxAndComboAsPdf(String orderNumber) throws JRException;
	
	ReportSummary createReportSummary(String expectedDelivery, String storeName);

	byte[] getSaleReportAsPdf(String storeidpcode)throws JRException;

	byte[] getAllOrdersByMethodOfOrderAsPdf(LocalDate date, String storeId, String methodOfOrder) throws JRException;

	byte[] getAllOrdersByPaymentStatusAsPdf(LocalDate date, String storeId, String paymentStatus) throws JRException;

	byte[] getAllOrdersByDateAsPdf(LocalDate date) throws JRException;

	byte[] getAllOrdersByDateAndStoreNameAsPdf(LocalDate date, String storeId) throws JRException;

	byte[] getAllOrdersBetweenDatesAsPdf(LocalDate fromDate, LocalDate toDate) throws JRException;


	/*OrderAggregator getOrderAggregator(String orderNumber);

	OrderMaster findOrderMasterByOrderNumber(String orderNumber);

	List<OrderLine> findOrderLineByOrderMaster(Long orderMasterId);

	List<ComboItem> findComboItemByOrderLine(Long orderLineId);

	List<AuxItem> findAuxItemByOrderLine(Long orderLineId);*/
	

}
