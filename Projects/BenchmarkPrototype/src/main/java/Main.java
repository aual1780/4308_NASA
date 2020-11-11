import spice.basic.SpiceErrorException;

public class Main {
    public static void main(String[] args) throws SpiceErrorException {
        System.load("/usr/local/JNISpice/lib/libJNISpice.so");
        int iterations = 100000;
        int workers = 4;
        Benchmark bench = new InSituSensing(iterations, workers);
        System.out.println("start");
        System.out.flush();
        bench.doLocal();
        System.out.println("end");
    }

    public static void singleLocal(int iter) throws SpiceErrorException {

    }
}
