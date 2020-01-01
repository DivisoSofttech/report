package com.diviso.graeshoppe.report.service.dto;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.diviso.graeshoppe.report.domain.ComboItem} entity.
 */
public class ComboItemDTO implements Serializable {

    private Long id;

    private String comboItem;

    private Double quantity;


    private Long orderLineId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getComboItem() {
        return comboItem;
    }

    public void setComboItem(String comboItem) {
        this.comboItem = comboItem;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
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

        ComboItemDTO comboItemDTO = (ComboItemDTO) o;
        if (comboItemDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), comboItemDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ComboItemDTO{" +
            "id=" + getId() +
            ", comboItem='" + getComboItem() + "'" +
            ", quantity=" + getQuantity() +
            ", orderLineId=" + getOrderLineId() +
            "}";
    }
}
