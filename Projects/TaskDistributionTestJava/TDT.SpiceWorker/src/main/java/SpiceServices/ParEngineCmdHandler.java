package SpiceServices;

import spice.basic.CSPICE;
import org.Main.Commands.*;

// The structure of the handling functions should all be very similar
public class ParEngineCmdHandler {
    public double FurnshHandler(FurnshObject o){
        try{
            CSPICE.furnsh(System.getProperty("user.dir") + "\\" + o.arg1); // client and engine/worker share the same directory
        }
        catch (Exception e){
            System.out.println(e); // temp solution for error handling
            return -0.0;
        }

        return 1.0;
    }

    public double Str2EtHandler(Str2EtObject o){
        try{
            return CSPICE.str2et(o.arg1);
        }
        catch (Exception e){
            System.out.println(e);
            return -0.0;
        }
    }
}