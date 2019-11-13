package com.diviso.graeshoppe.report.web.rest;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.diviso.graeshoppe.report.domain.OrderAggregator;
import com.diviso.graeshoppe.report.domain.ReportSummary;
import com.diviso.graeshoppe.report.service.QueryService;

/**
 * REST controller for managing Reports.
 */
@RestController
@RequestMapping("/api")
public class QueryResource {
	
	@Autowired
	private  QueryService queryService;
	
	@GetMapping("/report/{date}/{storeId}")
	public ReportSummary createReportSummary(@PathVariable LocalDate date,@PathVariable String storeId) {
		return queryService.createReportSummary(date,storeId);
	}
	
	@GetMapping("/orderAggregator/{orderNumber}")
	public OrderAggregator getOrderAggregator(@PathVariable String orderNumber) {
		return queryService.getOrderAggregator(orderNumber);
	}
	
}
