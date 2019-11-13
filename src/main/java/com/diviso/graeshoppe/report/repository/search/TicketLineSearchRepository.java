package com.diviso.graeshoppe.report.repository.search;

import com.diviso.graeshoppe.report.domain.TicketLine;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the TicketLine entity.
 */
public interface TicketLineSearchRepository extends ElasticsearchRepository<TicketLine, Long> {
}
