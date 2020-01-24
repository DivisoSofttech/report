package com.diviso.graeshoppe.report.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.diviso.graeshoppe.report.web.rest.TestUtil;

public class OfferLineDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(OfferLineDTO.class);
        OfferLineDTO offerLineDTO1 = new OfferLineDTO();
        offerLineDTO1.setId(1L);
        OfferLineDTO offerLineDTO2 = new OfferLineDTO();
        assertThat(offerLineDTO1).isNotEqualTo(offerLineDTO2);
        offerLineDTO2.setId(offerLineDTO1.getId());
        assertThat(offerLineDTO1).isEqualTo(offerLineDTO2);
        offerLineDTO2.setId(2L);
        assertThat(offerLineDTO1).isNotEqualTo(offerLineDTO2);
        offerLineDTO1.setId(null);
        assertThat(offerLineDTO1).isNotEqualTo(offerLineDTO2);
    }
}
