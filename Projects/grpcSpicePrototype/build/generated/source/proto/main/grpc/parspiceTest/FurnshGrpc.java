package parspiceTest;

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
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.33.0)",
    comments = "Source: parspice.proto")
public final class FurnshGrpc {

  private FurnshGrpc() {}

  public static final String SERVICE_NAME = "parspiceTest.Furnsh";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<parspiceTest.FurnshReq,
      com.google.protobuf.Empty> getParFurnshMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ParFurnsh",
      requestType = parspiceTest.FurnshReq.class,
      responseType = com.google.protobuf.Empty.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<parspiceTest.FurnshReq,
      com.google.protobuf.Empty> getParFurnshMethod() {
    io.grpc.MethodDescriptor<parspiceTest.FurnshReq, com.google.protobuf.Empty> getParFurnshMethod;
    if ((getParFurnshMethod = FurnshGrpc.getParFurnshMethod) == null) {
      synchronized (FurnshGrpc.class) {
        if ((getParFurnshMethod = FurnshGrpc.getParFurnshMethod) == null) {
          FurnshGrpc.getParFurnshMethod = getParFurnshMethod =
              io.grpc.MethodDescriptor.<parspiceTest.FurnshReq, com.google.protobuf.Empty>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ParFurnsh"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  parspiceTest.FurnshReq.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.google.protobuf.Empty.getDefaultInstance()))
              .setSchemaDescriptor(new FurnshMethodDescriptorSupplier("ParFurnsh"))
              .build();
        }
      }
    }
    return getParFurnshMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static FurnshStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<FurnshStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<FurnshStub>() {
        @java.lang.Override
        public FurnshStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new FurnshStub(channel, callOptions);
        }
      };
    return FurnshStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static FurnshBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<FurnshBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<FurnshBlockingStub>() {
        @java.lang.Override
        public FurnshBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new FurnshBlockingStub(channel, callOptions);
        }
      };
    return FurnshBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static FurnshFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<FurnshFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<FurnshFutureStub>() {
        @java.lang.Override
        public FurnshFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new FurnshFutureStub(channel, callOptions);
        }
      };
    return FurnshFutureStub.newStub(factory, channel);
  }

  /**
   */
  public static abstract class FurnshImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     * Sends furnsh request
     * </pre>
     */
    public void parFurnsh(parspiceTest.FurnshReq request,
        io.grpc.stub.StreamObserver<com.google.protobuf.Empty> responseObserver) {
      asyncUnimplementedUnaryCall(getParFurnshMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getParFurnshMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                parspiceTest.FurnshReq,
                com.google.protobuf.Empty>(
                  this, METHODID_PAR_FURNSH)))
          .build();
    }
  }

  /**
   */
  public static final class FurnshStub extends io.grpc.stub.AbstractAsyncStub<FurnshStub> {
    private FurnshStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected FurnshStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new FurnshStub(channel, callOptions);
    }

    /**
     * <pre>
     * Sends furnsh request
     * </pre>
     */
    public void parFurnsh(parspiceTest.FurnshReq request,
        io.grpc.stub.StreamObserver<com.google.protobuf.Empty> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getParFurnshMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class FurnshBlockingStub extends io.grpc.stub.AbstractBlockingStub<FurnshBlockingStub> {
    private FurnshBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected FurnshBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new FurnshBlockingStub(channel, callOptions);
    }

    /**
     * <pre>
     * Sends furnsh request
     * </pre>
     */
    public com.google.protobuf.Empty parFurnsh(parspiceTest.FurnshReq request) {
      return blockingUnaryCall(
          getChannel(), getParFurnshMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class FurnshFutureStub extends io.grpc.stub.AbstractFutureStub<FurnshFutureStub> {
    private FurnshFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected FurnshFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new FurnshFutureStub(channel, callOptions);
    }

    /**
     * <pre>
     * Sends furnsh request
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.google.protobuf.Empty> parFurnsh(
        parspiceTest.FurnshReq request) {
      return futureUnaryCall(
          getChannel().newCall(getParFurnshMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_PAR_FURNSH = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final FurnshImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(FurnshImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_PAR_FURNSH:
          serviceImpl.parFurnsh((parspiceTest.FurnshReq) request,
              (io.grpc.stub.StreamObserver<com.google.protobuf.Empty>) responseObserver);
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

  private static abstract class FurnshBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    FurnshBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return parspiceTest.Parspice.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("Furnsh");
    }
  }

  private static final class FurnshFileDescriptorSupplier
      extends FurnshBaseDescriptorSupplier {
    FurnshFileDescriptorSupplier() {}
  }

  private static final class FurnshMethodDescriptorSupplier
      extends FurnshBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    FurnshMethodDescriptorSupplier(String methodName) {
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
      synchronized (FurnshGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new FurnshFileDescriptorSupplier())
              .addMethod(getParFurnshMethod())
              .build();
        }
      }
    }
    return result;
  }
}
