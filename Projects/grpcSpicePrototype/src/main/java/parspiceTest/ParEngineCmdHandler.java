package parspiceTest;

import spice.basic.CSPICE;

public class ParEngineCmdHandler {
    public boolean FurnshHandler(FurnshObject o){
        try{
            CSPICE.furnsh(System.getProperty("user.dir") + "\\" + o.arg1);
        }
        catch (Exception e){
            System.out.println(e);
            return false;
        }

        return true;
    }
}
