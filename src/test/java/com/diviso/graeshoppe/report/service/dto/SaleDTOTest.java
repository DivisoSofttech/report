package com.diviso.graeshoppe.report.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.diviso.graeshoppe.report.web.rest.TestUtil;

public class SaleDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(SaleDTO.class);
        SaleDTO saleDTO1 = new SaleDTO();
        saleDTO1.setId(1L);
        SaleDTO saleDTO2 = new SaleDTO();
        assertThat(saleDTO1).isNotEqualTo(saleDTO2);
        saleDTO2.setId(saleDTO1.getId());
        assertThat(saleDTO1).isEqualTo(saleDTO2);
        saleDTO2.setId(2L);
        assertThat(saleDTO1).isNotEqualTo(saleDTO2);
        saleDTO1.setId(null);
        assertThat(saleDTO1).isNotEqualTo(saleDTO2);
    }
}
