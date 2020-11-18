package parspiceTest;

public class GClient {
    public static void main(String[] args) throws Exception {
        //String timeInput = "2004 jun 11 19:32:00";
        //String target = "localhost:50051";

        //test parcommand
        FurnshParCommand furnshTest = new FurnshParCommand();
        furnshTest.addArg("convtm.tm");
        furnshTest.addArg("kernel2");
        System.out.println("Info: " + furnshTest.getFuncInfo());
        ParResponse results = furnshTest.distribute();
    }
}
