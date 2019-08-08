package com.diviso.graeshoppe.report.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the OrderMaster entity.
 */
public class OrderMasterDTO implements Serializable {

    private Long id;

    private String storeName;

    private Long storePhone;

    private String methodOfOrder;

    private String dueDate;

    private String dueTime;

    private String orderNumber;

    private String notes;

    private Double deliveryCharge;

    private Double serviceCharge;

    private Double totalDue;

    private String orderStatus;

    private String customerId;

    private Long pincode;

    private String houseNoOrBuildingName;

    private String roadNameAreaOrStreet;

    private String city;

    private String state;

    private String landmark;

    private String name;

    private Long phone;

    private Long alternatePhone;

    private String addressType;

    private Integer orderFromCustomer;

    private Integer customerOrder;

    private String orderPlaceAt;

    private String orderAcceptedAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public Long getStorePhone() {
        return storePhone;
    }

    public void setStorePhone(Long storePhone) {
        this.storePhone = storePhone;
    }

    public String getMethodOfOrder() {
        return methodOfOrder;
    }

    public void setMethodOfOrder(String methodOfOrder) {
        this.methodOfOrder = methodOfOrder;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public String getDueTime() {
        return dueTime;
    }

    public void setDueTime(String dueTime) {
        this.dueTime = dueTime;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Double getDeliveryCharge() {
        return deliveryCharge;
    }

    public void setDeliveryCharge(Double deliveryCharge) {
        this.deliveryCharge = deliveryCharge;
    }

    public Double getServiceCharge() {
        return serviceCharge;
    }

    public void setServiceCharge(Double serviceCharge) {
        this.serviceCharge = serviceCharge;
    }

    public Double getTotalDue() {
        return totalDue;
    }

    public void setTotalDue(Double totalDue) {
        this.totalDue = totalDue;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public Long getPincode() {
        return pincode;
    }

    public void setPincode(Long pincode) {
        this.pincode = pincode;
    }

    public String getHouseNoOrBuildingName() {
        return houseNoOrBuildingName;
    }

    public void setHouseNoOrBuildingName(String houseNoOrBuildingName) {
        this.houseNoOrBuildingName = houseNoOrBuildingName;
    }

    public String getRoadNameAreaOrStreet() {
        return roadNameAreaOrStreet;
    }

    public void setRoadNameAreaOrStreet(String roadNameAreaOrStreet) {
        this.roadNameAreaOrStreet = roadNameAreaOrStreet;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getLandmark() {
        return landmark;
    }

    public void setLandmark(String landmark) {
        this.landmark = landmark;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getPhone() {
        return phone;
    }

    public void setPhone(Long phone) {
        this.phone = phone;
    }

    public Long getAlternatePhone() {
        return alternatePhone;
    }

    public void setAlternatePhone(Long alternatePhone) {
        this.alternatePhone = alternatePhone;
    }

    public String getAddressType() {
        return addressType;
    }

    public void setAddressType(String addressType) {
        this.addressType = addressType;
    }

    public Integer getOrderFromCustomer() {
        return orderFromCustomer;
    }

    public void setOrderFromCustomer(Integer orderFromCustomer) {
        this.orderFromCustomer = orderFromCustomer;
    }

    public Integer getCustomerOrder() {
        return customerOrder;
    }

    public void setCustomerOrder(Integer customerOrder) {
        this.customerOrder = customerOrder;
    }

    public String getOrderPlaceAt() {
        return orderPlaceAt;
    }

    public void setOrderPlaceAt(String orderPlaceAt) {
        this.orderPlaceAt = orderPlaceAt;
    }

    public String getOrderAcceptedAt() {
        return orderAcceptedAt;
    }

    public void setOrderAcceptedAt(String orderAcceptedAt) {
        this.orderAcceptedAt = orderAcceptedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        OrderMasterDTO orderMasterDTO = (OrderMasterDTO) o;
        if (orderMasterDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), orderMasterDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "OrderMasterDTO{" +
            "id=" + getId() +
            ", storeName='" + getStoreName() + "'" +
            ", storePhone=" + getStorePhone() +
            ", methodOfOrder='" + getMethodOfOrder() + "'" +
            ", dueDate='" + getDueDate() + "'" +
            ", dueTime='" + getDueTime() + "'" +
            ", orderNumber='" + getOrderNumber() + "'" +
            ", notes='" + getNotes() + "'" +
            ", deliveryCharge=" + getDeliveryCharge() +
            ", serviceCharge=" + getServiceCharge() +
            ", totalDue=" + getTotalDue() +
            ", orderStatus='" + getOrderStatus() + "'" +
            ", customerId='" + getCustomerId() + "'" +
            ", pincode=" + getPincode() +
            ", houseNoOrBuildingName='" + getHouseNoOrBuildingName() + "'" +
            ", roadNameAreaOrStreet='" + getRoadNameAreaOrStreet() + "'" +
            ", city='" + getCity() + "'" +
            ", state='" + getState() + "'" +
            ", landmark='" + getLandmark() + "'" +
            ", name='" + getName() + "'" +
            ", phone=" + getPhone() +
            ", alternatePhone=" + getAlternatePhone() +
            ", addressType='" + getAddressType() + "'" +
            ", orderFromCustomer=" + getOrderFromCustomer() +
            ", customerOrder=" + getCustomerOrder() +
            ", orderPlaceAt='" + getOrderPlaceAt() + "'" +
            ", orderAcceptedAt='" + getOrderAcceptedAt() + "'" +
            "}";
    }
}
