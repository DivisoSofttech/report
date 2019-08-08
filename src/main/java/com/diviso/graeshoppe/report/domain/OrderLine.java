package com.diviso.graeshoppe.report.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;

import javax.persistence.*;

import org.springframework.data.elasticsearch.annotations.Document;
import java.io.Serializable;
import java.util.Objects;

/**
 * OrderLine entity
 * Author Neeraja
 */
@ApiModel(description = "OrderLine entity Author Neeraja")
@Entity
@Table(name = "order_line")
@Document(indexName = "orderline")
public class OrderLine implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "item")
    private String item;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "total")
    private Double total;

    @ManyToOne
    @JsonIgnoreProperties("orderLines")
    private OrderMaster orderMaster;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getItem() {
        return item;
    }

    public OrderLine item(String item) {
        this.item = item;
        return this;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public OrderLine quantity(Integer quantity) {
        this.quantity = quantity;
        return this;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getTotal() {
        return total;
    }

    public OrderLine total(Double total) {
        this.total = total;
        return this;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public OrderMaster getOrderMaster() {
        return orderMaster;
    }

    public OrderLine orderMaster(OrderMaster orderMaster) {
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
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        OrderLine orderLine = (OrderLine) o;
        if (orderLine.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), orderLine.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "OrderLine{" +
            "id=" + getId() +
            ", item='" + getItem() + "'" +
            ", quantity=" + getQuantity() +
            ", total=" + getTotal() +
            "}";
    }
}
