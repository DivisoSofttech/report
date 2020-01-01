package com.diviso.graeshoppe.report.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class ComboItemMapperTest {

    private ComboItemMapper comboItemMapper;

    @BeforeEach
    public void setUp() {
        comboItemMapper = new ComboItemMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(comboItemMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(comboItemMapper.fromId(null)).isNull();
    }
}
