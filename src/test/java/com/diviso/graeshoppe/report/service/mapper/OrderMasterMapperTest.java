package com.diviso.graeshoppe.report.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class OrderMasterMapperTest {

    private OrderMasterMapper orderMasterMapper;

    @BeforeEach
    public void setUp() {
        orderMasterMapper = new OrderMasterMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(orderMasterMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(orderMasterMapper.fromId(null)).isNull();
    }
}
