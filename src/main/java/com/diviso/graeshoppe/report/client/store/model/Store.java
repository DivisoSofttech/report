package com.diviso.graeshoppe.report.client.store.model;

import java.util.Objects;
import com.diviso.graeshoppe.report.client.store.model.Banner;
import com.diviso.graeshoppe.report.client.store.model.DeliveryInfo;
import com.diviso.graeshoppe.report.client.store.model.PreOrderSettings;
import com.diviso.graeshoppe.report.client.store.model.StoreAddress;
import com.diviso.graeshoppe.report.client.store.model.StoreSettings;
import com.diviso.graeshoppe.report.client.store.model.StoreType;
import com.diviso.graeshoppe.report.client.store.model.UserRatingReview;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Store
 */
@Validated
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2019-12-31T16:37:58.581+05:30[Asia/Kolkata]")

public class Store   {
  @JsonProperty("banners")
  @Valid
  private List<Banner> banners = null;

  @JsonProperty("closingTime")
  private OffsetDateTime closingTime = null;

  @JsonProperty("contactNo")
  private Long contactNo = null;

  @JsonProperty("deliveryInfos")
  @Valid
  private List<DeliveryInfo> deliveryInfos = null;

  @JsonProperty("email")
  private String email = null;

  @JsonProperty("id")
  private Long id = null;

  @JsonProperty("imageLink")
  private String imageLink = null;

  @JsonProperty("info")
  private String info = null;

  @JsonProperty("location")
  private String location = null;

  @JsonProperty("locationName")
  private String locationName = null;

  @JsonProperty("maxDeliveryTime")
  private OffsetDateTime maxDeliveryTime = null;

  @JsonProperty("minAmount")
  private Double minAmount = null;

  @JsonProperty("name")
  private String name = null;

  @JsonProperty("openingTime")
  private OffsetDateTime openingTime = null;

  @JsonProperty("preOrderSettings")
  private PreOrderSettings preOrderSettings = null;

  @JsonProperty("regNo")
  private String regNo = null;

  @JsonProperty("storeAddress")
  private StoreAddress storeAddress = null;

  @JsonProperty("storeSettings")
  private StoreSettings storeSettings = null;

  @JsonProperty("storeTypes")
  @Valid
  private List<StoreType> storeTypes = null;

  @JsonProperty("storeUniqueId")
  private String storeUniqueId = null;

  @JsonProperty("totalRating")
  private Double totalRating = null;

  @JsonProperty("userRatingReviews")
  @Valid
  private List<UserRatingReview> userRatingReviews = null;

  public Store banners(List<Banner> banners) {
    this.banners = banners;
    return this;
  }

  public Store addBannersItem(Banner bannersItem) {
    if (this.banners == null) {
      this.banners = new ArrayList<Banner>();
    }
    this.banners.add(bannersItem);
    return this;
  }

  /**
   * Get banners
   * @return banners
  **/
  @ApiModelProperty(value = "")

  @Valid

  public List<Banner> getBanners() {
    return banners;
  }

  public void setBanners(List<Banner> banners) {
    this.banners = banners;
  }

  public Store closingTime(OffsetDateTime closingTime) {
    this.closingTime = closingTime;
    return this;
  }

  /**
   * Get closingTime
   * @return closingTime
  **/
  @ApiModelProperty(value = "")

  @Valid

  public OffsetDateTime getClosingTime() {
    return closingTime;
  }

  public void setClosingTime(OffsetDateTime closingTime) {
    this.closingTime = closingTime;
  }

  public Store contactNo(Long contactNo) {
    this.contactNo = contactNo;
    return this;
  }

  /**
   * Get contactNo
   * @return contactNo
  **/
  @ApiModelProperty(value = "")


  public Long getContactNo() {
    return contactNo;
  }

  public void setContactNo(Long contactNo) {
    this.contactNo = contactNo;
  }

  public Store deliveryInfos(List<DeliveryInfo> deliveryInfos) {
    this.deliveryInfos = deliveryInfos;
    return this;
  }

  public Store addDeliveryInfosItem(DeliveryInfo deliveryInfosItem) {
    if (this.deliveryInfos == null) {
      this.deliveryInfos = new ArrayList<DeliveryInfo>();
    }
    this.deliveryInfos.add(deliveryInfosItem);
    return this;
  }

  /**
   * Get deliveryInfos
   * @return deliveryInfos
  **/
  @ApiModelProperty(value = "")

  @Valid

  public List<DeliveryInfo> getDeliveryInfos() {
    return deliveryInfos;
  }

  public void setDeliveryInfos(List<DeliveryInfo> deliveryInfos) {
    this.deliveryInfos = deliveryInfos;
  }

  public Store email(String email) {
    this.email = email;
    return this;
  }

