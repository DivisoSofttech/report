package com.diviso.graeshoppe.report.service;

import java.time.LocalDate;

import net.sf.jasperreports.engine.JRException;

public interface ReportService {

	byte[] getReportAsPdf(Long orderMasterId) throws JRException;

	byte[] getReportSummaryAsPdf(LocalDate date,String storeId) throws JRException;

}
