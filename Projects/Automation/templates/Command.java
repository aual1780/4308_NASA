package parspiceTest;

import java.util.ArrayList;

public class ###UPPER_NAME###Command extends ParCommand {

    private ArrayList args = new ArrayList<###UPPER_NAME###Object>();

    public ###UPPER_NAME###Command(){
        name = "###LOWER_NAME###";
    }

    public void addArg(###ARGS###){
        args.add(new ###UPPER_NAME###Object(###ARGS_NO_TYPES###));
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