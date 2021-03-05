package org.Main.Commands;
import org.Main.AwaitableStreamObserver;
import org.Main.Worker.WorkerPool;
import parspiceTest.*;

import java.util.ArrayList;

public class SpkposCommand extends ParCommand {

    private ArrayList args = new ArrayList<SpkposObject>();

    public SpkposCommand(){
        name = "str2et";
    }

    public void addArg(String arg){
        args.add(new Str2EtObject(arg));
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
    private SpkposBundle.Builder BuilderFactory()
    {
        return SpkposBundle.newBuilder();
    }

    //TODO: automate
    private void BuilderAggregator(SpkposBundle.Builder builder, SpkposObject arg)
    {
        SpkposReq req = SpkposReq.newBuilder()
                .setTarget(arg.arg1)
                .setEt(arg.arg2)
                .setRef(arg.arg3)
                .setAbcorr(arg.arg4)
                .setObserver(arg.arg5)
                .addAllPos(arg.arg6)
                .addAllLt(arg.arg7)
                .build();

        builder.addRequests(req);
    }

    //TODO: automate
    private SpkposBundle RequestFactory(SpkposBundle.Builder builder)
    {
        return builder.build();
    }

    //TODO: automate
    private void SendRequest(ParSpiceGrpc.ParSpiceStub stub, SpkposBundle request, AwaitableStreamObserver<SpkposRep> awaiterTask)
    {
        stub.parSpkpos(request, awaiterTask);
    }

    //TODO: automate
    private void ResponseProcessor(Object sender, SpkposRep response)
    {
        //TODO: write processor code
        //must aggregate multiple responses
        //use local list object for aggregation
        //this function can just grab the response object and attach it to the aggregator
        //return list from distribute()
    }
}
