package com.diviso.graeshoppe.report.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.diviso.graeshoppe.report.web.rest.TestUtil;

public class AuxItemTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(AuxItem.class);
        AuxItem auxItem1 = new AuxItem();
        auxItem1.setId(1L);
        AuxItem auxItem2 = new AuxItem();
        auxItem2.setId(auxItem1.getId());
        assertThat(auxItem1).isEqualTo(auxItem2);
        auxItem2.setId(2L);
        assertThat(auxItem1).isNotEqualTo(auxItem2);
        auxItem1.setId(null);
        assertThat(auxItem1).isNotEqualTo(auxItem2);
    }
}
