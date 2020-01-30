package com.diviso.graeshoppe.report.domain;

import java.time.Instant;

public class DetailedOrder {

	private String orderNumber;
	private Instant DateTime;
	private Double totalDue;
	public String getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}
	public Instant getDateTime() {
		return DateTime;
	}
	public void setDateTime(Instant dateTime) {
		DateTime = dateTime;
	}
	public Double getTotalDue() {
		return totalDue;
	}
	public void setTotalDue(Double totalDue) {
		this.totalDue = totalDue;
	}
	
}
