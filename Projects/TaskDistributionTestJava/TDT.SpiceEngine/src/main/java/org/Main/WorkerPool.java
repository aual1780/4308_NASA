package org.Main;

import io.grpc.Channel;
import io.grpc.ManagedChannelBuilder;

import io.grpc.stub.StreamObserver;
import org.Main.Spice.MathCalc.OneArg.MathCalcGrpc.*;
import org.Main.Spice.MathCalc.OneArg.MathCalcGrpc;
import org.Main.Spice.MathCalc.OneArg.MathCalcReply;
import org.Main.Spice.MathCalc.OneArg.MathCalcRequest;
import org.Main.Spice.MathCalc.OneArg.MathCalcRequest.*;

import java.io.IOException;
import java.util.function.Function;
import java.util.logging.Logger;


public class WorkerPool  {

    Process[] _processCollection;
    Channel[] _channelCollection;
    MathCalcStub[] _mathClients;
    int _maxBatchSize;
    private static final Logger logger = Logger.getLogger(WorkerPool.class.getName());

    public WorkerPool(String ServerPath, int StartPort, int WorkerCount, int MaxBatchSize) throws IOException {
        int currentPort = StartPort;
        _maxBatchSize = MaxBatchSize;
        _channelCollection = new Channel[WorkerCount];
        _processCollection = new Process[WorkerCount];
        _mathClients = new MathCalcStub[WorkerCount];

        ManagedChannelBuilder<?> channelBuilder;
        for (int i = 0; i < WorkerCount; ++i)
        {
            int port = currentPort++;
            String args = "-p " + port;
            Process proc = Runtime.getRuntime().exec( ServerPath + args); // throws io exception
            _processCollection[i] = proc;

            channelBuilder = getChannelBuilder("127.0.0.1:", port);

            _channelCollection[i] = channelBuilder.build();
            _mathClients[i] = MathCalcGrpc.newStub(_channelCollection[i]);
        }
    }

    private ManagedChannelBuilder<?> getChannelBuilder(String host, int port) {
        return ManagedChannelBuilder.forAddress(host, port).usePlaintext();
    }


    public void PerformThreadedDistributedTask(
            String CalcName,
            int BatchSize,
            Function<Integer, Double> ArgFactory,
            StreamObserver<MathCalcReply> ResponseCallback) // TODO Task replacement
    {
        DistributedTaskState state = new DistributedTaskState(CalcName, _channelCollection.length, BatchSize, ArgFactory, ResponseCallback);

        // TODO: start thread1worker and thread2worker
        Thread t1 = new Thread();
        Thread t2 = new Thread();
        t1.start();
        t2.start();
    }


    public void Thread1Worker(DistributedTaskState state) throws InterruptedException {
        int batchID = 0;
        int currentBatchSize = state.batchSize;
        int currentIdx = 0;

        //continue working until the entire batch is processed
        while (currentIdx < currentBatchSize)
        {
            state.requestWaitHandle.waitOne(); // throws InterruptedException

            //send batch request to each worker
            //send all batch requests to the workers at once
            //the batches are sent out in waves
            //this allows the response processor to guarantee in-order evaluation

            for (int i = 0; i < _channelCollection.length && currentIdx < currentBatchSize; ++i)
            {
                //make a new batch request for this worker
                int rqstSize = 0;
                MathCalcRequest mathRequest = new MathCalcRequest();
                Builder builder = MathCalcRequest.newBuilder().setBatchID(batchID++).setCalcName(state.calcName);
                //Calculate the args for this request
                //this allows the client to provide an arg factory and lazily evaluate arguments
                //prevents all arguments from being stored in memory at once
                for (int j = 0; j < _maxBatchSize && currentIdx < currentBatchSize; ++j, ++currentIdx, ++rqstSize)
                {
                    double arg = state.argFactory.apply(currentIdx);
                    mathRequest = builder.setArgs(currentIdx, arg).build();
                }
                //dont send request if the batch is empty
                if (rqstSize == 0)
                {
                    continue;
                }
                //send request to next worker
                //add awaitable to stack

                StreamObserver<MathCalcReply> requestTask = new StreamObserver<MathCalcReply>() {
                    @Override
                    public void onNext(MathCalcReply value) {

                    }

                    @Override
                    public void onError(Throwable t) {

                    }

                    @Override
                    public void onCompleted() {

                    }
                };
                _mathClients[i].doMath(mathRequest, requestTask);
                state.currentTaskBatch.set(i, requestTask);
            }
            state.responseWaitHandle.set();
        }
    }

