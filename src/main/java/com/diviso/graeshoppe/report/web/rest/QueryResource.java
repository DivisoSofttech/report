package com.diviso.graeshoppe.report.web.rest;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.diviso.graeshoppe.report.domain.AuxItem;
import com.diviso.graeshoppe.report.domain.OfferLine;
import com.diviso.graeshoppe.report.domain.OrderLine;
import com.diviso.graeshoppe.report.domain.OrderMaster;
import com.diviso.graeshoppe.report.domain.ReportOrderModel;
import com.diviso.graeshoppe.report.domain.ReportSummary;
import com.diviso.graeshoppe.report.service.AuxItemService;
import com.diviso.graeshoppe.report.service.OfferLineService;
import com.diviso.graeshoppe.report.service.OrderLineService;
import com.diviso.graeshoppe.report.service.OrderMasterService;
import com.diviso.graeshoppe.report.service.QueryService;

import net.sf.jasperreports.engine.JRException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;


/**
 * REST controller for managing Reports.
 */
@RestController
@RequestMapping("/api")
public class QueryResource {

	private Logger log = LoggerFactory.getLogger(QueryResource.class);

	@Autowired
	AuxItemService auxItemService;

	@Autowired
	private QueryService queryService;

	@Autowired
	private OrderMasterService orderMasterService;

	@Autowired
	private OrderLineService orderLineService;

	@Autowired
	OfferLineService offerLineService;
	/*
	 * @GetMapping("/orderAggregator/{orderNumber}") public OrderAggregator
	 * getOrderAggregator(@PathVariable String orderNumber) { return
	 * queryService.getOrderAggregator(orderNumber); }
	 */

