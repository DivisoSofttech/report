package com.diviso.graeshoppe.report.domain;

public class ReportSummary {
	
	private Long typeAllCount;
	private Double typeAllTotal;

 
	private Integer typeDeliveryCount;
	private Double typeDeliveryTotal;

	private Integer typeCollectionCount;
	private Double typeCollectionTotal;

	
	private Integer typeCashCount;
	private Double typeCashTotal;

	private Integer typeCardCount;
	private Double typeCardTotal;
	public Long getTypeAllCount() {
		return typeAllCount;
	}
	public void setTypeAllCount(Long typeAllCount) {
		this.typeAllCount = typeAllCount;
	}
	public Double getTypeAllTotal() {
		return typeAllTotal;
	}
	public void setTypeAllTotal(Double typeAllTotal) {
		this.typeAllTotal = typeAllTotal;
	}
	public Integer getTypeDeliveryCount() {
		return typeDeliveryCount;
	}
	public void setTypeDeliveryCount(Integer typeDeliveryCount) {
		this.typeDeliveryCount = typeDeliveryCount;
	}
	public Double getTypeDeliveryTotal() {
		return typeDeliveryTotal;
	}
	public void setTypeDeliveryTotal(Double typeDeliveryTotal) {
		this.typeDeliveryTotal = typeDeliveryTotal;
	}
	public Integer getTypeCollectionCount() {
		return typeCollectionCount;
	}
	public void setTypeCollectionCount(Integer typeCollectionCount) {
		this.typeCollectionCount = typeCollectionCount;
	}
	public Double getTypeCollectionTotal() {
		return typeCollectionTotal;
	}
	public void setTypeCollectionTotal(Double typeCollectionTotal) {
		this.typeCollectionTotal = typeCollectionTotal;
	}
	public Integer getTypeCashCount() {
		return typeCashCount;
	}
	public void setTypeCashCount(Integer typeCashCount) {
		this.typeCashCount = typeCashCount;
	}
	public Double getTypeCashTotal() {
		return typeCashTotal;
	}
	public void setTypeCashTotal(Double typeCashTotal) {
		this.typeCashTotal = typeCashTotal;
	}
	public Integer getTypeCardCount() {
		return typeCardCount;
	}
	public void setTypeCardCount(Integer typeCardCount) {
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
}
