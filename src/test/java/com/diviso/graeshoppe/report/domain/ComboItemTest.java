package com.diviso.graeshoppe.report.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.diviso.graeshoppe.report.web.rest.TestUtil;

public class ComboItemTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ComboItem.class);
        ComboItem comboItem1 = new ComboItem();
        comboItem1.setId(1L);
        ComboItem comboItem2 = new ComboItem();
        comboItem2.setId(comboItem1.getId());
        assertThat(comboItem1).isEqualTo(comboItem2);
        comboItem2.setId(2L);
        assertThat(comboItem1).isNotEqualTo(comboItem2);
        comboItem1.setId(null);
        assertThat(comboItem1).isNotEqualTo(comboItem2);
    }
}
