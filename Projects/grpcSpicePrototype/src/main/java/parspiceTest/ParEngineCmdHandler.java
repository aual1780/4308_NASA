package parspiceTest;

import spice.basic.CSPICE;

public class ParEngineCmdHandler {
    public boolean FurnshHandler(FurnshObject o){
        try{
            CSPICE.furnsh(o.arg1);
        }
        catch (Exception e){
            System.out.println(e);
            return false;
        }

        return true;
    }
}
