package com.diviso.graeshoppe.report.service.dto;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the AuxItem entity.
 */
public class AuxItemDTO implements Serializable {

    private Long id;

    private String auxItem;

    private Integer quantity;

    private Double total;


    private Long orderLineId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAuxItem() {
        return auxItem;
    }

    public void setAuxItem(String auxItem) {
        this.auxItem = auxItem;
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

    public Long getOrderLineId() {
        return orderLineId;
    }

    public void setOrderLineId(Long orderLineId) {
        this.orderLineId = orderLineId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        AuxItemDTO auxItemDTO = (AuxItemDTO) o;
        if (auxItemDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), auxItemDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "AuxItemDTO{" +
            "id=" + getId() +
            ", auxItem='" + getAuxItem() + "'" +
            ", quantity=" + getQuantity() +
            ", total=" + getTotal() +
            ", orderLine=" + getOrderLineId() +
            "}";
    }
}
