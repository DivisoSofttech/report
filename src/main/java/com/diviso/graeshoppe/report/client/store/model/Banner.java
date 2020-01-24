package com.diviso.graeshoppe.report.client.store.model;

import java.util.Objects;
import com.diviso.graeshoppe.report.client.store.model.Store;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;

/**
 * Banner
 */
@Validated
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2019-12-31T16:37:58.581+05:30[Asia/Kolkata]")

public class Banner   {
  @JsonProperty("id")
  private Long id = null;

  @JsonProperty("imageLink")
  private String imageLink = null;

  @JsonProperty("store")
  private Store store = null;

  public Banner id(Long id) {
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

  public Banner imageLink(String imageLink) {
    this.imageLink = imageLink;
    return this;
  }

  /**
   * Get imageLink
   * @return imageLink
  **/
  @ApiModelProperty(value = "")


  public String getImageLink() {
    return imageLink;
  }

  public void setImageLink(String imageLink) {
    this.imageLink = imageLink;
  }

  public Banner store(Store store) {
    this.store = store;
    return this;
  }

  /**
   * Get store
   * @return store
  **/
  @ApiModelProperty(value = "")

  @Valid

  public Store getStore() {
    return store;
  }

  public void setStore(Store store) {
    this.store = store;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Banner banner = (Banner) o;
    return Objects.equals(this.id, banner.id) &&
        Objects.equals(this.imageLink, banner.imageLink) &&
        Objects.equals(this.store, banner.store);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, imageLink, store);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Banner {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    imageLink: ").append(toIndentedString(imageLink)).append("\n");
    sb.append("    store: ").append(toIndentedString(store)).append("\n");
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

