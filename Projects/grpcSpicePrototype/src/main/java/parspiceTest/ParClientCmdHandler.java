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
                System.out.println("c1");
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
        System.out.println(bundle.getRequests(0).getFile());

        // send off with stub
        try{
            stub.parFurnsh(bundle);
        }
        catch (StatusRuntimeException e) {
           System.out.println(e.getStatus());
           //logger.log(Level.WARNING, "RPC failed: {0}", e.getStatus());
        }

        // compose results
        ParResponse results = new ParResponse();
        return results;
    }
}

class GrpcStr2EtHandler{
    static ParResponse SendMessage(ParCommand cmd, ParSpiceGrpc.ParSpiceBlockingStub stub){
        int i = 0;

        // put into proto stuct
        for (Object s : cmd.getArg()) {
            String o = String.valueOf(s);
            System.out.println(o);
        }

        // send off with stub
        return null;
    }
}

class GrpcSpkposHandler{
    static ParResponse SendMessage(ParCommand cmd, ParSpiceGrpc.ParSpiceBlockingStub stub){
        // put into proto stuct
        for (Object s : cmd.getArg()) {

            String[] o = (String[])s;
            System.out.println(o);
        }

        // send off with stub
        return null;
    }
}