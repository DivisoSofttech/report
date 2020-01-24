package com.diviso.graeshoppe.report.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.diviso.graeshoppe.report.web.rest.TestUtil;

public class TicketLineDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(TicketLineDTO.class);
        TicketLineDTO ticketLineDTO1 = new TicketLineDTO();
        ticketLineDTO1.setId(1L);
        TicketLineDTO ticketLineDTO2 = new TicketLineDTO();
        assertThat(ticketLineDTO1).isNotEqualTo(ticketLineDTO2);
        ticketLineDTO2.setId(ticketLineDTO1.getId());
        assertThat(ticketLineDTO1).isEqualTo(ticketLineDTO2);
        ticketLineDTO2.setId(2L);
        assertThat(ticketLineDTO1).isNotEqualTo(ticketLineDTO2);
        ticketLineDTO1.setId(null);
        assertThat(ticketLineDTO1).isNotEqualTo(ticketLineDTO2);
    }
}
