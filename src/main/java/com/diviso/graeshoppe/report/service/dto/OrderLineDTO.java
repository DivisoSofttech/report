package com.diviso.graeshoppe.report.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the OrderLine entity.
 */
public class OrderLineDTO implements Serializable {

    private Long id;

    private String item;

    private Integer quantity;

    private Double total;

    private Long orderMasterId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
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

        OrderLineDTO orderLineDTO = (OrderLineDTO) o;
        if (orderLineDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), orderLineDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "OrderLineDTO{" +
            "id=" + getId() +
            ", item='" + getItem() + "'" +
            ", quantity=" + getQuantity() +
            ", total=" + getTotal() +
            ", orderMaster=" + getOrderMasterId() +
            "}";
    }
}
