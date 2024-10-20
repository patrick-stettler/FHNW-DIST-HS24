public class App {
    public static void main(String[] args) {
        
        SpamFilter filter = new SpamFilter();

        filter.train("ham-anlern.zip", "spam-anlern.zip");
        filter.calibrate("ham-kallibrierung.zip", "spam-kallibrierung.zip");
        filter.test("ham-test.zip", "spam-test.zip");
        filter.summary();
        
    }
}
