// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: spice_services_mxv.proto

package org.Main.Spice.Services.Mxv;

/**
 * <pre>
 * The response message containing the greetings
 * </pre>
 *
 * Protobuf type {@code Spice.Services.Mxv.MxvResponse}
 */
public  final class MxvResponse extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:Spice.Services.Mxv.MxvResponse)
    MxvResponseOrBuilder {
private static final long serialVersionUID = 0L;
  // Use MxvResponse.newBuilder() to construct.
  private MxvResponse(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private MxvResponse() {
  }

  @java.lang.Override
  @SuppressWarnings({"unused"})
  protected java.lang.Object newInstance(
      UnusedPrivateParameter unused) {
    return new MxvResponse();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private MxvResponse(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    this();
    if (extensionRegistry == null) {
      throw new java.lang.NullPointerException();
    }
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
          case 10: {
            org.Main.Spice.Primitives.Double.DblArray.Builder subBuilder = null;
            if (doubles_ != null) {
              subBuilder = doubles_.toBuilder();
            }
            doubles_ = input.readMessage(org.Main.Spice.Primitives.Double.DblArray.parser(), extensionRegistry);
            if (subBuilder != null) {
              subBuilder.mergeFrom(doubles_);
              doubles_ = subBuilder.buildPartial();
            }

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
      this.unknownFields = unknownFields.build();
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return org.Main.Spice.Services.Mxv.SpiceServicesMxv.internal_static_Spice_Services_Mxv_MxvResponse_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return org.Main.Spice.Services.Mxv.SpiceServicesMxv.internal_static_Spice_Services_Mxv_MxvResponse_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            org.Main.Spice.Services.Mxv.MxvResponse.class, org.Main.Spice.Services.Mxv.MxvResponse.Builder.class);
  }

  public static final int DOUBLES_FIELD_NUMBER = 1;
  private org.Main.Spice.Primitives.Double.DblArray doubles_;
  /**
   * <code>.Spice.Primitives.Double.DblArray doubles = 1;</code>
   * @return Whether the doubles field is set.
   */
  public boolean hasDoubles() {
    return doubles_ != null;
  }
  /**
   * <code>.Spice.Primitives.Double.DblArray doubles = 1;</code>
   * @return The doubles.
   */
  public org.Main.Spice.Primitives.Double.DblArray getDoubles() {
    return doubles_ == null ? org.Main.Spice.Primitives.Double.DblArray.getDefaultInstance() : doubles_;
  }
  /**
   * <code>.Spice.Primitives.Double.DblArray doubles = 1;</code>
   */
  public org.Main.Spice.Primitives.Double.DblArrayOrBuilder getDoublesOrBuilder() {
    return getDoubles();
  }

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
    if (doubles_ != null) {
      output.writeMessage(1, getDoubles());
    }
    unknownFields.writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (doubles_ != null) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(1, getDoubles());
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
    if (!(obj instanceof org.Main.Spice.Services.Mxv.MxvResponse)) {
      return super.equals(obj);
    }
    org.Main.Spice.Services.Mxv.MxvResponse other = (org.Main.Spice.Services.Mxv.MxvResponse) obj;

    if (hasDoubles() != other.hasDoubles()) return false;
    if (hasDoubles()) {
      if (!getDoubles()
          .equals(other.getDoubles())) return false;
    }
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
    if (hasDoubles()) {
      hash = (37 * hash) + DOUBLES_FIELD_NUMBER;
      hash = (53 * hash) + getDoubles().hashCode();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static org.Main.Spice.Services.Mxv.MxvResponse parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static org.Main.Spice.Services.Mxv.MxvResponse parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static org.Main.Spice.Services.Mxv.MxvResponse parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static org.Main.Spice.Services.Mxv.MxvResponse parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static org.Main.Spice.Services.Mxv.MxvResponse parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static org.Main.Spice.Services.Mxv.MxvResponse parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static org.Main.Spice.Services.Mxv.MxvResponse parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static org.Main.Spice.Services.Mxv.MxvResponse parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static org.Main.Spice.Services.Mxv.MxvResponse parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static org.Main.Spice.Services.Mxv.MxvResponse parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static org.Main.Spice.Services.Mxv.MxvResponse parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static org.Main.Spice.Services.Mxv.MxvResponse parseFrom(
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
  public static Builder newBuilder(org.Main.Spice.Services.Mxv.MxvResponse prototype) {
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
   * <pre>
   * The response message containing the greetings
   * </pre>
   *
   * Protobuf type {@code Spice.Services.Mxv.MxvResponse}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:Spice.Services.Mxv.MxvResponse)
      org.Main.Spice.Services.Mxv.MxvResponseOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return org.Main.Spice.Services.Mxv.SpiceServicesMxv.internal_static_Spice_Services_Mxv_MxvResponse_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return org.Main.Spice.Services.Mxv.SpiceServicesMxv.internal_static_Spice_Services_Mxv_MxvResponse_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              org.Main.Spice.Services.Mxv.MxvResponse.class, org.Main.Spice.Services.Mxv.MxvResponse.Builder.class);
    }

    // Construct using org.Main.Spice.Services.Mxv.MxvResponse.newBuilder()
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
      if (doublesBuilder_ == null) {
        doubles_ = null;
      } else {
        doubles_ = null;
        doublesBuilder_ = null;
      }
      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return org.Main.Spice.Services.Mxv.SpiceServicesMxv.internal_static_Spice_Services_Mxv_MxvResponse_descriptor;
    }

    @java.lang.Override
    public org.Main.Spice.Services.Mxv.MxvResponse getDefaultInstanceForType() {
      return org.Main.Spice.Services.Mxv.MxvResponse.getDefaultInstance();
    }

    @java.lang.Override
    public org.Main.Spice.Services.Mxv.MxvResponse build() {
      org.Main.Spice.Services.Mxv.MxvResponse result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public org.Main.Spice.Services.Mxv.MxvResponse buildPartial() {
      org.Main.Spice.Services.Mxv.MxvResponse result = new org.Main.Spice.Services.Mxv.MxvResponse(this);
      if (doublesBuilder_ == null) {
        result.doubles_ = doubles_;
      } else {
        result.doubles_ = doublesBuilder_.build();
      }
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
      if (other instanceof org.Main.Spice.Services.Mxv.MxvResponse) {
        return mergeFrom((org.Main.Spice.Services.Mxv.MxvResponse)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(org.Main.Spice.Services.Mxv.MxvResponse other) {
      if (other == org.Main.Spice.Services.Mxv.MxvResponse.getDefaultInstance()) return this;
      if (other.hasDoubles()) {
        mergeDoubles(other.getDoubles());
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
      org.Main.Spice.Services.Mxv.MxvResponse parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (org.Main.Spice.Services.Mxv.MxvResponse) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }

    private org.Main.Spice.Primitives.Double.DblArray doubles_;
    private com.google.protobuf.SingleFieldBuilderV3<
        org.Main.Spice.Primitives.Double.DblArray, org.Main.Spice.Primitives.Double.DblArray.Builder, org.Main.Spice.Primitives.Double.DblArrayOrBuilder> doublesBuilder_;
    /**
     * <code>.Spice.Primitives.Double.DblArray doubles = 1;</code>
     * @return Whether the doubles field is set.
     */
    public boolean hasDoubles() {
      return doublesBuilder_ != null || doubles_ != null;
    }
    /**
     * <code>.Spice.Primitives.Double.DblArray doubles = 1;</code>
     * @return The doubles.
     */
    public org.Main.Spice.Primitives.Double.DblArray getDoubles() {
      if (doublesBuilder_ == null) {
        return doubles_ == null ? org.Main.Spice.Primitives.Double.DblArray.getDefaultInstance() : doubles_;
      } else {
        return doublesBuilder_.getMessage();
      }
    }
    /**
     * <code>.Spice.Primitives.Double.DblArray doubles = 1;</code>
     */
    public Builder setDoubles(org.Main.Spice.Primitives.Double.DblArray value) {
      if (doublesBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        doubles_ = value;
        onChanged();
      } else {
        doublesBuilder_.setMessage(value);
      }

      return this;
    }
    /**
     * <code>.Spice.Primitives.Double.DblArray doubles = 1;</code>
     */
    public Builder setDoubles(
        org.Main.Spice.Primitives.Double.DblArray.Builder builderForValue) {
      if (doublesBuilder_ == null) {
        doubles_ = builderForValue.build();
        onChanged();
      } else {
        doublesBuilder_.setMessage(builderForValue.build());
      }

      return this;
    }
    /**
     * <code>.Spice.Primitives.Double.DblArray doubles = 1;</code>
     */
    public Builder mergeDoubles(org.Main.Spice.Primitives.Double.DblArray value) {
      if (doublesBuilder_ == null) {
        if (doubles_ != null) {
          doubles_ =
            org.Main.Spice.Primitives.Double.DblArray.newBuilder(doubles_).mergeFrom(value).buildPartial();
        } else {
          doubles_ = value;
        }
        onChanged();
      } else {
        doublesBuilder_.mergeFrom(value);
      }

      return this;
    }
    /**
     * <code>.Spice.Primitives.Double.DblArray doubles = 1;</code>
     */
    public Builder clearDoubles() {
      if (doublesBuilder_ == null) {
        doubles_ = null;
        onChanged();
      } else {
        doubles_ = null;
        doublesBuilder_ = null;
      }

      return this;
    }
    /**
     * <code>.Spice.Primitives.Double.DblArray doubles = 1;</code>
     */
    public org.Main.Spice.Primitives.Double.DblArray.Builder getDoublesBuilder() {
      
      onChanged();
      return getDoublesFieldBuilder().getBuilder();
    }
    /**
     * <code>.Spice.Primitives.Double.DblArray doubles = 1;</code>
     */
    public org.Main.Spice.Primitives.Double.DblArrayOrBuilder getDoublesOrBuilder() {
      if (doublesBuilder_ != null) {
        return doublesBuilder_.getMessageOrBuilder();
      } else {
        return doubles_ == null ?
            org.Main.Spice.Primitives.Double.DblArray.getDefaultInstance() : doubles_;
      }
    }
    /**
     * <code>.Spice.Primitives.Double.DblArray doubles = 1;</code>
     */
    private com.google.protobuf.SingleFieldBuilderV3<
        org.Main.Spice.Primitives.Double.DblArray, org.Main.Spice.Primitives.Double.DblArray.Builder, org.Main.Spice.Primitives.Double.DblArrayOrBuilder> 
        getDoublesFieldBuilder() {
      if (doublesBuilder_ == null) {
        doublesBuilder_ = new com.google.protobuf.SingleFieldBuilderV3<
            org.Main.Spice.Primitives.Double.DblArray, org.Main.Spice.Primitives.Double.DblArray.Builder, org.Main.Spice.Primitives.Double.DblArrayOrBuilder>(
                getDoubles(),
                getParentForChildren(),
                isClean());
        doubles_ = null;
      }
      return doublesBuilder_;
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


    // @@protoc_insertion_point(builder_scope:Spice.Services.Mxv.MxvResponse)
  }

  // @@protoc_insertion_point(class_scope:Spice.Services.Mxv.MxvResponse)
  private static final org.Main.Spice.Services.Mxv.MxvResponse DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new org.Main.Spice.Services.Mxv.MxvResponse();
  }

  public static org.Main.Spice.Services.Mxv.MxvResponse getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<MxvResponse>
      PARSER = new com.google.protobuf.AbstractParser<MxvResponse>() {
    @java.lang.Override
    public MxvResponse parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new MxvResponse(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<MxvResponse> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<MxvResponse> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public org.Main.Spice.Services.Mxv.MxvResponse getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

