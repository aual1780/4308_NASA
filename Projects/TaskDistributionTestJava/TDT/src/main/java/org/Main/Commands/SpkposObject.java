package org.Main.Commands;
import java.util.ArrayList;

public class SpkposObject implements ParObject {
    public String arg1;
    public Double arg2;
    public String arg3;
    public String arg4;
    public String arg5;
    public ArrayList<Double> arg6;
    public ArrayList<Double> arg7;

    public SpkposObject(){
        arg1 = "arg1";
        arg2 = 0.0;
        arg3 = "arg3";
        arg4 = "arg4";
        arg5 = "arg5";
        arg6 = new ArrayList<Double>();
        arg7 = new ArrayList<Double>();
    }

    public SpkposObject(String a1, Double a2, String a3, String a4, String a5, ArrayList<Double> a6, ArrayList<Double> a7){
        arg1 = a1;
        arg2 = a2;
        arg3 = a3;
        arg4 = a4;
        arg5 = a5;
        arg6 = a6;
        arg7 = a7;
    }
}