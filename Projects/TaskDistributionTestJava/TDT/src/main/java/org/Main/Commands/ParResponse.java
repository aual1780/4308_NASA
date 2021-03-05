package org.Main.Commands;

import java.util.ArrayList;
import java.util.List;

public class ParResponse {
    private ArrayList<Double> results;
    private String status;

    public ParResponse(){
        results = new ArrayList<Double>();
        status = "";
    }

    public ParResponse(List a){
        results = new ArrayList<Double>();
        for(Object e : a){
            Double element = (Double)e;
            results.add(element);
        }
        status = "";
    }

    public void add(Double input){
        results.add(input);
    }

    public ArrayList<Double> get(){
        return results;
    }
}
