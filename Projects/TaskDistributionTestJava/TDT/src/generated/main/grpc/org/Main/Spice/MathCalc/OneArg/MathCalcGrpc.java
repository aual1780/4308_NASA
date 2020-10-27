package org.Main.Spice.MathCalc.OneArg;

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
    comments = "Source: mathcalc_onearg.proto")
public final class MathCalcGrpc {

  private MathCalcGrpc() {}

  public static final String SERVICE_NAME = "MathCalc";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<org.Main.Spice.MathCalc.OneArg.MathCalcRequest,
      org.Main.Spice.MathCalc.OneArg.MathCalcReply> getDoMathMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "DoMath",
      requestType = org.Main.Spice.MathCalc.OneArg.MathCalcRequest.class,
      responseType = org.Main.Spice.MathCalc.OneArg.MathCalcReply.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<org.Main.Spice.MathCalc.OneArg.MathCalcRequest,
      org.Main.Spice.MathCalc.OneArg.MathCalcReply> getDoMathMethod() {
    io.grpc.MethodDescriptor<org.Main.Spice.MathCalc.OneArg.MathCalcRequest, org.Main.Spice.MathCalc.OneArg.MathCalcReply> getDoMathMethod;
    if ((getDoMathMethod = MathCalcGrpc.getDoMathMethod) == null) {
      synchronized (MathCalcGrpc.class) {
        if ((getDoMathMethod = MathCalcGrpc.getDoMathMethod) == null) {
          MathCalcGrpc.getDoMathMethod = getDoMathMethod =
              io.grpc.MethodDescriptor.<org.Main.Spice.MathCalc.OneArg.MathCalcRequest, org.Main.Spice.MathCalc.OneArg.MathCalcReply>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "DoMath"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.Main.Spice.MathCalc.OneArg.MathCalcRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.Main.Spice.MathCalc.OneArg.MathCalcReply.getDefaultInstance()))
              .setSchemaDescriptor(new MathCalcMethodDescriptorSupplier("DoMath"))
              .build();
        }
      }
    }
    return getDoMathMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static MathCalcStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<MathCalcStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<MathCalcStub>() {
        @java.lang.Override
        public MathCalcStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new MathCalcStub(channel, callOptions);
        }
      };
    return MathCalcStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static MathCalcBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<MathCalcBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<MathCalcBlockingStub>() {
        @java.lang.Override
        public MathCalcBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new MathCalcBlockingStub(channel, callOptions);
        }
      };
    return MathCalcBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static MathCalcFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<MathCalcFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<MathCalcFutureStub>() {
        @java.lang.Override
        public MathCalcFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new MathCalcFutureStub(channel, callOptions);
        }
      };
    return MathCalcFutureStub.newStub(factory, channel);
  }

  /**
   * <pre>
   * The greeting service definition.
   * </pre>
   */
  public static abstract class MathCalcImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     * Sends a greeting
     * </pre>
     */
    public void doMath(org.Main.Spice.MathCalc.OneArg.MathCalcRequest request,
        io.grpc.stub.StreamObserver<org.Main.Spice.MathCalc.OneArg.MathCalcReply> responseObserver) {
      asyncUnimplementedUnaryCall(getDoMathMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getDoMathMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                org.Main.Spice.MathCalc.OneArg.MathCalcRequest,
                org.Main.Spice.MathCalc.OneArg.MathCalcReply>(
                  this, METHODID_DO_MATH)))
          .build();
    }
  }

  /**
   * <pre>
   * The greeting service definition.
   * </pre>
   */
  public static final class MathCalcStub extends io.grpc.stub.AbstractAsyncStub<MathCalcStub> {
    private MathCalcStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected MathCalcStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new MathCalcStub(channel, callOptions);
    }

    /**
     * <pre>
     * Sends a greeting
     * </pre>
     */
    public void doMath(org.Main.Spice.MathCalc.OneArg.MathCalcRequest request,
        io.grpc.stub.StreamObserver<org.Main.Spice.MathCalc.OneArg.MathCalcReply> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getDoMathMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * <pre>
   * The greeting service definition.
   * </pre>
   */
  public static final class MathCalcBlockingStub extends io.grpc.stub.AbstractBlockingStub<MathCalcBlockingStub> {
    private MathCalcBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected MathCalcBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new MathCalcBlockingStub(channel, callOptions);
    }

    /**
     * <pre>
     * Sends a greeting
     * </pre>
     */
    public org.Main.Spice.MathCalc.OneArg.MathCalcReply doMath(org.Main.Spice.MathCalc.OneArg.MathCalcRequest request) {
      return blockingUnaryCall(
          getChannel(), getDoMathMethod(), getCallOptions(), request);
    }
  }

  /**
   * <pre>
   * The greeting service definition.
   * </pre>
   */
  public static final class MathCalcFutureStub extends io.grpc.stub.AbstractFutureStub<MathCalcFutureStub> {
    private MathCalcFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected MathCalcFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new MathCalcFutureStub(channel, callOptions);
    }

    /**
     * <pre>
     * Sends a greeting
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<org.Main.Spice.MathCalc.OneArg.MathCalcReply> doMath(
        org.Main.Spice.MathCalc.OneArg.MathCalcRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getDoMathMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_DO_MATH = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final MathCalcImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(MathCalcImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_DO_MATH:
          serviceImpl.doMath((org.Main.Spice.MathCalc.OneArg.MathCalcRequest) request,
              (io.grpc.stub.StreamObserver<org.Main.Spice.MathCalc.OneArg.MathCalcReply>) responseObserver);
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

  private static abstract class MathCalcBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    MathCalcBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return org.Main.Spice.MathCalc.OneArg.MathcalcOnearg.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("MathCalc");
    }
  }

  private static final class MathCalcFileDescriptorSupplier
      extends MathCalcBaseDescriptorSupplier {
    MathCalcFileDescriptorSupplier() {}
  }

  private static final class MathCalcMethodDescriptorSupplier
      extends MathCalcBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    MathCalcMethodDescriptorSupplier(String methodName) {
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
      synchronized (MathCalcGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new MathCalcFileDescriptorSupplier())
              .addMethod(getDoMathMethod())
              .build();
        }
      }
    }
    return result;
  }
}
