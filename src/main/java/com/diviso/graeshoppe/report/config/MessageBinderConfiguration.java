package com.diviso.graeshoppe.report.config;

import org.apache.kafka.streams.kstream.KStream;
import org.springframework.cloud.stream.annotation.Input;

import com.diviso.graeshoppe.order.avro.Order;
import com.diviso.graeshoppe.payment.avro.Payment;

public interface MessageBinderConfiguration {

	String PAYMENT="payment";
	
	String ORDER ="order";
	
	@Input(PAYMENT)
	KStream<String, Payment> payment();

	@Input(ORDER)
	KStream<String, Order> order();
	
}
