package parspiceTest;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;

import java.util.logging.Logger;

public class ParClientCmdHandler {

    // grpc variables
    private static final Logger logger = Logger.getLogger(GClient.class.getName());
    private ParSpiceGrpc.ParSpiceBlockingStub blockingStub;

    public ParClientCmdHandler(){
        // init type
        // 0 = grpc
        int type = 0;

        blockingStub = null;
        switch(type){
            case 0:
                // establish grpc stub
                ConnectGrpc();
                break;
            // open to other messaging services
            default:
        }
    }

    private void ConnectGrpc(){
        String target = "localhost:50051";
        ManagedChannel channel = ManagedChannelBuilder.forTarget(target)
                .usePlaintext()
                .build();
        blockingStub = ParSpiceGrpc.newBlockingStub(channel);
    }

    public ParResponse ParProcess(ParCommand cmd){
        ParResponse response;

        switch (cmd.getFuncInfo()){
            case "furnsh":
                response = GrpcFurnshHandler.SendMessage(cmd, blockingStub);
                break;
            case "str2et":
                response = GrpcStr2EtHandler.SendMessage(cmd, blockingStub);
                break;
            default:
                response = null;
        }

        return response;
    }
}

class GrpcFurnshHandler{
    static ParResponse SendMessage(ParCommand cmd, ParSpiceGrpc.ParSpiceBlockingStub stub){
        // put request into proto stuct
        FurnshBundle.Builder bundleBuilder = FurnshBundle.newBuilder();
        for (Object o : cmd.getArg()) {
            FurnshObject o1 = (FurnshObject)(o);
            bundleBuilder.addRequests(FurnshReq.newBuilder()
                    .setFile(o1.arg1)
                    .build());
        }
        FurnshBundle bundle = bundleBuilder.build();

        // send off with stub
        ParResponse results = new ParResponse();

        try{
            FurnshRep response = stub.parFurnsh(bundle);
            results = new ParResponse(response.getFileList());
        }
        catch (StatusRuntimeException e) {
           System.out.println(e.getStatus());
        }

        // compose results
        return results;
    }
}

class GrpcStr2EtHandler{
    static ParResponse SendMessage(ParCommand cmd, ParSpiceGrpc.ParSpiceBlockingStub stub){
        // put request into proto stuct
        Str2EtBundle.Builder bundleBuilder = Str2EtBundle.newBuilder();
        for (Object o : cmd.getArg()) {
            Str2EtObject o1 = (Str2EtObject)(o);
            bundleBuilder.addRequests(Str2EtReq.newBuilder()
                    .setTime(o1.arg1)
                    .build());
        }
        Str2EtBundle bundle = bundleBuilder.build();

        // send off with stub
        ParResponse results;
        try{
            Str2EtRep response = stub.parStr2Et(bundle);
            results = new ParResponse(response.getTimeList());
        }
        catch (StatusRuntimeException e) {
            System.out.println(e.getStatus());
            results = null;
        }

        // compose results
        return results;
    }
}

class GrpcSpkposHandler{
    static ParResponse SendMessage(ParCommand cmd, ParSpiceGrpc.ParSpiceBlockingStub stub) {
        // put request into proto stuct
        SpkposBundle.Builder bundleBuilder = SpkposBundle.newBuilder();
        for (Object o : cmd.getArg()) {
            SpkposObject o1 = (SpkposObject)(o);
            // all named after jnispice documentation
            bundleBuilder.addRequests(SpkposReq.newBuilder()
                    .setTarget(o1.arg1)
                    .setEt(o1.arg2)
                    .setRef(o1.arg3)
                    .setAbcorr(o1.arg4)
                    .setObserver(o1.arg5)
                    .addAllPos(o1.arg6)
                    .addAllLt(o1.arg7)
                    .build());
        }
        SpkposBundle bundle = bundleBuilder.build();

        // send off with stub
        ParResponse results;
        try{
            SpkposRep response = stub.parSpkpos(bundle);
            results = new ParResponse(response.getTimeList());
        }
        catch (StatusRuntimeException e) {
            System.out.println(e.getStatus());
            results = null;
        }

        // compose results
        return results;
    }
}