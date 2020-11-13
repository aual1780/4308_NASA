package parspiceTest;

import java.util.List;
import java.util.*;
public abstract class ParCommand{

    private ArrayList args; //ArrayLists chosen due to their thread-safe nature
    private String name;
    public String getFuncInfo(){
        return this.name;
    };
    public abstract void addArg(String arg);
    public abstract ArrayList getArg(int index);
    public int getNumArgs(){
        return args.size();
    }
    public abstract void distribute();
    
    //https://github.com/aual1780/4308_NASA/blob/master/Projects/Tutorials/src/Main.java
}