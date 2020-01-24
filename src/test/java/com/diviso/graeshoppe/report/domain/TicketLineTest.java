package com.diviso.graeshoppe.report.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.diviso.graeshoppe.report.web.rest.TestUtil;

public class TicketLineTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TicketLine.class);
        TicketLine ticketLine1 = new TicketLine();
        ticketLine1.setId(1L);
        TicketLine ticketLine2 = new TicketLine();
        ticketLine2.setId(ticketLine1.getId());
        assertThat(ticketLine1).isEqualTo(ticketLine2);
        ticketLine2.setId(2L);
        assertThat(ticketLine1).isNotEqualTo(ticketLine2);
        ticketLine1.setId(null);
        assertThat(ticketLine1).isNotEqualTo(ticketLine2);
    }
}
