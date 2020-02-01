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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.diviso.graeshoppe.report.domain.AuxItem;
import com.diviso.graeshoppe.report.domain.CancellationSummary;
import com.diviso.graeshoppe.report.domain.EscPosDocket;
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
import com.diviso.graeshoppe.report.service.dto.OrderMasterDTO;

import io.github.jhipster.web.util.PaginationUtil;
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


	
	@GetMapping("/reportSummary/{date}")
	public ResponseEntity<byte[]> getReportSummaryAsPdf(@PathVariable String date, @RequestParam(value="storeName", required=false) String storeName) {

		// log.debug("REST request to get a pdf");

		byte[] pdfContents = null;
		
		if(date!=null && storeName!=null) {
			System.out.println(">>>>>>>>>>>>>>>>>>>>>>> + storeName!=null");

			try {
				System.out.println(">>>>>>>>>>>>>>>>>>>>>>> inside try block");
				pdfContents = queryService.getReportSummaryAsPdf(date,storeName);
			} catch (JRException e) {
				e.printStackTrace();
			}
		}
		else if(date != null && storeName == null ) {
			System.out.println(">>>>>>>>>>>>>>>>>>>>>>> + storeName==null");
			try {
				pdfContents = queryService.getReportSummaryByDateOnlyAsPdf(date);
			} catch (JRException e) {
				e.printStackTrace();
			}
		}

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.parseMediaType("application/pdf"));
		String fileName = "reportsummary.pdf";
		headers.add("content-disposition", "attachment; filename=" + fileName);
		ResponseEntity<byte[]> response = new ResponseEntity<byte[]>(pdfContents, headers, HttpStatus.OK);
		return response;
	}

	// Detailed Report Summary Docket
	@GetMapping("/detailedOrderSummary/{date}/{storeName}")
	public ResponseEntity<byte[]> getDetailedOrderSummaryAsPdf(@PathVariable String date, @PathVariable String storeName) {


		// log.debug("REST request to get a pdf");

		byte[] pdfContents = null;
		// System.out.println(">>>>>>>>>>>>>>>>>>>>"+orderNumber);

		try {


			// System.out.println("starting of try block>>>>>>>>>>>>>>>>>>>>"+orderNumber);
			pdfContents = queryService.getDetailedOrderSummaryAsPdf(date, storeName);
			// System.out.println("ending try block>>>>>>>>>>>>>>>>>>>>"+orderNumber);

		} catch (JRException e) {
			e.printStackTrace();
		}

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.parseMediaType("application/pdf"));
		String fileName = "DetailedOrderSummary.pdf";
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

	
	
	@GetMapping("/reportview/{date}")

	public ReportSummary createReportSummary(@PathVariable String date, @RequestParam(value="storeName", required=false) String storeName) {
	
		
		return queryService.createReportSummary(date, storeName);
	
		
	}

	/*
	 * @GetMapping("/reportview/{fromDate}/{toDate}")
	 * 
	 * public ReportSummary createReportSummaryBetweenTwoDates(@PathVariable String
	 * fromDate, @PathVariable String toDate) { return
	 * queryService.createReportSummaryBetweenTwoDates(fromDate, toDate); }
	 */

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

	@GetMapping("/findOrderCountByStatusName/{statusName}")
	public Long findOrderCountByStatusName(@PathVariable String statusName) {
		return orderMasterService.countByOrderStatus(statusName);
	}

	@GetMapping("/findOrderMasterByExpectedDeliveryBetween/{from}/{to}")
	public Page<OrderMaster> findOrderMasterByExpectedDeliveryBetween(@PathVariable String from,
			@PathVariable String to, Pageable pageable) {
		return orderMasterService.findByExpectedDeliveryBetween(from, to, pageable);
	}

	@GetMapping("/countOrderMasterByDeliveryBetween/{from}/{to}")
	public Long findOrderMasterCountByExpectedDeliveryBetween(@PathVariable String from, @PathVariable String to) {
		log.debug("<<<<<<<<<<<<findOrderMasterCountByExpectedDeliveryBetween >>>>>{}{}", from, to);
		return orderMasterService.countByExpectedDeliveryBetween(from, to);
	}

	/**
	 * @author Prince
	 * @param orderId
	 * @return
	 */
	@GetMapping("/findOfferLinesByOrderNumber/{orderId}")
	public List<OfferLine> findOfferLinesByOrderNumber(@PathVariable String orderId) {
		log.debug("<<<<<<<findByOfferLine_orderNumber >>>>>>{}", orderId);
		return offerLineService.findOfferLinesByOrderNumber(orderId);

	}

	/**
	 * @author Prince
	 * @param id
	 * @return
	 */
	@GetMapping("/findAuxItemByid/{id}")
	public List<AuxItem> findAuxItemByid(@PathVariable Long id) {
		log.debug("<<<<<<<findAuxItemByid >>>>>>{}", id);
		return auxItemService.findAuxItemByid(id);

	}

	/**
	 * @author Prince
	 * @param orderNumber
	 * @return
	 */
	@GetMapping("/findOrderLinesByOrderNumber/{orderNumber}")
	public List<OrderLine> findOrderLineByOrderNumber(@PathVariable String orderNumber) {
		log.debug("<<<<<<<<<<<<findByOrderLine_OrderMaster>>>>>>>>{}", orderNumber);
		return orderLineService.findOrderLineByOrderNumber(orderNumber);

	}
	

	
	
	

	@GetMapping("/orderSummaryBetweenDatesAndStoreId/{fromDate}/{toDate}/{storeId}")
	public ResponseEntity<byte[]> getOrderSummaryBetweenDatesAsPdf(@PathVariable String fromDate, @PathVariable String toDate , @PathVariable String storeId){

		// log.debug("REST request to get a pdf");

		byte[] pdfContents = null;


		try {
			pdfContents = queryService.getOrderSummaryBetweenDatesAsPdf(fromDate, toDate, storeId );
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
			pdfContents = queryService.getOrderSummaryByDateAndStoreNameAsPdf(date, storeId);
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
	
	
	@GetMapping("/allOrdersByFiltering/{fromDate}/{toDate}")
	public ResponseEntity<Page<OrderMaster>> getOrdersByFilter(@PathVariable String fromDate,@PathVariable String toDate,@RequestParam(value="storeId", required=false) String storeId,@RequestParam(value="methodOfOrder", required=false) String methodOfOrder,
			@RequestParam(value="paymentStatus", required=false) String paymentStatus, Pageable pageable) {
		
		Page<OrderMaster> page = null;
		HttpHeaders headers= null;
		System.out.println(">>>>>>>>>>>>>"+fromDate+">>>>>>>>>"+ toDate+">>>>>>>"+ storeId+">>>>>>>>>>>>>>>>"+ methodOfOrder+">>>>>>>"+paymentStatus);
		
		if(fromDate!=null && toDate!=null && storeId!=null && methodOfOrder!=null && paymentStatus ==null) {
			//to get the orders according to delivery type from a particular store
			
			 page = queryService.getOrdersViewByMethodOfOrder(storeId, fromDate, toDate, methodOfOrder, pageable);
		     headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
		        
			
		}

		else if(fromDate!=null && toDate!=null && storeId!=null && methodOfOrder==null && paymentStatus !=null) {
			// to get the orders according to payment status from a particular store
			
			page = queryService.getOrdersViewByPaymentStatus(storeId, fromDate, toDate, paymentStatus,pageable);
	        headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
	        
			
		}
		
		else if(fromDate!=null && toDate!=null && storeId==null && methodOfOrder==null && paymentStatus ==null) {
			
			// to get all orders between two given dates irrespective of the store
			
			page = queryService.getOrdersViewBetweenDates(fromDate, toDate, pageable);
	        headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
			
		}
		
		else if(fromDate!= null && toDate!= null && storeId!= null && methodOfOrder == null && paymentStatus == null ) {
			
			// to get all orders between two dates from a particular store
			page = queryService.getOrdersViewBetweenDatesAndStoreIdpcode(fromDate, toDate, storeId, pageable);
	        headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
			
		}
		
       else if(fromDate!= null && toDate!= null && storeId== null && methodOfOrder != null && paymentStatus == null ) {
			
			// to get all orders by giving two dates and method of order irrespective of the store
    	   page = queryService.getOrdersViewBetweenDatesAndMethodOfOrder(fromDate, toDate,methodOfOrder, pageable);
           headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
			
			
		}
		
       else if(fromDate!= null && toDate!= null && storeId== null && methodOfOrder == null && paymentStatus != null ) {
			
			// to get all orders by giving dates and payment status irrespective of the store
    	   page = queryService.getOrdersViewBetweenDatesAndPaymentStatus(fromDate, toDate,paymentStatus, pageable);
           headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
			
		}
		
       else if(fromDate!= null && toDate!= null && storeId!= null && methodOfOrder != null && paymentStatus != null ) {
			//to get all orders by giving all fields
		
		page = queryService.getOrdersViewBetweenDatesAndStoreIdpcodeAndPaymentStatusAndMethodOfOrder(fromDate, toDate,storeId, paymentStatus,methodOfOrder, pageable);
        headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
		
       }
		
       else if(fromDate!= null && toDate!= null && storeId== null && methodOfOrder != null && paymentStatus != null ) {
			//to get all orders without giving the store
		
		page = queryService.getOrdersViewBetweenDatesAndPaymentStatusAndMethodOfOrder(fromDate, toDate, paymentStatus,methodOfOrder, pageable);
       headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
		
      }
		
		return ResponseEntity.ok().headers(headers).body(page);
		
	}

	
	
	
	
	@GetMapping("/allOrdersPdfByFiltering/{fromDate}/{toDate}")
	public ResponseEntity<byte[]> getOrdersPdfByFilter(@PathVariable String fromDate,@PathVariable String toDate,@RequestParam(value="storeId", required=false) String storeId,@RequestParam(value="methodOfOrder", required=false) String methodOfOrder,
			@RequestParam(value="paymentStatus", required=false) String paymentStatus) {
		
		byte[] pdfContents = null;
		HttpHeaders headers= null;
		System.out.println(">>>>>>>>>>>>>"+fromDate+">>>>>>>>>"+ toDate+">>>>>>>"+ storeId+">>>>>>>>>>>>>>>>"+ methodOfOrder+">>>>>>>"+paymentStatus);
		
		if(fromDate!=null && toDate!=null && storeId!=null && methodOfOrder!=null && paymentStatus ==null) {
		

			// to get the orders according to method of order from a particular store
			try {
				pdfContents = queryService.getAllOrdersByMethodOfOrderAsPdf(fromDate, toDate, storeId, methodOfOrder);
			} catch (JRException e) {
				e.printStackTrace();
			}

			
		}

		else if(fromDate!=null && toDate!=null && storeId!=null && methodOfOrder==null && paymentStatus !=null) {
			// to get the orders according to payment status from a particular store
			
System.out.println(">>>>>>>>>>>>>>> entering getAllOrdersByPaymentStatusAsPdf resource");
			try {
				pdfContents = queryService.getAllOrdersByPaymentStatusAsPdf(fromDate, toDate, storeId, paymentStatus);
			} catch (JRException e) {
				e.printStackTrace();
			}
			
		}
		
		else if(fromDate!=null && toDate!=null && storeId==null && methodOfOrder==null && paymentStatus ==null) {
			
			// to get all orders between two given dates irrespective of the store
			
			try {
				pdfContents = queryService.getAllOrdersBetweenDatesAsPdf(fromDate, toDate);
			} catch (JRException e) {
				e.printStackTrace();
			}
		}
		
		else if(fromDate!= null && toDate!= null && storeId!= null && methodOfOrder == null && paymentStatus == null ) {
			
			// to get all orders between two dates from a particular store
			try {
				pdfContents = queryService.getAllOrdersBetweenDatesAndStoreIdAsPdf(fromDate, toDate, storeId );
			} catch (JRException e) {
				e.printStackTrace();
			}
			
		}
		
       else if(fromDate!= null && toDate!= null && storeId== null && methodOfOrder != null && paymentStatus == null ) {
			
			// to get all orders by giving two dates and method of order irrespective of the store
    	   try {
   			pdfContents = queryService.getAllOrdersBetweenDatesByMethodOfOrderAsPdf(fromDate, toDate, methodOfOrder );
   		} catch (JRException e) {
   			e.printStackTrace();
   		}
		}
		
       else if(fromDate!= null && toDate!= null && storeId== null && methodOfOrder == null && paymentStatus != null ) {
			
			// to get all orders by giving dates and payment status irrespective of the store
    		try {
    			pdfContents = queryService.getAllOrdersBetweenDatesByPaymentStatusAsPdf(fromDate, toDate, paymentStatus );
    		} catch (JRException e) {
    			e.printStackTrace();
    		}
    	   
		}
		
       else if(fromDate!= null && toDate!= null && storeId!= null && methodOfOrder != null && paymentStatus != null ) {
			//to get all orders by giving all fields
    	 System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>entering all fields resource");
    	   try {
   			pdfContents = queryService.getAllOrdersBetweenDatesByStoreIdAndPaymentStatusAndMethodOfOrderAsPdf(fromDate, toDate,storeId, paymentStatus, methodOfOrder );
   		} catch (JRException e) {
   			e.printStackTrace();
   		}
       }
		
       else if(fromDate!= null && toDate!= null && storeId== null && methodOfOrder != null && paymentStatus != null ) {
			//to get all orders without giving the store
		
    	   try {
   			pdfContents = queryService.getAllOrdersBetweenDatesByPaymentStatusAndMethodOfOrderAsPdf(fromDate, toDate,paymentStatus, methodOfOrder );
   		} catch (JRException e) {
   			e.printStackTrace();
   		}
      }
		headers = new HttpHeaders();
		headers.setContentType(MediaType.parseMediaType("application/pdf"));
		String fileName = "orders.pdf";
		headers.add("content-disposition", "attachment; filename=" + fileName);
		ResponseEntity<byte[]> response = new ResponseEntity<byte[]>(pdfContents, headers, HttpStatus.OK);
		return response;
		
		
	}
	@GetMapping("/findWeakOrderCount/{date}/{statusName}")
	public Long findWeakOrderCount(@PathVariable String date, @PathVariable String statusName) {
		log.debug("<<<<<<<<<<<<<<<<<<<< findOrderCountByDateAndStatusName >>>>>>>>>>{}", date);
		return orderMasterService.weakOrderCount(date, statusName);
	}
	
	@GetMapping("/cancellationSummary/{date}/{storeName}")
	public ResponseEntity<byte[]> getCancellationSummaryAsPdf(@PathVariable String date, @PathVariable String storeName) {

		// log.debug("REST request to get a pdf");

		byte[] pdfContents = null;
		 System.out.println(">>>>>>>>>>>>>>>>>>>> cancellationSummary"+date+">>>>>>>>>>"+storeName);

		try {

			// System.out.println("starting of try block>>>>>>>>>>>>>>>>>>>>"+orderNumber);
			pdfContents = queryService.getCancellationSummaryAsPdf(date, storeName);
			// System.out.println("ending try block>>>>>>>>>>>>>>>>>>>>"+orderNumber);
		} catch (JRException e) {
			e.printStackTrace();
		}

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.parseMediaType("application/pdf"));
		String fileName = "CancellationSummary.pdf";
		headers.add("content-disposition", "attachment; filename=" + fileName);
		ResponseEntity<byte[]> response = new ResponseEntity<byte[]>(pdfContents, headers, HttpStatus.OK);
		return response;
	}



	@GetMapping("/cancellationReportview/{date}/{storeName}")

	public CancellationSummary createCancellationReportSummaryView(@PathVariable String date, @PathVariable String storeName) {
	
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>> cancellationReportView"+ date+""+storeName);
		return queryService.createCancellationReportSummaryView(date, storeName);
	
		
	}
	
	@GetMapping("grandTotalOfAStore/{fromDate}/{toDate}/{storeName}")
	public Double findGrandTotalBetweenAndStoreName(@PathVariable String fromDate, @PathVariable String toDate, @PathVariable String storeName) {
		log.debug("<<<<<<<<<<<<<<<<<<<< findGrandTotalByDateAndStatusName >>>>>>>>>>{}", fromDate, toDate, storeName);
		return queryService.findGrandTotalBetweenDateAndStoreName(fromDate, toDate, storeName);
	}
	
	
	@GetMapping("/detailedReportview/{fromDate}/{toDate}/{storeName}")

	public ReportSummary createDetailedReportSummaryView(@PathVariable String fromDate,@PathVariable String toDate, @PathVariable String storeName) {
	
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>> detailedReportView"+ fromDate+""+toDate+""+storeName);
		return queryService.createDetailedReportSummaryView(fromDate, toDate,storeName);
	
		
	}
	
	@GetMapping("/allEscDocket")
	public EscPosDocket findAll() {
		return null;
		
	}
	
	@GetMapping("/docketHeader/{orderNumber}")

	public String getDocketHeader(@PathVariable String orderNumber) {
	
		//System.out.println(">>>>>>>>>>>>>>>>>>>>>>> detailedReportView"+ fromDate+""+toDate+""+storeName);
		return queryService.getDocketHeaderView(orderNumber);
	
		
	}
	
	@GetMapping("/docketContent/{orderNumber}")

	public String getDocketContent(@PathVariable String orderNumber) {

		return queryService.getDocketContent(orderNumber);
	
		
	}
	

	@GetMapping("/docketProducts/{orderNumber}")

	public String getDocketProducts(@PathVariable String orderNumber) {
	
		return queryService.getDocketProduct(orderNumber);
	
		
	}
	

	@GetMapping("/docketDiscountAndTotal/{orderNumber}")

	public String getDocketDiscountAndTotal(@PathVariable String orderNumber) {
	
		return queryService.getDocketDiscountAndTotal(orderNumber);
	
		
	}
	
	@GetMapping("/docketPaymentStatus/{orderNumber}")

	public String getDocketPaymentStatus(@PathVariable String orderNumber) {
	
		return queryService.getDocketPaymentStatus(orderNumber);
	
		
	}
	

	@GetMapping("/docketCustomerOrder/{orderNumber}")

	public String getCustomerOrderDetails(@PathVariable String orderNumber) {
	
		return queryService.getDocketCustomerOrderDetails(orderNumber);	
	}

	@GetMapping("/docketCustomerDetails/{orderNumber}")

	public String getCustomerDetails(@PathVariable String orderNumber) {
	
		return queryService.getDocketCustomerDetails(orderNumber);	
	}
	@GetMapping("/docketAttentionForOrder/{orderNumber}")

	public String getAttentionForOrder(@PathVariable String orderNumber) {
	
		return queryService.getAttentionForOrder(orderNumber);	
	}

	@GetMapping("/docketFooters/{orderNumber}")

	public String getFooters(@PathVariable String orderNumber) {
	
		return queryService.getFooters(orderNumber);	
	}

	
	
	
	
	
	
	
}
