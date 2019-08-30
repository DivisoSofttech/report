package com.diviso.graeshoppe.report.jasper;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.diviso.graeshoppe.report.domain.ReportSummary;
import com.diviso.graeshoppe.report.service.QueryService;
import com.diviso.graeshoppe.report.service.impl.QueryServiceImpl;

public class ReportSummaryFactory {
	
	@Autowired
	QueryService queryService;
	
	
	
	public static List<ReportSummary> getReportSummaryList() {
		
		List<ReportSummary> reportSummaryList= new ArrayList<ReportSummary>();
		
		/*reportSummary.getTypeAllCount();
	
		reportSummaryList.add(reportSummary);*/
		
		
		/*reportSummary.setTypeAllCount(123l);
		reportSummary.setTypeAllTotal(100.0);
		reportSummary.setTypeCardCount(25);
		reportSummary.setTypeCardTotal(500.0);
		reportSummary.setTypeCashCount(20);
		reportSummary.setTypeCashTotal(456.0);
		reportSummary.setTypeCollectionCount(22);
		reportSummary.setTypeCollectionTotal(560.0);
		reportSummary.setTypeDeliveryCount(12);
		reportSummary.setTypeDeliveryTotal(275.0);*/
		//reportSummaryList.add(reportSummary);
		
		return reportSummaryList;
		
	}

}
