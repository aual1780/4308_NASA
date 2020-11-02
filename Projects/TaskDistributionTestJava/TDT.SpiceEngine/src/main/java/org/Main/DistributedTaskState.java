package org.Main;

import io.grpc.stub.StreamObserver;
import org.Main.Spice.MathCalc.OneArg.*;

import java.util.ArrayList;
import java.util.function.Function;

public class DistributedTaskState implements Runnable {
    public String calcName;
    public int workerCount;
    public int batchSize;
    public Function<Integer, Double> argFactory;
    public StreamObserver<MathCalcReply> responseCallback; // TODO async
    public AutoResetEvent requestWaitHandle;
    public AutoResetEvent responseWaitHandle;
    public ArrayList<StreamObserver<MathCalcReply>> currentTaskBatch; // TODO async
    public TaskCompletionSource<Object> completionSource = new TaskCompletionSource<Object>();

    public DistributedTaskState(String CalcName, int WorkerCount, int BatchSize, Function<Integer, Double> ArgFactory, StreamObserver<MathCalcReply> ResponseCallback) {
        this.calcName = CalcName;
        this.workerCount = WorkerCount;
        this.batchSize = BatchSize;
        this.argFactory = ArgFactory;
        this.responseCallback = ResponseCallback; // TODO async
        this.currentTaskBatch = new ArrayList<StreamObserver<MathCalcReply>>();
        requestWaitHandle = new AutoResetEvent(true);
        responseWaitHandle = new AutoResetEvent(false);
    }

    public void run() {

    }
}
