package com.diviso.graeshoppe.report.client.payment.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Patch
 */
@Validated
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2019-08-27T13:48:47.677+05:30[Asia/Kolkata]")

public class Patch   {
  @JsonProperty("from")
  private String from = null;

  @JsonProperty("op")
  private String op = null;

  @JsonProperty("path")
  private String path = null;

  @JsonProperty("value")
  private Object value = null;

  public Patch from(String from) {
    this.from = from;
    return this;
  }

  /**
   * Get from
   * @return from
  **/
  @ApiModelProperty(value = "")


  public String getFrom() {
    return from;
  }

  public void setFrom(String from) {
    this.from = from;
  }

  public Patch op(String op) {
    this.op = op;
    return this;
  }

  /**
   * Get op
   * @return op
  **/
  @ApiModelProperty(value = "")


  public String getOp() {
    return op;
  }

  public void setOp(String op) {
    this.op = op;
  }

  public Patch path(String path) {
    this.path = path;
    return this;
  }

  /**
   * Get path
   * @return path
  **/
  @ApiModelProperty(value = "")


  public String getPath() {
    return path;
  }

  public void setPath(String path) {
    this.path = path;
  }

  public Patch value(Object value) {
    this.value = value;
    return this;
  }

  /**
   * Get value
   * @return value
  **/
  @ApiModelProperty(value = "")


  public Object getValue() {
    return value;
  }

  public void setValue(Object value) {
    this.value = value;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Patch patch = (Patch) o;
    return Objects.equals(this.from, patch.from) &&
        Objects.equals(this.op, patch.op) &&
        Objects.equals(this.path, patch.path) &&
        Objects.equals(this.value, patch.value);
  }

  @Override
  public int hashCode() {
    return Objects.hash(from, op, path, value);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Patch {\n");
    
    sb.append("    from: ").append(toIndentedString(from)).append("\n");
    sb.append("    op: ").append(toIndentedString(op)).append("\n");
    sb.append("    path: ").append(toIndentedString(path)).append("\n");
    sb.append("    value: ").append(toIndentedString(value)).append("\n");
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

