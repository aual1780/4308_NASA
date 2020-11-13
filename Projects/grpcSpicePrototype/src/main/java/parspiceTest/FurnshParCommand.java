package parspiceTest;
import java.util.*;
import spice.basic.CSPICE;
import spice.basic.SpiceErrorException;

public class FurnshParCommand extends ParCommand {
    public String name = "furnsh";
    private List args = new ArrayList();

    public void addArg(String arg){
        args.add(arg);
        return;
    }

    public ArrayList getArg(int index){
        ArrayList arg = new ArrayList();
        arg.add(args.get(index).toString());
        return (arg);
    }

    public void distribute(){
        return;
    }
}
