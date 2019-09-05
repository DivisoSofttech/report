package com.diviso.graeshoppe.report.web.rest;

import org.apache.kafka.streams.kstream.KStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;

import com.diviso.graeshoppe.order.avro.Order;
import com.diviso.graeshoppe.payment.avro.Payment;
import com.diviso.graeshoppe.report.config.MessageBinderConfiguration;
import com.diviso.graeshoppe.report.service.OrderMasterService;
import com.diviso.graeshoppe.report.service.dto.OrderMasterDTO;

@EnableBinding(MessageBinderConfiguration.class)
public class StreamConsumerService {

	private final Logger LOG = LoggerFactory.getLogger(StreamConsumerService.class);

	@Autowired
	private OrderMasterService orderMasterService;

	@StreamListener(MessageBinderConfiguration.PAYMENT)
	public void listenToPayment(KStream<String, Payment> message) {
		message.foreach((key, value) -> {
			System.out.println("payment Value consumed is " + value);
//			OrderMasterDTO orderMasterDTO = orderMasterService.findByOrderNumber(value.getTargetId()).get();
//			if (orderMasterDTO != null) {
//				if (!value.getPaymentType().equals("cod")) {
//					LOG.info("Order paid");
//					orderMasterDTO.setOrderStatus("ORDER PAID");
//				} else {
//					LOG.info("Order Not paid");
//					orderMasterDTO.setOrderStatus("ORDER NOT PAID");
//				}
//				orderMasterService.save(orderMasterDTO);
//			}
//		});
	}

	@StreamListener(MessageBinderConfiguration.ORDER)
	public void listenToOrder(KStream<String, Order> message) {
		message.foreach((key, value) -> {
			System.out.println("order Value consumed is " + value);
			orderMasterService.convertAndSaveOrderMaster(value);
		});
	}

}
