package parspiceTest;
import java.util.ArrayList;

public class ParResponse {
    private ArrayList results = new ArrayList<Double>();

    public void add(Double input){
        results.add(input);
    }

    public ArrayList<Double> get(){
        return results;
    }
}