	@GetMapping("/reportSummary/{date}/{storeId}")
	public ResponseEntity<byte[]> getReportSummaryAsPdf(@PathVariable String date, @PathVariable String storeId) {

		// log.debug("REST request to get a pdf");

		byte[] pdfContents = null;

		try {
			pdfContents = queryService.getReportSummaryAsPdf(LocalDate.parse(date), storeId);
		} catch (JRException e) {
			e.printStackTrace();
		}

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.parseMediaType("application/pdf"));
		String fileName = "reportsummary.pdf";
		headers.add("content-disposition", "attachment; filename=" + fileName);
		ResponseEntity<byte[]> response = new ResponseEntity<byte[]>(pdfContents, headers, HttpStatus.OK);
		return response;
	}

	@GetMapping("/auxcombo/{orderNumber}")
	public ResponseEntity<byte[]> getReportWithAuxAndComboAsPdf(@PathVariable String orderNumber) {

		// log.debug("REST request to get a pdf");

		byte[] pdfContents = null;
		// System.out.println(">>>>>>>>>>>>>>>>>>>>"+orderNumber);

		try {

			// System.out.println("starting of try block>>>>>>>>>>>>>>>>>>>>"+orderNumber);
			pdfContents = queryService.getReportWithAuxAndComboAsPdf(orderNumber);
			// System.out.println("ending try block>>>>>>>>>>>>>>>>>>>>"+orderNumber);
		} catch (JRException e) {
			e.printStackTrace();
		}

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.parseMediaType("application/pdf"));
		String fileName = "reportAuxCombo.pdf";
		headers.add("content-disposition", "attachment; filename=" + fileName);
		ResponseEntity<byte[]> response = new ResponseEntity<byte[]>(pdfContents, headers, HttpStatus.OK);
		return response;
	}

	@GetMapping("/reportview/{expectedDelivery}/{storeName}")

	public ReportSummary createReportSummary(@PathVariable String expectedDelivery, @PathVariable String storeName) {
		return queryService.createReportSummary(expectedDelivery, storeName);
	}

	@GetMapping("/salereport/{storeidpcode}")
	public ResponseEntity<byte[]> getSaleReportAsPdf(@PathVariable String storeidpcode) {

		// log.debug("REST request to get a pdf");

		byte[] pdfContents = null;

		try {
			pdfContents = queryService.getSaleReportAsPdf(storeidpcode);
		} catch (JRException e) {
			e.printStackTrace();
		}

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.parseMediaType("application/pdf"));
		String fileName = "salereport.pdf";
		headers.add("content-disposition", "attachment; filename=" + fileName);
		ResponseEntity<byte[]> response = new ResponseEntity<byte[]>(pdfContents, headers, HttpStatus.OK);
		return response;
	}

	@GetMapping("/findOrderByDatebetweenAndStoreId/{from}/{to}/{storeId}")
	public Page<OrderMaster> findOrderByDatebetweenAndStoreId(@PathVariable String from, @PathVariable String to,
			@PathVariable String storeId, Pageable pageable) {
		return orderMasterService.findByExpectedDeliveryBetweenAndStoreIdpcode(from, to, storeId, pageable);
	}

	@GetMapping("/findOrderCountByDateAndStatusName/{date}/{statusName}")
	public Long findOrderCountByDateAndStatusName(@PathVariable String date, @PathVariable String statusName) {
		log.debug("<<<<<<<<<<<<<<<<<<<< findOrderCountByDateAndStatusName >>>>>>>>>>{}", date);
		return orderMasterService.countByExpectedDeliveryAndOrderStatus(date, statusName);
	}

	@GetMapping("/indOrderCountByStatusName/{statusName}")
	public Long findOrderCountByStatusName(@PathVariable String statusName) {
		return orderMasterService.countByOrderStatus(statusName);
	}

	@GetMapping("/findOrderMasterByExpectedDeliveryBetween/{from}/{to}")
	public Page<OrderMaster> findOrderMasterByExpectedDeliveryBetween(@PathVariable Instant from,
			@PathVariable Instant to, Pageable pageable) {
		return orderMasterService.findByExpectedDeliveryBetween(from, to, pageable);
	}

	@GetMapping("/countOrderMasterByDeliveryBetween/{from}/{to}")
	public Long findOrderMasterCountByExpectedDeliveryBetween(@PathVariable String from, @PathVariable String to) {
		log.debug("<<<<<<<<<<<<findOrderMasterCountByExpectedDeliveryBetween >>>>>{}{}", from, to);
		return orderMasterService.countByExpectedDeliveryBetween(from, to);
	}

	@GetMapping("/findOfferLinesByOrderNumber/{orderId}")
	public List<OfferLine> findOfferLinesByOrderNumber(@PathVariable String orderId) {
		log.debug("<<<<<<<findByOfferLine_orderNumber >>>>>>{}", orderId);
		return offerLineService.findOfferLinesByOrderNumber(orderId);

	}

	@GetMapping("/findAuxItemByid/{id}")
	public List<AuxItem> findAuxItemByid(@PathVariable Long id) {
		log.debug("<<<<<<<findAuxItemByid >>>>>>{}", id);
		return auxItemService.findAuxItemByid(id);

	}

	@GetMapping("/findOrderLinesByOrderNumber/{orderNumber}")
	public List<OrderLine> findOrderLineByOrderNumber(@PathVariable String orderNumber) {
		log.debug("<<<<<<<<<<<<findByOrderLine_OrderMaster>>>>>>>>{}", orderNumber);
		return orderLineService.findOrderLineByOrderNumber(orderNumber);

	}
	

	@GetMapping("/orders/{storeId}/{date}/{methodOfOrder}")
	public ResponseEntity<byte[]> getAllOrdersByMethodOfOrderAsPdf(@PathVariable String storeId, @PathVariable String date, @PathVariable String methodOfOrder) {

		// log.debug("REST request to get a pdf");

		byte[] pdfContents = null;


		try {
			pdfContents = queryService.getAllOrdersByMethodOfOrderAsPdf(LocalDate.parse(date), storeId, methodOfOrder);
		} catch (JRException e) {
			e.printStackTrace();
		}

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.parseMediaType("application/pdf"));
		String fileName = "orderByMethodOfOrder.pdf";
		headers.add("content-disposition", "attachment; filename=" + fileName);
		ResponseEntity<byte[]> response = new ResponseEntity<byte[]>(pdfContents, headers, HttpStatus.OK);
		return response;
	}
	
	@GetMapping("/ordersbypayment/{storeId}/{date}/{paymentStatus}")
	public ResponseEntity<byte[]> getAllOrdersByPaymentStatusAsPdf(@PathVariable String storeId, @PathVariable String date, @PathVariable String paymentStatus) {

		// log.debug("REST request to get a pdf");

		byte[] pdfContents = null;


		try {
			pdfContents = queryService.getAllOrdersByPaymentStatusAsPdf(LocalDate.parse(date), storeId, paymentStatus);
		} catch (JRException e) {
			e.printStackTrace();
		}

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.parseMediaType("application/pdf"));
		String fileName = "orderByPaymentStatus.pdf";
		headers.add("content-disposition", "attachment; filename=" + fileName);
		ResponseEntity<byte[]> response = new ResponseEntity<byte[]>(pdfContents, headers, HttpStatus.OK);
		return response;
	}
	
	@GetMapping("/ordersbydate/{date}")
	public ResponseEntity<byte[]> getAllOrdersByDateAsPdf(@PathVariable String date) {

		// log.debug("REST request to get a pdf");

		byte[] pdfContents = null;


		try {
			pdfContents = queryService.getAllOrdersByDateAsPdf(LocalDate.parse(date));
		} catch (JRException e) {
			e.printStackTrace();
		}

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.parseMediaType("application/pdf"));
		String fileName = "ordersByDate.pdf";
		headers.add("content-disposition", "attachment; filename=" + fileName);
		ResponseEntity<byte[]> response = new ResponseEntity<byte[]>(pdfContents, headers, HttpStatus.OK);
		return response;
	}
	
	
	@GetMapping("/ordersbydateandstorename/{date}/{storeId}")
	public ResponseEntity<byte[]> getAllOrdersByDateAndStoreNameAsPdf(@PathVariable String date, @PathVariable String storeId) {

		// log.debug("REST request to get a pdf");

		byte[] pdfContents = null;


		try {
			pdfContents = queryService.getAllOrdersByDateAndStoreNameAsPdf(LocalDate.parse(date), storeId);
		} catch (JRException e) {
			e.printStackTrace();
		}

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.parseMediaType("application/pdf"));
		String fileName = "ordersByDateAndStoreName.pdf";
		headers.add("content-disposition", "attachment; filename=" + fileName);
		ResponseEntity<byte[]> response = new ResponseEntity<byte[]>(pdfContents, headers, HttpStatus.OK);
		return response;
	}
	
	
	@GetMapping("/ordersbetweendates/{fromDate}/{toDate}")
	public ResponseEntity<byte[]> getAllOrdersBetweenDatesAsPdf(@PathVariable String fromDate, @PathVariable String toDate ){

		// log.debug("REST request to get a pdf");

		byte[] pdfContents = null;


		try {
			pdfContents = queryService.getAllOrdersBetweenDatesAsPdf(LocalDate.parse(fromDate), LocalDate.parse(toDate));
		} catch (JRException e) {
			e.printStackTrace();
		}

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.parseMediaType("application/pdf"));
		String fileName = "ordersBetweenDates.pdf";
		headers.add("content-disposition", "attachment; filename=" + fileName);
		ResponseEntity<byte[]> response = new ResponseEntity<byte[]>(pdfContents, headers, HttpStatus.OK);
		return response;
	}
	

	@GetMapping("/ordersummarybetweendates/{fromDate}/{toDate}/{storeId}")
	public ResponseEntity<byte[]> getOrderSummaryBetweenDatesAsPdf(@PathVariable String fromDate, @PathVariable String toDate , @PathVariable String storeId){

		// log.debug("REST request to get a pdf");

		byte[] pdfContents = null;


		try {
			pdfContents = queryService.getOrderSummaryBetweenDatesAsPdf(LocalDate.parse(fromDate), LocalDate.parse(toDate), storeId );
		} catch (JRException e) {
			e.printStackTrace();
		}

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.parseMediaType("application/pdf"));
		String fileName = "orderSummaryBetweenDates.pdf";
		headers.add("content-disposition", "attachment; filename=" + fileName);
		ResponseEntity<byte[]> response = new ResponseEntity<byte[]>(pdfContents, headers, HttpStatus.OK);
		return response;
	}
	
	@GetMapping("/ordersummarybydateandstorename/{date}/{storeId}")
	public ResponseEntity<byte[]> getOrderSummaryByDateAndStoreNameAsPdf(@PathVariable String date, @PathVariable String storeId) {

		// log.debug("REST request to get a pdf");

		byte[] pdfContents = null;


		try {
			pdfContents = queryService.getOrderSummaryByDateAndStoreNameAsPdf(LocalDate.parse(date), storeId);
		} catch (JRException e) {
			e.printStackTrace();
		}

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.parseMediaType("application/pdf"));
		String fileName = "orderSummaryByDateAndStoreName.pdf";
		headers.add("content-disposition", "attachment; filename=" + fileName);
		ResponseEntity<byte[]> response = new ResponseEntity<byte[]>(pdfContents, headers, HttpStatus.OK);
		return response;
	}
	
	
	@GetMapping("/allordersbetweendatesandstorename/{fromDate}/{toDate}/{storeId}")
	public ResponseEntity<byte[]> getAllOrdersBetweenDatesAndStoreIdAsPdf(@PathVariable String fromDate, @PathVariable String toDate , @PathVariable String storeId){

		// log.debug("REST request to get a pdf");

		byte[] pdfContents = null;


		try {
			pdfContents = queryService.getAllOrdersBetweenDatesAndStoreIdAsPdf(LocalDate.parse(fromDate), LocalDate.parse(toDate), storeId );
		} catch (JRException e) {
			e.printStackTrace();
		}

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.parseMediaType("application/pdf"));
		String fileName = "orderSummaryBetweenDates.pdf";
		headers.add("content-disposition", "attachment; filename=" + fileName);
		ResponseEntity<byte[]> response = new ResponseEntity<byte[]>(pdfContents, headers, HttpStatus.OK);
		return response;
	}
	
	
	@GetMapping("/orderview/{storeId}/{date}/{methodOfOrder}")

	public List<ReportOrderModel> getOrdersViewByMethodOfOrder(@PathVariable String storeId, @PathVariable String date, @PathVariable String methodOfOrder) {
		return queryService.getOrdersViewByMethodOfOrder(storeId, date, methodOfOrder);
	}
}
