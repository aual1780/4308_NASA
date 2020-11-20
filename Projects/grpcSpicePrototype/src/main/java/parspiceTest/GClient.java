package parspiceTest;

public class GClient {
    public static void main(String[] args) throws Exception {
        //test parcommand
        FurnshParCommand furnshTest = new FurnshParCommand();
        furnshTest.addArg("convtm.tm");
        ParResponse results = furnshTest.distribute();
        System.out.println(results.get().get(0));

        Str2EtParCommand str2etTest = new Str2EtParCommand();
        str2etTest.addArg("2004 jun 11 19:32:00");
        results = str2etTest.distribute();
        System.out.println(results.get().get(0));
    }
}
