package com.diviso.graeshoppe.report.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.diviso.graeshoppe.report.web.rest.TestUtil;

public class ComboItemDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ComboItemDTO.class);
        ComboItemDTO comboItemDTO1 = new ComboItemDTO();
        comboItemDTO1.setId(1L);
        ComboItemDTO comboItemDTO2 = new ComboItemDTO();
        assertThat(comboItemDTO1).isNotEqualTo(comboItemDTO2);
        comboItemDTO2.setId(comboItemDTO1.getId());
        assertThat(comboItemDTO1).isEqualTo(comboItemDTO2);
        comboItemDTO2.setId(2L);
        assertThat(comboItemDTO1).isNotEqualTo(comboItemDTO2);
        comboItemDTO1.setId(null);
        assertThat(comboItemDTO1).isNotEqualTo(comboItemDTO2);
    }
}
