package SpiceServices;

//import org.Main.Spice.Services.Mxv.

import io.grpc.stub.StreamObserver;
import org.Main.Spice.Primitives.Double.DblArray;
import org.Main.Spice.Primitives.Double.DblArray2D;
import org.Main.Spice.Services.Mxv.MxvRequest;
import org.Main.Spice.Services.Mxv.MxvResponse;
import org.Main.Spice.Services.Mxv.MxvResponseOrBuilder;
import org.Main.Spice.Services.Mxv.MxvServiceGrpc;
import spice.basic.CSPICE;
import spice.basic.SpiceErrorException;

public class mxvService extends MxvServiceGrpc.MxvServiceImplBase {

    CSPICE _spice;
    public mxvService(CSPICE spice) throws Exception {
        if(spice == null)
        {
            throw new Exception("spice cannot be null");
        }
        _spice = spice;
    }

    @Override
    public void calculate(MxvRequest request, StreamObserver<MxvResponse> responseObserver) {

        double[][] mCache = new double[][]{
                {0, 0, 0},
                {0, 0, 0},
                {0, 0, 0}
        };
        double[] vCache = new double[]{ 0, 0, 0};
        double[] resultCache;

        DblArray2D mRequest = request.getDoubles();
        DblArray vRequest = request.getDoubles1();
        for(int i = 0; i < mRequest.getElementsCount(); ++i)
        {
            DblArray innerArr = mRequest.getElements(i) ;
            for(int j = 0; j < innerArr.getElementsCount(); ++j)
            {
                mCache[i][j] = innerArr.getElements(j);
            }
        }

        try
        {
            resultCache = _spice.mxv(mCache, vCache);
        } catch (SpiceErrorException e) {
            System.out.println(e.getMessage());
            return;
        }

        MxvResponse.Builder replyBuilder = MxvResponse.newBuilder();
        for(int i = 0; i < resultCache.length; ++i)
        {
            replyBuilder.getDoublesOrBuilder().getElementsList().add(resultCache[i]);
        }
        MxvResponse reply = replyBuilder.build();

        responseObserver.onNext(reply);
        responseObserver.onCompleted();
    }
}
