package com.diviso.graeshoppe.report.repository.search;

import com.diviso.graeshoppe.report.domain.ComboItem;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the ComboItem entity.
 */
public interface ComboItemSearchRepository extends ElasticsearchRepository<ComboItem, Long> {
}
