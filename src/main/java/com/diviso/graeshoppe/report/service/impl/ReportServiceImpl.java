package com.diviso.graeshoppe.report.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.diviso.graeshoppe.report.client.order.model.Reportsummary;
import com.diviso.graeshoppe.report.domain.ReportSummary;

import com.diviso.graeshoppe.report.service.QueryService;
import com.diviso.graeshoppe.report.service.ReportService;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Service
@Transactional

public class ReportServiceImpl implements ReportService{
	
	@Autowired
    DataSource dataSource;
	
	@Autowired
	QueryService queryService;
	
	private static List<ReportSummary> reportSummaryList = new ArrayList<ReportSummary>();
	


	@Override
	public byte[] getReportAsPdf(Long orderMasterId) throws JRException {
		JasperReport jr = JasperCompileManager.compileReport("report.jrxml");
        
	      //Preparing parameters
          Map<String, Object> parameters = new HashMap<String, Object>();
          parameters.put("order_master_id", orderMasterId);
          Connection conn = null;
          try {
              conn = dataSource.getConnection();
          } catch (SQLException e) {
              // TODO Auto-generated catch block
              e.printStackTrace();
          }
	  JasperPrint jp = JasperFillManager.fillReport(jr, parameters, conn);
          
	  return JasperExportManager.exportReportToPdf(jp);

	}


	@Override
	public byte[] getReportSummaryAsPdf(LocalDate date,String storeId) throws JRException {
		JasperReport jr = JasperCompileManager.compileReport("reportsummary.jrxml");
		
		ReportSummary reportSummary = queryService.createReportSummary(date, storeId);
		reportSummaryList.add(reportSummary);
       JRBeanCollectionDataSource collectionDatasource = new JRBeanCollectionDataSource(ReportServiceImpl.getReportSummaryList());
                      
        
//Preparing parameters
        Map<String, Object> parameters = new HashMap<String, Object>();
                      
        JasperPrint jp = JasperFillManager.fillReport(jr, parameters, collectionDatasource);
      
        return JasperExportManager.exportReportToPdf(jp);

		
	}


	public static List<ReportSummary> getReportSummaryList() {
		return reportSummaryList;
	}


	public static void setReportSummaryList(List<ReportSummary> reportSummaryList) {
		ReportServiceImpl.reportSummaryList = reportSummaryList;
	}

}
