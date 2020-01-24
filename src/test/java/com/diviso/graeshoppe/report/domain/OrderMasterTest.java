package com.diviso.graeshoppe.report.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.diviso.graeshoppe.report.web.rest.TestUtil;

public class OrderMasterTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(OrderMaster.class);
        OrderMaster orderMaster1 = new OrderMaster();
        orderMaster1.setId(1L);
        OrderMaster orderMaster2 = new OrderMaster();
        orderMaster2.setId(orderMaster1.getId());
        assertThat(orderMaster1).isEqualTo(orderMaster2);
        orderMaster2.setId(2L);
        assertThat(orderMaster1).isNotEqualTo(orderMaster2);
        orderMaster1.setId(null);
        assertThat(orderMaster1).isNotEqualTo(orderMaster2);
    }
}
