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

		/*
		 * Instant dateBegin = Instant.parse(LocalDate.now().toString() + "T00:00:00Z");
		 * Instant dateEnd = Instant.parse(LocalDate.now().toString() + "T23:59:59Z");
		 */

		Instant dateBegin = Instant.parse("2019-08-27T00:00:00Z");
		Instant dateEnd = Instant.parse("2019-08-27T23:59:59Z");
		ReportSummary reportSummary = new ReportSummary();
		reportSummary.setTypeAllCount(
				ReportQueryResourceApi.countAllOrdersByDateAndStoreIdUsingGET(dateBegin, dateEnd, storeId).getBody());

		List<String> allReference = ReportQueryResourceApi
				.findAllPaymentReferenceByDateAndStoreIdUsingGET(dateBegin, dateEnd, storeId).getBody();

		reportSummary.setTypeAllTotal(calculateTotal(convertStringToLong(getPaymentReference(allReference))));

		List<Long> paymentIdByCard = findByPaymentType("card", allReference);

		reportSummary.setTypeCardTotal(calculateTotal(paymentIdByCard));
		reportSummary.setTypeCardCount(paymentIdByCard.size());

		List<Long> paymentIdByCash = findByPaymentType("cash", allReference);
		reportSummary.setTypeCashTotal(calculateTotal(paymentIdByCash));
		reportSummary.setTypeCardCount(paymentIdByCash.size());
		reportSummary.setTypeDeliveryCount(ReportQueryResourceApi
				.countOrdersByStoreIdAndDeliveryTypeUsingGET(dateBegin, dateEnd, storeId, "delivery").getBody());

		List<String> deleveryReference = ReportQueryResourceApi
				.findAllPaymentRefByDeliveryTypeUsingGET(dateBegin, dateEnd, "delivery", storeId).getBody();
		reportSummary.setTypeDeliveryTotal(calculateTotal(convertStringToLong(getPaymentReference(deleveryReference))));
		reportSummary.setTypeCollectionCount(ReportQueryResourceApi
				.countOrdersByStoreIdAndDeliveryTypeUsingGET(dateBegin, dateEnd, storeId, "collection").getBody());
		List<String> collectionReference = ReportQueryResourceApi
				.findAllPaymentRefByDeliveryTypeUsingGET(dateBegin, dateEnd, "collection", storeId).getBody();
		reportSummary
				.setTypeCollectionTotal(calculateTotal(convertStringToLong(getPaymentReference(collectionReference))));
		return reportSummary;
	}

	Double calculateTotal(List<Long> paymentRefList) {
		Double amount = 0.0;
		for (Long payRef : paymentRefList) {
			PaymentDTO payment = paymentResourceApi.getPaymentUsingGET(payRef).getBody();

			amount = amount + payment.getAmount();
		}

		return amount;
	}

	List<String> getPaymentReference(List<String> payRef) {
		List<String> result = new ArrayList<String>();
		for (String reference : payRef) {

			if (reference != null) {
				result.add(reference);
			}

		}
		return result;
	}

	List<Long> convertStringToLong(List<String> list) {
		List<Long> longList = new ArrayList<>();
		for (String ref : list) {
			longList.add(Long.parseLong(ref));
		}

		return longList;
	}

	List<Long> findByPaymentType(String paymentType, List<String> paymentRefList) {
		List<Long> paymentIdList = new ArrayList<Long>();
		PaymentDTO payment = null;
		for (String payRef : paymentRefList) {
			payment = paymentResourceApi.getPaymentUsingGET(Long.parseLong(payRef)).getBody();
			if (payment.getPaymentType().equalsIgnoreCase(paymentType)) {
				paymentIdList.add(payment.getId());
			}

		}

		return paymentIdList;
	}

}
