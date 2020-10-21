package org.Main;

import SpiceServices.mxvService;
import com.typesafe.config.Config;
import spice.basic.CSPICE;
import spice.basic.SpiceErrorException;
import com.typesafe.config.ConfigFactory;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;

import java.io.File;
import java.util.Arrays;
import java.util.List;

public class App {

    public static void main(String[] args) throws Exception {
        Config conf = ConfigFactory.load("SpiceWorker.conf");
        List<String> spiceNativePaths = conf.getStringList("SpiceLib.NativeExecPaths");
        File nativeFile = null;
        for(String path : spiceNativePaths)
        {
            nativeFile = new File(path);
            if(nativeFile.exists())
            {
                break;
            }
            else
            {
                nativeFile = null;
            }
        }
        if(nativeFile == null)
        {
            throw new Exception("Cannot find any of the specified SPICE native files");
        }

        System.out.println(nativeFile.getAbsolutePath());
        System.load(nativeFile.getAbsolutePath());

        //CSPICE cspice = new CSPICE();

        int serverPort = 30672;
        try
        {
            serverPort = Integer.parseInt(args[0]);
        }
        catch(Exception ex)
        {
            //noop
        }

        //Server rpcServer = ServerBuilder.forPort(serverPort)
        //        .addService(new mxvService(cspice))
        //        .build()
        //        .start();

        File f = new File("C://users/aalbert/Desktop/asd.txt");
        f.createNewFile();


        // testing cspice function
        double[][] m = new double[][]{
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        double[] v = new double[]{ 1, 2, 3};
        double[] result;
        //try
        //{
        //    result = cspice.mxv(m, v);
        //} catch (SpiceErrorException e) {
        //    System.out.println(e.getMessage());
        //    return;
        //}
        //System.out.println(Arrays.toString(result));


        //if (rpcServer != null) {
       //     rpcServer.awaitTermination();
        //}
    }
}
