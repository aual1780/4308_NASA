package org.Main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.function.Function;

import org.Main.Spice.MathCalc.OneArg.*;

public class Program {

    static int responseCount = 0;

    public static void main(String[] args) {
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

        Function2<Object, MathCalcReply, Void> mathCalcCallBack = new Function2<Object, MathCalcReply, Void>() {
            @Override
            public Void apply(Object object, MathCalcReply reply) throws InterruptedException {
                responseCount += reply.getResponsesCount();
                System.out.printf("Response group returned: %d", reply.getBatchID());
                System.out.printf("Cumulative responses returned: %d}", responseCount);
                Thread.sleep(20);
                return null;
            }
        };

        ArrayList<String> logs = new ArrayList<>();

        long startTime;
        long stopTime;
        //throw away init code
        try {
        engine.PerformDistributedTask(mathFunc, 10, argFactory, mathCalcCallBack);
        ///////////////////////////////////////////////////////////////////////////////////////////////////////
        responseCount = 0;
        startTime = System.currentTimeMillis();
        engine.PerformDistributedTask(mathFunc, batchSize, argFactory, mathCalcCallBack);
        stopTime = System.currentTimeMillis();
        logs.add("Distributed call elapsed time (millis) " + (stopTime-startTime));

        responseCount = 0;
        startTime = System.currentTimeMillis();

            engine.PerformDistributedTask(mathFunc, batchSize, argFactory, mathCalcCallBack);

        stopTime = System.currentTimeMillis();
        logs.add("Distributed call elapsed time (millis) " + (stopTime-startTime));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ///////////////////////////////////////////////////////////////////////////////////////////////////////
        responseCount = 0;
        startTime = System.currentTimeMillis();
        engine.PerformThreadedDistributedTask(mathFunc, batchSize, argFactory, mathCalcCallBack);
        stopTime = System.currentTimeMillis();
        logs.add("Distributed threaded call elapsed time (millis) " + (stopTime-startTime));

        responseCount = 0;
        startTime = System.currentTimeMillis();
        engine.PerformThreadedDistributedTask(mathFunc, batchSize, argFactory, mathCalcCallBack);
        stopTime = System.currentTimeMillis();
        logs.add("Distributed threaded call elapsed time (millis) " + (stopTime-startTime));


        for (String log: logs) {
            System.out.printf(log);
        }
    }

    public interface Callback  {
        static void MathCalcCallback(Object Sender, MathCalcReply reply) throws InterruptedException {
            responseCount += reply.getResponsesCount();
            System.out.printf("Response group returned: %d", reply.getBatchID());
            System.out.printf("Cumulative responses returned: %d}", responseCount);
            Thread.sleep(20);
        }
    }

}
