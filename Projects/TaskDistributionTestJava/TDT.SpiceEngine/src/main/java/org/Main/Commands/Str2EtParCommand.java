package org.Main.Commands;

import org.Main.AwaitableStreamObserver;
import org.Main.Worker.WorkerPool;
import parspiceTest.*;

import java.util.ArrayList;

public class Str2EtParCommand extends ParCommand {

    private ArrayList args = new ArrayList<Str2EtObject>();

    public Str2EtParCommand(){
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
        pool.PerformDistributedTask(
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
    private Str2EtBundle.Builder BuilderFactory()
    {
        return Str2EtBundle.newBuilder();
    }

    //TODO: automate
    private void BuilderAggregator(Str2EtBundle.Builder builder, Str2EtObject arg)
    {
        Str2EtReq req = Str2EtBundle.newBuilder()
                .setTime(arg.arg1)
                .build();

        builder.addRequests(req);
    }

    //TODO: automate
    private Str2EtBundle RequestFactory(Str2EtBundle.Builder builder)
    {
        return builder.build();
    }

    //TODO: automate
    private void SendRequest(ParSpiceGrpc.ParSpiceStub stub, Str2EtBundle request, AwaitableStreamObserver<Str2EtRep> awaiterTask)
    {
        stub.parStr2Et(request, awaiterTask);
    }

    //TODO: automate
    private void ResponseProcessor(Object sender, Str2EtRep response)
    {
        //TODO: write processor code
        //must aggregate multiple responses
        //use local list object for aggregation
        //this function can just grab the response object and attach it to the aggregator
        //return list from distribute()
    }
}