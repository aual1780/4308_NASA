import spice.basic.CSPICE;
import spice.basic.SpiceErrorException;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SpiceErrorException {
        System.load("/usr/local/JNISpice/lib/libJNISpice.so");
        tutorial1();
        tutorial2();
        otherFunctions();
    }

    /*
    This is a translation of the "Time Conversion" tutorial task from the "remote_sensing" lession.
     */
    private static void tutorial1() throws SpiceErrorException {
        System.out.println("\n\nTUTORIAL 1");

        final String METAKR = "convtm.tm";
        final int SCLKID = -82;
        final int STRLEN = 50;

        String calet, sclkst, utctim;

        double et = 0;

        CSPICE.furnsh(METAKR);

        Scanner reader = new Scanner(System.in);
        System.out.println("Input UTC Time: ");
        utctim = "2004 jun 11 19:32:00";
        System.out.printf("Converting UTC Time %s\n%n",utctim);

        et = CSPICE.str2et(utctim);

        System.out.printf("ET Seconds past j2000: %16.3f\n", et);

        calet = CSPICE.timout(et, "YYYY-MON-DDTHR:MN:SC ::TDB");

        System.out.printf("Calendar ET (timout): %s\n", calet);

        sclkst = CSPICE.sce2s(SCLKID, et);

        System.out.printf("Spacecraft clock time: %s\n", sclkst);
    }

    /*
    This is a translation of the "Obtaining Target States and Positions" tutorial task from the "remote_sensing" lession.
     */
    private static void tutorial2() throws SpiceErrorException {
        System.out.println("\n\nTUTORIAL 2");

        final String METAKR = "getsta.tm";
        /*
      Local Variables
      */
        String utctim;

        double dist;
        double et;
        double[] ltime = new double[1];
        double[] pos = new double[3];
        double[] state = new double[6];

      /*
      Load the kernels that this program requires.  We
      will need a leapseconds kernel to convert input
      UTC time strings into ET.  We also will need the
      necessary SPK files with coverage for the bodies
      in which we are interested.
      */
        CSPICE.furnsh ( METAKR );

      /*
      Prompt the user for the input time string.
      */
        utctim = "2004 jun 11 19:32:00";

        System.out.printf( "Converting UTC Time: %s\n", utctim  );

      /*
      Convert utctim to ET.
      */
        et = CSPICE.str2et(utctim);

        System.out.printf( "   ET seconds past J2000: %16.3f\n", et );

      /*
      Compute the apparent state of Phoebe as seen from
      CASSINI in the J2000 frame.  All of the ephemeris
      readers return states in units of kilometers and
      kilometers per second.
      */
        CSPICE.spkezr( "PHOEBE", et,    "J2000", "LT+S",
                "CASSINI",  state, ltime);

        System.out.printf ( "   Apparent state of Phoebe as seen from CASSINI in the J2000\n"        );
        System.out.printf ( "      frame (km, km/s):\n"          );
        System.out.printf ( "      X = %16.3f\n", state[0]       );
        System.out.printf ( "      Y = %16.3f\n", state[1]       );
        System.out.printf ( "      Z = %16.3f\n", state[2]       );
        System.out.printf ( "     VX = %16.3f\n", state[3]       );
        System.out.printf ( "     VY = %16.3f\n", state[4]       );
        System.out.printf ( "     VZ = %16.3f\n", state[5]       );

      /*
      Compute the apparent position of Earth as seen from
      CASSINI in the J2000 frame.  Note: We could have
      continued using spkezr_c and simply ignored the
      velocity components.
      */
        CSPICE.spkpos ( "EARTH", et,  "J2000", "LT+S",
                "CASSINI",   pos, ltime             );

        System.out.printf ( "   Apparent position of Earth as seen from CASSINI in the J2000\n"     );
        System.out.printf ( "      frame (km): \n"                 );
        System.out.printf ( "      X = %16.3f\n", pos[0]           );
        System.out.printf ( "      Y = %16.3f\n", pos[1]           );
        System.out.printf ( "      Z = %16.3f\n", pos[2]           );


      /*
      We need only display LTIME, as it is precisely the
      light time in which we are interested.
      */
        System.out.printf ( "   One way light time between CASSINI and the apparent position\n"                    );
        System.out.printf ( "      of Earth (seconds): %16.3f\n", ltime[0]  );

      /*
      Compute the apparent position of the Sun as seen
      from Phoebe in the J2000 frame.
      */
        CSPICE.spkpos ( "SUN",  et,  "J2000", "LT+S",
                "PHOEBE", pos, ltime                );

        System.out.print( "   Apparent position of Sun as seen from Phoebe in the\n"                 );
        System.out.print( "      J2000 frame (km): \n"           );
        System.out.printf ( "      X = %16.3f\n", pos[0]           );
        System.out.printf ( "      Y = %16.3f\n", pos[1]           );
        System.out.printf ( "      Z = %16.3f\n", pos[2]           );

      /*
      Now we need to compute the actual distance between
      the Sun and Phoebe.  The above SPKPOS call gives us
      the apparent distance, so we need to adjust our
      aberration correction appropriately.
      */
        CSPICE.spkpos ( "SUN",  et,  "J2000", "NONE",
                "PHOEBE", pos, ltime                );

      /*
      Compute the distance between the body centers in
      kilometers.
      */
        dist = CSPICE.vnorm ( pos );

      /*
      Convert this value to AU using convrt_c.
      */
        dist = CSPICE.convrt ( dist, "KM", "AU");

        System.out.printf ( "   Actual distance between Sun and Phoebe body centers:\n"               );
        System.out.printf ( "      (AU): %16.3f\n", dist           );
    }

    private static void otherFunctions() throws SpiceErrorException {
        System.out.println("\n\nOTHER FUNCTION CALLS");

        System.out.println(CSPICE.gfrefn(2, 3, false, true));
        double[][] mat = CSPICE.axisar(new double[]{2, 3, 4}, 1.5);
        System.out.println(Arrays.deepToString(mat));
        System.out.println(CSPICE.b1900());
        double[][] mat2 = CSPICE.drdcyl(3, 40, 5);
        System.out.println(Arrays.deepToString(mat2));
        System.out.println(Arrays.toString(CSPICE.dvcrss(new double[]{2, 3, 4, 5, 6, 7}, new double[]{0.5, 0.1, 1000, 20, 0.00034667, 1})));
        System.out.println(CSPICE.isrot(mat, 0.1, 0.1));
        System.out.println(CSPICE.isrot(mat2, 0.1, 0.1));
        CSPICE.clpool();
    }
}
