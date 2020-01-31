package com.diviso.graeshoppe.report.domain;

import java.util.HashSet;
import java.util.Set;

public class EscOrderLine {

	private Long id;
	 private String item;
	 
	 private Double total;
	 private Long productId;
	 
	 private Set<AuxItem> auxItems = new HashSet<>();
	 private Set<ComboItem> comboItems = new HashSet<>();
	 
	 private OrderMaster orderMaster;
	
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
	public Double getTotal() {
		return total;
	}
	public void setTotal(Double total) {
		this.total = total;
	}
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	public Set<AuxItem> getAuxItems() {
		return auxItems;
	}
	public void setAuxItems(Set<AuxItem> auxItems) {
		this.auxItems = auxItems;
	}
	public Set<ComboItem> getComboItems() {
		return comboItems;
	}
	public void setComboItems(Set<ComboItem> comboItems) {
		this.comboItems = comboItems;
	}
	public OrderMaster getOrderMaster() {
		return orderMaster;
	}
	public void setOrderMaster(OrderMaster orderMaster) {
		this.orderMaster = orderMaster;
	}
}
