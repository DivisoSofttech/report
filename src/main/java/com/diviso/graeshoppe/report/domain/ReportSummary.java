package com.diviso.graeshoppe.report.domain;

import java.time.LocalDate;

public class ReportSummary {
	
	private LocalDate fromDate;
	private LocalDate toDate;
	private String storeId;
	
	private Integer typeAllCount;
	private Double typeAllTotal;

 
	private Long typeDeliveryCount;
	private Double typeDeliveryTotal;

	private Long typeCollectionCount;
	private Double typeCollectionTotal;

	
	private Long typeCashCount;
	private Double typeCashTotal;

	private Long typeCardCount;
	private Double typeCardTotal;
	
	public Integer getTypeAllCount() {
		return typeAllCount;
	}
	public void setTypeAllCount(Integer typeAllCount) {
		this.typeAllCount = typeAllCount;
	}
	public Double getTypeAllTotal() {
		return typeAllTotal;
	}
	public void setTypeAllTotal(Double typeAllTotal) {
		this.typeAllTotal = typeAllTotal;
	}
	public Long getTypeDeliveryCount() {
		return typeDeliveryCount;
	}
	public void setTypeDeliveryCount(Long typeDeliveryCount) {
		this.typeDeliveryCount = typeDeliveryCount;
	}
	public Double getTypeDeliveryTotal() {
		return typeDeliveryTotal;
	}
	public void setTypeDeliveryTotal(Double typeDeliveryTotal) {
		this.typeDeliveryTotal = typeDeliveryTotal;
	}
	public Long getTypeCollectionCount() {
		return typeCollectionCount;
	}
	public void setTypeCollectionCount(Long typeCollectionCount) {
		this.typeCollectionCount = typeCollectionCount;
	}
	public Double getTypeCollectionTotal() {
		return typeCollectionTotal;
	}
	public void setTypeCollectionTotal(Double typeCollectionTotal) {
		this.typeCollectionTotal = typeCollectionTotal;
	}
	public Long getTypeCashCount() {
		return typeCashCount;
	}
	public void setTypeCashCount(Long typeCashCount) {
		this.typeCashCount = typeCashCount;
	}
	public Double getTypeCashTotal() {
		return typeCashTotal;
	}
	public void setTypeCashTotal(Double typeCashTotal) {
		this.typeCashTotal = typeCashTotal;
	}
	public Long getTypeCardCount() {
		return typeCardCount;
	}
	public void setTypeCardCount(Long typeCardCount) {
		this.typeCardCount = typeCardCount;
	}
	public Double getTypeCardTotal() {
		return typeCardTotal;
	}
	public void setTypeCardTotal(Double typeCardTotal) {
		this.typeCardTotal = typeCardTotal;
	}
	@Override
	public String toString() {
		return "ReportSummary [typeAllCount=" + typeAllCount + ", typeAllTotal=" + typeAllTotal + ", typeDeliveryCount="
				+ typeDeliveryCount + ", typeDeliveryTotal=" + typeDeliveryTotal + ", typeCollectionCount="
				+ typeCollectionCount + ", typeCollectionTotal=" + typeCollectionTotal + ", typeCashCount="
				+ typeCashCount + ", typeCashTotal=" + typeCashTotal + ", typeCardCount=" + typeCardCount
				+ ", typeCardTotal=" + typeCardTotal + "]";
	}
	
	public String getStoreId() {
		return storeId;
	}
	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}
	public LocalDate getFromDate() {
		return fromDate;
	}
	public void setFromDate(LocalDate fromDate) {
		this.fromDate = fromDate;
	}
	public LocalDate getToDate() {
		return toDate;
	}
	public void setToDate(LocalDate toDate) {
		this.toDate = toDate;
	}
	
}
