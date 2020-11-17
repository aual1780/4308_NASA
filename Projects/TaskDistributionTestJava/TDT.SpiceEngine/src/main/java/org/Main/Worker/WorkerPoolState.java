package org.Main.Worker;

import com.tunnelvisionlabs.util.validation.NotNull;
import io.grpc.Channel;
import org.Main.Spice.MathCalc.OneArg.MathCalcGrpc;

import java.sql.Driver;

public class WorkerPoolState {
    private final int workerCount;
    private final int maxBatchSize;
    private final Process[] processCollection;
    private final Channel[] channelCollection;
    private final MathCalcGrpc.MathCalcStub[] mathClients;

    public WorkerPoolState(
            int workerCount,
            int maxBatchSize,
            @NotNull Process[] processCollection,
            @NotNull Channel[] channelCollection,
            @NotNull MathCalcGrpc.MathCalcStub[] mathClients)
    {
        this.workerCount = workerCount;
        this.maxBatchSize = maxBatchSize;
        this.processCollection = processCollection;
        this.channelCollection = channelCollection;
        this.mathClients = mathClients;
    }

    @NotNull
    public int getWorkerCount()
    {
        return workerCount;
    }

    @NotNull
    public int getMaxBatchSize() {
        return maxBatchSize;
    }

    @NotNull
    public Process[] getProcessCollection()
    {
        return processCollection;
    }

    @NotNull
    public Channel[] getChannelCollection() {
        return channelCollection;
    }

    @NotNull
    public MathCalcGrpc.MathCalcStub[] getMathClients()
    {
        return mathClients;
    }
}
