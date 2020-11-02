package org.Main;

import io.grpc.*;
import org.Main.Spice.MathCalc.OneArg.*;

import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

public class DistributedTaskState implements Runnable {
    public String calcName;
    public int workerCount;
    public int batchSize;
    public Function<Integer, Double> argFactory;
    public MathCalcReply responseCallback; // TODO async
    public AutoResetEvent requestWaitHandle;
    public AutoResetEvent responseWaitHandle;
    public ArrayList<MathCalcReply> currentTaskBatch; // TODO async
    public TaskCompletionSource<Object> completionSource = new TaskCompletionSource<Object>();

    public DistributedTaskState(String CalcName, int WorkerCount, int BatchSize, Function<Integer, Double> ArgFactory, MathCalcReply ResponseCallback) {
        this.calcName = CalcName;
        this.workerCount = WorkerCount;
        this.batchSize = BatchSize;
        this.argFactory = ArgFactory;
        this.responseCallback = ResponseCallback; // TODO async
        currentTaskBatch = new ArrayList<MathCalcReply>();// TODO async
        requestWaitHandle = new AutoResetEvent(true);
        responseWaitHandle = new AutoResetEvent(false);
    }

    public void run() {

    }
}
