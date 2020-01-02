package com.diviso.graeshoppe.report.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.diviso.graeshoppe.report.web.rest.TestUtil;

public class OrderMasterDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(OrderMasterDTO.class);
        OrderMasterDTO orderMasterDTO1 = new OrderMasterDTO();
        orderMasterDTO1.setId(1L);
        OrderMasterDTO orderMasterDTO2 = new OrderMasterDTO();
        assertThat(orderMasterDTO1).isNotEqualTo(orderMasterDTO2);
        orderMasterDTO2.setId(orderMasterDTO1.getId());
        assertThat(orderMasterDTO1).isEqualTo(orderMasterDTO2);
        orderMasterDTO2.setId(2L);
        assertThat(orderMasterDTO1).isNotEqualTo(orderMasterDTO2);
        orderMasterDTO1.setId(null);
        assertThat(orderMasterDTO1).isNotEqualTo(orderMasterDTO2);
    }
}
