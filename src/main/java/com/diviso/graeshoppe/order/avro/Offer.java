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
public class Offer extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
  private static final long serialVersionUID = 8295749247496557154L;
  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"Offer\",\"namespace\":\"com.diviso.graeshoppe.order.avro\",\"fields\":[{\"name\":\"offerRef\",\"type\":[\"null\",{\"type\":\"string\",\"avro.java.string\":\"String\"}],\"default\":null}]}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }

  private static SpecificData MODEL$ = new SpecificData();

  private static final BinaryMessageEncoder<Offer> ENCODER =
      new BinaryMessageEncoder<Offer>(MODEL$, SCHEMA$);

  private static final BinaryMessageDecoder<Offer> DECODER =
      new BinaryMessageDecoder<Offer>(MODEL$, SCHEMA$);

  /**
   * Return the BinaryMessageDecoder instance used by this class.
   */
  public static BinaryMessageDecoder<Offer> getDecoder() {
    return DECODER;
  }

  /**
   * Create a new BinaryMessageDecoder instance for this class that uses the specified {@link SchemaStore}.
   * @param resolver a {@link SchemaStore} used to find schemas by fingerprint
   */
  public static BinaryMessageDecoder<Offer> createDecoder(SchemaStore resolver) {
    return new BinaryMessageDecoder<Offer>(MODEL$, SCHEMA$, resolver);
  }

  /** Serializes this Offer to a ByteBuffer. */
  public java.nio.ByteBuffer toByteBuffer() throws java.io.IOException {
    return ENCODER.encode(this);
  }

  /** Deserializes a Offer from a ByteBuffer. */
  public static Offer fromByteBuffer(
      java.nio.ByteBuffer b) throws java.io.IOException {
    return DECODER.decode(b);
  }

  @Deprecated public java.lang.String offerRef;

  /**
   * Default constructor.  Note that this does not initialize fields
   * to their default values from the schema.  If that is desired then
   * one should use <code>newBuilder()</code>.
   */
  public Offer() {}

  /**
   * All-args constructor.
   * @param offerRef The new value for offerRef
   */
  public Offer(java.lang.String offerRef) {
    this.offerRef = offerRef;
  }

  @Override
public org.apache.avro.Schema getSchema() { return SCHEMA$; }
  // Used by DatumWriter.  Applications should not call.
  @Override
public java.lang.Object get(int field$) {
    switch (field$) {
    case 0: return offerRef;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  // Used by DatumReader.  Applications should not call.
  @Override
@SuppressWarnings(value="unchecked")
  public void put(int field$, java.lang.Object value$) {
    switch (field$) {
    case 0: offerRef = (java.lang.String)value$; break;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  /**
   * Gets the value of the 'offerRef' field.
   * @return The value of the 'offerRef' field.
   */
  public java.lang.String getOfferRef() {
    return offerRef;
  }

  /**
   * Sets the value of the 'offerRef' field.
   * @param value the value to set.
   */
  public void setOfferRef(java.lang.String value) {
    this.offerRef = value;
  }

  /**
   * Creates a new Offer RecordBuilder.
   * @return A new Offer RecordBuilder
   */
  public static com.diviso.graeshoppe.order.avro.Offer.Builder newBuilder() {
    return new com.diviso.graeshoppe.order.avro.Offer.Builder();
  }

  /**
   * Creates a new Offer RecordBuilder by copying an existing Builder.
   * @param other The existing builder to copy.
   * @return A new Offer RecordBuilder
   */
  public static com.diviso.graeshoppe.order.avro.Offer.Builder newBuilder(com.diviso.graeshoppe.order.avro.Offer.Builder other) {
    return new com.diviso.graeshoppe.order.avro.Offer.Builder(other);
  }

  /**
   * Creates a new Offer RecordBuilder by copying an existing Offer instance.
   * @param other The existing instance to copy.
   * @return A new Offer RecordBuilder
   */
  public static com.diviso.graeshoppe.order.avro.Offer.Builder newBuilder(com.diviso.graeshoppe.order.avro.Offer other) {
    return new com.diviso.graeshoppe.order.avro.Offer.Builder(other);
  }

  /**
   * RecordBuilder for Offer instances.
   */
  public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<Offer>
    implements org.apache.avro.data.RecordBuilder<Offer> {

    private java.lang.String offerRef;

    /** Creates a new Builder */
    private Builder() {
      super(SCHEMA$);
    }

    /**
     * Creates a Builder by copying an existing Builder.
     * @param other The existing Builder to copy.
     */
    private Builder(com.diviso.graeshoppe.order.avro.Offer.Builder other) {
      super(other);
      if (isValidValue(fields()[0], other.offerRef)) {
        this.offerRef = data().deepCopy(fields()[0].schema(), other.offerRef);
        fieldSetFlags()[0] = true;
      }
    }

    /**
     * Creates a Builder by copying an existing Offer instance
     * @param other The existing instance to copy.
     */
    private Builder(com.diviso.graeshoppe.order.avro.Offer other) {
            super(SCHEMA$);
      if (isValidValue(fields()[0], other.offerRef)) {
        this.offerRef = data().deepCopy(fields()[0].schema(), other.offerRef);
        fieldSetFlags()[0] = true;
      }
    }

    /**
      * Gets the value of the 'offerRef' field.
      * @return The value.
      */
    public java.lang.String getOfferRef() {
      return offerRef;
    }

    /**
      * Sets the value of the 'offerRef' field.
      * @param value The value of 'offerRef'.
      * @return This builder.
      */
    public com.diviso.graeshoppe.order.avro.Offer.Builder setOfferRef(java.lang.String value) {
      validate(fields()[0], value);
      this.offerRef = value;
      fieldSetFlags()[0] = true;
      return this;
    }

    /**
      * Checks whether the 'offerRef' field has been set.
      * @return True if the 'offerRef' field has been set, false otherwise.
      */
    public boolean hasOfferRef() {
      return fieldSetFlags()[0];
    }


    /**
      * Clears the value of the 'offerRef' field.
      * @return This builder.
      */
    public com.diviso.graeshoppe.order.avro.Offer.Builder clearOfferRef() {
      offerRef = null;
      fieldSetFlags()[0] = false;
      return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Offer build() {
      try {
        Offer record = new Offer();
        record.offerRef = fieldSetFlags()[0] ? this.offerRef : (java.lang.String) defaultValue(fields()[0]);
        return record;
      } catch (java.lang.Exception e) {
        throw new org.apache.avro.AvroRuntimeException(e);
      }
    }
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumWriter<Offer>
    WRITER$ = MODEL$.createDatumWriter(SCHEMA$);

  @Override public void writeExternal(java.io.ObjectOutput out)
    throws java.io.IOException {
    WRITER$.write(this, SpecificData.getEncoder(out));
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumReader<Offer>
    READER$ = MODEL$.createDatumReader(SCHEMA$);

  @Override public void readExternal(java.io.ObjectInput in)
    throws java.io.IOException {
    READER$.read(this, SpecificData.getDecoder(in));
  }

}
