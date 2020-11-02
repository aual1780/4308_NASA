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
 * <pre>
 * The greeting service definition.
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.33.0)",
    comments = "Source: grpcSpice.proto")
public final class Str2EtGrpc {

  private Str2EtGrpc() {}

  public static final String SERVICE_NAME = "parspiceTest.Str2Et";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<parspiceTest.Str2EtReq,
      parspiceTest.Str2EtRep> getParStr2EtMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ParStr2Et",
      requestType = parspiceTest.Str2EtReq.class,
      responseType = parspiceTest.Str2EtRep.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<parspiceTest.Str2EtReq,
      parspiceTest.Str2EtRep> getParStr2EtMethod() {
    io.grpc.MethodDescriptor<parspiceTest.Str2EtReq, parspiceTest.Str2EtRep> getParStr2EtMethod;
    if ((getParStr2EtMethod = Str2EtGrpc.getParStr2EtMethod) == null) {
      synchronized (Str2EtGrpc.class) {
        if ((getParStr2EtMethod = Str2EtGrpc.getParStr2EtMethod) == null) {
          Str2EtGrpc.getParStr2EtMethod = getParStr2EtMethod =
              io.grpc.MethodDescriptor.<parspiceTest.Str2EtReq, parspiceTest.Str2EtRep>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ParStr2Et"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  parspiceTest.Str2EtReq.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  parspiceTest.Str2EtRep.getDefaultInstance()))
              .setSchemaDescriptor(new Str2EtMethodDescriptorSupplier("ParStr2Et"))
              .build();
        }
      }
    }
    return getParStr2EtMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static Str2EtStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<Str2EtStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<Str2EtStub>() {
        @java.lang.Override
        public Str2EtStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new Str2EtStub(channel, callOptions);
        }
      };
    return Str2EtStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static Str2EtBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<Str2EtBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<Str2EtBlockingStub>() {
        @java.lang.Override
        public Str2EtBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new Str2EtBlockingStub(channel, callOptions);
        }
      };
    return Str2EtBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static Str2EtFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<Str2EtFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<Str2EtFutureStub>() {
        @java.lang.Override
        public Str2EtFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new Str2EtFutureStub(channel, callOptions);
        }
      };
    return Str2EtFutureStub.newStub(factory, channel);
  }

  /**
   * <pre>
   * The greeting service definition.
   * </pre>
   */
  public static abstract class Str2EtImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     * Sends a greeting
     * </pre>
     */
    public void parStr2Et(parspiceTest.Str2EtReq request,
        io.grpc.stub.StreamObserver<parspiceTest.Str2EtRep> responseObserver) {
      asyncUnimplementedUnaryCall(getParStr2EtMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getParStr2EtMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                parspiceTest.Str2EtReq,
                parspiceTest.Str2EtRep>(
                  this, METHODID_PAR_STR2ET)))
          .build();
    }
  }

  /**
   * <pre>
   * The greeting service definition.
   * </pre>
   */
  public static final class Str2EtStub extends io.grpc.stub.AbstractAsyncStub<Str2EtStub> {
    private Str2EtStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected Str2EtStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new Str2EtStub(channel, callOptions);
    }

    /**
     * <pre>
     * Sends a greeting
     * </pre>
     */
    public void parStr2Et(parspiceTest.Str2EtReq request,
        io.grpc.stub.StreamObserver<parspiceTest.Str2EtRep> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getParStr2EtMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * <pre>
   * The greeting service definition.
   * </pre>
   */
  public static final class Str2EtBlockingStub extends io.grpc.stub.AbstractBlockingStub<Str2EtBlockingStub> {
    private Str2EtBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected Str2EtBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new Str2EtBlockingStub(channel, callOptions);
    }

    /**
     * <pre>
     * Sends a greeting
     * </pre>
     */
    public parspiceTest.Str2EtRep parStr2Et(parspiceTest.Str2EtReq request) {
      return blockingUnaryCall(
          getChannel(), getParStr2EtMethod(), getCallOptions(), request);
    }
  }

  /**
   * <pre>
   * The greeting service definition.
   * </pre>
   */
  public static final class Str2EtFutureStub extends io.grpc.stub.AbstractFutureStub<Str2EtFutureStub> {
    private Str2EtFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected Str2EtFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new Str2EtFutureStub(channel, callOptions);
    }

    /**
     * <pre>
     * Sends a greeting
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<parspiceTest.Str2EtRep> parStr2Et(
        parspiceTest.Str2EtReq request) {
      return futureUnaryCall(
          getChannel().newCall(getParStr2EtMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_PAR_STR2ET = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final Str2EtImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(Str2EtImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_PAR_STR2ET:
          serviceImpl.parStr2Et((parspiceTest.Str2EtReq) request,
              (io.grpc.stub.StreamObserver<parspiceTest.Str2EtRep>) responseObserver);
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

  private static abstract class Str2EtBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    Str2EtBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return parspiceTest.parspiceTesting.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("Str2Et");
    }
  }

  private static final class Str2EtFileDescriptorSupplier
      extends Str2EtBaseDescriptorSupplier {
    Str2EtFileDescriptorSupplier() {}
  }

  private static final class Str2EtMethodDescriptorSupplier
      extends Str2EtBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    Str2EtMethodDescriptorSupplier(String methodName) {
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
      synchronized (Str2EtGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new Str2EtFileDescriptorSupplier())
              .addMethod(getParStr2EtMethod())
              .build();
        }
      }
    }
    return result;
  }
}
