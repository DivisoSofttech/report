package com.diviso.graeshoppe.report.repository.search;

import com.diviso.graeshoppe.report.domain.OfferLine;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the OfferLine entity.
 */
public interface OfferLineSearchRepository extends ElasticsearchRepository<OfferLine, Long> {
}
