import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Random;


public class FilterApp {

    public static void main(String[] args) throws Exception {
        
        int n = 1000; // erwartete Anzahl von Elementen
        double p = 0.01; // Fehlerwahrscheinlichkeit

        BloomFilter bloomFilter = new BloomFilter(n, p);
        List<String> words = Files.readAllLines(Paths.get("bloom-filter/src/main/resources/words.txt"));
        for (String word : words) {
            bloomFilter.add(word);
        }

        // Fehlerrate testen
        int testCount = 10000;
        int falsePositives = 0;
        Random random = new Random();

        for (int i = 0; i < testCount; i++) {
            String randomWord = randomString(random, 10);
            if (!words.contains(randomWord) && bloomFilter.mightContain(randomWord)) {
                falsePositives++;
            }
        }

        // Ergebnisse ausgeben
        System.out.println("Erwartete Fehlerrate: " + p);
        System.out.println("Experimentelle Fehlerrate: " + (falsePositives / (double) testCount));
        System.out.println("Filtergröße (m): " + bloomFilter.getBitSetSize());
        System.out.println("Anzahl der Hashfunktionen (k): " + bloomFilter.getNumHashFunctions());
        
    }


    // zufälligeZeichenkette generieren
    private static String randomString(Random random, int length) {
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            sb.append(alphabet.charAt(random.nextInt(alphabet.length())));
        }
        return sb.toString();        
    }

}
