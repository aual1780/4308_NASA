package org.Main.Worker;

import io.grpc.Channel;
import io.grpc.ManagedChannelBuilder;

import org.Main.AwaitableStreamObserver;
import org.Main.Commands.ParObject;
import parspiceTest.FurnshBundle;
import parspiceTest.ParSpiceGrpc;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.function.Function;
import java.util.logging.Logger;


public class WorkerPool  {

    private final WorkerPoolState _poolState;
    private static final int _batchSize = 200; //TODO: Get better batch size
    private static final Logger logger = Logger.getLogger(WorkerPool.class.getName());

    public WorkerPool(String ServerPath, int StartPort, int WorkerCount, int MaxBatchSize) throws IOException {
        int currentPort = StartPort;

        _poolState = new WorkerPoolState(
                WorkerCount,
                MaxBatchSize,
                new Process[WorkerCount],
                new Channel[WorkerCount],
                new ParSpiceGrpc.ParSpiceStub[WorkerCount]);

        ManagedChannelBuilder<?> channelBuilder;
        for (int i = 0; i < WorkerCount; ++i)
        {
            int port = currentPort++;
            String args = "-p " + port;
            Process proc = Runtime.getRuntime().exec( ServerPath + args); // throws io exception
            _poolState.getProcessCollection()[i] = proc;

            channelBuilder = getChannelBuilder("127.0.0.1:", port);

            _poolState.getChannelCollection()[i] = channelBuilder.build();
            Channel channelI = _poolState.getChannelCollection()[i];
            _poolState.getParClients()[i] = ParSpiceGrpc.newStub(channelI);
        }
    }

    private ManagedChannelBuilder<?> getChannelBuilder(String host, int port) {
        return ManagedChannelBuilder.forAddress(host, port).usePlaintext();
    }


    //public void PerformThreadedDistributedTask(
    //        String CalcName,
    //        int BatchSize,
    //        Function<Integer, Double> ArgFactory,
    //        Function2<Object, MathCalcReply, Void> ResponseCallback) throws InterruptedException {
    //    DistributedTaskState state = new DistributedTaskState(
    //            _poolState,
    //            CalcName,
    //            BatchSize,
    //            ArgFactory,
    //            ResponseCallback);
//
    //    // TODID: start thread1worker and thread2worker pass in state
    //    CancellableThread<DistributedTaskState> t1 = new CancellableThread<>(new Thread1Worker(), state);
    //    CancellableThread<DistributedTaskState> t2 = new CancellableThread<>(new Thread2Worker(), state);
    //    t1.start();
    //    t2.start();
//
    //    state.getJobCompletionHandle().waitOne();
    //}


    /**
     * Distribute collection of parSpice tasks across worker processors
     * @param CalcName SPICE function Name
     * @param ArgFactory List of SPICE task arguments
     * @param RequestBuilderFactory Function that returns a properly typed gRPC BundleBuilder object for the supplied T_Spice type
     * @param RequestBuilderAggregator Function that converts T_Spice objects to requests and appends them to a BundleBuilder
     * @param RequestFactory Function that builds a BundleBuilder and returns the resultant Request object
     * @param StubSenderCallback Function that invokes the proper ParSpiceStub request endpoint to transmit the request
     * @param ResponseCallback Function that processes response objects from workers
     * @param <T_Spice> Type of SPICE task (ex: FurnshObject)
     * @param <T_RequestBuilder> Type of BundleBuilder (ex: FurnshBundle.Builder)
     * @param <T_Request> Type of gRPC outbound request (ex: FurnshBundle)
     * @param <T_Response> Type of gRPC response object (ex: FurnshRep)
     * @throws Throwable
     */
    public <T_Spice extends ParObject,
            T_RequestMsgOrBuilder extends com.google.protobuf.MessageOrBuilder,
            T_RequestBuilder extends T_RequestMsgOrBuilder,
            T_Request extends T_RequestMsgOrBuilder,
            T_Response extends com.google.protobuf.MessageOrBuilder>
    void PerformDistributedTask(
            String CalcName,
            Iterable<T_Spice> ArgFactory,
            Function<Integer, T_RequestBuilder> RequestBuilderFactory,
            Consumer2<T_RequestBuilder, T_Spice> RequestBuilderAggregator,
            Function<T_RequestBuilder, T_Request> RequestFactory,
            Consumer3<ParSpiceGrpc.ParSpiceStub, T_Request, AwaitableStreamObserver<T_Response>> StubSenderCallback,
            Consumer2<Object, T_Response> ResponseCallback) throws Throwable {

        DistributedTaskState<T_Spice, T_Response> state = new DistributedTaskState<T_Spice, T_Response>(
                _poolState,
                CalcName,
                _batchSize,
                ArgFactory,
                ResponseCallback);

        final Channel[] channelCollection = state.getWorkerPoolState().getChannelCollection();
        final int maxBatchSize = state.getWorkerPoolState().getMaxBatchSize();
        final ParSpiceGrpc.ParSpiceStub[] parClients = state.getWorkerPoolState().getParClients();

        final int currentBatchSize = state.getBatchSize();
        final String calcName = state.getCalcName();
        final Iterable<T_Spice> argFactory = state.getArgFactory();
        final Iterator<T_Spice> argFactoryIter = argFactory.iterator();
        final Consumer2<Object, T_Response> responseCallback = state.getResponseCallback();
        final ArrayList<AwaitableStreamObserver<T_Response>> currentTaskWave = state.getCurrentTaskWave();


        int batchID = 0;
        int currentIdx = 0;

        //continue working until the entire batch is processed
        while (currentIdx < _batchSize)
        {
            for (int i = 0; i < channelCollection.length; ++i)
            {
                currentTaskWave.set(i, null);
            }

            //send batch cluster to each worker
            //send batches to each cluster at once
            for (int i = 0; i < channelCollection.length && currentIdx < currentBatchSize; ++i)
            {
                //make a new batch request for this worker
                int rqstSize = 0;
                T_RequestBuilder parRequestBuilder = RequestBuilderFactory.apply(batchID++);

                //Calculate the args for this request
                //this allows the client to provide an arg factory and lazily evaluate arguments
                //prevents all arguments from being stored in memory at once
                for (int j = 0; j < maxBatchSize && currentIdx < currentBatchSize; ++j, ++currentIdx, ++rqstSize)
                {
                    if(argFactoryIter.hasNext())
                    {
                        T_Spice arg = argFactoryIter.next();
                        RequestBuilderAggregator.apply(parRequestBuilder, arg);
                    }
                    else
                    {
                        break;
                    }
                }

                T_Request parRequest = RequestFactory.apply(parRequestBuilder);

                //dont send request if the batch is empty
                if (rqstSize == 0)
                {
                    continue;
                }
                //send request to next worker
                //add awaitable to stack
                AwaitableStreamObserver<T_Response> requestTask = new AwaitableStreamObserver<>();
                StubSenderCallback.apply(parClients[i], parRequest, requestTask);
                currentTaskWave.set(i, requestTask);
            }

            //wait for all worker responses to return before sending new batches
            //process the receipts, then repeat the process
            for (int i = 0; i < channelCollection.length; ++i)
            {
                AwaitableStreamObserver<T_Response> task = currentTaskWave.get(i);
                if (task == null)
                    continue;
                T_Response val = task.awaitValue();
                responseCallback.apply(this, val);
            }
        }
    }

}
