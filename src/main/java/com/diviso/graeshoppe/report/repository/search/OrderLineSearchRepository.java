package com.diviso.graeshoppe.report.repository.search;

import com.diviso.graeshoppe.report.domain.OrderLine;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the OrderLine entity.
 */
public interface OrderLineSearchRepository extends ElasticsearchRepository<OrderLine, Long> {
}
