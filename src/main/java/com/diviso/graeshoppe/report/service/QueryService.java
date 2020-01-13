package com.diviso.graeshoppe.report.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.diviso.graeshoppe.report.domain.OrderMaster;
import com.diviso.graeshoppe.report.domain.ReportOrderModel;
import com.diviso.graeshoppe.report.domain.ReportSummary;

import net.sf.jasperreports.engine.JRException;
public interface QueryService {
		

	byte[] getReportSummaryAsPdf(LocalDate date,String storeId) throws JRException;

	/**
	 * @param orderNumber
	 * @return
	 */
	byte[] getReportWithAuxAndComboAsPdf(String orderNumber) throws JRException;
	
	ReportSummary createReportSummary(String fromDate, String toDate,String storeName);

	byte[] getSaleReportAsPdf(String storeidpcode)throws JRException;

	byte[] getAllOrdersByMethodOfOrderAsPdf(LocalDate date, String storeId, String methodOfOrder) throws JRException;

	byte[] getAllOrdersByPaymentStatusAsPdf(LocalDate date, String storeId, String paymentStatus) throws JRException;

	byte[] getAllOrdersByDateAsPdf(LocalDate date) throws JRException;

	byte[] getAllOrdersByDateAndStoreNameAsPdf(LocalDate date, String storeId) throws JRException;

	byte[] getAllOrdersBetweenDatesAsPdf(LocalDate fromDate, LocalDate toDate) throws JRException;

	byte[] getOrderSummaryBetweenDatesAsPdf(LocalDate fromDate, LocalDate toDate , String storeId) throws JRException;

	byte[] getOrderSummaryByDateAndStoreNameAsPdf(LocalDate date, String storeId )throws JRException;

	byte[] getAllOrdersBetweenDatesAndStoreIdAsPdf(LocalDate fromDate, LocalDate toDate, String storeId)throws JRException;

	Page<OrderMaster> getOrdersViewByMethodOfOrder(String storeId, String fromDate,String toDate, String methodOfOrder, Pageable pageable);

	Page<OrderMaster> getOrdersViewByPaymentStatus(String storeId, String fromDate,String toDate, String paymentStatus, Pageable pageable);

	Page<OrderMaster> getOrdersViewBetweenDates(String fromDate, String toDate, Pageable pageable);

	Page<OrderMaster> getOrdersViewBetweenDatesAndStoreIdpcode(String fromDate, String toDate, String storeId,Pageable pageable);

	Page<OrderMaster> getOrdersViewBetweenDatesAndPaymentStatus(String fromDate, String toDate, String paymentStatus,
			Pageable pageable);

	Page<OrderMaster> getOrdersViewBetweenDatesAndMethodOfOrder(String fromDate, String toDate, String methodOfOrder,
			Pageable pageable);

	Page<OrderMaster> getOrdersViewBetweenDatesAndPaymentStatusAndMethodOfOrder(String fromDate, String toDate,
			String paymentStatus, String methodOfOrder, Pageable pageable);

	Page<OrderMaster> getOrdersViewBetweenDatesAndStoreIdpcodeAndPaymentStatusAndMethodOfOrder(String fromDate,
			String toDate, String storeId, String paymentStatus, String methodOfOrder, Pageable pageable);

	byte[] getAllOrdersBetweenDatesByPaymentStatusAsPdf(LocalDate fromDate, LocalDate toDate,  String paymentStatus)throws JRException;

	byte[] getAllOrdersBetweenDatesByMethodOfOrderAsPdf(LocalDate fromDate, LocalDate toDate, String methodOfOrder) throws JRException;

	//List<OrderMaster> getOrdersViewByDateAndStoreIdpcode(String fromDate,String toDate, String storeId);


	/*OrderAggregator getOrderAggregator(String orderNumber);

	OrderMaster findOrderMasterByOrderNumber(String orderNumber);

	List<OrderLine> findOrderLineByOrderMaster(Long orderMasterId);

	List<ComboItem> findComboItemByOrderLine(Long orderLineId);

	List<AuxItem> findAuxItemByOrderLine(Long orderLineId);*/
	

}
