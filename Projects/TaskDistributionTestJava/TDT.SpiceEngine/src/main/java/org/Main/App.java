package org.Main;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

import java.io.File;

public class App {

    public static void main(String[] args) throws Exception {
        Config conf = ConfigFactory.load("SpiceEngine.conf");
        String subenginePath = conf.getString("ParSpice.SubEngine.Path");
        int initPort = conf.getInt("ParSpice.SubEngine.InitPort");
        File subengineJar = new File(subenginePath);

        String engineStartCmd = "java -jar \"" + subengineJar.getAbsolutePath() + "\" \"" + initPort + "\"";

        Process serverProc = Runtime.getRuntime().exec(engineStartCmd);

        System.out.println(subengineJar.getAbsolutePath());
        System.out.println(engineStartCmd);




    }
}
