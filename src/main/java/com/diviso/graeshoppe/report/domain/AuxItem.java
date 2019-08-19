package com.diviso.graeshoppe.report.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

import org.springframework.data.elasticsearch.annotations.Document;
import java.io.Serializable;
import java.util.Objects;

/**
 * A AuxItem.
 */
@Entity
@Table(name = "aux_item")
@Document(indexName = "auxitem")
public class AuxItem implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "aux_item")
    private String auxItem;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "total")
    private Double total;

    @ManyToOne
    @JsonIgnoreProperties("auxItems")
    private OrderLine orderLine;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAuxItem() {
        return auxItem;
    }

    public AuxItem auxItem(String auxItem) {
        this.auxItem = auxItem;
        return this;
    }

    public void setAuxItem(String auxItem) {
        this.auxItem = auxItem;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public AuxItem quantity(Integer quantity) {
        this.quantity = quantity;
        return this;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getTotal() {
        return total;
    }

    public AuxItem total(Double total) {
        this.total = total;
        return this;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public OrderLine getOrderLine() {
        return orderLine;
    }

    public AuxItem orderLine(OrderLine orderLine) {
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
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AuxItem auxItem = (AuxItem) o;
        if (auxItem.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), auxItem.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "AuxItem{" +
            "id=" + getId() +
            ", auxItem='" + getAuxItem() + "'" +
            ", quantity=" + getQuantity() +
            ", total=" + getTotal() +
            "}";
    }
}
