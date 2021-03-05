// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: parspice.proto

package parspiceTest;

/**
 * Protobuf type {@code parspiceTest.SpkposRep}
 */
public  final class SpkposRep extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:parspiceTest.SpkposRep)
    SpkposRepOrBuilder {
private static final long serialVersionUID = 0L;
  // Use SpkposRep.newBuilder() to construct.
  private SpkposRep(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private SpkposRep() {
    time_ = emptyDoubleList();
  }

  @java.lang.Override
  @SuppressWarnings({"unused"})
  protected java.lang.Object newInstance(
      UnusedPrivateParameter unused) {
    return new SpkposRep();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private SpkposRep(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    this();
    if (extensionRegistry == null) {
      throw new java.lang.NullPointerException();
    }
    int mutable_bitField0_ = 0;
    com.google.protobuf.UnknownFieldSet.Builder unknownFields =
        com.google.protobuf.UnknownFieldSet.newBuilder();
    try {
      boolean done = false;
      while (!done) {
        int tag = input.readTag();
        switch (tag) {
          case 0:
            done = true;
            break;
          case 9: {
            if (!((mutable_bitField0_ & 0x00000001) != 0)) {
              time_ = newDoubleList();
              mutable_bitField0_ |= 0x00000001;
            }
            time_.addDouble(input.readDouble());
            break;
          }
          case 10: {
            int length = input.readRawVarint32();
            int limit = input.pushLimit(length);
            if (!((mutable_bitField0_ & 0x00000001) != 0) && input.getBytesUntilLimit() > 0) {
              time_ = newDoubleList();
              mutable_bitField0_ |= 0x00000001;
            }
            while (input.getBytesUntilLimit() > 0) {
              time_.addDouble(input.readDouble());
            }
            input.popLimit(limit);
            break;
          }
          default: {
            if (!parseUnknownField(
                input, unknownFields, extensionRegistry, tag)) {
              done = true;
            }
            break;
          }
        }
      }
    } catch (com.google.protobuf.InvalidProtocolBufferException e) {
      throw e.setUnfinishedMessage(this);
    } catch (java.io.IOException e) {
      throw new com.google.protobuf.InvalidProtocolBufferException(
          e).setUnfinishedMessage(this);
    } finally {
      if (((mutable_bitField0_ & 0x00000001) != 0)) {
        time_.makeImmutable(); // C
      }
      this.unknownFields = unknownFields.build();
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return parspiceTest.Parspice.internal_static_parspiceTest_SpkposRep_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return parspiceTest.Parspice.internal_static_parspiceTest_SpkposRep_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            parspiceTest.SpkposRep.class, parspiceTest.SpkposRep.Builder.class);
  }

  public static final int TIME_FIELD_NUMBER = 1;
  private com.google.protobuf.Internal.DoubleList time_;
  /**
   * <code>repeated double time = 1;</code>
   * @return A list containing the time.
   */
  public java.util.List<java.lang.Double>
      getTimeList() {
    return time_;
  }
  /**
   * <code>repeated double time = 1;</code>
   * @return The count of time.
   */
  public int getTimeCount() {
    return time_.size();
  }
  /**
   * <code>repeated double time = 1;</code>
   * @param index The index of the element to return.
   * @return The time at the given index.
   */
  public double getTime(int index) {
    return time_.getDouble(index);
  }
  private int timeMemoizedSerializedSize = -1;

  private byte memoizedIsInitialized = -1;
  @java.lang.Override
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    memoizedIsInitialized = 1;
    return true;
  }

  @java.lang.Override
  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    getSerializedSize();
    if (getTimeList().size() > 0) {
      output.writeUInt32NoTag(10);
      output.writeUInt32NoTag(timeMemoizedSerializedSize);
    }
    for (int i = 0; i < time_.size(); i++) {
      output.writeDoubleNoTag(time_.getDouble(i));
    }
    unknownFields.writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    {
      int dataSize = 0;
      dataSize = 8 * getTimeList().size();
      size += dataSize;
      if (!getTimeList().isEmpty()) {
        size += 1;
        size += com.google.protobuf.CodedOutputStream
            .computeInt32SizeNoTag(dataSize);
      }
      timeMemoizedSerializedSize = dataSize;
    }
    size += unknownFields.getSerializedSize();
    memoizedSize = size;
    return size;
  }

  @java.lang.Override
  public boolean equals(final java.lang.Object obj) {
    if (obj == this) {
     return true;
    }
    if (!(obj instanceof parspiceTest.SpkposRep)) {
      return super.equals(obj);
    }
    parspiceTest.SpkposRep other = (parspiceTest.SpkposRep) obj;

    if (!getTimeList()
        .equals(other.getTimeList())) return false;
    if (!unknownFields.equals(other.unknownFields)) return false;
    return true;
  }

  @java.lang.Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptor().hashCode();
    if (getTimeCount() > 0) {
      hash = (37 * hash) + TIME_FIELD_NUMBER;
      hash = (53 * hash) + getTimeList().hashCode();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static parspiceTest.SpkposRep parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static parspiceTest.SpkposRep parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static parspiceTest.SpkposRep parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static parspiceTest.SpkposRep parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static parspiceTest.SpkposRep parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static parspiceTest.SpkposRep parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static parspiceTest.SpkposRep parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static parspiceTest.SpkposRep parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static parspiceTest.SpkposRep parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static parspiceTest.SpkposRep parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static parspiceTest.SpkposRep parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static parspiceTest.SpkposRep parseFrom(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  @java.lang.Override
  public Builder newBuilderForType() { return newBuilder(); }
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  public static Builder newBuilder(parspiceTest.SpkposRep prototype) {
    return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
  }
  @java.lang.Override
  public Builder toBuilder() {
    return this == DEFAULT_INSTANCE
        ? new Builder() : new Builder().mergeFrom(this);
  }

  @java.lang.Override
  protected Builder newBuilderForType(
      com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
    Builder builder = new Builder(parent);
    return builder;
  }
  /**
   * Protobuf type {@code parspiceTest.SpkposRep}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:parspiceTest.SpkposRep)
      parspiceTest.SpkposRepOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return parspiceTest.Parspice.internal_static_parspiceTest_SpkposRep_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return parspiceTest.Parspice.internal_static_parspiceTest_SpkposRep_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              parspiceTest.SpkposRep.class, parspiceTest.SpkposRep.Builder.class);
    }

    // Construct using parspiceTest.SpkposRep.newBuilder()
    private Builder() {
      maybeForceBuilderInitialization();
    }

    private Builder(
        com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
      super(parent);
      maybeForceBuilderInitialization();
    }
    private void maybeForceBuilderInitialization() {
      if (com.google.protobuf.GeneratedMessageV3
              .alwaysUseFieldBuilders) {
      }
    }
    @java.lang.Override
    public Builder clear() {
      super.clear();
      time_ = emptyDoubleList();
      bitField0_ = (bitField0_ & ~0x00000001);
      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return parspiceTest.Parspice.internal_static_parspiceTest_SpkposRep_descriptor;
    }

    @java.lang.Override
    public parspiceTest.SpkposRep getDefaultInstanceForType() {
      return parspiceTest.SpkposRep.getDefaultInstance();
    }

    @java.lang.Override
    public parspiceTest.SpkposRep build() {
      parspiceTest.SpkposRep result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public parspiceTest.SpkposRep buildPartial() {
      parspiceTest.SpkposRep result = new parspiceTest.SpkposRep(this);
      int from_bitField0_ = bitField0_;
      if (((bitField0_ & 0x00000001) != 0)) {
        time_.makeImmutable();
        bitField0_ = (bitField0_ & ~0x00000001);
      }
      result.time_ = time_;
      onBuilt();
      return result;
    }

    @java.lang.Override
    public Builder clone() {
      return super.clone();
    }
    @java.lang.Override
    public Builder setField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return super.setField(field, value);
    }
    @java.lang.Override
    public Builder clearField(
        com.google.protobuf.Descriptors.FieldDescriptor field) {
      return super.clearField(field);
    }
    @java.lang.Override
    public Builder clearOneof(
        com.google.protobuf.Descriptors.OneofDescriptor oneof) {
      return super.clearOneof(oneof);
    }
    @java.lang.Override
    public Builder setRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        int index, java.lang.Object value) {
      return super.setRepeatedField(field, index, value);
    }
    @java.lang.Override
    public Builder addRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return super.addRepeatedField(field, value);
    }
    @java.lang.Override
    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof parspiceTest.SpkposRep) {
        return mergeFrom((parspiceTest.SpkposRep)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(parspiceTest.SpkposRep other) {
      if (other == parspiceTest.SpkposRep.getDefaultInstance()) return this;
      if (!other.time_.isEmpty()) {
        if (time_.isEmpty()) {
          time_ = other.time_;
          bitField0_ = (bitField0_ & ~0x00000001);
        } else {
          ensureTimeIsMutable();
          time_.addAll(other.time_);
        }
        onChanged();
      }
      this.mergeUnknownFields(other.unknownFields);
      onChanged();
      return this;
    }

    @java.lang.Override
    public final boolean isInitialized() {
      return true;
    }

    @java.lang.Override
    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      parspiceTest.SpkposRep parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (parspiceTest.SpkposRep) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }
    private int bitField0_;

    private com.google.protobuf.Internal.DoubleList time_ = emptyDoubleList();
    private void ensureTimeIsMutable() {
      if (!((bitField0_ & 0x00000001) != 0)) {
        time_ = mutableCopy(time_);
        bitField0_ |= 0x00000001;
       }
    }
    /**
     * <code>repeated double time = 1;</code>
     * @return A list containing the time.
     */
    public java.util.List<java.lang.Double>
        getTimeList() {
      return ((bitField0_ & 0x00000001) != 0) ?
               java.util.Collections.unmodifiableList(time_) : time_;
    }
    /**
     * <code>repeated double time = 1;</code>
     * @return The count of time.
     */
    public int getTimeCount() {
      return time_.size();
    }
    /**
     * <code>repeated double time = 1;</code>
     * @param index The index of the element to return.
     * @return The time at the given index.
     */
    public double getTime(int index) {
      return time_.getDouble(index);
    }
    /**
     * <code>repeated double time = 1;</code>
     * @param index The index to set the value at.
     * @param value The time to set.
     * @return This builder for chaining.
     */
    public Builder setTime(
        int index, double value) {
      ensureTimeIsMutable();
      time_.setDouble(index, value);
      onChanged();
      return this;
    }
    /**
     * <code>repeated double time = 1;</code>
     * @param value The time to add.
     * @return This builder for chaining.
     */
    public Builder addTime(double value) {
      ensureTimeIsMutable();
      time_.addDouble(value);
      onChanged();
      return this;
    }
    /**
     * <code>repeated double time = 1;</code>
     * @param values The time to add.
     * @return This builder for chaining.
     */
    public Builder addAllTime(
        java.lang.Iterable<? extends java.lang.Double> values) {
      ensureTimeIsMutable();
      com.google.protobuf.AbstractMessageLite.Builder.addAll(
          values, time_);
      onChanged();
      return this;
    }
    /**
     * <code>repeated double time = 1;</code>
     * @return This builder for chaining.
     */
    public Builder clearTime() {
      time_ = emptyDoubleList();
      bitField0_ = (bitField0_ & ~0x00000001);
      onChanged();
      return this;
    }
    @java.lang.Override
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.setUnknownFields(unknownFields);
    }

    @java.lang.Override
    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.mergeUnknownFields(unknownFields);
    }


    // @@protoc_insertion_point(builder_scope:parspiceTest.SpkposRep)
  }

  // @@protoc_insertion_point(class_scope:parspiceTest.SpkposRep)
  private static final parspiceTest.SpkposRep DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new parspiceTest.SpkposRep();
  }

  public static parspiceTest.SpkposRep getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<SpkposRep>
      PARSER = new com.google.protobuf.AbstractParser<SpkposRep>() {
    @java.lang.Override
    public SpkposRep parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new SpkposRep(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<SpkposRep> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<SpkposRep> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public parspiceTest.SpkposRep getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

