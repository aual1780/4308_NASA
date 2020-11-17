package org.Main.Worker;

import io.grpc.Channel;
import io.grpc.ManagedChannelBuilder;

import io.grpc.stub.StreamObserver;
import org.Main.AutoResetEvent;
import org.Main.AwaitableStreamObserver;
import org.Main.CancellableThread;
import org.Main.Worker.Function2;
import org.Main.Spice.MathCalc.OneArg.MathCalcGrpc.*;
import org.Main.Spice.MathCalc.OneArg.MathCalcGrpc;
import org.Main.Spice.MathCalc.OneArg.MathCalcReply;
import org.Main.Spice.MathCalc.OneArg.MathCalcRequest;
import org.Main.Spice.MathCalc.OneArg.MathCalcRequest.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.function.Function;
import java.util.logging.Logger;


public class WorkerPool  {
    private final WorkerPoolState _poolState;
    private static final Logger logger = Logger.getLogger(WorkerPool.class.getName());

    public WorkerPool(String ServerPath, int StartPort, int WorkerCount, int MaxBatchSize) throws IOException {
        int currentPort = StartPort;

        _poolState = new WorkerPoolState(
                WorkerCount,
                MaxBatchSize,
                new Process[WorkerCount],
                new Channel[WorkerCount],
                new MathCalcStub[WorkerCount]);

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
            _poolState.getMathClients()[i] = MathCalcGrpc.newStub(channelI);
        }
    }

    private ManagedChannelBuilder<?> getChannelBuilder(String host, int port) {
        return ManagedChannelBuilder.forAddress(host, port).usePlaintext();
    }


    public void PerformThreadedDistributedTask(String CalcName, int BatchSize, Function<Integer, Double> ArgFactory, Function2<Object, MathCalcReply, Void> ResponseCallback) throws InterruptedException {
        DistributedTaskState state = new DistributedTaskState(
                _poolState,
                CalcName,
                BatchSize,
                ArgFactory,
                ResponseCallback);

        // TODID: start thread1worker and thread2worker pass in state
        CancellableThread<DistributedTaskState> t1 = new CancellableThread<>(new Thread1Worker(), state);
        CancellableThread<DistributedTaskState> t2 = new CancellableThread<>(new Thread2Worker(), state);
        t1.start();
        t2.start();

        state.getJobCompletionHandle().waitOne();
    }



    public void PerformDistributedTask(String CalcName, int BatchSize, Function<Integer, Double> ArgFactory,  Function2<Object, MathCalcReply, Void> ResponseCallback) throws Throwable {
        DistributedTaskState state = new DistributedTaskState(
                _poolState,
                CalcName,
                BatchSize,
                ArgFactory,
                ResponseCallback);

        final Channel[] channelCollection = state.getWorkerPoolState().getChannelCollection();
        final int maxBatchSize = state.getWorkerPoolState().getMaxBatchSize();
        final MathCalcGrpc.MathCalcStub[] mathClients = state.getWorkerPoolState().getMathClients();

        final int currentBatchSize = state.getBatchSize();
        final String calcName = state.getCalcName();
        final Function<Integer, Double> argFactory = state.getArgFactory();
        final Function2<Object, MathCalcReply, Void> responseCallback = state.getResponseCallback();
        final ArrayList<AwaitableStreamObserver<MathCalcReply>> currentTaskWave = state.getCurrentTaskWave();

        int batchID = 0;
        int currentIdx = 0;

        //continue working until the entire batch is processed
        while (currentIdx < BatchSize)
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
                MathCalcRequest mathRequest = new MathCalcRequest();
                Builder builder = MathCalcRequest.newBuilder().setBatchID(batchID++).setCalcName(calcName);
                //Calculate the args for this request
                //this allows the client to provide an arg factory and lazily evaluate arguments
                //prevents all arguments from being stored in memory at once
                for (int j = 0; j < maxBatchSize && currentIdx < currentBatchSize; ++j, ++currentIdx, ++rqstSize)
                {
                    double arg = argFactory.apply(currentIdx);
                    mathRequest = builder.setArgs(currentIdx, arg).build();
                }
                //dont send request if the batch is empty
                if (rqstSize == 0)
                {
                    continue;
                }
                //send request to next worker
                //add awaitable to stack
                AwaitableStreamObserver<MathCalcReply> requestTask = new AwaitableStreamObserver<>();
                mathClients[i].doMath(mathRequest, requestTask);
                currentTaskWave.set(i, requestTask);
            }

            //wait for all worker responses to return before sending new batches
            //process the receipts, then repeat the process
            for (int i = 0; i < channelCollection.length; ++i)
            {
                AwaitableStreamObserver<MathCalcReply> task = currentTaskWave.get(i);
                if (task == null)
                    continue;
                MathCalcReply val = task.awaitValue();
                responseCallback.apply(this, val);
            }
        }
    }

}
