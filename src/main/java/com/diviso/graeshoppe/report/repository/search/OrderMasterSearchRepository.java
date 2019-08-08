package com.diviso.graeshoppe.report.repository.search;

import com.diviso.graeshoppe.report.domain.OrderMaster;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the OrderMaster entity.
 */
public interface OrderMasterSearchRepository extends ElasticsearchRepository<OrderMaster, Long> {
}
