package org.Main.Worker;

import io.grpc.Channel;
import io.grpc.stub.StreamObserver;
import org.Main.DistributedTaskState;
import org.Main.Spice.MathCalc.OneArg.MathCalcGrpc;
import org.Main.Spice.MathCalc.OneArg.MathCalcGrpc.*;
import org.Main.Spice.MathCalc.OneArg.MathCalcReply;
import org.Main.Spice.MathCalc.OneArg.MathCalcRequest;

public class Thread1Worker implements Runnable{
    DistributedTaskState state;
    Channel[] _channelCollection;
    int _maxBatchSize;
    MathCalcGrpc.MathCalcStub[] _mathClients;

    public Thread1Worker(DistributedTaskState state, Channel[] channelCollection, int maxBatchSize, MathCalcStub[] mathClients){
        this.state = state;
        this._channelCollection = channelCollection;
        this._maxBatchSize = maxBatchSize;
        this._mathClients = mathClients;
    }

    @Override
    public void run() {
        int batchID = 0;
        int currentBatchSize = state.batchSize;
        int currentIdx = 0;

        //continue working until the entire batch is processed
        while (currentIdx < currentBatchSize)
        {
            try {
                state.requestWaitHandle.waitOne(); // throws InterruptedException
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            //send batch request to each worker
            //send all batch requests to the workers at once
            //the batches are sent out in waves
            //this allows the response processor to guarantee in-order evaluation

            for (int i = 0; i < _channelCollection.length && currentIdx < currentBatchSize; ++i)
            {
                //make a new batch request for this worker
                int rqstSize = 0;
                MathCalcRequest mathRequest = new MathCalcRequest();
                MathCalcRequest.Builder builder = MathCalcRequest.newBuilder().setBatchID(batchID++).setCalcName(state.calcName);
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
}
