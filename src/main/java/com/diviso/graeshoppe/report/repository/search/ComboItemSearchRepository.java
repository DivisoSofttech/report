package com.diviso.graeshoppe.report.repository.search;

import com.diviso.graeshoppe.report.domain.ComboItem;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the {@link ComboItem} entity.
 */
public interface ComboItemSearchRepository extends ElasticsearchRepository<ComboItem, Long> {
}
