package com.diviso.graeshoppe.report.domain;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class EscPosDocket {

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
	 private Double refundedAmount;
	 
	 private Long cancellationRef;
	 private Set<OrderLine> orderLines = new HashSet<>();
	 
	 private Set<OfferLine> offerLines = new HashSet<>();
	
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
	public Double getRefundedAmount() {
		return refundedAmount;
	}
	public void setRefundedAmount(Double refundedAmount) {
		this.refundedAmount = refundedAmount;
	}
	public Long getCancellationRef() {
		return cancellationRef;
	}
	public void setCancellationRef(Long cancellationRef) {
		this.cancellationRef = cancellationRef;
	}
	public Set<OrderLine> getOrderLines() {
		return orderLines;
	}
	public void setOrderLines(Set<OrderLine> orderLines) {
		this.orderLines = orderLines;
	}
	public Set<OfferLine> getOfferLines() {
		return offerLines;
	}
	public void setOfferLines(Set<OfferLine> offerLines) {
		this.offerLines = offerLines;
	}
	
	public String headers() {
		
		return ""+getStoreName()+
			   "/n"+getStorelocationName()+
			   "/n----------------------------";
			
	}
	
	public String content() {
		 String content= null;
		// Date d1=new Date();
		// LocalDateTime t = LocalDateTime.ofInstant(this.getExpectedDelivery(), null);
		Instant date = this.getExpectedDelivery();
		System.out.println("++++++++++++++++++++++++++++++++++++++++++"+date);
		Date d = Date.from(date);
		 SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy");  
		    String strDate= dateFormatter.format(d);  
		    SimpleDateFormat timeFormatter = new SimpleDateFormat("HH:mm a");  
		    String strTime =timeFormatter.format(d);
		System.out.println(d);
		 if(getPreOrderDate()==null) {
			content= ""+getMethodOfOrder()+
				     "/n Due "+strDate+" at asap /"+strTime+"/nOrder Number :"+getOrderNumber()+
				     "/n--------------------------------"+
				     "/nRestaurant notes: "+
				     "/n"+getNotes()+
				     "/nPlease ring me on:"+getPhone()+
				     "/n--------------------------------"+
				     "                                EUR";
			
		   }
		 else if(getPreOrderDate()!=null) {
			 content= ""+getMethodOfOrder()+
					 "/n Requested for "+strDate+" at asap  /"+strTime+"/nOrder Number :"+getOrderNumber()+
					 "/n--------------------------------"+
				     "/nRestaurant notes: "+
				     "/n"+getNotes()+
				     "/nPlease ring me on:"+getPhone()+
				     "/n--------------------------------"+
				     "                                EUR";
		 }
		 return content;
	}
	
	public String products() {
		 String content= null;
		 for(OrderLine ol:getOrderLines()) {
			 content= content.concat(""+ol.getQuantity()+" x "+""+ol.getItem()+"   "+ol.getTotal()+"/n");
		 }
		 
		 return content;
	}
	
	
	
	
	}
	
	
	 
	 

