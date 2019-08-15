package com.diviso.graeshoppe.report.service;

import net.sf.jasperreports.engine.JRException;

public interface ReportService {

	byte[] getReportAsPdf(Long orderMasterId) throws JRException;

}
