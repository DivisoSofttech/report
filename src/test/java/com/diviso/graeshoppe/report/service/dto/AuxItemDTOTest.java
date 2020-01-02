package com.diviso.graeshoppe.report.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.diviso.graeshoppe.report.web.rest.TestUtil;

public class AuxItemDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(AuxItemDTO.class);
        AuxItemDTO auxItemDTO1 = new AuxItemDTO();
        auxItemDTO1.setId(1L);
        AuxItemDTO auxItemDTO2 = new AuxItemDTO();
        assertThat(auxItemDTO1).isNotEqualTo(auxItemDTO2);
        auxItemDTO2.setId(auxItemDTO1.getId());
        assertThat(auxItemDTO1).isEqualTo(auxItemDTO2);
        auxItemDTO2.setId(2L);
        assertThat(auxItemDTO1).isNotEqualTo(auxItemDTO2);
        auxItemDTO1.setId(null);
        assertThat(auxItemDTO1).isNotEqualTo(auxItemDTO2);
    }
}
