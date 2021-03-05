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
    value = "by gRPC proto compiler (version 1.32.1)",
    comments = "Source: parspice.proto")
public final class ParSpiceGrpc {

  private ParSpiceGrpc() {}

  public static final String SERVICE_NAME = "parspiceTest.ParSpice";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<parspiceTest.FurnshBundle,
      parspiceTest.FurnshRep> getParFurnshMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ParFurnsh",
      requestType = parspiceTest.FurnshBundle.class,
      responseType = parspiceTest.FurnshRep.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<parspiceTest.FurnshBundle,
      parspiceTest.FurnshRep> getParFurnshMethod() {
    io.grpc.MethodDescriptor<parspiceTest.FurnshBundle, parspiceTest.FurnshRep> getParFurnshMethod;
    if ((getParFurnshMethod = ParSpiceGrpc.getParFurnshMethod) == null) {
      synchronized (ParSpiceGrpc.class) {
        if ((getParFurnshMethod = ParSpiceGrpc.getParFurnshMethod) == null) {
          ParSpiceGrpc.getParFurnshMethod = getParFurnshMethod =
              io.grpc.MethodDescriptor.<parspiceTest.FurnshBundle, parspiceTest.FurnshRep>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ParFurnsh"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  parspiceTest.FurnshBundle.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  parspiceTest.FurnshRep.getDefaultInstance()))
              .setSchemaDescriptor(new ParSpiceMethodDescriptorSupplier("ParFurnsh"))
              .build();
        }
      }
    }
    return getParFurnshMethod;
  }

  private static volatile io.grpc.MethodDescriptor<parspiceTest.Str2EtBundle,
      parspiceTest.Str2EtRep> getParStr2EtMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ParStr2Et",
      requestType = parspiceTest.Str2EtBundle.class,
      responseType = parspiceTest.Str2EtRep.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<parspiceTest.Str2EtBundle,
      parspiceTest.Str2EtRep> getParStr2EtMethod() {
    io.grpc.MethodDescriptor<parspiceTest.Str2EtBundle, parspiceTest.Str2EtRep> getParStr2EtMethod;
    if ((getParStr2EtMethod = ParSpiceGrpc.getParStr2EtMethod) == null) {
      synchronized (ParSpiceGrpc.class) {
        if ((getParStr2EtMethod = ParSpiceGrpc.getParStr2EtMethod) == null) {
          ParSpiceGrpc.getParStr2EtMethod = getParStr2EtMethod =
              io.grpc.MethodDescriptor.<parspiceTest.Str2EtBundle, parspiceTest.Str2EtRep>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ParStr2Et"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  parspiceTest.Str2EtBundle.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  parspiceTest.Str2EtRep.getDefaultInstance()))
              .setSchemaDescriptor(new ParSpiceMethodDescriptorSupplier("ParStr2Et"))
              .build();
        }
      }
    }
    return getParStr2EtMethod;
  }

