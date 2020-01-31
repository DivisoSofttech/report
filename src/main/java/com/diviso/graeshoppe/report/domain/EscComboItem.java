package com.diviso.graeshoppe.report.domain;

public class EscComboItem {

	 private Long id;
	 private String comboItem;
	 
	  private Double quantity;
	  private Long productId;
	  
	  private OrderLine orderLine;

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

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public OrderLine getOrderLine() {
		return orderLine;
	}

	public void setOrderLine(OrderLine orderLine) {
		this.orderLine = orderLine;
	}
	  
	  
	
}
