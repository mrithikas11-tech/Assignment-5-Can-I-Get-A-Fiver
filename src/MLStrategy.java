import java.util.HashMap;
import java.util.Set;
import java.io.PrintWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class MLStrategy implements Strategy {

    private RoundSequence temp = new RoundSequence(null, null); // access to round results
    private HashMap<String, SequenceHashMap> storedSequence; // round sequence stored
    private final int N; // size of saved sequence
    private String key; // last round choices with N-1 length
    private String currentSequence; // last round choices with N length
    private Sign lastPrediction; // last predicted human choice

    private final RandomStrategy random;

    public MLStrategy() {
        random = new RandomStrategy();
        N = 5;
        storedSequence = new HashMap<>();
        loadFromFile("frequency_data.txt");
    }

    public MLStrategy(int seed) {
        random = new RandomStrategy(seed);
        N = 5;
        storedSequence = new HashMap<>();
        loadFromFile("frequency_data.txt");
    }

    public MLStrategy(RoundSequence seq, int N) {
        random = new RandomStrategy();
        temp = seq;
        this.N = N;
        storedSequence = new HashMap<>();
        loadFromFile("frequency_data.txt");
    }

    public MLStrategy(int seed, RoundSequence seq, int N) {
        random = new RandomStrategy(seed);
        temp = seq;
        this.N = N;
        storedSequence = new HashMap<>();
        loadFromFile("frequency_data.txt");
    }

    @Override
    public Sign makeMove() {
        storeNSequence();
        if (temp.isEmpty()) {
            return random.makeMove();
        } else {
            return makePrediction();
        }
    }

    /**
     * Record last round choices add them to hashmap and add frequency count
     */
    public void storeNSequence() {
        if (temp.isLengthGreater(N)) {
            setKey();
            setCurrentSequence();
            if (storedSequence.containsKey(key)) {
                SequenceHashMap theMap = storedSequence.get(key);
                if (theMap.hasKey(currentSequence)) {
                    theMap.addFreq(currentSequence);
                } else {
                    theMap.addHashMapSequence(currentSequence);
                }
            } else {
                storedSequence.put(key, new SequenceHashMap(currentSequence));
            }
        }
    }

    /**
     * Record last round choices add them to hashmap and add frequency count
     */
    public void storeNSequence(String sequence, HashMap<String, SequenceHashMap> newMap) {
        String mapKey = sequence.substring(0, sequence.length() - 1);
        if (newMap.containsKey(mapKey)) {
            SequenceHashMap theMap = storedSequence.get(mapKey);
            if (theMap.hasKey(sequence)) {
                theMap.addFreq(sequence);
            } else {
                theMap.addHashMapSequence(sequence);
            }
        } else {
            newMap.put(mapKey, new SequenceHashMap(sequence));
        }
    }

    /**
     * Update last round choice throw out old choices size N
     */
    public void setCurrentSequence() {
        currentSequence = temp.getNSizeSequence(N);
    }

    /**
     * Key is sequence of current round size N-1
     */
    public void setKey() {
        String currSequence;
        currSequence = temp.getRoundSequence();
        key = currSequence.substring(startIndex(), lastIndex());
    }

    /**
     * Find the start of the sequence
     * 
     * @return
     */
    public int startIndex() {
        String currSequence;
        currSequence = temp.getRoundSequence();
        int startIndex;
        if (currSequence.isEmpty()) {
            startIndex = 0;
        } else {
            startIndex = currSequence.length() - N - 1;
        }
        return startIndex;
    }

    /**
     * end of the sequence
     * 
     * @return
     */
    public int lastIndex() {
        String currSequence;
        currSequence = temp.getRoundSequence();
        int lastIndex;
        if (currSequence.isEmpty() || currSequence.length() < N) {
            return currSequence.length();
        } else {
            lastIndex = currSequence.length() - 2;
            return lastIndex;
        }
    }

    /**
     * The algo
     * 
     * @return
     */
    public Sign makePrediction() {
        if (temp.isLengthEqualN(N) && key != null) {
            if (storedSequence.containsKey(key)) {
                Sign predictionSign = getMostFreqSeq(key);
                lastPrediction = predictionSign;
                return predictionSign.loseTo();
            } else {
                lastPrediction = null;
                return random.makeMove();
            }
        }
        lastPrediction = null;
        return random.makeMove();
    }

    /**
     * Get last prediction for GUI display
     * 
     * @return predicted Sign, or null if prediction was random
     */
    public Sign getLastPrediction() {
        return lastPrediction;
    }

    /**
     * Get the most frequent pattern
     * 
     * @param lastChoices the sequence of the last rounds String size N-1
     * @return
     */
    public Sign getMostFreqSeq(String lastChoices) {
        String mostSeq;
        SequenceHashMap list = storedSequence.get(lastChoices);
        mostSeq = list.getMostFreqSequence();
        if (mostSeq == null) {
            return random.makeMove();
        } else {
            String signToPredict = mostSeq.substring(mostSeq.length() - 1);
            return Sign.getSignFromString(signToPredict);
        }
    }

    /**
     * UNFINSHED, makes a another hashmap with a different size N, currently just
     * takes the existing saved
     * round results and make a new hashmap. Does not add new individuals rounds to
     * map nor predict the next
     * round
     * 
     * @param n
     * @return
     */
    public HashMap<String, SequenceHashMap> storeSequenceWithDifferentN(int n) {
        HashMap<String, SequenceHashMap> newMap = new HashMap<>();
        String totalSequence = temp.getRoundSequence();
        String sequenceSizeN;
        for (int i = 0; i < totalSequence.length() - 2 - n; i = i + 2) {
            sequenceSizeN = totalSequence.substring(i, i + n - 1);
            storeNSequence(sequenceSizeN, newMap);
        }
        return newMap;
    }

    // Testing delete later
    public void printKeyS() {
        System.out.println("Key " + key + " Seq " + currentSequence);
    }

    /**
     * Checker
     */
    public void printAll() {
        System.out.println("Total sequence   " + temp.getRoundSequence());
        Set<String> allPatternkey = storedSequence.keySet();
        System.out.println("For Size N=" + N);
        for (String str : allPatternkey) {
            System.out.print("Key: " + str + " Sequences> ");
            storedSequence.get(str).printSequenceFrequency();
        }
    }

    /**
     * Saves frequency table to a file so ML data persists across games
     * 
     */
    public void saveToFile(String filename) {
        try {
            PrintWriter writer = new PrintWriter(filename);
            Set<String> outerKeys = storedSequence.keySet();
            for (String outerKey : outerKeys) {
                SequenceHashMap innerMap = storedSequence.get(outerKey);
                HashMap<String, Integer> rawMap = innerMap.getaSeqHashMap();
                Set<String> innerKeys = rawMap.keySet();
                for (String innerKey : innerKeys) {
                    int freq = rawMap.get(innerKey);
                    writer.println(outerKey + "," + innerKey + "," + freq);
                }
            }
            writer.close();
        } catch (FileNotFoundException e) {
            System.out.println("Could not save frequency data: " + e.getMessage());
        }
    }

    public void loadFromFile(String filename) {
        File file = new File(filename);
        if (!file.exists()) {
            return;
        }
        try {
            Scanner fileScanner = new Scanner(file);
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine().trim();
                if (line.isEmpty()) {
                    continue;
                }
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    String outerKey = parts[0];
                    String innerKey = parts[1];
                    int freq = Integer.parseInt(parts[2]);
                    if (storedSequence.containsKey(outerKey)) {
                        SequenceHashMap innerMap = storedSequence.get(outerKey);
                        for (int i = 0; i < freq; i++) {
                            innerMap.addFreq(innerKey);
                        }
                    } else {
                        SequenceHashMap innerMap = new SequenceHashMap(innerKey);
                        for (int i = 1; i < freq; i++) {
                            innerMap.addFreq(innerKey);
                        }
                        storedSequence.put(outerKey, innerMap);
                    }
                }
            }
            fileScanner.close();
        } catch (FileNotFoundException e) {
            // File doesn't exist
        }
    }
}
