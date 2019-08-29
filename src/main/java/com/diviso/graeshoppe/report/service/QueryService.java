package com.diviso.graeshoppe.report.service;
import java.time.LocalDate;

import com.diviso.graeshoppe.report.domain.ReportSummary;
public interface QueryService {
	
	ReportSummary createReportSummary(LocalDate date,String storeId);

}
