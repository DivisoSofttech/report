package com.diviso.graeshoppe.report.domain;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
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
	
	public String getHeaders() {
		
		return ""+getStoreName()+
			   "\n"+getStorelocationName()+
			   "\n==============================================";
	
	}
	
	public String getContent() {
		 String content= null;
		 
		 String notes= null;
		 if(getNotes()!=null) {
			 notes=getNotes();
		 }
		 else {
			 notes="";
		 }
		// Date d1=new Date();
		// LocalDateTime t = LocalDateTime.ofInstant(this.getExpectedDelivery(), null);
		/*
		 * Instant date = this.getExpectedDelivery();
		 * System.out.println("++++++++++++++++++++++++++++++++++++++++++"+date); Date d
		 * = Date.from(date); SimpleDateFormat dateFormatter = new
		 * SimpleDateFormat("dd/MM/yyyy");
		 */
		 
		 
		/*
		 * String strDate= dateFormatter.format(d); SimpleDateFormat timeFormatter = new
		 * SimpleDateFormat("HH:mm a"); String strTime =timeFormatter.format(d);
		 */
		
		 if(getPreOrderDate()==null) {
			 
			 String strExpdlvryDate=java.time.format.DateTimeFormatter.ofPattern("d/M/yyyy hh:mm a")
			            .withZone(java.time.ZoneId.of(getZoneId()))
			            .format(java.time.Instant.parse(getExpectedDelivery().toString().substring(0, 10)+"T"+getExpectedDelivery().toString().substring(11,19)+".000Z")).substring(0,9);   
			 String strExpdlvryTime =java.time.format.DateTimeFormatter.ofPattern("d/M/yyyy hh:mm a")
			            .withZone(java.time.ZoneId.of(getZoneId()))
			            .format(java.time.Instant.parse(getExpectedDelivery().toString().substring(0, 10)+"T"+getExpectedDelivery().toString().substring(11,19)+".000Z")).substring(9);
			 
			 
			content= ""+getMethodOfOrder()+
				     "\nDue "+strExpdlvryDate+" at asap /"+strExpdlvryTime+"\nOrder Number :"+getOrderNumber()+
				     "\n=============================================="+
				     "\nRestaurant notes: "+
				     "\n"+notes+
				     "\nPlease ring me on:"+getPhone()+
				     "\n=============================================="+
				     "                                         EUR";
			
		   }
		 else if(getPreOrderDate()!=null) {
			 
			 String strPreOrderDate=java.time.format.DateTimeFormatter.ofPattern("d/M/yyyy hh:mm a")
			            .withZone(java.time.ZoneId.of(getZoneId()))
			            .format(java.time.Instant.parse(getPreOrderDate().toString().substring(0, 10)+"T"+getPreOrderDate().toString().substring(11,19)+".000Z")).substring(0,9);   
			 String strPreOrderTime =java.time.format.DateTimeFormatter.ofPattern("d/M/yyyy hh:mm a")
			            .withZone(java.time.ZoneId.of(getZoneId()))
			            .format(java.time.Instant.parse(getPreOrderDate().toString().substring(0, 10)+"T"+getPreOrderDate().toString().substring(11,19)+".000Z")).substring(9);
			 
			 content= ""+getMethodOfOrder()+
					 "\n Requested for "+strPreOrderDate+" at asap  /"+strPreOrderTime+"\nOrder Number :"+getOrderNumber()+
					 "\n=============================================="+
				     "\nRestaurant notes: "+
				     "\n"+notes+
				     "\nPlease ring me on:"+getPhone()+
				     "\n=============================================="+
				     "                                       EUR";
		 }
		 return content;
	}
	
	public String getProducts() {
		 String content= "";
		 System.out.println(">>>>>>>>>>>>>>>>>>>>>>"+getOrderLines());
		 
		
		 for(OrderLine ol:getOrderLines()) {
			 String total=""+ol.getTotal();
			 int space=(44-ol.getItem().length())-total.length()-8;
			 System.out.println("?????????????????????????"+space);
			 if(ol.getItem().length()>36) {
				 content= content.concat("  "+ol.getQuantity()+" x "+""+splitOrderLine(ol.getItem(),total)+"\n"); 
			 }
			 else {
			content= content.concat("  "+ol.getQuantity()+" x "+""+ol.getItem()+""+getSpace(space)+" "+ol.getTotal()+"\n");
			 }
			
			for(AuxItem ai:ol.getAuxItems()) {
				String auxTotal=""+ai.getTotal();
				 int auxSpace=(44-ai.getAuxItem().length())-auxTotal.length()-8;
				 System.out.println("////////////Entering auxitem for loop in escposdocket");
				 content=content.concat("  "+ai.getQuantity()+" x "+""+ai.getAuxItem()+""+getSpace(auxSpace)+" "+ai.getTotal()+"\n");
			 }
			 for(ComboItem ci:ol.getComboItems()) {
				 System.out.println("////////////Entering comboitem for loop in escposdocket");
				 content=content.concat("     "+ci.getComboItem()+"       "+ci.getQuantity()+"\n"); 
			 }
		 }
		 
		 return content;
	}
	
	public String getDiscountAndTotal() {
		
	String discountAndTotal="";
				if(getMethodOfOrder()=="DELIVERY") {
					discountAndTotal= "Food Exp discount (10%)    :           "+getOrderDiscountAmount()+"\n"+
				"Delivery charge            :           "+getDeliveryCharge()+"\n"+
				"                                   ============"+"\n"+
				" Total Due                    :         "+getTotalDue()+"\n";			
	
				}
				else {
					discountAndTotal= "Food Exp discount (10%)    :           "+getOrderDiscountAmount()+"\n"+
							"                                   ============"+"\n"+
							" Total Due                    :         "+getTotalDue()+"\n";		
				}
				return discountAndTotal;
				}
	
	
	public String getPaymentStatusForDocket() {
		return "==============================================\n"+
				""+getPaymentStatus()+"\n"+
				"==============================================\n";
				
	}
	
	public String getCustomerOrderDetails() {
		
		return "  Customer Id"+getSpace((44-"Customer Id".length())-getCustomerId().length()-8)+" :"+getCustomerId()+"\n"+
				"  Loyalty card point"+getSpace((44-"Loyalty card point".length())-getCustomerId().length()-8)+" :"+getLoyaltyPoint()+"\n"+
				"  Order from this customer"+getSpace((44-"Order from this customer".length())-getCustomerId().length()-8)+" :"+getOrderFromCustomer()+"\n"+
				"  Customer's food exp order"+getSpace((44-"Order from this customer".length())-getCustomerId().length()-8)+":"+getCustomerOrder();
		
		
	/*	return 
			   "  Customer Id               :"+getCustomerId()+"\n"+
	           "Loyalty card point        :"+getLoyaltyPoint()+"\n"+
			   "Order from this customer  :"+getOrderFromCustomer()+"\n"+
	           "Customer's food exp order :"+getCustomerOrder()+"\n\n";         */  
	}
	
	public String getCustomerDetails() {
		
		return "  Customer details:"+getSpace((44-"Customer details:".length()))+"\n"+
				   "  "+getCustomerName()+getSpace((44-getCustomerName().length()))+"\n"+
				   "  "+getHouseNoOrBuildingName()+getSpace((44-getHouseNoOrBuildingName().length()))+"\n"+
				   "  "+getLandmark()+getSpace((44-getLandmark().length()))+"\n"+
				   "  "+getCity()+getSpace((44-getCity().length()))+"\n";
				 //  "  "+getState()+getSpace((44-getState().length()))+"\n"+
				 //  "  "+getPincode()+getSpace((44-getPincode().length()))+"\n\n";
		
		
	/*	return "Customer details:"+"\n"+
			   ""+getCustomerName()+"\n"+
			   ""+getHouseNoOrBuildingName()+"\n"+
			   ""+getLandmark()+"\n"+
			   ""+getCity()+"\n"+
			   ""+getState()+"\n"+
			   ""+getPincode()+"\n\n";*/
			  
			   
	}
	
	public String getAttentionForFirstOrder() {
		return "Attention : Please note this is the first order from this customer, please make sure the order details are correct.\n\n\n";
		
	}
	
	public String getOrderTimes() {
		
		 /* String orderPlacedAt=java.time.format.DateTimeFormatter.ofPattern("d/M/yyyy hh:mm a") .withZone(java.time.ZoneId.of(getZoneId()))
		                .format(java.time.Instant.parse(getOrderPlaceAt().toString().substring(0,10)+"T"+getOrderPlaceAt().toString().substring(11,19)+".000Z"));
		  
		 */
		
		
		 String orderPlaceAt=java.time.format.DateTimeFormatter.ofPattern("d/M/yyyy hh:mm a")
		            .withZone(java.time.ZoneId.of(getZoneId()))
		            .format(java.time.Instant.parse(getOrderPlaceAt().toString().substring(0, 10)+"T"+getOrderPlaceAt().toString().substring(11,19)+".000Z"));   
		 String orderAcceptedAt =java.time.format.DateTimeFormatter.ofPattern("d/M/yyyy hh:mm a")
		            .withZone(java.time.ZoneId.of(getZoneId()))
		            .format(java.time.Instant.parse(getOrderAcceptedAt().toString().substring(0, 10)+"T"+getOrderAcceptedAt().toString().substring(11,19)+".000Z"));
		 
		return "  Order placed at   : "+orderPlaceAt+"\n"+
		       "  Order Accepted at : "+orderAcceptedAt+"\n\n";
	}
	
	public String getFooter() {
		return "Powered by Graeshoppe\n"+
				""+java.time.format.DateTimeFormatter.ofPattern("d/M/yyyy hh:mm a").withZone(java.time.ZoneId.of(getZoneId())).format( java.time.Instant.now())+"\n\n";
	
	}
	
	public String getSpace(int x) {
		String s= "";

		 for(int i=0;i<=x;i++) { 
			 System.out.println("inside getspace loop "+i);
		    s+= " ";
		 }
		 System.out.println(">>>>>>>>>>"+s);
		 return s;
	}
	
	
	public String splitOrderLine(String input, String total) {
		
		 String s=input;
			//System.out.println("Hello World"+s);
			int l = s.length();
			System.out.println(l);
			//l/2
			ArrayList<String>  outputList=new ArrayList<String>();
			String result1="";
			if(s.length()>36){
				/*
				 * String[] output = s.split("\\s+"); for(int i=0;i <output.length;i++)
				 * System.out.println(output[i]);
				 */
				System.out.println(s.substring(0,36));
				String sub = s.substring(0,36); 
				String[] output = sub.split("\\s+"); 
				int len=output.length;
				System.out.println("********"+len);
				for(int i=0;i <output.length-1;i++) {
					 System.out.println(output[i]);
				
				outputList.add(output[i]);
				}
				System.out.println("list*************"+outputList);
				String delim =" ";
				String result = String.join(delim, outputList);
					
				System.out.println(result);
			 
				//System.out.println(result.length());
				System.out.println(s.substring(result.length()));
				String sub1=s.substring(result.length());
				int space=(44-result.length())-total.length()-8;
				result1=result+""+getSpace(space)+" "+total+"\n"+sub1;
			}
		
		return result1;
		
	}
	
	}
	
	
	 
	 

