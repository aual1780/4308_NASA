package org.Main.Worker;

import io.grpc.stub.StreamObserver;
import org.Main.AutoResetEvent;
import org.Main.AwaitableStreamObserver;
import org.Main.Worker.Function2;
import org.Main.Spice.MathCalc.OneArg.*;

import java.util.ArrayList;
import java.util.function.Function;

public class DistributedTaskState {
    private WorkerPoolState poolState;
    private String calcName;
    private int batchSize;
    private Function<Integer, Double> argFactory;
    private Function2<Object, MathCalcReply, Void> responseCallback;
    private AutoResetEvent requestWaitHandle;
    private AutoResetEvent responseWaitHandle;
    private ArrayList<AwaitableStreamObserver<MathCalcReply>> currentTaskBatch;
    private AutoResetEvent jobCompletionHandle;

    public DistributedTaskState(
            WorkerPoolState PoolState,
            String CalcName,
            int BatchSize,
            Function<Integer, Double> ArgFactory,
            Function2<Object, MathCalcReply, Void> ResponseCallback) {
        this.calcName = CalcName;
        this.batchSize = BatchSize;
        this.argFactory = ArgFactory;
        this.responseCallback = ResponseCallback;
        this.currentTaskBatch = new ArrayList<AwaitableStreamObserver<MathCalcReply>>();
        for(int i = 0; i < poolState.getWorkerCount(); ++i)
        {
            this.currentTaskBatch.add(null);
        }
        requestWaitHandle = new AutoResetEvent(true);
        responseWaitHandle = new AutoResetEvent(false);
        jobCompletionHandle = new AutoResetEvent(false);
    }

    public WorkerPoolState getWorkerPoolState()
    {
        return poolState;
    }

    public String getCalcName()
    {
        return calcName;
    }

    public int getBatchSize()
    {
        return  batchSize;
    }

    public Function<Integer, Double> getArgFactory()
    {
        return argFactory;
    }

    public Function2<Object, MathCalcReply, Void> getResponseCallback()
    {
        return responseCallback;
    }

    public AutoResetEvent getRequestWaitHandle() {
        return requestWaitHandle;
    }

    public AutoResetEvent getResponseWaitHandle() {
        return responseWaitHandle;
    }

    public ArrayList<AwaitableStreamObserver<MathCalcReply>> getCurrentTaskWave() {
        return currentTaskBatch;
    }

    public AutoResetEvent getJobCompletionHandle() {
        return jobCompletionHandle;
    }
}
