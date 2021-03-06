package parspiceTest;

import java.util.ArrayList;

public class Str2EtParCommand extends ParCommand {

    private ArrayList args = new ArrayList<Str2EtObject>();

    public Str2EtParCommand(){
        name = "str2et";
    }

    public void addArg(String arg){
        args.add(new Str2EtObject(arg));
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
