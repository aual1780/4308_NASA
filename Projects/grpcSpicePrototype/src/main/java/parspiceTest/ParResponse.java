package parspiceTest;
import java.util.ArrayList;

public class ParResponse {
    private ArrayList results;
    private String status;

    public ParResponse(){
        results = new ArrayList<Double>();
        status = "";
    }

    public void add(Double input){
        results.add(input);
    }

    public ArrayList<Double> get(){
        return results;
    }
}
