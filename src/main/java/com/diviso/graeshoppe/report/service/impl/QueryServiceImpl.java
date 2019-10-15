package com.diviso.graeshoppe.report.service.impl;

import static org.elasticsearch.index.query.QueryBuilders.termQuery;

import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.data.elasticsearch.core.query.StringQuery;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import com.diviso.graeshoppe.report.client.order.api.ReportQueryResourceApi;
import com.diviso.graeshoppe.report.client.payment.api.PaymentResourceApi;
import com.diviso.graeshoppe.report.client.payment.model.PaymentDTO;
import com.diviso.graeshoppe.report.domain.AuxItem;
import com.diviso.graeshoppe.report.domain.ComboItem;
import com.diviso.graeshoppe.report.domain.OrderAggregator;
import com.diviso.graeshoppe.report.domain.OrderLine;
import com.diviso.graeshoppe.report.domain.OrderMaster;
import com.diviso.graeshoppe.report.domain.ReportSummary;
import com.diviso.graeshoppe.report.service.QueryService;
import com.github.vanroy.springdata.jest.JestElasticsearchTemplate;

import io.searchbox.client.JestClient;

@Service
@Transactional
public class QueryServiceImpl implements QueryService {
	
	
	private final JestClient jestClient;
	private final JestElasticsearchTemplate elasticsearchTemplate;

	int i = 0;
	Long count = 0L;
	private final Logger log = LoggerFactory.getLogger(QueryServiceImpl.class);

	public QueryServiceImpl(JestClient jestClient) {
		this.jestClient = jestClient;
		this.elasticsearchTemplate = new JestElasticsearchTemplate(this.jestClient);
	}

	@Autowired
	ElasticsearchOperations elasticsearchOperations;
	
	@Autowired
	ReportQueryResourceApi ReportQueryResourceApi;
	@Autowired
	PaymentResourceApi paymentResourceApi;
	
	
	

	public ReportSummary createReportSummary(LocalDate date,String storeId) {

		
		 Instant dateBegin = Instant.parse(date.toString() + "T00:00:00Z");
		  Instant dateEnd = Instant.parse(date.toString() + "T23:59:59Z");
		 

		/*Instant dateBegin = Instant.parse("2019-08-27T00:00:00Z");
		Instant dateEnd = Instant.parse("2019-08-27T23:59:59Z");*/
		ReportSummary reportSummary = new ReportSummary();
		
		reportSummary.setDate(date);
		reportSummary.setStoreId(storeId);
		reportSummary.setTypeAllCount(
				ReportQueryResourceApi.countAllOrdersByDateAndStoreIdUsingGET(dateBegin, dateEnd, storeId).getBody());

		List<String> allReference = ReportQueryResourceApi
				.findAllPaymentReferenceByDateAndStoreIdUsingGET(dateBegin, dateEnd, storeId).getBody();

		reportSummary.setTypeAllTotal(calculateTotal(convertStringToLong(getPaymentReference(allReference))));

		List<Long> paymentIdByCard = findByPaymentType("card", getPaymentReference(allReference));

		reportSummary.setTypeCardTotal(calculateTotal(paymentIdByCard));
		reportSummary.setTypeCardCount(paymentIdByCard.size());

		List<Long> paymentIdByCash = findByPaymentType("cash",getPaymentReference(allReference));
		reportSummary.setTypeCashCount(paymentIdByCash.size());
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

	@Override
	public OrderMaster findOrderMasterByOrderNumber(@PathVariable String orderNumber) {
	  
		StringQuery searchQuery = new StringQuery(termQuery("orderNumber.keyword", orderNumber).toString());
		return elasticsearchOperations.queryForObject(searchQuery, OrderMaster.class);
	  
	  }
	
	@Override
	public List<OrderLine> findOrderLineByOrderMaster(@PathVariable Long orderMasterId) {
		log.info("orderMaster Id is " + orderMasterId);
		SearchQuery searchQuery = new NativeSearchQueryBuilder().withQuery(termQuery("orderMaster.id", orderMasterId))
				.withIndices("reportorderline").build();
		return elasticsearchOperations.queryForList(searchQuery, OrderLine.class);
	  
	  }
	 
	@Override
	public List<ComboItem> findComboItemByOrderLine(@PathVariable Long orderLineId) {
		log.info("orderLine Id is " + orderLineId);
		SearchQuery searchQuery = new NativeSearchQueryBuilder().withQuery(termQuery("orderLine.id", orderLineId))
		.build();
		return elasticsearchOperations.queryForList(searchQuery, ComboItem.class);
	  
	  }
	
	@Override
	public List<AuxItem> findAuxItemByOrderLine(@PathVariable Long orderLineId) {
		log.info("orderLine Id is " + orderLineId);
		SearchQuery searchQuery = new NativeSearchQueryBuilder().withQuery(termQuery("orderLine.id", orderLineId))
		.build();
		return elasticsearchOperations.queryForList(searchQuery, AuxItem.class);
	  }
	
	@Override
	public OrderAggregator getOrderAggregator(String orderNumber) {

		OrderAggregator orderAggregator= new OrderAggregator();
		OrderMaster master=findOrderMasterByOrderNumber(orderNumber);
		orderAggregator.setOrderMaster(master);
		Set<OrderLine> orderLines=new HashSet<OrderLine>(findOrderLineByOrderMaster(master.getId()));
		orderAggregator.getOrderMaster().setOrderLines(orderLines);
		
		System.out.println("+++++++++++++++++++++++++++++++++++++++"+orderAggregator.getOrderLine());;
		
		
		for(OrderLine orderLine : orderAggregator.getOrderMaster().getOrderLines()) {
			
			List<ComboItem> combos=findComboItemByOrderLine(orderLine.getId());
			List<AuxItem> auxes=findAuxItemByOrderLine(orderLine.getId());
			orderLine.setComboItems(new HashSet<>(combos));
			orderLine.setAuxItems(new HashSet<>(auxes));
			
		}
		return orderAggregator;
	}

}
