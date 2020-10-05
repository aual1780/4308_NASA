package org.Main.Spice.Services.Mxv;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 * <pre>
 * The greeting service definition.
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.32.1)",
    comments = "Source: spice_services_mxv.proto")
public final class MxvServiceGrpc {

  private MxvServiceGrpc() {}

  public static final String SERVICE_NAME = "Spice.Services.Mxv.MxvService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<org.Main.Spice.Services.Mxv.MxvRequest,
      org.Main.Spice.Services.Mxv.MxvResponse> getCalculateMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Calculate",
      requestType = org.Main.Spice.Services.Mxv.MxvRequest.class,
      responseType = org.Main.Spice.Services.Mxv.MxvResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<org.Main.Spice.Services.Mxv.MxvRequest,
      org.Main.Spice.Services.Mxv.MxvResponse> getCalculateMethod() {
    io.grpc.MethodDescriptor<org.Main.Spice.Services.Mxv.MxvRequest, org.Main.Spice.Services.Mxv.MxvResponse> getCalculateMethod;
    if ((getCalculateMethod = MxvServiceGrpc.getCalculateMethod) == null) {
      synchronized (MxvServiceGrpc.class) {
        if ((getCalculateMethod = MxvServiceGrpc.getCalculateMethod) == null) {
          MxvServiceGrpc.getCalculateMethod = getCalculateMethod =
              io.grpc.MethodDescriptor.<org.Main.Spice.Services.Mxv.MxvRequest, org.Main.Spice.Services.Mxv.MxvResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Calculate"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.Main.Spice.Services.Mxv.MxvRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.Main.Spice.Services.Mxv.MxvResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MxvServiceMethodDescriptorSupplier("Calculate"))
              .build();
        }
      }
    }
    return getCalculateMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static MxvServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<MxvServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<MxvServiceStub>() {
        @java.lang.Override
        public MxvServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new MxvServiceStub(channel, callOptions);
        }
      };
    return MxvServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static MxvServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<MxvServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<MxvServiceBlockingStub>() {
        @java.lang.Override
        public MxvServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new MxvServiceBlockingStub(channel, callOptions);
        }
      };
    return MxvServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static MxvServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<MxvServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<MxvServiceFutureStub>() {
        @java.lang.Override
        public MxvServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new MxvServiceFutureStub(channel, callOptions);
        }
      };
    return MxvServiceFutureStub.newStub(factory, channel);
  }

  /**
   * <pre>
   * The greeting service definition.
   * </pre>
   */
  public static abstract class MxvServiceImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     * Sends a greeting
     * </pre>
     */
    public void calculate(org.Main.Spice.Services.Mxv.MxvRequest request,
        io.grpc.stub.StreamObserver<org.Main.Spice.Services.Mxv.MxvResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getCalculateMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getCalculateMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                org.Main.Spice.Services.Mxv.MxvRequest,
                org.Main.Spice.Services.Mxv.MxvResponse>(
                  this, METHODID_CALCULATE)))
          .build();
    }
  }

  /**
   * <pre>
   * The greeting service definition.
   * </pre>
   */
  public static final class MxvServiceStub extends io.grpc.stub.AbstractAsyncStub<MxvServiceStub> {
    private MxvServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected MxvServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new MxvServiceStub(channel, callOptions);
    }

    /**
     * <pre>
     * Sends a greeting
     * </pre>
     */
    public void calculate(org.Main.Spice.Services.Mxv.MxvRequest request,
        io.grpc.stub.StreamObserver<org.Main.Spice.Services.Mxv.MxvResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getCalculateMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * <pre>
   * The greeting service definition.
   * </pre>
   */
  public static final class MxvServiceBlockingStub extends io.grpc.stub.AbstractBlockingStub<MxvServiceBlockingStub> {
    private MxvServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected MxvServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new MxvServiceBlockingStub(channel, callOptions);
    }

    /**
     * <pre>
     * Sends a greeting
     * </pre>
     */
    public org.Main.Spice.Services.Mxv.MxvResponse calculate(org.Main.Spice.Services.Mxv.MxvRequest request) {
      return blockingUnaryCall(
          getChannel(), getCalculateMethod(), getCallOptions(), request);
    }
  }

  /**
   * <pre>
   * The greeting service definition.
   * </pre>
   */
  public static final class MxvServiceFutureStub extends io.grpc.stub.AbstractFutureStub<MxvServiceFutureStub> {
    private MxvServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected MxvServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new MxvServiceFutureStub(channel, callOptions);
    }

    /**
     * <pre>
     * Sends a greeting
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<org.Main.Spice.Services.Mxv.MxvResponse> calculate(
        org.Main.Spice.Services.Mxv.MxvRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getCalculateMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_CALCULATE = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final MxvServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(MxvServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_CALCULATE:
          serviceImpl.calculate((org.Main.Spice.Services.Mxv.MxvRequest) request,
              (io.grpc.stub.StreamObserver<org.Main.Spice.Services.Mxv.MxvResponse>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class MxvServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    MxvServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return org.Main.Spice.Services.Mxv.SpiceServicesMxv.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("MxvService");
    }
  }

  private static final class MxvServiceFileDescriptorSupplier
      extends MxvServiceBaseDescriptorSupplier {
    MxvServiceFileDescriptorSupplier() {}
  }

  private static final class MxvServiceMethodDescriptorSupplier
      extends MxvServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    MxvServiceMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (MxvServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new MxvServiceFileDescriptorSupplier())
              .addMethod(getCalculateMethod())
              .build();
        }
      }
    }
    return result;
  }
}
