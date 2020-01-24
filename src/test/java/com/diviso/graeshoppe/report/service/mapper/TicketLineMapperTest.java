package com.diviso.graeshoppe.report.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class TicketLineMapperTest {

    private TicketLineMapper ticketLineMapper;

    @BeforeEach
    public void setUp() {
        ticketLineMapper = new TicketLineMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(ticketLineMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(ticketLineMapper.fromId(null)).isNull();
    }
}
