package com.diviso.graeshoppe.report.domain;

import java.util.List;

public class OrderAggregator {

	private OrderMaster orderMaster;
	
	private List<OrderLine> orderLine;
	
	private List<AuxItem> auxitem;
	
	private List<ComboItem> comboItem;

	public OrderMaster getOrderMaster() {
		return orderMaster;
	}

	public void setOrderMaster(OrderMaster orderMaster) {
		this.orderMaster = orderMaster;
	}

	public List<OrderLine> getOrderLine() {
		return orderLine;
	}

	public void setOrderLine(List<OrderLine> orderLine) {
		this.orderLine = orderLine;
	}

	public List<AuxItem> getAuxitem() {
		return auxitem;
	}

	public void setAuxitem(List<AuxItem> auxitem) {
		this.auxitem = auxitem;
	}

	public List<ComboItem> getComboItem() {
		return comboItem;
	}

	public void setComboItem(List<ComboItem> comboItem) {
		this.comboItem = comboItem;
	}


	
	
}