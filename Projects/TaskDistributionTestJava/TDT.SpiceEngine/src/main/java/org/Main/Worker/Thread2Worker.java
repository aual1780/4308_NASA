package org.Main.Worker;

import com.tunnelvisionlabs.util.concurrent.CancellationToken;
import io.grpc.Channel;
import io.grpc.stub.StreamObserver;
import org.Main.AutoResetEvent;
import org.Main.AwaitableStreamObserver;
import org.Main.CancellableThreadRunnable;
import org.Main.Spice.MathCalc.OneArg.MathCalcGrpc;
import org.Main.Spice.MathCalc.OneArg.MathCalcGrpc.*;
import org.Main.Spice.MathCalc.OneArg.MathCalcReply;

import java.util.ArrayList;
import java.util.function.Function;

public class Thread2Worker implements CancellableThreadRunnable<DistributedTaskState> {

    public Thread2Worker(){

    }

    @Override
    public void run(DistributedTaskState state, CancellationToken token) throws Throwable {
        final Channel[] channelCollection = state.getWorkerPoolState().getChannelCollection();

        final int batchSize = state.getBatchSize();
        final AutoResetEvent requestWaitHandle = state.getRequestWaitHandle();
        final AutoResetEvent responseWaitHandle = state.getResponseWaitHandle();
        final Function2<Object, MathCalcReply, Void> responseCallback = state.getResponseCallback();
        final ArrayList<AwaitableStreamObserver<MathCalcReply>> currentTaskWave = state.getCurrentTaskWave();

        //wait for batch responses
        //separates batch dispatching from result callback processing
        //allows one thread to push out new requests while this thread evaluates responses
        //improves performance with non-trivial callbacks

        int cumResponseCount = 0;

        while (cumResponseCount < batchSize && !token.isCancellationRequested())
        {
            try {
                responseWaitHandle.waitOne(); // throws interrupted exception
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            MathCalcReply[] resultClone = new MathCalcReply[channelCollection.length];

            //wait for all worker responses to return before sending new batches
            //process the receipts, then repeat the process
            //cache responses in 2nd buffer
            for (int i = 0; i < channelCollection.length; ++i)
            {
                AwaitableStreamObserver<MathCalcReply> task = currentTaskWave.get(i);
                if (task == null)
                    continue;
                MathCalcReply response = task.awaitValue();
                resultClone[i] = response; // TODID wait for real response
                currentTaskWave.set(i, null);
            }
            //let requester thread send new wave of batches
            requestWaitHandle.set();
            //process response callbacks while new batch wave is being processed by workers
            for (int i = 0; i < channelCollection.length; ++i)
            {
                MathCalcReply response = resultClone[i];
                if(response != null)
                {
                    cumResponseCount += response.getResponsesList().size();
                    responseCallback.apply(this, response); // TODID callback function
                }
            }
        }
        state.getJobCompletionHandle().set(); // TODID determine completion state
    }
}
