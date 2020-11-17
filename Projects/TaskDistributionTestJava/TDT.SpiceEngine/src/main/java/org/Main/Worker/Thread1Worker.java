package org.Main.Worker;

import com.tunnelvisionlabs.util.concurrent.CancellationToken;
import io.grpc.Channel;
import io.grpc.stub.StreamObserver;
import org.Main.AutoResetEvent;
import org.Main.AwaitableStreamObserver;
import org.Main.CancellableThreadRunnable;
import org.Main.Spice.MathCalc.OneArg.MathCalcGrpc;
import org.Main.Spice.MathCalc.OneArg.MathCalcReply;
import org.Main.Spice.MathCalc.OneArg.MathCalcRequest;

import java.util.ArrayList;
import java.util.function.Function;

public class Thread1Worker implements CancellableThreadRunnable<DistributedTaskState> {

    public Thread1Worker(){
    }

    @Override
    public void run(DistributedTaskState state, CancellationToken token) {
        final Channel[] channelCollection = state.getWorkerPoolState().getChannelCollection();
        final int maxBatchSize = state.getWorkerPoolState().getMaxBatchSize();
        final MathCalcGrpc.MathCalcStub[] mathClients = state.getWorkerPoolState().getMathClients();

        final int currentBatchSize = state.getBatchSize();
        final String calcName = state.getCalcName();
        final AutoResetEvent requestWaitHandle = state.getRequestWaitHandle();
        final AutoResetEvent responseWaitHandle = state.getResponseWaitHandle();
        final Function<Integer, Double> argFactory = state.getArgFactory();
        final ArrayList<AwaitableStreamObserver<MathCalcReply>> currentTaskWave = state.getCurrentTaskWave();

        int batchID = 0;
        int currentIdx = 0;

        //continue working until the entire batch is processed
        while (currentIdx < currentBatchSize && !token.isCancellationRequested())
        {
            try {
                requestWaitHandle.waitOne(); // throws InterruptedException
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            //send batch request to each worker
            //send all batch requests to the workers at once
            //the batches are sent out in waves
            //this allows the response processor to guarantee in-order evaluation

            for (int i = 0; i < channelCollection.length && currentIdx < currentBatchSize; ++i)
            {
                //make a new batch request for this worker
                int rqstSize = 0;
                MathCalcRequest.Builder builder = MathCalcRequest.newBuilder().setBatchID(batchID++).setCalcName(calcName);
                MathCalcRequest mathRequest = builder.build();
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

                //TODID
                AwaitableStreamObserver<MathCalcReply> requestTask = new AwaitableStreamObserver<>();
                mathClients[i].doMath(mathRequest, requestTask);
                currentTaskWave.set(i, requestTask);
            }
            responseWaitHandle.set();
        }
    }
}
