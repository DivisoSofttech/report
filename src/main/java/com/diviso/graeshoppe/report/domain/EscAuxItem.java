package com.diviso.graeshoppe.report.domain;

public class EscAuxItem {

	private Long id;
	 private String auxItem;
	 
	 private Integer quantity;
	 private Double total;
	 
	 private Long productId;
	 private OrderLine orderLine;
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
