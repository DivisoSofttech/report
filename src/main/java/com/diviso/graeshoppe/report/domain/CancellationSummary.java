package com.diviso.graeshoppe.report.domain;

import java.time.LocalDate;

public class CancellationSummary {

private LocalDate date;
	
	private String storeId;
	
	private Long allCardCount;
	private Double allCardTotal;
	private Double allRefundAmount;
	
	private Long deliveryCardCount;
	private Double deliveryCardTotal;
	private Double deliveryRefundAmount;

	private Long collectionCardCount;
	private Double collectionCardTotal;
	private Double collectionRefundAmount;
	
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public String getStoreId() {
		return storeId;
	}
	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}
	public Long getAllCardCount() {
		return allCardCount;
	}
	public void setAllCardCount(Long allCardCount) {
		this.allCardCount = allCardCount;
	}
	public Double getAllCardTotal() {
		return allCardTotal;
	}
	public void setAllCardTotal(Double allCardTotal) {
		this.allCardTotal = allCardTotal;
	}
	public Double getAllRefundAmount() {
		return allRefundAmount;
	}
	public void setAllRefundAmount(Double allRefundAmount) {
		this.allRefundAmount = allRefundAmount;
	}
	public Long getDeliveryCardCount() {
		return deliveryCardCount;
	}
	public void setDeliveryCardCount(Long deliveryCardCount) {
		this.deliveryCardCount = deliveryCardCount;
	}
	public Double getDeliveryCardTotal() {
		return deliveryCardTotal;
	}
	public void setDeliveryCardTotal(Double deliveryCardTotal) {
		this.deliveryCardTotal = deliveryCardTotal;
	}
	public Double getDeliveryRefundAmount() {
		return deliveryRefundAmount;
	}
	public void setDeliveryRefundAmount(Double deliveryRefundAmount) {
		this.deliveryRefundAmount = deliveryRefundAmount;
	}
	public Long getCollectionCardCount() {
		return collectionCardCount;
	}
	public void setCollectionCardCount(Long collectionCardCount) {
		this.collectionCardCount = collectionCardCount;
	}
	public Double getCollectionCardTotal() {
		return collectionCardTotal;
	}
	public void setCollectionCardTotal(Double collectionCardTotal) {
		this.collectionCardTotal = collectionCardTotal;
	}
	public Double getCollectionRefundAmount() {
		return collectionRefundAmount;
	}
	public void setCollectionRefundAmount(Double collectionRefundAmount) {
		this.collectionRefundAmount = collectionRefundAmount;
	}
	

	
	
}
