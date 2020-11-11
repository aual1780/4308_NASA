import spice.basic.SpiceErrorException;

public interface Benchmark {
    void doLocal() throws SpiceErrorException;
    void doSeriesRemote();
    void doConcurrentRemote();
}
