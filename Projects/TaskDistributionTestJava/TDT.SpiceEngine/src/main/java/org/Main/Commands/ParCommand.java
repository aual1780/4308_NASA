package org.Main.Commands;

import org.Main.Worker.WorkerPool;

import java.util.ArrayList;
public abstract class ParCommand{

    private ArrayList args; //ArrayLists chosen due to their thread-safe nature
    protected String name;
    public String getFuncInfo(){return name;};
    public abstract void addArg(String arg);
    public abstract ArrayList getArg(int index);
    public abstract ArrayList getArg();
    public abstract ParResponse distribute(WorkerPool pool) throws Throwable;

    //https://github.com/aual1780/4308_NASA/blob/master/Projects/Tutorials/src/Main.java
}