  private static volatile io.grpc.MethodDescriptor<parspiceTest.SpkposBundle,
      parspiceTest.SpkposRep> getParSpkposMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ParSpkpos",
      requestType = parspiceTest.SpkposBundle.class,
      responseType = parspiceTest.SpkposRep.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<parspiceTest.SpkposBundle,
      parspiceTest.SpkposRep> getParSpkposMethod() {
    io.grpc.MethodDescriptor<parspiceTest.SpkposBundle, parspiceTest.SpkposRep> getParSpkposMethod;
    if ((getParSpkposMethod = ParSpiceGrpc.getParSpkposMethod) == null) {
      synchronized (ParSpiceGrpc.class) {
        if ((getParSpkposMethod = ParSpiceGrpc.getParSpkposMethod) == null) {
          ParSpiceGrpc.getParSpkposMethod = getParSpkposMethod =
              io.grpc.MethodDescriptor.<parspiceTest.SpkposBundle, parspiceTest.SpkposRep>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ParSpkpos"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  parspiceTest.SpkposBundle.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  parspiceTest.SpkposRep.getDefaultInstance()))
              .setSchemaDescriptor(new ParSpiceMethodDescriptorSupplier("ParSpkpos"))
              .build();
        }
      }
    }
    return getParSpkposMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static ParSpiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ParSpiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ParSpiceStub>() {
        @java.lang.Override
        public ParSpiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ParSpiceStub(channel, callOptions);
        }
      };
    return ParSpiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static ParSpiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ParSpiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ParSpiceBlockingStub>() {
        @java.lang.Override
        public ParSpiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ParSpiceBlockingStub(channel, callOptions);
        }
      };
    return ParSpiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static ParSpiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ParSpiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ParSpiceFutureStub>() {
        @java.lang.Override
        public ParSpiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ParSpiceFutureStub(channel, callOptions);
        }
      };
    return ParSpiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public static abstract class ParSpiceImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     * RPC functions for each spice call
     * </pre>
     */
    public void parFurnsh(parspiceTest.FurnshBundle request,
        io.grpc.stub.StreamObserver<parspiceTest.FurnshRep> responseObserver) {
      asyncUnimplementedUnaryCall(getParFurnshMethod(), responseObserver);
    }

    /**
     */
    public void parStr2Et(parspiceTest.Str2EtBundle request,
        io.grpc.stub.StreamObserver<parspiceTest.Str2EtRep> responseObserver) {
      asyncUnimplementedUnaryCall(getParStr2EtMethod(), responseObserver);
    }

    /**
     */
    public void parSpkpos(parspiceTest.SpkposBundle request,
        io.grpc.stub.StreamObserver<parspiceTest.SpkposRep> responseObserver) {
      asyncUnimplementedUnaryCall(getParSpkposMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getParFurnshMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                parspiceTest.FurnshBundle,
                parspiceTest.FurnshRep>(
                  this, METHODID_PAR_FURNSH)))
          .addMethod(
            getParStr2EtMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                parspiceTest.Str2EtBundle,
                parspiceTest.Str2EtRep>(
                  this, METHODID_PAR_STR2ET)))
          .addMethod(
            getParSpkposMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                parspiceTest.SpkposBundle,
                parspiceTest.SpkposRep>(
                  this, METHODID_PAR_SPKPOS)))
          .build();
    }
  }

  /**
   */
  public static final class ParSpiceStub extends io.grpc.stub.AbstractAsyncStub<ParSpiceStub> {
    private ParSpiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ParSpiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ParSpiceStub(channel, callOptions);
    }

    /**
     * <pre>
     * RPC functions for each spice call
     * </pre>
     */
    public void parFurnsh(parspiceTest.FurnshBundle request,
        io.grpc.stub.StreamObserver<parspiceTest.FurnshRep> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getParFurnshMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void parStr2Et(parspiceTest.Str2EtBundle request,
        io.grpc.stub.StreamObserver<parspiceTest.Str2EtRep> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getParStr2EtMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void parSpkpos(parspiceTest.SpkposBundle request,
        io.grpc.stub.StreamObserver<parspiceTest.SpkposRep> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getParSpkposMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class ParSpiceBlockingStub extends io.grpc.stub.AbstractBlockingStub<ParSpiceBlockingStub> {
    private ParSpiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ParSpiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ParSpiceBlockingStub(channel, callOptions);
    }

    /**
     * <pre>
     * RPC functions for each spice call
     * </pre>
     */
    public parspiceTest.FurnshRep parFurnsh(parspiceTest.FurnshBundle request) {
      return blockingUnaryCall(
          getChannel(), getParFurnshMethod(), getCallOptions(), request);
    }

    /**
     */
    public parspiceTest.Str2EtRep parStr2Et(parspiceTest.Str2EtBundle request) {
      return blockingUnaryCall(
          getChannel(), getParStr2EtMethod(), getCallOptions(), request);
    }

    /**
     */
    public parspiceTest.SpkposRep parSpkpos(parspiceTest.SpkposBundle request) {
      return blockingUnaryCall(
          getChannel(), getParSpkposMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class ParSpiceFutureStub extends io.grpc.stub.AbstractFutureStub<ParSpiceFutureStub> {
    private ParSpiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ParSpiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ParSpiceFutureStub(channel, callOptions);
    }

    /**
     * <pre>
     * RPC functions for each spice call
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<parspiceTest.FurnshRep> parFurnsh(
        parspiceTest.FurnshBundle request) {
      return futureUnaryCall(
          getChannel().newCall(getParFurnshMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<parspiceTest.Str2EtRep> parStr2Et(
        parspiceTest.Str2EtBundle request) {
      return futureUnaryCall(
          getChannel().newCall(getParStr2EtMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<parspiceTest.SpkposRep> parSpkpos(
        parspiceTest.SpkposBundle request) {
      return futureUnaryCall(
          getChannel().newCall(getParSpkposMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_PAR_FURNSH = 0;
  private static final int METHODID_PAR_STR2ET = 1;
  private static final int METHODID_PAR_SPKPOS = 2;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final ParSpiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(ParSpiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_PAR_FURNSH:
          serviceImpl.parFurnsh((parspiceTest.FurnshBundle) request,
              (io.grpc.stub.StreamObserver<parspiceTest.FurnshRep>) responseObserver);
          break;
        case METHODID_PAR_STR2ET:
          serviceImpl.parStr2Et((parspiceTest.Str2EtBundle) request,
              (io.grpc.stub.StreamObserver<parspiceTest.Str2EtRep>) responseObserver);
          break;
        case METHODID_PAR_SPKPOS:
          serviceImpl.parSpkpos((parspiceTest.SpkposBundle) request,
              (io.grpc.stub.StreamObserver<parspiceTest.SpkposRep>) responseObserver);
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

  private static abstract class ParSpiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    ParSpiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return parspiceTest.Parspice.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("ParSpice");
    }
  }

  private static final class ParSpiceFileDescriptorSupplier
      extends ParSpiceBaseDescriptorSupplier {
    ParSpiceFileDescriptorSupplier() {}
  }

  private static final class ParSpiceMethodDescriptorSupplier
      extends ParSpiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    ParSpiceMethodDescriptorSupplier(String methodName) {
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
      synchronized (ParSpiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new ParSpiceFileDescriptorSupplier())
              .addMethod(getParFurnshMethod())
              .addMethod(getParStr2EtMethod())
              .addMethod(getParSpkposMethod())
              .build();
        }
      }
    }
    return result;
  }
}
