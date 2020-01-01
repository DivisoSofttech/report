package com.diviso.graeshoppe.report.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class SaleMapperTest {

    private SaleMapper saleMapper;

    @BeforeEach
    public void setUp() {
        saleMapper = new SaleMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(saleMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(saleMapper.fromId(null)).isNull();
    }
}