    public void Thread2Worker(DistributedTaskState state) throws InterruptedException {
        //wait for batch responses
        //separates batch dispatching from result callback processing
        //allows one thread to push out new requests while this thread evaluates responses
        //improves performance with non-trivial callbacks

        int cumResponseCount = 0;
        int batchSize = state.batchSize;

        while (cumResponseCount < batchSize)
        {
            state.responseWaitHandle.waitOne(); // throws interrupted exception

            MathCalcReply[] resultClone = new MathCalcReply[_channelCollection.length];

            //wait for all worker responses to return before sending new batches
            //process the receipts, then repeat the process
            //cache responses in 2nd buffer
            for (int i = 0; i < _channelCollection.length; ++i)
            {
                StreamObserver<MathCalcReply> task = state.currentTaskBatch.get(i);
                if (task == null)
                    continue;
                StreamObserver<MathCalcReply> response = task;
                resultClone[i] = response; // TODO
                state.currentTaskBatch.set(i, null);
            }
            //let requester thread send new wave of batches
            state.requestWaitHandle.set();
            //process response callbacks while new batch wave is being processed by workers
            for (int i = 0; i < _channelCollection.length; ++i)
            {
                MathCalcReply response = resultClone[i];
                if(response != null)
                {
                    cumResponseCount += response.getResponsesList().size();
                    // state.ResponseCallback?.Invoke(this, response); // TODO callback function
                }
            }
        }
        // state.completionSource.SetResult(null); // TODO determine
    }



    public void PerformDistributedTask(String CalcName, int BatchSize, Function<Integer, Double> ArgFactory, StreamObserver<MathCalcReply> ResponseCallback)
    {
        DistributedTaskState state = new DistributedTaskState(CalcName, _channelCollection.length, BatchSize, ArgFactory,ResponseCallback);

        int batchID = 0;
        int currentBatchSize = state.batchSize;
        int currentIdx = 0;

        //continue working until the entire batch is processed
        while (currentIdx < currentBatchSize)
        {
            for (int i = 0; i < _channelCollection.length; ++i)
            {
                state.currentTaskBatch.set(i, null);
            }

            //send batch cluster to each worker
            //send batches to each cluster at once
            for (int i = 0; i < _channelCollection.length && currentIdx < currentBatchSize; ++i)
            {
                //make a new batch request for this worker
                int rqstSize = 0;
                MathCalcRequest mathRequest = new MathCalcRequest();
                Builder builder = MathCalcRequest.newBuilder().setBatchID(batchID++).setCalcName(state.calcName);
                //Calculate the args for this request
                //this allows the client to provide an arg factory and lazily evaluate arguments
                //prevents all arguments from being stored in memory at once
                for (int j = 0; j < _maxBatchSize && currentIdx < currentBatchSize; ++j, ++currentIdx, ++rqstSize)
                {
                    double arg = state.argFactory.apply(currentIdx);
                    mathRequest = builder.setArgs(currentIdx, arg).build();
                }
                //dont send request if the batch is empty
                if (rqstSize == 0)
                {
                    continue;
                }
                //send request to next worker
                //add awaitable to stack
                StreamObserver<MathCalcReply> requestTask = new StreamObserver<MathCalcReply>() {
                    @Override
                    public void onNext(MathCalcReply value) {

                    }

                    @Override
                    public void onError(Throwable t) {

                    }

                    @Override
                    public void onCompleted() {

                    }
                };
                _mathClients[i].doMath(mathRequest, requestTask);
                state.currentTaskBatch.set(i, requestTask);
            }

            //wait for all worker responses to return before sending new batches
            //process the receipts, then repeat the process
            for (int i = 0; i < _channelCollection.length; ++i)
            {
                StreamObserver<MathCalcReply> task = state.currentTaskBatch.get(i);
                if (task == null)
                    continue;
                StreamObserver<MathCalcReply> response = task; // TODO
                state.responseCallback?.Invoke(this, response); // TODO callback function in replacement
            }
        }
    }

}
