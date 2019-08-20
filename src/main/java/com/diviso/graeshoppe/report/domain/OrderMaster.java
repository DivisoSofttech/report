package com.diviso.graeshoppe.report.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

import org.springframework.data.elasticsearch.annotations.Document;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A OrderMaster.
 */
@Entity
@Table(name = "order_master")
@Document(indexName = "ordermaster")
public class OrderMaster implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "store_name")
    private String storeName;

    @Column(name = "store_phone")
    private Long storePhone;

    @Column(name = "method_of_order")
    private String methodOfOrder;

    @Column(name = "due_date")
    private String dueDate;

    @Column(name = "due_time")
    private String dueTime;

    @Column(name = "order_number")
    private String orderNumber;

    @Column(name = "notes")
    private String notes;

    @Column(name = "delivery_charge")
    private Double deliveryCharge;

    @Column(name = "service_charge")
    private Double serviceCharge;

    @Column(name = "total_due")
    private Double totalDue;

    @Column(name = "order_status")
    private String orderStatus;

    @Column(name = "customer_id")
    private String customerId;

    @Column(name = "pincode")
    private Long pincode;

    @Column(name = "house_no_or_building_name")
    private String houseNoOrBuildingName;

    @Column(name = "road_name_area_or_street")
    private String roadNameAreaOrStreet;

    @Column(name = "city")
    private String city;

    @Column(name = "state")
    private String state;

    @Column(name = "landmark")
    private String landmark;

    @Column(name = "name")
    private String name;

    @Column(name = "phone")
    private Long phone;

    @Column(name = "alternate_phone")
    private Long alternatePhone;

    @Column(name = "address_type")
    private String addressType;

    @Column(name = "order_from_customer")
    private Long orderFromCustomer;

    @Column(name = "customer_order")
    private Long customerOrder;

    @Column(name = "order_place_at")
    private String orderPlaceAt;

    @Column(name = "order_accepted_at")
    private String orderAcceptedAt;

    @OneToMany(mappedBy = "orderMaster")
    private Set<OrderLine> orderLines = new HashSet<>();
    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStoreName() {
        return storeName;
    }

    public OrderMaster storeName(String storeName) {
        this.storeName = storeName;
        return this;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public Long getStorePhone() {
        return storePhone;
    }

    public OrderMaster storePhone(Long storePhone) {
        this.storePhone = storePhone;
        return this;
    }

    public void setStorePhone(Long storePhone) {
        this.storePhone = storePhone;
    }

    public String getMethodOfOrder() {
        return methodOfOrder;
    }

    public OrderMaster methodOfOrder(String methodOfOrder) {
        this.methodOfOrder = methodOfOrder;
        return this;
    }

    public void setMethodOfOrder(String methodOfOrder) {
        this.methodOfOrder = methodOfOrder;
    }

    public String getDueDate() {
        return dueDate;
    }

    public OrderMaster dueDate(String dueDate) {
        this.dueDate = dueDate;
        return this;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public String getDueTime() {
        return dueTime;
    }

    public OrderMaster dueTime(String dueTime) {
        this.dueTime = dueTime;
        return this;
    }

    public void setDueTime(String dueTime) {
        this.dueTime = dueTime;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public OrderMaster orderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
        return this;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getNotes() {
        return notes;
    }

    public OrderMaster notes(String notes) {
        this.notes = notes;
        return this;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Double getDeliveryCharge() {
        return deliveryCharge;
    }

    public OrderMaster deliveryCharge(Double deliveryCharge) {
        this.deliveryCharge = deliveryCharge;
        return this;
    }

    public void setDeliveryCharge(Double deliveryCharge) {
        this.deliveryCharge = deliveryCharge;
    }

    public Double getServiceCharge() {
        return serviceCharge;
    }

    public OrderMaster serviceCharge(Double serviceCharge) {
        this.serviceCharge = serviceCharge;
        return this;
    }

    public void setServiceCharge(Double serviceCharge) {
        this.serviceCharge = serviceCharge;
    }

    public Double getTotalDue() {
        return totalDue;
    }

    public OrderMaster totalDue(Double totalDue) {
        this.totalDue = totalDue;
        return this;
    }

    public void setTotalDue(Double totalDue) {
        this.totalDue = totalDue;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public OrderMaster orderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
        return this;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getCustomerId() {
        return customerId;
    }

    public OrderMaster customerId(String customerId) {
        this.customerId = customerId;
        return this;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public Long getPincode() {
        return pincode;
    }

    public OrderMaster pincode(Long pincode) {
        this.pincode = pincode;
        return this;
    }

    public void setPincode(Long pincode) {
        this.pincode = pincode;
    }

    public String getHouseNoOrBuildingName() {
        return houseNoOrBuildingName;
    }

    public OrderMaster houseNoOrBuildingName(String houseNoOrBuildingName) {
        this.houseNoOrBuildingName = houseNoOrBuildingName;
        return this;
    }

    public void setHouseNoOrBuildingName(String houseNoOrBuildingName) {
        this.houseNoOrBuildingName = houseNoOrBuildingName;
    }

    public String getRoadNameAreaOrStreet() {
        return roadNameAreaOrStreet;
    }

    public OrderMaster roadNameAreaOrStreet(String roadNameAreaOrStreet) {
        this.roadNameAreaOrStreet = roadNameAreaOrStreet;
        return this;
    }

    public void setRoadNameAreaOrStreet(String roadNameAreaOrStreet) {
        this.roadNameAreaOrStreet = roadNameAreaOrStreet;
    }

    public String getCity() {
        return city;
    }

    public OrderMaster city(String city) {
        this.city = city;
        return this;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public OrderMaster state(String state) {
        this.state = state;
        return this;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getLandmark() {
        return landmark;
    }

    public OrderMaster landmark(String landmark) {
        this.landmark = landmark;
        return this;
    }

    public void setLandmark(String landmark) {
        this.landmark = landmark;
    }

    public String getName() {
        return name;
    }

    public OrderMaster name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getPhone() {
        return phone;
    }

    public OrderMaster phone(Long phone) {
        this.phone = phone;
        return this;
    }

    public void setPhone(Long phone) {
        this.phone = phone;
    }

    public Long getAlternatePhone() {
        return alternatePhone;
    }

    public OrderMaster alternatePhone(Long alternatePhone) {
        this.alternatePhone = alternatePhone;
        return this;
    }

    public void setAlternatePhone(Long alternatePhone) {
        this.alternatePhone = alternatePhone;
    }

    public String getAddressType() {
        return addressType;
    }

    public OrderMaster addressType(String addressType) {
        this.addressType = addressType;
        return this;
    }

    public void setAddressType(String addressType) {
        this.addressType = addressType;
    }

    public Long getOrderFromCustomer() {
        return orderFromCustomer;
    }

    public OrderMaster orderFromCustomer(Long orderFromCustomer) {
        this.orderFromCustomer = orderFromCustomer;
        return this;
    }

    public void setOrderFromCustomer(Long orderFromCustomer) {
        this.orderFromCustomer = orderFromCustomer;
    }

    public Long getCustomerOrder() {
        return customerOrder;
    }

    public OrderMaster customerOrder(Long customerOrder) {
        this.customerOrder = customerOrder;
        return this;
    }

    public void setCustomerOrder(Long customerOrder) {
        this.customerOrder = customerOrder;
    }

    public String getOrderPlaceAt() {
        return orderPlaceAt;
    }

    public OrderMaster orderPlaceAt(String orderPlaceAt) {
        this.orderPlaceAt = orderPlaceAt;
        return this;
    }

    public void setOrderPlaceAt(String orderPlaceAt) {
        this.orderPlaceAt = orderPlaceAt;
    }

    public String getOrderAcceptedAt() {
        return orderAcceptedAt;
    }

    public OrderMaster orderAcceptedAt(String orderAcceptedAt) {
        this.orderAcceptedAt = orderAcceptedAt;
        return this;
    }

    public void setOrderAcceptedAt(String orderAcceptedAt) {
        this.orderAcceptedAt = orderAcceptedAt;
    }

    public Set<OrderLine> getOrderLines() {
        return orderLines;
    }

    public OrderMaster orderLines(Set<OrderLine> orderLines) {
        this.orderLines = orderLines;
        return this;
    }

    public OrderMaster addOrderLine(OrderLine orderLine) {
        this.orderLines.add(orderLine);
        orderLine.setOrderMaster(this);
        return this;
    }

    public OrderMaster removeOrderLine(OrderLine orderLine) {
        this.orderLines.remove(orderLine);
        orderLine.setOrderMaster(null);
        return this;
    }

    public void setOrderLines(Set<OrderLine> orderLines) {
        this.orderLines = orderLines;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        OrderMaster orderMaster = (OrderMaster) o;
        if (orderMaster.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), orderMaster.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "OrderMaster{" +
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
