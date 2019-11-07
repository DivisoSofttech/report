package com.diviso.graeshoppe.report.repository.search;

import com.diviso.graeshoppe.report.domain.Sale;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the Sale entity.
 */
public interface SaleSearchRepository extends ElasticsearchRepository<Sale, Long> {
}
