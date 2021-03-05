package org.Main.Commands;
import org.Main.AwaitableStreamObserver;
import org.Main.Worker.WorkerPool;
import parspiceTest.*;

import java.util.ArrayList;

public class FurnshParCommand extends ParCommand {

    private ArrayList args = new ArrayList<FurnshObject>();

    public FurnshParCommand(){
        name = "furnsh";
    }

    public void addArg(String arg){
        args.add(new FurnshObject(arg));
        return;
    }

    public ArrayList getArg(int index){
        ArrayList arg = new ArrayList();
        arg.add(args.get(index).toString());
        return (arg);
    }

    public ArrayList getArg(){
        return (args);
    }

    public ParResponse distribute(WorkerPool pool) throws Throwable {
        //return new ParClientCmdHandler().ParProcess(this);

        //blocking function call
        pool.PerformBroadcastTask(
                name,
                args,
                this::BuilderFactory,
                this::BuilderAggregator,
                this::RequestFactory,
                this::SendRequest,
                this::ResponseProcessor);

        //TODO: return parResponse here
    }

    //TODO: automate
    private FurnshBundle.Builder BuilderFactory()
    {
        return FurnshBundle.newBuilder();
    }

    //TODO: automate
    private void BuilderAggregator(FurnshBundle.Builder builder, FurnshObject arg)
    {
        FurnshReq req = FurnshReq.newBuilder().setFile(arg.arg1).build();
        builder.addRequests(req);
    }

    //TODO: automate
    private FurnshBundle RequestFactory(FurnshBundle.Builder builder)
    {
        return builder.build();
    }

    //TODO: automate
    private void SendRequest(ParSpiceGrpc.ParSpiceStub stub, FurnshBundle request, AwaitableStreamObserver<FurnshRep> awaiterTask)
    {
        stub.parFurnsh(request, awaiterTask);
    }

    //TODO: automate
    private void ResponseProcessor(Object sender, FurnshRep response)
    {
        //TODO: write processor code
        //must aggregate multiple responses
        //use local list object for aggregation
        //this function can just grab the response object and attach it to the aggregator
        //return list from distribute()
    }
}
