package com.diviso.graeshoppe.report.service.dto;
import java.time.Instant;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.diviso.graeshoppe.report.domain.OrderMaster} entity.
 */
public class OrderMasterDTO implements Serializable {

    private Long id;

    private String storeIdpcode;

    private String storeName;

    private Long storePhone;

    private String storelocationName;

    private String methodOfOrder;

    private Instant expectedDelivery;

    private String orderNumber;

    private String notes;

    private Double subTotal;

    private Double orderDiscountAmount;

    private Double deliveryCharge;

    private Double serviceCharge;

    private Double totalDue;

    private String orderStatus;

    private String customerId;

    private String customerName;

    private String pincode;

    private String houseNoOrBuildingName;

    private String roadNameAreaOrStreet;

    private String city;

    private String state;

    private String landmark;

    private Long phone;

    private Long alternatePhone;

    private String addressType;

    private Long orderFromCustomer;

    private Long customerOrder;

    private Instant orderPlaceAt;

    private Instant orderAcceptedAt;

    private String allergyNote;

    private Instant preOrderDate;

    private String email;

    private String paymentRef;

    private String paymentStatus;

    private String zoneId;

    private Long loyaltyPoint;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStoreIdpcode() {
        return storeIdpcode;
    }

    public void setStoreIdpcode(String storeIdpcode) {
        this.storeIdpcode = storeIdpcode;
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

    public String getStorelocationName() {
        return storelocationName;
    }

    public void setStorelocationName(String storelocationName) {
        this.storelocationName = storelocationName;
    }

    public String getMethodOfOrder() {
        return methodOfOrder;
    }

    public void setMethodOfOrder(String methodOfOrder) {
        this.methodOfOrder = methodOfOrder;
    }

    public Instant getExpectedDelivery() {
        return expectedDelivery;
    }

    public void setExpectedDelivery(Instant expectedDelivery) {
        this.expectedDelivery = expectedDelivery;
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

    public Double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(Double subTotal) {
        this.subTotal = subTotal;
    }

    public Double getOrderDiscountAmount() {
        return orderDiscountAmount;
    }

    public void setOrderDiscountAmount(Double orderDiscountAmount) {
        this.orderDiscountAmount = orderDiscountAmount;
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

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
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

    public Long getOrderFromCustomer() {
        return orderFromCustomer;
    }

    public void setOrderFromCustomer(Long orderFromCustomer) {
        this.orderFromCustomer = orderFromCustomer;
    }

    public Long getCustomerOrder() {
        return customerOrder;
    }

    public void setCustomerOrder(Long customerOrder) {
        this.customerOrder = customerOrder;
    }

    public Instant getOrderPlaceAt() {
        return orderPlaceAt;
    }

    public void setOrderPlaceAt(Instant orderPlaceAt) {
        this.orderPlaceAt = orderPlaceAt;
    }

    public Instant getOrderAcceptedAt() {
        return orderAcceptedAt;
    }

    public void setOrderAcceptedAt(Instant orderAcceptedAt) {
        this.orderAcceptedAt = orderAcceptedAt;
    }

    public String getAllergyNote() {
        return allergyNote;
    }

    public void setAllergyNote(String allergyNote) {
        this.allergyNote = allergyNote;
    }

    public Instant getPreOrderDate() {
        return preOrderDate;
    }

    public void setPreOrderDate(Instant preOrderDate) {
        this.preOrderDate = preOrderDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPaymentRef() {
        return paymentRef;
    }

    public void setPaymentRef(String paymentRef) {
        this.paymentRef = paymentRef;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public String getZoneId() {
        return zoneId;
    }

    public void setZoneId(String zoneId) {
        this.zoneId = zoneId;
    }

    public Long getLoyaltyPoint() {
        return loyaltyPoint;
    }

    public void setLoyaltyPoint(Long loyaltyPoint) {
        this.loyaltyPoint = loyaltyPoint;
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
            ", storeIdpcode='" + getStoreIdpcode() + "'" +
            ", storeName='" + getStoreName() + "'" +
            ", storePhone=" + getStorePhone() +
            ", storelocationName='" + getStorelocationName() + "'" +
            ", methodOfOrder='" + getMethodOfOrder() + "'" +
            ", expectedDelivery='" + getExpectedDelivery() + "'" +
            ", orderNumber='" + getOrderNumber() + "'" +
            ", notes='" + getNotes() + "'" +
            ", subTotal=" + getSubTotal() +
            ", orderDiscountAmount=" + getOrderDiscountAmount() +
            ", deliveryCharge=" + getDeliveryCharge() +
            ", serviceCharge=" + getServiceCharge() +
            ", totalDue=" + getTotalDue() +
            ", orderStatus='" + getOrderStatus() + "'" +
            ", customerId='" + getCustomerId() + "'" +
            ", customerName='" + getCustomerName() + "'" +
            ", pincode='" + getPincode() + "'" +
            ", houseNoOrBuildingName='" + getHouseNoOrBuildingName() + "'" +
            ", roadNameAreaOrStreet='" + getRoadNameAreaOrStreet() + "'" +
            ", city='" + getCity() + "'" +
            ", state='" + getState() + "'" +
            ", landmark='" + getLandmark() + "'" +
            ", phone=" + getPhone() +
            ", alternatePhone=" + getAlternatePhone() +
            ", addressType='" + getAddressType() + "'" +
            ", orderFromCustomer=" + getOrderFromCustomer() +
            ", customerOrder=" + getCustomerOrder() +
            ", orderPlaceAt='" + getOrderPlaceAt() + "'" +
            ", orderAcceptedAt='" + getOrderAcceptedAt() + "'" +
            ", allergyNote='" + getAllergyNote() + "'" +
            ", preOrderDate='" + getPreOrderDate() + "'" +
            ", email='" + getEmail() + "'" +
            ", paymentRef='" + getPaymentRef() + "'" +
            ", paymentStatus='" + getPaymentStatus() + "'" +
            ", zoneId='" + getZoneId() + "'" +
            ", loyaltyPoint=" + getLoyaltyPoint() +
            "}";
    }
}
