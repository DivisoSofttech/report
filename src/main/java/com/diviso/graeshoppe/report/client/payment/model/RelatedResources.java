package com.diviso.graeshoppe.report.client.payment.model;

import java.util.Objects;
import com.diviso.graeshoppe.report.client.payment.model.Refund;
import com.diviso.graeshoppe.report.client.payment.model.Sale;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * RelatedResources
 */
@Validated
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2019-08-27T13:48:47.677+05:30[Asia/Kolkata]")

public class RelatedResources   {
  @JsonProperty("refund")
  private Refund refund = null;

  @JsonProperty("sale")
  private Sale sale = null;

  public RelatedResources refund(Refund refund) {
    this.refund = refund;
    return this;
  }

  /**
   * Get refund
   * @return refund
  **/
  @ApiModelProperty(value = "")

  @Valid

  public Refund getRefund() {
    return refund;
  }

  public void setRefund(Refund refund) {
    this.refund = refund;
  }

  public RelatedResources sale(Sale sale) {
    this.sale = sale;
    return this;
  }

  /**
   * Get sale
   * @return sale
  **/
  @ApiModelProperty(value = "")

  @Valid

  public Sale getSale() {
    return sale;
  }

  public void setSale(Sale sale) {
    this.sale = sale;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    RelatedResources relatedResources = (RelatedResources) o;
    return Objects.equals(this.refund, relatedResources.refund) &&
        Objects.equals(this.sale, relatedResources.sale);
  }

  @Override
  public int hashCode() {
    return Objects.hash(refund, sale);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class RelatedResources {\n");
    
    sb.append("    refund: ").append(toIndentedString(refund)).append("\n");
    sb.append("    sale: ").append(toIndentedString(sale)).append("\n");
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

