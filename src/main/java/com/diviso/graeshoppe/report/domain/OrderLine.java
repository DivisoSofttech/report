package com.diviso.graeshoppe.report.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import org.springframework.data.elasticsearch.annotations.FieldType;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A OrderLine.
 */
@Entity
@Table(name = "order_line")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@org.springframework.data.elasticsearch.annotations.Document(indexName = "orderline")
public class OrderLine implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @org.springframework.data.elasticsearch.annotations.Field(type = FieldType.Keyword)
    private Long id;

    @Column(name = "item")
    private String item;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "total")
    private Double total;

    @Column(name = "product_id")
    private Long productId;

    @OneToMany(mappedBy = "orderLine")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<AuxItem> auxItems = new HashSet<>();

    @OneToMany(mappedBy = "orderLine")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<ComboItem> comboItems = new HashSet<>();

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

    public Long getProductId() {
        return productId;
    }

    public OrderLine productId(Long productId) {
        this.productId = productId;
        return this;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Set<AuxItem> getAuxItems() {
        return auxItems;
    }

    public OrderLine auxItems(Set<AuxItem> auxItems) {
        this.auxItems = auxItems;
        return this;
    }

    public OrderLine addAuxItem(AuxItem auxItem) {
        this.auxItems.add(auxItem);
        auxItem.setOrderLine(this);
        return this;
    }

    public OrderLine removeAuxItem(AuxItem auxItem) {
        this.auxItems.remove(auxItem);
        auxItem.setOrderLine(null);
        return this;
    }

    public void setAuxItems(Set<AuxItem> auxItems) {
        this.auxItems = auxItems;
    }

    public Set<ComboItem> getComboItems() {
        return comboItems;
    }

    public OrderLine comboItems(Set<ComboItem> comboItems) {
        this.comboItems = comboItems;
        return this;
    }

    public OrderLine addComboItem(ComboItem comboItem) {
        this.comboItems.add(comboItem);
        comboItem.setOrderLine(this);
        return this;
    }

    public OrderLine removeComboItem(ComboItem comboItem) {
        this.comboItems.remove(comboItem);
        comboItem.setOrderLine(null);
        return this;
    }

    public void setComboItems(Set<ComboItem> comboItems) {
        this.comboItems = comboItems;
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
        if (!(o instanceof OrderLine)) {
            return false;
        }
        return id != null && id.equals(((OrderLine) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "OrderLine{" +
            "id=" + getId() +
            ", item='" + getItem() + "'" +
            ", quantity=" + getQuantity() +
            ", total=" + getTotal() +
            ", productId=" + getProductId() +
            "}";
    }
}
