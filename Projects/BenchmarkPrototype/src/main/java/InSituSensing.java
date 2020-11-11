import spice.basic.CSPICE;
import spice.basic.SpiceErrorException;

public class InSituSensing implements Benchmark {
    int iterations;
    int workers;

    public InSituSensing(int i, int w) {
        iterations = i;
        workers = w;
    }

    @Override
    public void doLocal() throws SpiceErrorException {
        String mkfile;
        String utc;
        String sclk;
        String target;
        String frame;
        String corrtn;
        String  observ;
        String  method;
        String  fromfr;
        String  tofr;

        double et;
        double[] state = new double[6];
        double[] sundir = new double[3];
        double[] ltime = new double[1];
        double[] spoint = new double[3];
        double[] trgepc = new double[1];
        double[] srfvec = new double[3];
        double[] sbpdir;
        double[][] m2imat;
        double[] scvdir;
        double[][] j2imat;

        int scid;

        mkfile  =  "scvel.tm";
        CSPICE.furnsh ( mkfile );

        for (int i = 0; i < iterations; i++) {

            utc = "2004-06-11T19:32:00";
            et = CSPICE.str2et(utc);


            scid = -82;
            sclk = "1465674964.105";
            et = CSPICE.scs2e(scid, sclk);

            target = "CASSINI";
            frame = "ECLIPJ2000";
            corrtn = "NONE";
            observ = "SUN";

            CSPICE.spkezr(target, et, frame, corrtn, observ, state, ltime);

            target = "SUN";
            frame = "CASSINI_INMS";
            corrtn = "LT+S";
            observ = "CASSINI";

            CSPICE.spkpos(target, et, frame, corrtn, observ, sundir, ltime);
            sundir = CSPICE.vhat(sundir);

            method = "NEAR POINT: ELLIPSOID";
            target = "PHOEBE";
            frame = "IAU_PHOEBE";
            corrtn = "NONE";
            observ = "CASSINI";

            CSPICE.subpnt(method, target, et, frame, corrtn, observ,
                    spoint, trgepc, srfvec);

            double[] ans = CSPICE.reclat(spoint);

            fromfr = "IAU_PHOEBE";
            tofr = "CASSINI_INMS";

            m2imat = CSPICE.pxform(fromfr, tofr, et);

            sbpdir = CSPICE.mxv(m2imat, srfvec);
            sbpdir = CSPICE.vhat(sbpdir);

            target = "CASSINI";
            frame = "J2000";
            corrtn = "NONE";
            observ = "PHOEBE";

            CSPICE.spkezr(target, et, frame, corrtn, observ, state, ltime);
            scvdir = new double[]{state[3], state[4], state[5]};

            fromfr = "J2000";
            tofr = "CASSINI_INMS";
            j2imat = CSPICE.pxform(fromfr, tofr, et);

            scvdir = CSPICE.mxv(j2imat, scvdir);
            scvdir = CSPICE.vhat(scvdir);
        }
    }

    @Override
    public void doSeriesRemote() {
        // set up one worker
        submitRemoteJobs();
    }

    @Override
    public void doConcurrentRemote() {
        // set up workers
        submitRemoteJobs();
    }

    void submitRemoteJobs() {

    }
}
