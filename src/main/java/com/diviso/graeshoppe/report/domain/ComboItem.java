package com.diviso.graeshoppe.report.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import org.springframework.data.elasticsearch.annotations.FieldType;
import java.io.Serializable;

/**
 * A ComboItem.
 */
@Entity
@Table(name = "combo_item")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@org.springframework.data.elasticsearch.annotations.Document(indexName = "comboitem")
public class ComboItem implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @org.springframework.data.elasticsearch.annotations.Field(type = FieldType.Keyword)
    private Long id;

    @Column(name = "combo_item")
    private String comboItem;

    @Column(name = "quantity")
    private Double quantity;

    @Column(name = "product_id")
    private Long productId;

    @ManyToOne
    @JsonIgnoreProperties("comboItems")
    private OrderLine orderLine;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getComboItem() {
        return comboItem;
    }

    public ComboItem comboItem(String comboItem) {
        this.comboItem = comboItem;
        return this;
    }

    public void setComboItem(String comboItem) {
        this.comboItem = comboItem;
    }

    public Double getQuantity() {
        return quantity;
    }

    public ComboItem quantity(Double quantity) {
        this.quantity = quantity;
        return this;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public Long getProductId() {
        return productId;
    }

    public ComboItem productId(Long productId) {
        this.productId = productId;
        return this;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public OrderLine getOrderLine() {
        return orderLine;
    }

    public ComboItem orderLine(OrderLine orderLine) {
        this.orderLine = orderLine;
        return this;
    }

    public void setOrderLine(OrderLine orderLine) {
        this.orderLine = orderLine;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ComboItem)) {
            return false;
        }
        return id != null && id.equals(((ComboItem) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "ComboItem{" +
            "id=" + getId() +
            ", comboItem='" + getComboItem() + "'" +
            ", quantity=" + getQuantity() +
            ", productId=" + getProductId() +
            "}";
    }
}
