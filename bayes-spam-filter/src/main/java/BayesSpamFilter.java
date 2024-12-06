import java.io.*;
import java.util.*;
import java.util.zip.*;


public class BayesSpamFilter {

    private static final String filePath = "bayes-spam-filter/src/main/resources/";

    private Map<String, Integer> hamWordCount;
    private Map<String, Integer> spamWordCount;
    private int hamMailCount;
    private int spamMailCount;

    private double alpha; 
    private double threshold;
    private double hamDetectionRate;
    private double spamDetectionRate;


    public BayesSpamFilter(){
        this.hamWordCount = new HashMap<>();
        this.spamWordCount = new HashMap<>();
        this.hamMailCount = 0;
        this.spamMailCount = 0;

        this.alpha = 0.01;
        this.threshold = 0.5;
        this.hamDetectionRate = 0.0;
        this.spamDetectionRate = 0.0;
    }


    // Training

    public void train(String hamFile, String spamFile) {
    
       try {

            List<String> hamEmails = extractZipFile(filePath + hamFile);
            List<String> spamEmails = extractZipFile(filePath + spamFile);

            for (String email : hamEmails) {
                readEmails(email, true); // Ham
            }

            for (String email : spamEmails) {
                readEmails(email, false); // Spam
            }

        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }


    // Kalibrierung

    public void calibrate(String hamFile, String spamFile)  {
       
        try {

            double bestAlpha = 0.01;
            double bestThreshold = 0.5;
            double bestAccuracy = 0.0;

            for (double a = 0.001; a <= 0.1; a += 0.01) {
                for (double t = 0.4; t <= 0.6; t += 0.05) {
                    this.alpha = a;
                    this.threshold = t;

                    double accuracy = calculateAccuracy(hamFile, spamFile);
                    if (accuracy > bestAccuracy) {
                        bestAccuracy = accuracy;
                        bestAlpha = a;
                        bestThreshold = t;
                    }
                }
            }

            this.alpha = bestAlpha;
            this.threshold = bestThreshold;

        } 
        catch (IOException e) {
            e.printStackTrace();
        }

    }


    // Test

    public void test(String hamFile, String spamFile)  {
        
        try {
            calculateAccuracy(hamFile, spamFile);
        } 
        catch (IOException e) {
            e.printStackTrace();
        }

    }


    public void summary(){
        System.out.println("----------------------------");
        System.out.println("Zusammenfassung:");
        System.out.println("----------------------------");
        System.out.println("Alpha-Wert: " + alpha);
        System.out.println("Schwellenwert: " + threshold);
        System.out.println("Erkennungsrate Ham: " + hamDetectionRate + "%");
        System.out.println("Erkennungsrate Spam: " + spamDetectionRate + "%");
        System.out.println("----------------------------");
    }


    // ZIP-Datei entpacken

    private List<String> extractZipFile(String fileName) throws IOException  {
        
        List<String> emails = new ArrayList<>();
        ZipFile zipFile = new ZipFile(fileName);
        Enumeration<? extends ZipEntry> entries = zipFile.entries();

        while (entries.hasMoreElements()) {
            ZipEntry entry = entries.nextElement();
            if (!entry.isDirectory()) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(zipFile.getInputStream(entry)));
                StringBuilder email = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    email.append(line).append(" ");
                }
                emails.add(email.toString());
            }
        }
        zipFile.close();
        return emails;

    }


    // E-Mail auswerten

    private void readEmails(String email, boolean isHam) {
        
        String[] words = email.split("\\s+");
        Set<String> uniqueWords = new HashSet<>(Arrays.asList(words));

        if (isHam) {
            hamMailCount++;
            for (String word : uniqueWords) {
                hamWordCount.put(word, hamWordCount.getOrDefault(word, 0) + 1);
            }
        } else {
            spamMailCount++;
            for (String word : uniqueWords) {
                spamWordCount.put(word, spamWordCount.getOrDefault(word, 0) + 1);
            }
        }

    }


    // Überprüfung auf SPAM

    public boolean isSpam(String email) {
        double probability = calculateSpamProbability(email);
        return probability >= threshold;
    }


    // Genauigkeit berechnen

    private double calculateAccuracy(String hamFile, String spamFile) throws IOException {
        
        List<String> hamEmails = extractZipFile(filePath + hamFile);
        List<String> spamEmails = extractZipFile(filePath + spamFile);

        int hamCorrect = 0, spamCorrect = 0;

        for (String email : hamEmails) {
            if (!isSpam(email)) hamCorrect++;
        }

        for (String email : spamEmails) {
            if (isSpam(email)) spamCorrect++;
        }

        hamDetectionRate = (double) hamCorrect / hamEmails.size() * 100;
        spamDetectionRate = (double) spamCorrect / spamEmails.size() * 100;

        return (hamDetectionRate + spamDetectionRate) / 2.0;
       
    }


    // Spamwahrscheinlichkeit berechnen

    private double calculateSpamProbability(String email) {

        String[] words = email.split("\\s+");
        double spamLogProbability = Math.log((double) spamMailCount / (spamMailCount + hamMailCount));
        double hamLogProbability = Math.log((double) hamMailCount / (spamMailCount + hamMailCount));

        for (String word : words) {
            int spamCount = spamWordCount.getOrDefault(word, 0);
            int hamCount = hamWordCount.getOrDefault(word, 0);

            double wordSpamProbability = (spamCount + alpha) / (spamMailCount + 2 * alpha);
            double wordHamProbability = (hamCount + alpha) / (hamMailCount + 2 * alpha);

            spamLogProbability += Math.log(wordSpamProbability);
            hamLogProbability += Math.log(wordHamProbability);
        }

        return 1 / (1 + Math.exp(hamLogProbability - spamLogProbability));

    }

}