package com.diviso.graeshoppe.report.service;

import com.diviso.graeshoppe.report.config.KafkaProperties;
import com.diviso.graeshoppe.report.service.dto.OrderMasterDTO;
import com.diviso.graeshoppe.order.avro.*;
import com.diviso.graeshoppe.avro.*;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.diviso.graeshoppe.order.avro.Order;

import java.text.NumberFormat;
import java.time.Duration;
import java.time.Instant;
import java.util.Collections;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class OrderSyncService {

	private final Logger log = LoggerFactory.getLogger(OrderSyncService.class);

	@Value("${topic.orderstate.destination}")
	private String orderStateTopic;
	@Value("${topic.order.destination}")
	private String orderTopic;
	@Value("${topic.approvalInfo.destination}")
	private String approvalInfoTopic;
	@Value("${topic.cancellation.destination}")
	private String cancellationTopic;
	private final KafkaProperties kafkaProperties;
	private ExecutorService sseExecutorService = Executors.newCachedThreadPool();

	@Autowired
	private OrderMasterService orderMasterService;

	public OrderSyncService(KafkaProperties kafkaProperties) {
		this.kafkaProperties = kafkaProperties;
	}

	public void subscribeToOrderState() {
		log.debug("REST request to consume records from Kafka topics {}", orderStateTopic);
		Map<String, Object> consumerProps = kafkaProperties.getConsumerProps();
		sseExecutorService.execute(() -> {
			KafkaConsumer<String, OrderState> consumer = new KafkaConsumer<>(consumerProps);
			consumer.subscribe(Collections.singletonList(orderStateTopic));
			boolean exitLoop = false;
			while (!exitLoop) {
				try {
					ConsumerRecords<String, OrderState> records = consumer.poll(Duration.ofSeconds(5));
					records.forEach(record -> {
						log.info("orderStateTopic  is consuming " + record);
						Optional<OrderMasterDTO> orderMaster = orderMasterService
								.findByOrderNumber(record.value().getTargetId());
						if (orderMaster.isPresent()) {
							orderMaster.get().setOrderStatus(record.value().getState());
							orderMasterService.save(orderMaster.get());
						} else {
							log.info("The order doesn't found with ref " + record.value().getTargetId());

						}

					});

				} catch (Exception ex) {
					ex.printStackTrace();
					log.trace("Complete with error {}", ex.getMessage(), ex);
					exitLoop = true;
				}
			}
			log.info("Consumer is going to close");
			consumer.close();
		});
	}

	public void subscribeToCancellationRequest() {
		log.debug("REST request to consume records from Kafka topics {}", cancellationTopic);
		Map<String, Object> consumerProps = kafkaProperties.getConsumerProps();
		sseExecutorService.execute(() -> {
			KafkaConsumer<String, CancellationRequest> consumer = new KafkaConsumer<>(consumerProps);
			consumer.subscribe(Collections.singletonList(cancellationTopic));
			boolean exitLoop = false;
			while (!exitLoop) {
				try {
					ConsumerRecords<String, CancellationRequest> records = consumer.poll(Duration.ofSeconds(5));
					NumberFormat formatter = NumberFormat.getInstance();
					formatter.setMinimumFractionDigits(1);
					formatter.setMinimumFractionDigits(2);
					formatter.setMaximumFractionDigits(2);
					records.forEach(record -> {
						log.info("CancellationRequest  is consuming " + record);
						Optional<OrderMasterDTO> orderMaster = orderMasterService
								.findByOrderNumber(record.value().getOrderId());
						if (orderMaster.isPresent()) {
							orderMaster.get().setRefundedAmount(record.value().getAmount());
							orderMaster.get().setTotalDue(Double.parseDouble(
									formatter.format(orderMaster.get().getTotalDue() - record.value().getAmount())));
							orderMaster.get().setCancellationRef(record.value().getId());
							orderMasterService.save(orderMaster.get());
						} else {
							log.info("The order doesn't found with ref " + record.value().getOrderId());

						}

					});

				} catch (Exception ex) {
					ex.printStackTrace();
					log.trace("Complete with error {}", ex.getMessage(), ex);
					exitLoop = true;
				}
			}
			log.info("Consumer is going to close");
			consumer.close();
		});
	}

	public void subscribeToApprovalInfo() {
		log.debug("REST request to consume records from Kafka topics {}", approvalInfoTopic);
		Map<String, Object> consumerProps = kafkaProperties.getConsumerProps();
		sseExecutorService.execute(() -> {
			KafkaConsumer<String, ApprovalInfo> consumer = new KafkaConsumer<>(consumerProps);
			consumer.subscribe(Collections.singletonList(approvalInfoTopic));
			boolean exitLoop = false;
			while (!exitLoop) {
				try {
					ConsumerRecords<String, ApprovalInfo> records = consumer.poll(Duration.ofSeconds(5));
					records.forEach(record -> {
						log.info("Approval Info  is consuming " + record);
						OrderMasterDTO orderMaster = orderMasterService.findByOrderNumber(record.value().getOrderId())
								.get();
						orderMaster.setOrderAcceptedAt(Instant.ofEpochMilli(record.value().getAcceptedAt()));
						if (record.value().getExpectedDelivery() != 0) {
							orderMaster.setExpectedDelivery(Instant.ofEpochMilli(record.value().getExpectedDelivery()));
						}
						orderMaster.setOrderStatus("payment-processed-approved");
						orderMasterService.save(orderMaster);
					});

				} catch (Exception ex) {
					ex.printStackTrace();
					log.trace("Complete with error {}", ex.getMessage(), ex);
					exitLoop = true;
				}
			}
			log.info("Consumer is going to close");
			consumer.close();
		});
	}

	public void subscribeToOrder() {
		log.debug("REST request to consume records from Kafka topics {}", orderTopic);
		Map<String, Object> consumerProps = kafkaProperties.getConsumerProps();
		sseExecutorService.execute(() -> {
			KafkaConsumer<String, Order> consumer = new KafkaConsumer<>(consumerProps);
			consumer.subscribe(Collections.singletonList(orderTopic));
			boolean exitLoop = false;
			while (!exitLoop) {
				try {
					ConsumerRecords<String, Order> records = consumer.poll(Duration.ofSeconds(5));
					records.forEach(record -> {
						log.info("Order is consuming " + record);
						log.info("Order master service is " + orderMasterService + " value is " + record.value());
						orderMasterService.convertAndSaveOrderMaster(record.value());
					});

				} catch (Exception ex) {
					ex.printStackTrace();
					log.trace("Complete with error {}", ex.getMessage(), ex);
					exitLoop = true;
				}
			}
			log.info("Consumer is going to close");
			consumer.close();
		});
	}

	public void startConsumers() {
		subscribeToApprovalInfo();
		subscribeToOrder();
		subscribeToCancellationRequest();
		subscribeToOrderState();
	}

}
