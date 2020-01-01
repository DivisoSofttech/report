package com.diviso.graeshoppe.report.repository.search;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Configuration;

/**
 * Configure a Mock version of {@link ComboItemSearchRepository} to test the
 * application without starting Elasticsearch.
 */
@Configuration
public class ComboItemSearchRepositoryMockConfiguration {

    @MockBean
    private ComboItemSearchRepository mockComboItemSearchRepository;

}
