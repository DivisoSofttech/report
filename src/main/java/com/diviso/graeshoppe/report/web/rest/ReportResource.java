 /*
 * Copyright 2002-2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.diviso.graeshoppe.report.web.rest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.diviso.graeshoppe.report.service.ReportService;
import com.diviso.graeshoppe.report.service.dto.OrderLine;
import com.diviso.graeshoppe.report.service.dto.OrderMaster;

import net.sf.jasperreports.engine.JRException;

/**
 * TODO Provide a detailed description here 
 * @author MayaSanjeev
 * mayabytatech, maya.k.k@lxisoft.com
 */

@RestController
@RequestMapping("/api/report")
public class ReportResource {
	
	@Autowired
	ReportService reportService;
	
	

	@GetMapping("/order-report/{orderNumber}")
	public OrderMaster getReportByOrderNumber(@PathVariable String orderNumber){
		
		OrderMaster orderMaster = new OrderMaster();
		
		orderMaster.setMethodOfOrder("delivery");
		orderMaster.setDueDate("02-Feb");
		orderMaster.setDueTime("18:05");
		orderMaster.setNotes("salt and vinegar");
		orderMaster.setDeliveryCharge(2.00);
		orderMaster.setServiceCharge(.50);
		orderMaster.setOrderStatus("ORDER NOT PAID");
		orderMaster.setCustomerId("22255545455");
		orderMaster.setAddressType("home");
		
		
		orderMaster.setCity("palakkad");
		orderMaster.setHouseNoOrBuildingName("458");
		orderMaster.setLandmark("new juma musjid");
		orderMaster.setCity("palakkad");
		orderMaster.setName("franklin");
		orderMaster.setPhone(5446464646L);
		orderMaster.setAlternatePhone(56566786545L);
		orderMaster.setRoadNameAreaOrStreet("mujid road");
		orderMaster.setPincode(4558885L);
		
		
		orderMaster.setOrderFromCustomer(14L);
		orderMaster.setCustomersOrder(83L);
		orderMaster.setOrderPlaceAt("03-Feb 14:55");
		orderMaster.setOrderAcceptedAt("03-Feb 15:05");
		
		List<OrderLine> orderLines =new ArrayList<OrderLine>();
		
		OrderLine order1= new OrderLine();
		
		OrderLine order2= new OrderLine();
		
		order1.setItem("pizza");
		order1.setQuantity(2);
		order1.setTotal(11.2);
		orderLines.add(order1);
		
		order2.setItem("burger");
		order1.setQuantity(1);
		order1.setTotal(11.2);
		orderLines.add(order2);
		
		orderMaster.setOrderLine(orderLines);
		
		return orderMaster;
	}
	
	@GetMapping("/pdf/{orderNumber}")
	public ResponseEntity<byte[]> getReportAsPdf(@PathVariable String orderNumber) {
	       
	       //log.debug("REST request to get a pdf");
	     
	       byte[] pdfContents = null;
	    
	      try
	      {
	        pdfContents=reportService.getReportAsPdf(orderNumber);
	      }
	      catch (JRException e) {
	           e.printStackTrace();
	      }
	     
	        HttpHeaders headers = new HttpHeaders();
	        headers.setContentType(MediaType.parseMediaType("application/pdf"));
	       String fileName ="report.pdf";
	        headers.add("content-disposition", "attachment; filename=" + fileName);
	        ResponseEntity<byte[]> response = new ResponseEntity<byte[]>(
	     	           pdfContents, headers, HttpStatus.OK);	      
	       return response;
	   }

	
	
	 @GetMapping("/reportSummary/{date}/{storeId}")
	 public ResponseEntity<byte[]> getReportSummaryAsPdf(@PathVariable LocalDate date,@PathVariable String storeId) {
	       
	       //log.debug("REST request to get a pdf");
	     
	       byte[] pdfContents = null;
	    
	      try
	      {
	        pdfContents=reportService.getReportSummaryAsPdf(date,storeId);
	      }
	      catch (JRException e) {
	           e.printStackTrace();
	      }
	     
	        HttpHeaders headers = new HttpHeaders();
	        headers.setContentType(MediaType.parseMediaType("application/pdf"));
	       String fileName ="reportsummary.pdf";
	        headers.add("content-disposition", "attachment; filename=" + fileName);
	        ResponseEntity<byte[]> response = new ResponseEntity<byte[]>(
	     	           pdfContents, headers, HttpStatus.OK);	      
	       return response;
	   }

	
	 @GetMapping("/auxcombo/{orderNumber}")
		public ResponseEntity<byte[]> getReportWithAuxAndComboAsPdf(@PathVariable String orderNumber) {
		       
		       //log.debug("REST request to get a pdf");
		     
		       byte[] pdfContents = null;
		     
		    
		      try
		      {
		        pdfContents=reportService.getReportWithAuxAndComboAsPdf(orderNumber);
		      }
		      catch (JRException e) {
		           e.printStackTrace();
		      }
		     
		        HttpHeaders headers = new HttpHeaders();
		        headers.setContentType(MediaType.parseMediaType("application/pdf"));
		       String fileName ="reportAuxCombo.pdf";
		        headers.add("content-disposition", "attachment; filename=" + fileName);
		        ResponseEntity<byte[]> response = new ResponseEntity<byte[]>(
		     	           pdfContents, headers, HttpStatus.OK);	      
		       return response;
		   }

		
	
	
	
}
