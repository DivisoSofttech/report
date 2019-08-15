package com.diviso.graeshoppe.report.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.diviso.graeshoppe.report.service.ReportService;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

@Service
@Transactional

public class ReportServiceImpl implements ReportService{
	
	@Autowired
    DataSource dataSource;


	@Override
	public byte[] getReportAsPdf(Long orderMasterId) throws JRException {
		JasperReport jr = JasperCompileManager.compileReport("testreport1.jrxml");
        
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

}
