package com.diviso.graeshoppe.report.web.rest;

import com.diviso.graeshoppe.report.config.KafkaProperties;
import com.diviso.graeshoppe.report.service.OrderMasterService;

import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.diviso.graeshoppe.order.avro.Order;
import java.time.Duration;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class OrderSyncService {

	private final Logger log = LoggerFactory.getLogger(OrderSyncService.class);

	@Value("${topic.order.destination}")
	private String orderTopic;
	private final KafkaProperties kafkaProperties;
	private ExecutorService sseExecutorService = Executors.newCachedThreadPool();
	
	@Autowired
	private OrderMasterService orderMasterService;

	public OrderSyncService(KafkaProperties kafkaProperties) {
		this.kafkaProperties = kafkaProperties;
		subscribeToOrder();
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
						log.info("Order is consuming "+record);
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

}
