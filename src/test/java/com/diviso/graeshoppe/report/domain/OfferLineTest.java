package com.diviso.graeshoppe.report.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.diviso.graeshoppe.report.web.rest.TestUtil;

public class OfferLineTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(OfferLine.class);
        OfferLine offerLine1 = new OfferLine();
        offerLine1.setId(1L);
        OfferLine offerLine2 = new OfferLine();
        offerLine2.setId(offerLine1.getId());
        assertThat(offerLine1).isEqualTo(offerLine2);
        offerLine2.setId(2L);
        assertThat(offerLine1).isNotEqualTo(offerLine2);
        offerLine1.setId(null);
        assertThat(offerLine1).isNotEqualTo(offerLine2);
    }
}
