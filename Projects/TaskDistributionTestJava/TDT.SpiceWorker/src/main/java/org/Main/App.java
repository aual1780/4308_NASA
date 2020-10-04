package org.Main;

import com.typesafe.config.Config;
import spice.basic.CSPICE;
import spice.basic.SpiceErrorException;
import com.typesafe.config.ConfigFactory;
import java.util.Arrays;

public class App {

    public static void main(String[] args)
    {
        Config conf = ConfigFactory.load("SpiceWorker.conf");
        String spiceNativePath = conf.getString("SpiceLib.NativeExecPath");
        System.load(spiceNativePath);

        CSPICE cspice = new CSPICE();
        // testing cspice function
        double[][] m = new double[][]{
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        double[] v = new double[]{ 1, 2, 3};
        double[] result;
        try
        {
            result = cspice.mxv(m, v);
        } catch (SpiceErrorException e) {
            System.out.println(e.getMessage());
            return;
        }
        System.out.println(Arrays.toString(result));
    }
}
