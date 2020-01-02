package com.diviso.graeshoppe.report.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class AuxItemMapperTest {

    private AuxItemMapper auxItemMapper;

    @BeforeEach
    public void setUp() {
        auxItemMapper = new AuxItemMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(auxItemMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(auxItemMapper.fromId(null)).isNull();
    }
}
