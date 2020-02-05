package com.diviso.graeshoppe.report.repository.search;

import com.diviso.graeshoppe.report.domain.Notification;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the Notification entity.
 */
public interface NotificationSearchRepository extends ElasticsearchRepository<Notification, Long> {
}
