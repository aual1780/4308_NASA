package org.Main.Worker;

import io.grpc.stub.StreamObserver;
import org.Main.AutoResetEvent;
import org.Main.AwaitableStreamObserver;
import org.Main.Commands.ParObject;
import org.Main.Worker.Function2;
import org.Main.Spice.MathCalc.OneArg.*;

import java.util.ArrayList;
import java.util.function.Function;

public class DistributedTaskState<T_Object extends ParObject, T_Response extends com.google.protobuf.MessageOrBuilder> {
    private WorkerPoolState poolState;
    private String calcName;
    private int batchSize;
    private Iterable<T_Object> argFactory;
    private Consumer2<Object, T_Response> responseCallback;
    private AutoResetEvent requestWaitHandle;
    private AutoResetEvent responseWaitHandle;
    private ArrayList<AwaitableStreamObserver<T_Response>> currentTaskBatch;
    private AutoResetEvent jobCompletionHandle;

    public DistributedTaskState(
            WorkerPoolState PoolState,
            String CalcName,
            int BatchSize,
            Iterable<T_Object> ArgFactory,
            Consumer2<Object, T_Response> ResponseCallback) {
        this.calcName = CalcName;
        this.batchSize = BatchSize;
        this.argFactory = ArgFactory;
        this.responseCallback = ResponseCallback;
        this.currentTaskBatch = new ArrayList<AwaitableStreamObserver<T_Response>>();
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

    public Iterable<T_Object> getArgFactory()
    {
        return argFactory;
    }

    public Consumer2<Object, T_Response> getResponseCallback()
    {
        return responseCallback;
    }

    public AutoResetEvent getRequestWaitHandle() {
        return requestWaitHandle;
    }

    public AutoResetEvent getResponseWaitHandle() {
        return responseWaitHandle;
    }

    public ArrayList<AwaitableStreamObserver<T_Response>> getCurrentTaskWave() {
        return currentTaskBatch;
    }

    public AutoResetEvent getJobCompletionHandle() {
        return jobCompletionHandle;
    }
}
