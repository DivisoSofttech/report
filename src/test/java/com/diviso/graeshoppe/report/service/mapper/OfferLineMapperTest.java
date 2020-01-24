package com.diviso.graeshoppe.report.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class OfferLineMapperTest {

    private OfferLineMapper offerLineMapper;

    @BeforeEach
    public void setUp() {
        offerLineMapper = new OfferLineMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(offerLineMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(offerLineMapper.fromId(null)).isNull();
    }
}
