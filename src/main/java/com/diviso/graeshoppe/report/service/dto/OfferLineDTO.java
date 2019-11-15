package com.diviso.graeshoppe.report.service.dto;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the OfferLine entity.
 */
public class OfferLineDTO implements Serializable {

    private Long id;

    private String offerRef;

    private Double discountAmount;


    private Long orderMasterId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOfferRef() {
        return offerRef;
    }

    public void setOfferRef(String offerRef) {
        this.offerRef = offerRef;
    }

    public Double getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(Double discountAmount) {
        this.discountAmount = discountAmount;
    }

    public Long getOrderMasterId() {
        return orderMasterId;
    }

    public void setOrderMasterId(Long orderMasterId) {
        this.orderMasterId = orderMasterId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        OfferLineDTO offerLineDTO = (OfferLineDTO) o;
        if (offerLineDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), offerLineDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "OfferLineDTO{" +
            "id=" + getId() +
            ", offerRef='" + getOfferRef() + "'" +
            ", discountAmount=" + getDiscountAmount() +
            ", orderMaster=" + getOrderMasterId() +
            "}";
    }
}
