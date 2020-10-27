package org.Main;

import org.Main.Spice.MathCalc.OneArg.*;

import java.awt.*;
import java.io.IOException;
import java.nio.channels.SocketChannel;
import java.util.function.Function;


public class WorkerPool {

    Process[] _processCollection;
    Channel[] _channelCollection;
    MathCalcGrpc.MathCalcFutureStub[] _mathClients;
    int _maxBatchSize;

    public WorkerPool(String ServerPath, int StartPort, int WorkerCount, int MaxBatchSize) throws IOException {
        int currentPort = StartPort;
        _maxBatchSize = MaxBatchSize;
        _channelCollection = new Channel[WorkerCount];
        _processCollection = new Process[WorkerCount];
        _mathClients = new MathCalc.OneArg.MathCalc.MathCalcClient[WorkerCount];
        for (int i = 0; i < WorkerCount; ++i)
        {
            int port = currentPort++;
            String args = "-p " + port;
            Process proc = Runtime.getRuntime().exec( ServerPath + args); // throws io exception
            _processCollection[i] = proc;
            _channelCollection[i] = new Channel("127.0.0.1:"+ port);
            _mathClients[i] = new MathCalc.OneArg.MathCalc.MathCalcClient(_channelCollection[i]);
        }
    }


    public Task PerformThreadedDistributedTask(
            String CalcName,
            int BatchSize,
            Function<Integer, Double> ArgFactory,
            EventHandler<MathCalcReply> ResponseCallback)
    {
        DistributedTaskState state = new DistributedTaskState(CalcName, _channelCollection.length, BatchSize, ArgFactory, ResponseCallback);

        Thread t1 = new Thread(state);
        Thread t2 = new Thread(state);
        t1.start();
        t2.start();

        return state.completionSource.Task;
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
//                {
//                    BatchID = batchID++,
//                    CalcName = state.calcName
//                };
                //Calculate the args for this request
                //this allows the client to provide an arg factory and lazily evaluate arguments
                //prevents all arguments from being stored in memory at once
                for (int j = 0; j < _maxBatchSize && currentIdx < currentBatchSize; ++j, ++currentIdx, ++rqstSize)
                {
                    double arg = state.argFactory.apply(currentIdx);
                    mathRequest.Args.Add(arg);
                }
                //dont send request if the batch is empty
                if (rqstSize == 0)
                {
                    continue;
                }
                //send request to next worker
                //add awaitable to stack
                AsyncUnaryCall<MathCalcReply> requestTask = _mathClients[i].DoMathAsync(mathRequest);
                state.currentTaskBatch[i] = requestTask;
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
                Task task = state.currentTaskBatch[i];
                if (task == null)
                    continue;
                MathCalcReply response = task;
                resultClone[i] = response;
                state.currentTaskBatch[i] = null;
            }
            //let requester thread send new wave of batches
            state.requestWaitHandle.set();
            //process response callbacks while new batch wave is being processed by workers
            for (int i = 0; i < _channelCollection.length; ++i)
            {
                MathCalcReply response = resultClone[i];
                if(response != null)
                {
                    cumResponseCount += response.getResponses(i).length; // Responses.Count;
                    state.ResponseCallback?.Invoke(this, response); // TODO callback function
                }
            }
        }
        state.completionSource. //.SetResult(null);
    }



    public Task PerformDistributedTask(
            String CalcName,
            int BatchSize,
            Function<Integer, Double> ArgFactory,
            EventHandler<MathCalcReply> ResponseCallback)
    {
        DistributedTaskState state = new DistributedTaskState(CalcName, _channelCollection.length, BatchSize, ArgFactory, ResponseCallback);

        int batchID = 0;
        int currentBatchSize = state.batchSize;
        int currentIdx = 0;

        //continue working until the entire batch is processed
        while (currentIdx < currentBatchSize)
        {
            for (int i = 0; i < _channelCollection.length; ++i)
            {
                state.currentTaskBatch[i] = null;
            }

            //send batch cluster to each worker
            //send batches to each cluster at once
            for (int i = 0; i < _channelCollection.length && currentIdx < currentBatchSize; ++i)
            {
                //make a new batch request for this worker
                int rqstSize = 0;
                MathCalcRequest mathRequest = new MathCalcRequest();
                //Calculate the args for this request
                //this allows the client to provide an arg factory and lazily evaluate arguments
                //prevents all arguments from being stored in memory at once
                for (int j = 0; j < _maxBatchSize && currentIdx < currentBatchSize; ++j, ++currentIdx, ++rqstSize)
                {
                    double arg = state.argFactory.apply(currentIdx);
                    mathRequest.Args.Add(arg);
                }
                //dont send request if the batch is empty
                if (rqstSize == 0)
                {
                    continue;
                }
                //send request to next worker
                //add awaitable to stack
                AsyncUnaryCall<MathCalcReply> requestTask = _mathClients[i].DoMathAsync(mathRequest);
                state.currentTaskBatch[i] = requestTask;
            }

            //wait for all worker responses to return before sending new batches
            //process the receipts, then repeat the process
            for (int i = 0; i < _channelCollection.length; ++i)
            {
                Task task = state.currentTaskBatch[i];
                if (task == null)
                    continue;
                Task response = task;
                state.responseCallback?.Invoke(this, response); // TODO callback function in java
            }
        }
    }

}
