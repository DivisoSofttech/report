/**
 * Autogenerated by Avro
 *
 * DO NOT EDIT DIRECTLY
 */
package com.diviso.graeshoppe.order.avro;

import org.apache.avro.specific.SpecificData;
import org.apache.avro.message.BinaryMessageEncoder;
import org.apache.avro.message.BinaryMessageDecoder;
import org.apache.avro.message.SchemaStore;

@SuppressWarnings("all")
@org.apache.avro.specific.AvroGenerated
public class OrderLine extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
  private static final long serialVersionUID = -2856764761358309748L;
  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"OrderLine\",\"namespace\":\"com.diviso.graeshoppe.order.avro\",\"fields\":[{\"name\":\"productId\",\"type\":\"long\"},{\"name\":\"quantity\",\"type\":\"int\"},{\"name\":\"pricePerUnit\",\"type\":\"double\"},{\"name\":\"total\",\"type\":\"double\"},{\"name\":\"auxilaryOrderLines\",\"type\":{\"type\":\"array\",\"items\":{\"type\":\"record\",\"name\":\"AuxilaryOrderLine\",\"fields\":[{\"name\":\"productId\",\"type\":\"long\"},{\"name\":\"quantity\",\"type\":\"int\"},{\"name\":\"pricePerUnit\",\"type\":\"double\"},{\"name\":\"total\",\"type\":\"double\"}]}}}]}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }

  private static SpecificData MODEL$ = new SpecificData();

  private static final BinaryMessageEncoder<OrderLine> ENCODER =
      new BinaryMessageEncoder<OrderLine>(MODEL$, SCHEMA$);

  private static final BinaryMessageDecoder<OrderLine> DECODER =
      new BinaryMessageDecoder<OrderLine>(MODEL$, SCHEMA$);

  /**
   * Return the BinaryMessageDecoder instance used by this class.
   */
  public static BinaryMessageDecoder<OrderLine> getDecoder() {
    return DECODER;
  }

  /**
   * Create a new BinaryMessageDecoder instance for this class that uses the specified {@link SchemaStore}.
   * @param resolver a {@link SchemaStore} used to find schemas by fingerprint
   */
  public static BinaryMessageDecoder<OrderLine> createDecoder(SchemaStore resolver) {
    return new BinaryMessageDecoder<OrderLine>(MODEL$, SCHEMA$, resolver);
  }

  /** Serializes this OrderLine to a ByteBuffer. */
  public java.nio.ByteBuffer toByteBuffer() throws java.io.IOException {
    return ENCODER.encode(this);
  }

  /** Deserializes a OrderLine from a ByteBuffer. */
  public static OrderLine fromByteBuffer(
      java.nio.ByteBuffer b) throws java.io.IOException {
    return DECODER.decode(b);
  }

  @Deprecated public long productId;
  @Deprecated public int quantity;
  @Deprecated public double pricePerUnit;
  @Deprecated public double total;
  @Deprecated public java.util.List<com.diviso.graeshoppe.order.avro.AuxilaryOrderLine> auxilaryOrderLines;

  /**
   * Default constructor.  Note that this does not initialize fields
   * to their default values from the schema.  If that is desired then
   * one should use <code>newBuilder()</code>.
   */
  public OrderLine() {}

  /**
   * All-args constructor.
   * @param productId The new value for productId
   * @param quantity The new value for quantity
   * @param pricePerUnit The new value for pricePerUnit
   * @param total The new value for total
   * @param auxilaryOrderLines The new value for auxilaryOrderLines
   */
  public OrderLine(java.lang.Long productId, java.lang.Integer quantity, java.lang.Double pricePerUnit, java.lang.Double total, java.util.List<com.diviso.graeshoppe.order.avro.AuxilaryOrderLine> auxilaryOrderLines) {
    this.productId = productId;
    this.quantity = quantity;
    this.pricePerUnit = pricePerUnit;
    this.total = total;
    this.auxilaryOrderLines = auxilaryOrderLines;
  }

  public org.apache.avro.Schema getSchema() { return SCHEMA$; }
  // Used by DatumWriter.  Applications should not call.
  public java.lang.Object get(int field$) {
    switch (field$) {
    case 0: return productId;
    case 1: return quantity;
    case 2: return pricePerUnit;
    case 3: return total;
    case 4: return auxilaryOrderLines;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  // Used by DatumReader.  Applications should not call.
  @SuppressWarnings(value="unchecked")
  public void put(int field$, java.lang.Object value$) {
    switch (field$) {
    case 0: productId = (java.lang.Long)value$; break;
    case 1: quantity = (java.lang.Integer)value$; break;
    case 2: pricePerUnit = (java.lang.Double)value$; break;
    case 3: total = (java.lang.Double)value$; break;
    case 4: auxilaryOrderLines = (java.util.List<com.diviso.graeshoppe.order.avro.AuxilaryOrderLine>)value$; break;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  /**
   * Gets the value of the 'productId' field.
   * @return The value of the 'productId' field.
   */
  public java.lang.Long getProductId() {
    return productId;
  }

  /**
   * Sets the value of the 'productId' field.
   * @param value the value to set.
   */
  public void setProductId(java.lang.Long value) {
    this.productId = value;
  }

  /**
   * Gets the value of the 'quantity' field.
   * @return The value of the 'quantity' field.
   */
  public java.lang.Integer getQuantity() {
    return quantity;
  }

  /**
   * Sets the value of the 'quantity' field.
   * @param value the value to set.
   */
  public void setQuantity(java.lang.Integer value) {
    this.quantity = value;
  }

  /**
   * Gets the value of the 'pricePerUnit' field.
   * @return The value of the 'pricePerUnit' field.
   */
  public java.lang.Double getPricePerUnit() {
    return pricePerUnit;
  }

  /**
   * Sets the value of the 'pricePerUnit' field.
   * @param value the value to set.
   */
  public void setPricePerUnit(java.lang.Double value) {
    this.pricePerUnit = value;
  }

  /**
   * Gets the value of the 'total' field.
   * @return The value of the 'total' field.
   */
  public java.lang.Double getTotal() {
    return total;
  }

  /**
   * Sets the value of the 'total' field.
   * @param value the value to set.
   */
  public void setTotal(java.lang.Double value) {
    this.total = value;
  }

  /**
   * Gets the value of the 'auxilaryOrderLines' field.
   * @return The value of the 'auxilaryOrderLines' field.
   */
  public java.util.List<com.diviso.graeshoppe.order.avro.AuxilaryOrderLine> getAuxilaryOrderLines() {
    return auxilaryOrderLines;
  }

  /**
   * Sets the value of the 'auxilaryOrderLines' field.
   * @param value the value to set.
   */
  public void setAuxilaryOrderLines(java.util.List<com.diviso.graeshoppe.order.avro.AuxilaryOrderLine> value) {
    this.auxilaryOrderLines = value;
  }

  /**
   * Creates a new OrderLine RecordBuilder.
   * @return A new OrderLine RecordBuilder
   */
  public static com.diviso.graeshoppe.order.avro.OrderLine.Builder newBuilder() {
    return new com.diviso.graeshoppe.order.avro.OrderLine.Builder();
  }

  /**
   * Creates a new OrderLine RecordBuilder by copying an existing Builder.
   * @param other The existing builder to copy.
   * @return A new OrderLine RecordBuilder
   */
  public static com.diviso.graeshoppe.order.avro.OrderLine.Builder newBuilder(com.diviso.graeshoppe.order.avro.OrderLine.Builder other) {
    return new com.diviso.graeshoppe.order.avro.OrderLine.Builder(other);
  }

  /**
   * Creates a new OrderLine RecordBuilder by copying an existing OrderLine instance.
   * @param other The existing instance to copy.
   * @return A new OrderLine RecordBuilder
   */
  public static com.diviso.graeshoppe.order.avro.OrderLine.Builder newBuilder(com.diviso.graeshoppe.order.avro.OrderLine other) {
    return new com.diviso.graeshoppe.order.avro.OrderLine.Builder(other);
  }

  /**
   * RecordBuilder for OrderLine instances.
   */
  public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<OrderLine>
    implements org.apache.avro.data.RecordBuilder<OrderLine> {

    private long productId;
    private int quantity;
    private double pricePerUnit;
    private double total;
    private java.util.List<com.diviso.graeshoppe.order.avro.AuxilaryOrderLine> auxilaryOrderLines;

    /** Creates a new Builder */
    private Builder() {
      super(SCHEMA$);
    }

    /**
     * Creates a Builder by copying an existing Builder.
     * @param other The existing Builder to copy.
     */
    private Builder(com.diviso.graeshoppe.order.avro.OrderLine.Builder other) {
      super(other);
      if (isValidValue(fields()[0], other.productId)) {
        this.productId = data().deepCopy(fields()[0].schema(), other.productId);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.quantity)) {
        this.quantity = data().deepCopy(fields()[1].schema(), other.quantity);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.pricePerUnit)) {
        this.pricePerUnit = data().deepCopy(fields()[2].schema(), other.pricePerUnit);
        fieldSetFlags()[2] = true;
      }
      if (isValidValue(fields()[3], other.total)) {
        this.total = data().deepCopy(fields()[3].schema(), other.total);
        fieldSetFlags()[3] = true;
      }
      if (isValidValue(fields()[4], other.auxilaryOrderLines)) {
        this.auxilaryOrderLines = data().deepCopy(fields()[4].schema(), other.auxilaryOrderLines);
        fieldSetFlags()[4] = true;
      }
    }

    /**
     * Creates a Builder by copying an existing OrderLine instance
     * @param other The existing instance to copy.
     */
    private Builder(com.diviso.graeshoppe.order.avro.OrderLine other) {
            super(SCHEMA$);
      if (isValidValue(fields()[0], other.productId)) {
        this.productId = data().deepCopy(fields()[0].schema(), other.productId);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.quantity)) {
        this.quantity = data().deepCopy(fields()[1].schema(), other.quantity);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.pricePerUnit)) {
        this.pricePerUnit = data().deepCopy(fields()[2].schema(), other.pricePerUnit);
        fieldSetFlags()[2] = true;
      }
      if (isValidValue(fields()[3], other.total)) {
        this.total = data().deepCopy(fields()[3].schema(), other.total);
        fieldSetFlags()[3] = true;
      }
      if (isValidValue(fields()[4], other.auxilaryOrderLines)) {
        this.auxilaryOrderLines = data().deepCopy(fields()[4].schema(), other.auxilaryOrderLines);
        fieldSetFlags()[4] = true;
      }
    }

    /**
      * Gets the value of the 'productId' field.
      * @return The value.
      */
    public java.lang.Long getProductId() {
      return productId;
    }

    /**
      * Sets the value of the 'productId' field.
      * @param value The value of 'productId'.
      * @return This builder.
      */
    public com.diviso.graeshoppe.order.avro.OrderLine.Builder setProductId(long value) {
      validate(fields()[0], value);
      this.productId = value;
      fieldSetFlags()[0] = true;
      return this;
    }

    /**
      * Checks whether the 'productId' field has been set.
      * @return True if the 'productId' field has been set, false otherwise.
      */
    public boolean hasProductId() {
      return fieldSetFlags()[0];
    }


    /**
      * Clears the value of the 'productId' field.
      * @return This builder.
      */
    public com.diviso.graeshoppe.order.avro.OrderLine.Builder clearProductId() {
      fieldSetFlags()[0] = false;
      return this;
    }

    /**
      * Gets the value of the 'quantity' field.
      * @return The value.
      */
    public java.lang.Integer getQuantity() {
      return quantity;
    }

    /**
      * Sets the value of the 'quantity' field.
      * @param value The value of 'quantity'.
      * @return This builder.
      */
    public com.diviso.graeshoppe.order.avro.OrderLine.Builder setQuantity(int value) {
      validate(fields()[1], value);
      this.quantity = value;
      fieldSetFlags()[1] = true;
      return this;
    }

    /**
      * Checks whether the 'quantity' field has been set.
      * @return True if the 'quantity' field has been set, false otherwise.
      */
    public boolean hasQuantity() {
      return fieldSetFlags()[1];
    }


    /**
      * Clears the value of the 'quantity' field.
      * @return This builder.
      */
    public com.diviso.graeshoppe.order.avro.OrderLine.Builder clearQuantity() {
      fieldSetFlags()[1] = false;
      return this;
    }

    /**
      * Gets the value of the 'pricePerUnit' field.
      * @return The value.
      */
    public java.lang.Double getPricePerUnit() {
      return pricePerUnit;
    }

    /**
      * Sets the value of the 'pricePerUnit' field.
      * @param value The value of 'pricePerUnit'.
      * @return This builder.
      */
    public com.diviso.graeshoppe.order.avro.OrderLine.Builder setPricePerUnit(double value) {
      validate(fields()[2], value);
      this.pricePerUnit = value;
      fieldSetFlags()[2] = true;
      return this;
    }

    /**
      * Checks whether the 'pricePerUnit' field has been set.
      * @return True if the 'pricePerUnit' field has been set, false otherwise.
      */
    public boolean hasPricePerUnit() {
      return fieldSetFlags()[2];
    }


    /**
      * Clears the value of the 'pricePerUnit' field.
      * @return This builder.
      */
    public com.diviso.graeshoppe.order.avro.OrderLine.Builder clearPricePerUnit() {
      fieldSetFlags()[2] = false;
      return this;
    }

    /**
      * Gets the value of the 'total' field.
      * @return The value.
      */
    public java.lang.Double getTotal() {
      return total;
    }

    /**
      * Sets the value of the 'total' field.
      * @param value The value of 'total'.
      * @return This builder.
      */
    public com.diviso.graeshoppe.order.avro.OrderLine.Builder setTotal(double value) {
      validate(fields()[3], value);
      this.total = value;
      fieldSetFlags()[3] = true;
      return this;
    }

    /**
      * Checks whether the 'total' field has been set.
      * @return True if the 'total' field has been set, false otherwise.
      */
    public boolean hasTotal() {
      return fieldSetFlags()[3];
    }


    /**
      * Clears the value of the 'total' field.
      * @return This builder.
      */
    public com.diviso.graeshoppe.order.avro.OrderLine.Builder clearTotal() {
      fieldSetFlags()[3] = false;
      return this;
    }

    /**
      * Gets the value of the 'auxilaryOrderLines' field.
      * @return The value.
      */
    public java.util.List<com.diviso.graeshoppe.order.avro.AuxilaryOrderLine> getAuxilaryOrderLines() {
      return auxilaryOrderLines;
    }

    /**
      * Sets the value of the 'auxilaryOrderLines' field.
      * @param value The value of 'auxilaryOrderLines'.
      * @return This builder.
      */
    public com.diviso.graeshoppe.order.avro.OrderLine.Builder setAuxilaryOrderLines(java.util.List<com.diviso.graeshoppe.order.avro.AuxilaryOrderLine> value) {
      validate(fields()[4], value);
      this.auxilaryOrderLines = value;
      fieldSetFlags()[4] = true;
      return this;
    }

    /**
      * Checks whether the 'auxilaryOrderLines' field has been set.
      * @return True if the 'auxilaryOrderLines' field has been set, false otherwise.
      */
    public boolean hasAuxilaryOrderLines() {
      return fieldSetFlags()[4];
    }


    /**
      * Clears the value of the 'auxilaryOrderLines' field.
      * @return This builder.
      */
    public com.diviso.graeshoppe.order.avro.OrderLine.Builder clearAuxilaryOrderLines() {
      auxilaryOrderLines = null;
      fieldSetFlags()[4] = false;
      return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public OrderLine build() {
      try {
        OrderLine record = new OrderLine();
        record.productId = fieldSetFlags()[0] ? this.productId : (java.lang.Long) defaultValue(fields()[0]);
        record.quantity = fieldSetFlags()[1] ? this.quantity : (java.lang.Integer) defaultValue(fields()[1]);
        record.pricePerUnit = fieldSetFlags()[2] ? this.pricePerUnit : (java.lang.Double) defaultValue(fields()[2]);
        record.total = fieldSetFlags()[3] ? this.total : (java.lang.Double) defaultValue(fields()[3]);
        record.auxilaryOrderLines = fieldSetFlags()[4] ? this.auxilaryOrderLines : (java.util.List<com.diviso.graeshoppe.order.avro.AuxilaryOrderLine>) defaultValue(fields()[4]);
        return record;
      } catch (java.lang.Exception e) {
        throw new org.apache.avro.AvroRuntimeException(e);
      }
    }
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumWriter<OrderLine>
    WRITER$ = (org.apache.avro.io.DatumWriter<OrderLine>)MODEL$.createDatumWriter(SCHEMA$);

  @Override public void writeExternal(java.io.ObjectOutput out)
    throws java.io.IOException {
    WRITER$.write(this, SpecificData.getEncoder(out));
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumReader<OrderLine>
    READER$ = (org.apache.avro.io.DatumReader<OrderLine>)MODEL$.createDatumReader(SCHEMA$);

  @Override public void readExternal(java.io.ObjectInput in)
    throws java.io.IOException {
    READER$.read(this, SpecificData.getDecoder(in));
  }

}