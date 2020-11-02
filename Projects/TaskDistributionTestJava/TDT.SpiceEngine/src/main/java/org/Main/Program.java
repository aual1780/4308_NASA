package org.Main;

import java.io.IOException;
import java.util.function.Function;

import io.grpc.stub.StreamObserver;
import org.Main.Spice.MathCalc.OneArg.*;

public class Program {

    static int responseCount = 0;

    public void main(String[] args) {
        String serverPath = "../../../../TDT.Server/bin/Debug/netcoreapp3.1/TDT.Server.exe";
        int initPort = 30672;
        int workerCount = 6;
        String mathFunc = nameof(MathCalc.MathTransform2); // TODO
        int maxBatchSize = 100000;
        int batchSize = 1000000;
        Function<Integer, Double> argFactory = x -> Double.valueOf(x);
        WorkerPool engine;
        try {
            engine = new WorkerPool(serverPath, initPort, workerCount, maxBatchSize);
        } catch (IOException e ) {
            System.out.println(e.getMessage());
            return;
        }

        String log1 = null;
        String log2 = null;
        String log3 = null;
        String log4 = null;
        String log5 = null;
        String log6 = null;

        //throw away init code
        engine.PerformDistributedTask(mathFunc, 10, argFactory, MathCalcCallback);
        ///////////////////////////////////////////////////////////////////////////////////////////////////////
        responseCount = 0;
        long startTime = System.currentTimeMillis();
        engine.PerformDistributedTask(mathFunc, batchSize, argFactory, MathCalcCallback);
        long stopTime = System.currentTimeMillis();
        log3 = "Distributed call elapsed time (millis) " + (stopTime-startTime);

        responseCount = 0;
        startTime = System.currentTimeMillis();
        engine.PerformDistributedTask(mathFunc, batchSize, argFactory, MathCalcCallback);
        stopTime = System.currentTimeMillis();
        log4 = "Distributed call elapsed time (millis) " + (stopTime-startTime);

        ///////////////////////////////////////////////////////////////////////////////////////////////////////
        responseCount = 0;
        startTime = System.currentTimeMillis();
        engine.PerformThreadedDistributedTask(mathFunc, batchSize, argFactory, MathCalcCallback);
        stopTime = System.currentTimeMillis();
        log1 = "Distributed threaded call elapsed time (millis) " + (stopTime-startTime);

        responseCount = 0;
        startTime = System.currentTimeMillis();
        engine.PerformThreadedDistributedTask(mathFunc, batchSize, argFactory, MathCalcCallback);
        stopTime = System.currentTimeMillis();
        log2 = "Distributed threaded call elapsed time (millis) " + (stopTime-startTime);

        /////////////////////////////////////////////////////////////////////////////////////////////////////
        responseCount = 0;
        startTime = System.currentTimeMillis();
        PerformNondistributedTask(mathFunc, maxBatchSize, batchSize, argFactory, MathCalcCallback);
        stopTime = System.currentTimeMillis();
        log5 = "Nondistributed call elapsed time (millis) " + (stopTime-startTime);

        //responseCount = 0;
        //long startTime = System.currentTimeMillis();
        //PerformNondistributedTask(mathFunc, maxBatchSize, batchSize, argFactory, MathCalcCallback);
        //stopTime = System.currentTimeMillis();
        //log6 = "Nondistributed call elapsed time (millis) " + (stopTime-startTime);


        System.out.printf(log1);
        System.out.printf(log2);
        System.out.printf(log3);
        System.out.printf(log4);
        System.out.printf(log5);
        System.out.printf(log6);

        System.out.printf("Press any key to exit...");
    }


    static void MathCalcCallback(Object Sender, MathCalcReply reply) throws InterruptedException {
        responseCount += reply.getResponsesCount();
        System.out.printf("Response group returned: %d", reply.getBatchID());
        System.out.printf("Cumulative responses returned: %d}", responseCount);
        Thread.sleep(20);
    }


    static void PerformNondistributedTask(String CalcName, int MaxBatchSize, int BatchSize, Function<Integer, Double> ArgFactory, StreamObserver<MathCalcReply> ResponseCallback)
    {
        String funcName = CalcName;
        String flags = BindingFlags.Public | BindingFlags.Static;
        Function func = typeof(MathOperations).GetMethod(funcName, flags);
        var funcDelegate = func.CreateDelegate<Function<Double, Double>>();

        // TODO MathCalc Reply
        MathCalcReply reply = new MathCalcReply();
        for (int i = 0; i < BatchSize; ++i)
        {
            double arg = ArgFactory.apply(i);
            double result = funcDelegate(arg);
            reply.getResponsesList().add(result);
            if (i % MaxBatchSize == MaxBatchSize - 1)
            {
                ResponseCallback?.Invoke(null, reply);
                reply = new MathCalcReply();
            }
        }
    }
}
