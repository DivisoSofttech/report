package com.diviso.graeshoppe.report.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.diviso.graeshoppe.report.domain.ReportSummary;
import com.diviso.graeshoppe.report.client.order.api.ReportQueryResourceApi;
import com.diviso.graeshoppe.report.client.payment.api.PaymentResourceApi;
import com.diviso.graeshoppe.report.client.payment.model.PaymentDTO;
import com.diviso.graeshoppe.report.service.QueryService;
import java.time.Instant;
import java.time.LocalDate;
import java.util.*;

@Service
@Transactional
public class QueryServiceImpl implements QueryService {
	@Autowired
	ReportQueryResourceApi ReportQueryResourceApi;
	@Autowired
	PaymentResourceApi paymentResourceApi;

	public ReportSummary createReportSummary(String storeId) {
	
		/*Instant dateBegin = Instant.parse(LocalDate.now().toString() + "T00:00:00Z");
		Instant dateEnd = Instant.parse(LocalDate.now().toString() + "T23:59:59Z");*/
		
		Instant dateBegin = Instant.parse("2019-08-27T00:00:00Z");
		Instant dateEnd = Instant.parse("2019-08-27T23:59:59Z");
		ReportSummary reportSummary = new ReportSummary();
		reportSummary.setTypeAllCount(
				ReportQueryResourceApi.countAllOrdersByDateAndStoreIdUsingGET(dateBegin, dateEnd, storeId).getBody());
/*
		List<String> allReference = ReportQueryResourceApi
				.findAllPaymentReferenceByDateAndStoreIdUsingGET(dateBegin, dateEnd, storeId).getBody();
		
		
		reportSummary.setTypeAllTotal(calculateTotal(getPaymentReference(allReference)));

		reportSummary.setTypeDeliveryCount(ReportQueryResourceApi
				.countOrdersByStoreIdAndDeliveryTypeUsingGET(dateBegin, dateEnd, storeId, "delivery").getBody());

		List<String> deleveryReference=	ReportQueryResourceApi.findAllPaymentRefByDeliveryTypeUsingGET(dateBegin, dateEnd, "delivery", storeId).getBody();
		reportSummary.setTypeDeliveryTotal(calculateTotal(getPaymentReference(deleveryReference)));
		reportSummary.setTypeDeliveryCount(ReportQueryResourceApi
				.countOrdersByStoreIdAndDeliveryTypeUsingGET(dateBegin, dateEnd, storeId, "collection").getBody());
		List<String> collectionReference=	ReportQueryResourceApi.findAllPaymentRefByDeliveryTypeUsingGET(dateBegin, dateEnd, "collection", storeId).getBody();
		reportSummary.setTypeDeliveryTotal(calculateTotal(getPaymentReference(collectionReference)));	*/
		return reportSummary;
	}

	Double calculateTotal(List<String> paymentRefList) {
		Double amount = 0.0;
		for (String payRef : paymentRefList) {
			PaymentDTO payment = paymentResourceApi.getPaymentUsingGET(Long.parseLong(payRef)).getBody();

			amount = amount + payment.getAmount();
		}

		return amount;
	}
	
	List<String> getPaymentReference(List<String> payRef){
		List<String> result = new ArrayList<String>();
		for (String reference : payRef) {

			if (reference != null) {
				result.add(reference);
			}

		}
		return result;
	}

}
