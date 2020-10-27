package org.Main;

import io.grpc.*;
import org.Main.Spice.MathCalc.OneArg.*;

import java.util.function.Function;

public class DistributedTaskState implements Runnable {
    public String calcName;
    public int workerCount;
    public int batchSize;
    public Function<Integer, Double> argFactory;
    public EventHandler<MathCalcReply> responseCallback;
    public AutoResetEvent requestWaitHandle;
    public AutoResetEvent responseWaitHandle;
    public AsyncUnaryCall<MathCalcReply>[] currentTaskBatch;
    public TaskCompletionSource<Object> completionSource = new TaskCompletionSource<Object>();

    public DistributedTaskState(String CalcName, int WorkerCount, int BatchSize, Function<Integer, Double> ArgFactory, EventHandler<MathCalcReply> ResponseCallback) {
        this.calcName = CalcName;
        this.workerCount = WorkerCount;
        this.batchSize = BatchSize;
        this.argFactory = ArgFactory;
        this.responseCallback = ResponseCallback;
        currentTaskBatch = new AsyncUnaryCall<MathCalcReply>[WorkerCount];
        requestWaitHandle = new AutoResetEvent(true);
        responseWaitHandle = new AutoResetEvent(false);
    }

    public void run() {

    }
}
