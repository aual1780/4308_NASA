// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: parspice.proto

package parspiceTest;

/**
 * Protobuf type {@code parspiceTest.FurnshRep}
 */
public final class FurnshRep extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:parspiceTest.FurnshRep)
    FurnshRepOrBuilder {
private static final long serialVersionUID = 0L;
  // Use FurnshRep.newBuilder() to construct.
  private FurnshRep(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private FurnshRep() {
    file_ = emptyDoubleList();
  }

  @java.lang.Override
  @SuppressWarnings({"unused"})
  protected java.lang.Object newInstance(
      UnusedPrivateParameter unused) {
    return new FurnshRep();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private FurnshRep(
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
              file_ = newDoubleList();
              mutable_bitField0_ |= 0x00000001;
            }
            file_.addDouble(input.readDouble());
            break;
          }
          case 10: {
            int length = input.readRawVarint32();
            int limit = input.pushLimit(length);
            if (!((mutable_bitField0_ & 0x00000001) != 0) && input.getBytesUntilLimit() > 0) {
              file_ = newDoubleList();
              mutable_bitField0_ |= 0x00000001;
            }
            while (input.getBytesUntilLimit() > 0) {
              file_.addDouble(input.readDouble());
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
        file_.makeImmutable(); // C
      }
      this.unknownFields = unknownFields.build();
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return parspiceTest.Parspice.internal_static_parspiceTest_FurnshRep_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return parspiceTest.Parspice.internal_static_parspiceTest_FurnshRep_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            parspiceTest.FurnshRep.class, parspiceTest.FurnshRep.Builder.class);
  }

  public static final int FILE_FIELD_NUMBER = 1;
  private com.google.protobuf.Internal.DoubleList file_;
  /**
   * <code>repeated double file = 1;</code>
   * @return A list containing the file.
   */
  @java.lang.Override
  public java.util.List<java.lang.Double>
      getFileList() {
    return file_;
  }
  /**
   * <code>repeated double file = 1;</code>
   * @return The count of file.
   */
  public int getFileCount() {
    return file_.size();
  }
  /**
   * <code>repeated double file = 1;</code>
   * @param index The index of the element to return.
   * @return The file at the given index.
   */
  public double getFile(int index) {
    return file_.getDouble(index);
  }
  private int fileMemoizedSerializedSize = -1;

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
    if (getFileList().size() > 0) {
      output.writeUInt32NoTag(10);
      output.writeUInt32NoTag(fileMemoizedSerializedSize);
    }
    for (int i = 0; i < file_.size(); i++) {
      output.writeDoubleNoTag(file_.getDouble(i));
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
      dataSize = 8 * getFileList().size();
      size += dataSize;
      if (!getFileList().isEmpty()) {
        size += 1;
        size += com.google.protobuf.CodedOutputStream
            .computeInt32SizeNoTag(dataSize);
      }
      fileMemoizedSerializedSize = dataSize;
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
    if (!(obj instanceof parspiceTest.FurnshRep)) {
      return super.equals(obj);
    }
    parspiceTest.FurnshRep other = (parspiceTest.FurnshRep) obj;

    if (!getFileList()
        .equals(other.getFileList())) return false;
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
    if (getFileCount() > 0) {
      hash = (37 * hash) + FILE_FIELD_NUMBER;
      hash = (53 * hash) + getFileList().hashCode();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static parspiceTest.FurnshRep parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static parspiceTest.FurnshRep parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static parspiceTest.FurnshRep parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static parspiceTest.FurnshRep parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static parspiceTest.FurnshRep parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static parspiceTest.FurnshRep parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static parspiceTest.FurnshRep parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static parspiceTest.FurnshRep parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static parspiceTest.FurnshRep parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static parspiceTest.FurnshRep parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static parspiceTest.FurnshRep parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static parspiceTest.FurnshRep parseFrom(
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
  public static Builder newBuilder(parspiceTest.FurnshRep prototype) {
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
   * Protobuf type {@code parspiceTest.FurnshRep}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:parspiceTest.FurnshRep)
      parspiceTest.FurnshRepOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return parspiceTest.Parspice.internal_static_parspiceTest_FurnshRep_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return parspiceTest.Parspice.internal_static_parspiceTest_FurnshRep_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              parspiceTest.FurnshRep.class, parspiceTest.FurnshRep.Builder.class);
    }

    // Construct using parspiceTest.FurnshRep.newBuilder()
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
      file_ = emptyDoubleList();
      bitField0_ = (bitField0_ & ~0x00000001);
      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return parspiceTest.Parspice.internal_static_parspiceTest_FurnshRep_descriptor;
    }

    @java.lang.Override
    public parspiceTest.FurnshRep getDefaultInstanceForType() {
      return parspiceTest.FurnshRep.getDefaultInstance();
    }

    @java.lang.Override
    public parspiceTest.FurnshRep build() {
      parspiceTest.FurnshRep result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public parspiceTest.FurnshRep buildPartial() {
      parspiceTest.FurnshRep result = new parspiceTest.FurnshRep(this);
      int from_bitField0_ = bitField0_;
      if (((bitField0_ & 0x00000001) != 0)) {
        file_.makeImmutable();
        bitField0_ = (bitField0_ & ~0x00000001);
      }
      result.file_ = file_;
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
      if (other instanceof parspiceTest.FurnshRep) {
        return mergeFrom((parspiceTest.FurnshRep)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(parspiceTest.FurnshRep other) {
      if (other == parspiceTest.FurnshRep.getDefaultInstance()) return this;
      if (!other.file_.isEmpty()) {
        if (file_.isEmpty()) {
          file_ = other.file_;
          bitField0_ = (bitField0_ & ~0x00000001);
        } else {
          ensureFileIsMutable();
          file_.addAll(other.file_);
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
      parspiceTest.FurnshRep parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (parspiceTest.FurnshRep) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }
    private int bitField0_;

    private com.google.protobuf.Internal.DoubleList file_ = emptyDoubleList();
    private void ensureFileIsMutable() {
      if (!((bitField0_ & 0x00000001) != 0)) {
        file_ = mutableCopy(file_);
        bitField0_ |= 0x00000001;
       }
    }
    /**
     * <code>repeated double file = 1;</code>
     * @return A list containing the file.
     */
    public java.util.List<java.lang.Double>
        getFileList() {
      return ((bitField0_ & 0x00000001) != 0) ?
               java.util.Collections.unmodifiableList(file_) : file_;
    }
    /**
     * <code>repeated double file = 1;</code>
     * @return The count of file.
     */
    public int getFileCount() {
      return file_.size();
    }
    /**
     * <code>repeated double file = 1;</code>
     * @param index The index of the element to return.
     * @return The file at the given index.
     */
    public double getFile(int index) {
      return file_.getDouble(index);
    }
    /**
     * <code>repeated double file = 1;</code>
     * @param index The index to set the value at.
     * @param value The file to set.
     * @return This builder for chaining.
     */
    public Builder setFile(
        int index, double value) {
      ensureFileIsMutable();
      file_.setDouble(index, value);
      onChanged();
      return this;
    }
    /**
     * <code>repeated double file = 1;</code>
     * @param value The file to add.
     * @return This builder for chaining.
     */
    public Builder addFile(double value) {
      ensureFileIsMutable();
      file_.addDouble(value);
      onChanged();
      return this;
    }
    /**
     * <code>repeated double file = 1;</code>
     * @param values The file to add.
     * @return This builder for chaining.
     */
    public Builder addAllFile(
        java.lang.Iterable<? extends java.lang.Double> values) {
      ensureFileIsMutable();
      com.google.protobuf.AbstractMessageLite.Builder.addAll(
          values, file_);
      onChanged();
      return this;
    }
    /**
     * <code>repeated double file = 1;</code>
     * @return This builder for chaining.
     */
    public Builder clearFile() {
      file_ = emptyDoubleList();
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


    // @@protoc_insertion_point(builder_scope:parspiceTest.FurnshRep)
  }

  // @@protoc_insertion_point(class_scope:parspiceTest.FurnshRep)
  private static final parspiceTest.FurnshRep DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new parspiceTest.FurnshRep();
  }

  public static parspiceTest.FurnshRep getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<FurnshRep>
      PARSER = new com.google.protobuf.AbstractParser<FurnshRep>() {
    @java.lang.Override
    public FurnshRep parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new FurnshRep(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<FurnshRep> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<FurnshRep> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public parspiceTest.FurnshRep getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

