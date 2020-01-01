package com.diviso.graeshoppe.report.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import org.springframework.data.elasticsearch.annotations.FieldType;
import java.io.Serializable;

/**
 * A OfferLine.
 */
@Entity
@Table(name = "offer_line")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@org.springframework.data.elasticsearch.annotations.Document(indexName = "reportofferline")
public class OfferLine implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @org.springframework.data.elasticsearch.annotations.Field(type = FieldType.Keyword)
    private Long id;

    @Column(name = "offer_ref")
    private String offerRef;

    @Column(name = "discount_amount")
    private Double discountAmount;

    @ManyToOne
    @JsonIgnoreProperties("offerLines")
    private OrderMaster orderMaster;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOfferRef() {
        return offerRef;
    }

    public OfferLine offerRef(String offerRef) {
        this.offerRef = offerRef;
        return this;
    }

    public void setOfferRef(String offerRef) {
        this.offerRef = offerRef;
    }

    public Double getDiscountAmount() {
        return discountAmount;
    }

    public OfferLine discountAmount(Double discountAmount) {
        this.discountAmount = discountAmount;
        return this;
    }

    public void setDiscountAmount(Double discountAmount) {
        this.discountAmount = discountAmount;
    }

    public OrderMaster getOrderMaster() {
        return orderMaster;
    }

    public OfferLine orderMaster(OrderMaster orderMaster) {
        this.orderMaster = orderMaster;
        return this;
    }

    public void setOrderMaster(OrderMaster orderMaster) {
        this.orderMaster = orderMaster;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof OfferLine)) {
            return false;
        }
        return id != null && id.equals(((OfferLine) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "OfferLine{" +
            "id=" + getId() +
            ", offerRef='" + getOfferRef() + "'" +
            ", discountAmount=" + getDiscountAmount() +
            "}";
    }
}
