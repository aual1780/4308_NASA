package org.Main.Worker;

import io.grpc.Channel;
import io.grpc.stub.StreamObserver;
import org.Main.DistributedTaskState;
import org.Main.Spice.MathCalc.OneArg.MathCalcGrpc;
import org.Main.Spice.MathCalc.OneArg.MathCalcGrpc.*;
import org.Main.Spice.MathCalc.OneArg.MathCalcReply;

public class Thread2Worker implements Runnable {

    DistributedTaskState state;
    Channel[] _channelCollection;
    int _maxBatchSize;
    MathCalcStub[] _mathClients;

    public Thread2Worker(DistributedTaskState state, Channel[] channelCollection, int maxBatchSize, MathCalcStub[] mathClients){
        this.state = state;
        this._channelCollection = channelCollection;
        this._maxBatchSize = maxBatchSize;
        this._mathClients = mathClients;
    }

    @Override
    public void run() {
        //wait for batch responses
        //separates batch dispatching from result callback processing
        //allows one thread to push out new requests while this thread evaluates responses
        //improves performance with non-trivial callbacks

        int cumResponseCount = 0;
        int batchSize = state.batchSize;

        while (cumResponseCount < batchSize)
        {
            try {
                state.responseWaitHandle.waitOne(); // throws interrupted exception
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

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
}