  /**
   * Get email
   * @return email
  **/
  @ApiModelProperty(value = "")


  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public Store id(Long id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
  **/
  @ApiModelProperty(value = "")


  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Store imageLink(String imageLink) {
    this.imageLink = imageLink;
    return this;
  }

  /**
   * Get imageLink
   * @return imageLink
  **/
  @ApiModelProperty(required = true, value = "")
  @NotNull


  public String getImageLink() {
    return imageLink;
  }

  public void setImageLink(String imageLink) {
    this.imageLink = imageLink;
  }

  public Store info(String info) {
    this.info = info;
    return this;
  }

  /**
   * Get info
   * @return info
  **/
  @ApiModelProperty(value = "")


  public String getInfo() {
    return info;
  }

  public void setInfo(String info) {
    this.info = info;
  }

  public Store location(String location) {
    this.location = location;
    return this;
  }

  /**
   * Get location
   * @return location
  **/
  @ApiModelProperty(value = "")


  public String getLocation() {
    return location;
  }

  public void setLocation(String location) {
    this.location = location;
  }

  public Store locationName(String locationName) {
    this.locationName = locationName;
    return this;
  }

  /**
   * Get locationName
   * @return locationName
  **/
  @ApiModelProperty(value = "")


  public String getLocationName() {
    return locationName;
  }

  public void setLocationName(String locationName) {
    this.locationName = locationName;
  }

  public Store maxDeliveryTime(OffsetDateTime maxDeliveryTime) {
    this.maxDeliveryTime = maxDeliveryTime;
    return this;
  }

  /**
   * Get maxDeliveryTime
   * @return maxDeliveryTime
  **/
  @ApiModelProperty(value = "")

  @Valid

  public OffsetDateTime getMaxDeliveryTime() {
    return maxDeliveryTime;
  }

  public void setMaxDeliveryTime(OffsetDateTime maxDeliveryTime) {
    this.maxDeliveryTime = maxDeliveryTime;
  }

  public Store minAmount(Double minAmount) {
    this.minAmount = minAmount;
    return this;
  }

  /**
   * Get minAmount
   * @return minAmount
  **/
  @ApiModelProperty(value = "")


  public Double getMinAmount() {
    return minAmount;
  }

  public void setMinAmount(Double minAmount) {
    this.minAmount = minAmount;
  }

  public Store name(String name) {
    this.name = name;
    return this;
  }

  /**
   * Get name
   * @return name
  **/
  @ApiModelProperty(value = "")


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Store openingTime(OffsetDateTime openingTime) {
    this.openingTime = openingTime;
    return this;
  }

  /**
   * Get openingTime
   * @return openingTime
  **/
  @ApiModelProperty(value = "")

  @Valid

  public OffsetDateTime getOpeningTime() {
    return openingTime;
  }

  public void setOpeningTime(OffsetDateTime openingTime) {
    this.openingTime = openingTime;
  }

  public Store preOrderSettings(PreOrderSettings preOrderSettings) {
    this.preOrderSettings = preOrderSettings;
    return this;
  }

  /**
   * Get preOrderSettings
   * @return preOrderSettings
  **/
  @ApiModelProperty(value = "")

  @Valid

  public PreOrderSettings getPreOrderSettings() {
    return preOrderSettings;
  }

  public void setPreOrderSettings(PreOrderSettings preOrderSettings) {
    this.preOrderSettings = preOrderSettings;
  }

  public Store regNo(String regNo) {
    this.regNo = regNo;
    return this;
  }

  /**
   * Get regNo
   * @return regNo
  **/
  @ApiModelProperty(value = "")


  public String getRegNo() {
    return regNo;
  }

  public void setRegNo(String regNo) {
    this.regNo = regNo;
  }

  public Store storeAddress(StoreAddress storeAddress) {
    this.storeAddress = storeAddress;
    return this;
  }

  /**
   * Get storeAddress
   * @return storeAddress
  **/
  @ApiModelProperty(value = "")

  @Valid

  public StoreAddress getStoreAddress() {
    return storeAddress;
  }

  public void setStoreAddress(StoreAddress storeAddress) {
    this.storeAddress = storeAddress;
  }

  public Store storeSettings(StoreSettings storeSettings) {
    this.storeSettings = storeSettings;
    return this;
  }

  /**
   * Get storeSettings
   * @return storeSettings
  **/
  @ApiModelProperty(value = "")

  @Valid

  public StoreSettings getStoreSettings() {
    return storeSettings;
  }

  public void setStoreSettings(StoreSettings storeSettings) {
    this.storeSettings = storeSettings;
  }

  public Store storeTypes(List<StoreType> storeTypes) {
    this.storeTypes = storeTypes;
    return this;
  }

  public Store addStoreTypesItem(StoreType storeTypesItem) {
    if (this.storeTypes == null) {
      this.storeTypes = new ArrayList<StoreType>();
    }
    this.storeTypes.add(storeTypesItem);
    return this;
  }

  /**
   * Get storeTypes
   * @return storeTypes
  **/
  @ApiModelProperty(value = "")

  @Valid

  public List<StoreType> getStoreTypes() {
    return storeTypes;
  }

  public void setStoreTypes(List<StoreType> storeTypes) {
    this.storeTypes = storeTypes;
  }

  public Store storeUniqueId(String storeUniqueId) {
    this.storeUniqueId = storeUniqueId;
    return this;
  }

  /**
   * Get storeUniqueId
   * @return storeUniqueId
  **/
  @ApiModelProperty(required = true, value = "")
  @NotNull


  public String getStoreUniqueId() {
    return storeUniqueId;
  }

  public void setStoreUniqueId(String storeUniqueId) {
    this.storeUniqueId = storeUniqueId;
  }

  public Store totalRating(Double totalRating) {
    this.totalRating = totalRating;
    return this;
  }

  /**
   * Get totalRating
   * @return totalRating
  **/
  @ApiModelProperty(value = "")


  public Double getTotalRating() {
    return totalRating;
  }

  public void setTotalRating(Double totalRating) {
    this.totalRating = totalRating;
  }

  public Store userRatingReviews(List<UserRatingReview> userRatingReviews) {
    this.userRatingReviews = userRatingReviews;
    return this;
  }

  public Store addUserRatingReviewsItem(UserRatingReview userRatingReviewsItem) {
    if (this.userRatingReviews == null) {
      this.userRatingReviews = new ArrayList<UserRatingReview>();
    }
    this.userRatingReviews.add(userRatingReviewsItem);
    return this;
  }

  /**
   * Get userRatingReviews
   * @return userRatingReviews
  **/
  @ApiModelProperty(value = "")

  @Valid

  public List<UserRatingReview> getUserRatingReviews() {
    return userRatingReviews;
  }

  public void setUserRatingReviews(List<UserRatingReview> userRatingReviews) {
    this.userRatingReviews = userRatingReviews;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Store store = (Store) o;
    return Objects.equals(this.banners, store.banners) &&
        Objects.equals(this.closingTime, store.closingTime) &&
        Objects.equals(this.contactNo, store.contactNo) &&
        Objects.equals(this.deliveryInfos, store.deliveryInfos) &&
        Objects.equals(this.email, store.email) &&
        Objects.equals(this.id, store.id) &&
        Objects.equals(this.imageLink, store.imageLink) &&
        Objects.equals(this.info, store.info) &&
        Objects.equals(this.location, store.location) &&
        Objects.equals(this.locationName, store.locationName) &&
        Objects.equals(this.maxDeliveryTime, store.maxDeliveryTime) &&
        Objects.equals(this.minAmount, store.minAmount) &&
        Objects.equals(this.name, store.name) &&
        Objects.equals(this.openingTime, store.openingTime) &&
        Objects.equals(this.preOrderSettings, store.preOrderSettings) &&
        Objects.equals(this.regNo, store.regNo) &&
        Objects.equals(this.storeAddress, store.storeAddress) &&
        Objects.equals(this.storeSettings, store.storeSettings) &&
        Objects.equals(this.storeTypes, store.storeTypes) &&
        Objects.equals(this.storeUniqueId, store.storeUniqueId) &&
        Objects.equals(this.totalRating, store.totalRating) &&
        Objects.equals(this.userRatingReviews, store.userRatingReviews);
  }

  @Override
  public int hashCode() {
    return Objects.hash(banners, closingTime, contactNo, deliveryInfos, email, id, imageLink, info, location, locationName, maxDeliveryTime, minAmount, name, openingTime, preOrderSettings, regNo, storeAddress, storeSettings, storeTypes, storeUniqueId, totalRating, userRatingReviews);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Store {\n");
    
    sb.append("    banners: ").append(toIndentedString(banners)).append("\n");
    sb.append("    closingTime: ").append(toIndentedString(closingTime)).append("\n");
    sb.append("    contactNo: ").append(toIndentedString(contactNo)).append("\n");
    sb.append("    deliveryInfos: ").append(toIndentedString(deliveryInfos)).append("\n");
    sb.append("    email: ").append(toIndentedString(email)).append("\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    imageLink: ").append(toIndentedString(imageLink)).append("\n");
    sb.append("    info: ").append(toIndentedString(info)).append("\n");
    sb.append("    location: ").append(toIndentedString(location)).append("\n");
    sb.append("    locationName: ").append(toIndentedString(locationName)).append("\n");
    sb.append("    maxDeliveryTime: ").append(toIndentedString(maxDeliveryTime)).append("\n");
    sb.append("    minAmount: ").append(toIndentedString(minAmount)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    openingTime: ").append(toIndentedString(openingTime)).append("\n");
    sb.append("    preOrderSettings: ").append(toIndentedString(preOrderSettings)).append("\n");
    sb.append("    regNo: ").append(toIndentedString(regNo)).append("\n");
    sb.append("    storeAddress: ").append(toIndentedString(storeAddress)).append("\n");
    sb.append("    storeSettings: ").append(toIndentedString(storeSettings)).append("\n");
    sb.append("    storeTypes: ").append(toIndentedString(storeTypes)).append("\n");
    sb.append("    storeUniqueId: ").append(toIndentedString(storeUniqueId)).append("\n");
    sb.append("    totalRating: ").append(toIndentedString(totalRating)).append("\n");
    sb.append("    userRatingReviews: ").append(toIndentedString(userRatingReviews)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

