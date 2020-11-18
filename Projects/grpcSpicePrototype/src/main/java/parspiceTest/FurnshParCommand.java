package parspiceTest;
import java.util.ArrayList;

public class FurnshParCommand extends ParCommand {

    private ArrayList args = new ArrayList<String>();

    public FurnshParCommand(){
        name = "furnsh";
    }

    public void addArg(String arg){
        args.add(new FurnshObject(arg));
        return;
    }

    public ArrayList getArg(int index){
        ArrayList arg = new ArrayList();
        arg.add(args.get(index).toString());
        return (arg);
    }

    public ArrayList getArg(){
        return (args);
    }

    public ParResponse distribute(){
        return new ParClientCmdHandler().ParProcess(this);
    }
}
