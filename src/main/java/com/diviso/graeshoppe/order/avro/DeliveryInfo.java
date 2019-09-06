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
public class DeliveryInfo extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
  private static final long serialVersionUID = 2820618934374478005L;
  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"DeliveryInfo\",\"namespace\":\"com.diviso.graeshoppe.order.avro\",\"fields\":[{\"name\":\"deliveryType\",\"type\":[\"null\",{\"type\":\"string\",\"avro.java.string\":\"String\"}]},{\"name\":\"deliveryCharge\",\"type\":[\"null\",\"double\"],\"default\":0.0},{\"name\":\"deliveryNotes\",\"type\":[\"null\",{\"type\":\"string\",\"avro.java.string\":\"String\"}],\"default\":null},{\"name\":\"deliveryAddress\",\"type\":[\"null\",{\"type\":\"record\",\"name\":\"Address\",\"fields\":[{\"name\":\"customerId\",\"type\":[\"null\",{\"type\":\"string\",\"avro.java.string\":\"String\"}],\"default\":null},{\"name\":\"pincode\",\"type\":\"long\"},{\"name\":\"houseNoOrBuildingName\",\"type\":[\"null\",{\"type\":\"string\",\"avro.java.string\":\"String\"}],\"default\":null},{\"name\":\"roadNameAreaOrStreet\",\"type\":[\"null\",{\"type\":\"string\",\"avro.java.string\":\"String\"}],\"default\":null},{\"name\":\"city\",\"type\":[\"null\",{\"type\":\"string\",\"avro.java.string\":\"String\"}],\"default\":null},{\"name\":\"state\",\"type\":[\"null\",{\"type\":\"string\",\"avro.java.string\":\"String\"}],\"default\":null},{\"name\":\"landmark\",\"type\":[\"null\",{\"type\":\"string\",\"avro.java.string\":\"String\"}],\"default\":null},{\"name\":\"name\",\"type\":[\"null\",{\"type\":\"string\",\"avro.java.string\":\"String\"}],\"default\":null},{\"name\":\"addressType\",\"type\":[\"null\",{\"type\":\"string\",\"avro.java.string\":\"String\"}],\"default\":null},{\"name\":\"phone\",\"type\":\"long\",\"default\":null},{\"name\":\"alternatePhone\",\"type\":[\"null\",\"long\"],\"default\":null}]}],\"default\":null}]}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }

  private static SpecificData MODEL$ = new SpecificData();

  private static final BinaryMessageEncoder<DeliveryInfo> ENCODER =
      new BinaryMessageEncoder<DeliveryInfo>(MODEL$, SCHEMA$);

  private static final BinaryMessageDecoder<DeliveryInfo> DECODER =
      new BinaryMessageDecoder<DeliveryInfo>(MODEL$, SCHEMA$);

  /**
   * Return the BinaryMessageDecoder instance used by this class.
   */
  public static BinaryMessageDecoder<DeliveryInfo> getDecoder() {
    return DECODER;
  }

  /**
   * Create a new BinaryMessageDecoder instance for this class that uses the specified {@link SchemaStore}.
   * @param resolver a {@link SchemaStore} used to find schemas by fingerprint
   */
  public static BinaryMessageDecoder<DeliveryInfo> createDecoder(SchemaStore resolver) {
    return new BinaryMessageDecoder<DeliveryInfo>(MODEL$, SCHEMA$, resolver);
  }

  /** Serializes this DeliveryInfo to a ByteBuffer. */
  public java.nio.ByteBuffer toByteBuffer() throws java.io.IOException {
    return ENCODER.encode(this);
  }

  /** Deserializes a DeliveryInfo from a ByteBuffer. */
  public static DeliveryInfo fromByteBuffer(
      java.nio.ByteBuffer b) throws java.io.IOException {
    return DECODER.decode(b);
  }

  @Deprecated public java.lang.String deliveryType;
  @Deprecated public java.lang.Double deliveryCharge;
  @Deprecated public java.lang.String deliveryNotes;
  @Deprecated public com.diviso.graeshoppe.order.avro.Address deliveryAddress;

  /**
   * Default constructor.  Note that this does not initialize fields
   * to their default values from the schema.  If that is desired then
   * one should use <code>newBuilder()</code>.
   */
  public DeliveryInfo() {}

  /**
   * All-args constructor.
   * @param deliveryType The new value for deliveryType
   * @param deliveryCharge The new value for deliveryCharge
   * @param deliveryNotes The new value for deliveryNotes
   * @param deliveryAddress The new value for deliveryAddress
   */
  public DeliveryInfo(java.lang.String deliveryType, java.lang.Double deliveryCharge, java.lang.String deliveryNotes, com.diviso.graeshoppe.order.avro.Address deliveryAddress) {
    this.deliveryType = deliveryType;
    this.deliveryCharge = deliveryCharge;
    this.deliveryNotes = deliveryNotes;
    this.deliveryAddress = deliveryAddress;
  }

  public org.apache.avro.Schema getSchema() { return SCHEMA$; }
  // Used by DatumWriter.  Applications should not call.
  public java.lang.Object get(int field$) {
    switch (field$) {
    case 0: return deliveryType;
    case 1: return deliveryCharge;
    case 2: return deliveryNotes;
    case 3: return deliveryAddress;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  // Used by DatumReader.  Applications should not call.
  @SuppressWarnings(value="unchecked")
  public void put(int field$, java.lang.Object value$) {
    switch (field$) {
    case 0: deliveryType = (java.lang.String)value$; break;
    case 1: deliveryCharge = (java.lang.Double)value$; break;
    case 2: deliveryNotes = (java.lang.String)value$; break;
    case 3: deliveryAddress = (com.diviso.graeshoppe.order.avro.Address)value$; break;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  /**
   * Gets the value of the 'deliveryType' field.
   * @return The value of the 'deliveryType' field.
   */
  public java.lang.String getDeliveryType() {
    return deliveryType;
  }

  /**
   * Sets the value of the 'deliveryType' field.
   * @param value the value to set.
   */
  public void setDeliveryType(java.lang.String value) {
    this.deliveryType = value;
  }

  /**
   * Gets the value of the 'deliveryCharge' field.
   * @return The value of the 'deliveryCharge' field.
   */
  public java.lang.Double getDeliveryCharge() {
    return deliveryCharge;
  }

  /**
   * Sets the value of the 'deliveryCharge' field.
   * @param value the value to set.
   */
  public void setDeliveryCharge(java.lang.Double value) {
    this.deliveryCharge = value;
  }

  /**
   * Gets the value of the 'deliveryNotes' field.
   * @return The value of the 'deliveryNotes' field.
   */
  public java.lang.String getDeliveryNotes() {
    return deliveryNotes;
  }

  /**
   * Sets the value of the 'deliveryNotes' field.
   * @param value the value to set.
   */
  public void setDeliveryNotes(java.lang.String value) {
    this.deliveryNotes = value;
  }

  /**
   * Gets the value of the 'deliveryAddress' field.
   * @return The value of the 'deliveryAddress' field.
   */
  public com.diviso.graeshoppe.order.avro.Address getDeliveryAddress() {
    return deliveryAddress;
  }

  /**
   * Sets the value of the 'deliveryAddress' field.
   * @param value the value to set.
   */
  public void setDeliveryAddress(com.diviso.graeshoppe.order.avro.Address value) {
    this.deliveryAddress = value;
  }

  /**
   * Creates a new DeliveryInfo RecordBuilder.
   * @return A new DeliveryInfo RecordBuilder
   */
  public static com.diviso.graeshoppe.order.avro.DeliveryInfo.Builder newBuilder() {
    return new com.diviso.graeshoppe.order.avro.DeliveryInfo.Builder();
  }

  /**
   * Creates a new DeliveryInfo RecordBuilder by copying an existing Builder.
   * @param other The existing builder to copy.
   * @return A new DeliveryInfo RecordBuilder
   */
  public static com.diviso.graeshoppe.order.avro.DeliveryInfo.Builder newBuilder(com.diviso.graeshoppe.order.avro.DeliveryInfo.Builder other) {
    return new com.diviso.graeshoppe.order.avro.DeliveryInfo.Builder(other);
  }

  /**
   * Creates a new DeliveryInfo RecordBuilder by copying an existing DeliveryInfo instance.
   * @param other The existing instance to copy.
   * @return A new DeliveryInfo RecordBuilder
   */
  public static com.diviso.graeshoppe.order.avro.DeliveryInfo.Builder newBuilder(com.diviso.graeshoppe.order.avro.DeliveryInfo other) {
    return new com.diviso.graeshoppe.order.avro.DeliveryInfo.Builder(other);
  }

  /**
   * RecordBuilder for DeliveryInfo instances.
   */
  public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<DeliveryInfo>
    implements org.apache.avro.data.RecordBuilder<DeliveryInfo> {

    private java.lang.String deliveryType;
    private java.lang.Double deliveryCharge;
    private java.lang.String deliveryNotes;
    private com.diviso.graeshoppe.order.avro.Address deliveryAddress;
    private com.diviso.graeshoppe.order.avro.Address.Builder deliveryAddressBuilder;

    /** Creates a new Builder */
    private Builder() {
      super(SCHEMA$);
    }

    /**
     * Creates a Builder by copying an existing Builder.
     * @param other The existing Builder to copy.
     */
    private Builder(com.diviso.graeshoppe.order.avro.DeliveryInfo.Builder other) {
      super(other);
      if (isValidValue(fields()[0], other.deliveryType)) {
        this.deliveryType = data().deepCopy(fields()[0].schema(), other.deliveryType);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.deliveryCharge)) {
        this.deliveryCharge = data().deepCopy(fields()[1].schema(), other.deliveryCharge);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.deliveryNotes)) {
        this.deliveryNotes = data().deepCopy(fields()[2].schema(), other.deliveryNotes);
        fieldSetFlags()[2] = true;
      }
      if (isValidValue(fields()[3], other.deliveryAddress)) {
        this.deliveryAddress = data().deepCopy(fields()[3].schema(), other.deliveryAddress);
        fieldSetFlags()[3] = true;
      }
      if (other.hasDeliveryAddressBuilder()) {
        this.deliveryAddressBuilder = com.diviso.graeshoppe.order.avro.Address.newBuilder(other.getDeliveryAddressBuilder());
      }
    }

    /**
     * Creates a Builder by copying an existing DeliveryInfo instance
     * @param other The existing instance to copy.
     */
    private Builder(com.diviso.graeshoppe.order.avro.DeliveryInfo other) {
            super(SCHEMA$);
      if (isValidValue(fields()[0], other.deliveryType)) {
        this.deliveryType = data().deepCopy(fields()[0].schema(), other.deliveryType);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.deliveryCharge)) {
        this.deliveryCharge = data().deepCopy(fields()[1].schema(), other.deliveryCharge);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.deliveryNotes)) {
        this.deliveryNotes = data().deepCopy(fields()[2].schema(), other.deliveryNotes);
        fieldSetFlags()[2] = true;
      }
      if (isValidValue(fields()[3], other.deliveryAddress)) {
        this.deliveryAddress = data().deepCopy(fields()[3].schema(), other.deliveryAddress);
        fieldSetFlags()[3] = true;
      }
      this.deliveryAddressBuilder = null;
    }

    /**
      * Gets the value of the 'deliveryType' field.
      * @return The value.
      */
    public java.lang.String getDeliveryType() {
      return deliveryType;
    }

    /**
      * Sets the value of the 'deliveryType' field.
      * @param value The value of 'deliveryType'.
      * @return This builder.
      */
    public com.diviso.graeshoppe.order.avro.DeliveryInfo.Builder setDeliveryType(java.lang.String value) {
      validate(fields()[0], value);
      this.deliveryType = value;
      fieldSetFlags()[0] = true;
      return this;
    }

    /**
      * Checks whether the 'deliveryType' field has been set.
      * @return True if the 'deliveryType' field has been set, false otherwise.
      */
    public boolean hasDeliveryType() {
      return fieldSetFlags()[0];
    }


    /**
      * Clears the value of the 'deliveryType' field.
      * @return This builder.
      */
    public com.diviso.graeshoppe.order.avro.DeliveryInfo.Builder clearDeliveryType() {
      deliveryType = null;
      fieldSetFlags()[0] = false;
      return this;
    }

    /**
      * Gets the value of the 'deliveryCharge' field.
      * @return The value.
      */
    public java.lang.Double getDeliveryCharge() {
      return deliveryCharge;
    }

    /**
      * Sets the value of the 'deliveryCharge' field.
      * @param value The value of 'deliveryCharge'.
      * @return This builder.
      */
    public com.diviso.graeshoppe.order.avro.DeliveryInfo.Builder setDeliveryCharge(java.lang.Double value) {
      validate(fields()[1], value);
      this.deliveryCharge = value;
      fieldSetFlags()[1] = true;
      return this;
    }

    /**
      * Checks whether the 'deliveryCharge' field has been set.
      * @return True if the 'deliveryCharge' field has been set, false otherwise.
      */
    public boolean hasDeliveryCharge() {
      return fieldSetFlags()[1];
    }


    /**
      * Clears the value of the 'deliveryCharge' field.
      * @return This builder.
      */
    public com.diviso.graeshoppe.order.avro.DeliveryInfo.Builder clearDeliveryCharge() {
      deliveryCharge = null;
      fieldSetFlags()[1] = false;
      return this;
    }

    /**
      * Gets the value of the 'deliveryNotes' field.
      * @return The value.
      */
    public java.lang.String getDeliveryNotes() {
      return deliveryNotes;
    }

    /**
      * Sets the value of the 'deliveryNotes' field.
      * @param value The value of 'deliveryNotes'.
      * @return This builder.
      */
    public com.diviso.graeshoppe.order.avro.DeliveryInfo.Builder setDeliveryNotes(java.lang.String value) {
      validate(fields()[2], value);
      this.deliveryNotes = value;
      fieldSetFlags()[2] = true;
      return this;
    }

    /**
      * Checks whether the 'deliveryNotes' field has been set.
      * @return True if the 'deliveryNotes' field has been set, false otherwise.
      */
    public boolean hasDeliveryNotes() {
      return fieldSetFlags()[2];
    }


    /**
      * Clears the value of the 'deliveryNotes' field.
      * @return This builder.
      */
    public com.diviso.graeshoppe.order.avro.DeliveryInfo.Builder clearDeliveryNotes() {
      deliveryNotes = null;
      fieldSetFlags()[2] = false;
      return this;
    }

    /**
      * Gets the value of the 'deliveryAddress' field.
      * @return The value.
      */
    public com.diviso.graeshoppe.order.avro.Address getDeliveryAddress() {
      return deliveryAddress;
    }

    /**
      * Sets the value of the 'deliveryAddress' field.
      * @param value The value of 'deliveryAddress'.
      * @return This builder.
      */
    public com.diviso.graeshoppe.order.avro.DeliveryInfo.Builder setDeliveryAddress(com.diviso.graeshoppe.order.avro.Address value) {
      validate(fields()[3], value);
      this.deliveryAddressBuilder = null;
      this.deliveryAddress = value;
      fieldSetFlags()[3] = true;
      return this;
    }

    /**
      * Checks whether the 'deliveryAddress' field has been set.
      * @return True if the 'deliveryAddress' field has been set, false otherwise.
      */
    public boolean hasDeliveryAddress() {
      return fieldSetFlags()[3];
    }

    /**
     * Gets the Builder instance for the 'deliveryAddress' field and creates one if it doesn't exist yet.
     * @return This builder.
     */
    public com.diviso.graeshoppe.order.avro.Address.Builder getDeliveryAddressBuilder() {
      if (deliveryAddressBuilder == null) {
        if (hasDeliveryAddress()) {
          setDeliveryAddressBuilder(com.diviso.graeshoppe.order.avro.Address.newBuilder(deliveryAddress));
        } else {
          setDeliveryAddressBuilder(com.diviso.graeshoppe.order.avro.Address.newBuilder());
        }
      }
      return deliveryAddressBuilder;
    }

    /**
     * Sets the Builder instance for the 'deliveryAddress' field
     * @param value The builder instance that must be set.
     * @return This builder.
     */
    public com.diviso.graeshoppe.order.avro.DeliveryInfo.Builder setDeliveryAddressBuilder(com.diviso.graeshoppe.order.avro.Address.Builder value) {
      clearDeliveryAddress();
      deliveryAddressBuilder = value;
      return this;
    }

    /**
     * Checks whether the 'deliveryAddress' field has an active Builder instance
     * @return True if the 'deliveryAddress' field has an active Builder instance
     */
    public boolean hasDeliveryAddressBuilder() {
      return deliveryAddressBuilder != null;
    }

    /**
      * Clears the value of the 'deliveryAddress' field.
      * @return This builder.
      */
    public com.diviso.graeshoppe.order.avro.DeliveryInfo.Builder clearDeliveryAddress() {
      deliveryAddress = null;
      deliveryAddressBuilder = null;
      fieldSetFlags()[3] = false;
      return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public DeliveryInfo build() {
      try {
        DeliveryInfo record = new DeliveryInfo();
        record.deliveryType = fieldSetFlags()[0] ? this.deliveryType : (java.lang.String) defaultValue(fields()[0]);
        record.deliveryCharge = fieldSetFlags()[1] ? this.deliveryCharge : (java.lang.Double) defaultValue(fields()[1]);
        record.deliveryNotes = fieldSetFlags()[2] ? this.deliveryNotes : (java.lang.String) defaultValue(fields()[2]);
        if (deliveryAddressBuilder != null) {
          record.deliveryAddress = this.deliveryAddressBuilder.build();
        } else {
          record.deliveryAddress = fieldSetFlags()[3] ? this.deliveryAddress : (com.diviso.graeshoppe.order.avro.Address) defaultValue(fields()[3]);
        }
        return record;
      } catch (java.lang.Exception e) {
        throw new org.apache.avro.AvroRuntimeException(e);
      }
    }
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumWriter<DeliveryInfo>
    WRITER$ = (org.apache.avro.io.DatumWriter<DeliveryInfo>)MODEL$.createDatumWriter(SCHEMA$);

  @Override public void writeExternal(java.io.ObjectOutput out)
    throws java.io.IOException {
    WRITER$.write(this, SpecificData.getEncoder(out));
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumReader<DeliveryInfo>
    READER$ = (org.apache.avro.io.DatumReader<DeliveryInfo>)MODEL$.createDatumReader(SCHEMA$);

  @Override public void readExternal(java.io.ObjectInput in)
    throws java.io.IOException {
    READER$.read(this, SpecificData.getDecoder(in));
  }

}
