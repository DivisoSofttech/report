package com.diviso.graeshoppe.report.repository.search;

import com.diviso.graeshoppe.report.domain.AuxItem;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the AuxItem entity.
 */
public interface AuxItemSearchRepository extends ElasticsearchRepository<AuxItem, Long> {
}
