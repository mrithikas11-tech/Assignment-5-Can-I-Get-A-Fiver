package canigetafiver.rockpaperscissors;
import java.util.HashMap;
import java.util.Set;

public class MLStrategy implements Strategy {

    private RoundSequence temp = new RoundSequence(null, null); //access to round results
    private HashMap<String, SequenceHashMap> storedSequence;    //round sequence stored
    private final int N;                                        //size of saved sequence
    private String key;                                         //last round choices with N-1 length
    private String currentSequence;                             //last round choices with N length
    private Sign predictionSign;                                //predicted Human Sign

    private final RandomStrategy random;

    public MLStrategy() {
        random = new RandomStrategy();
        N = 5;
        storedSequence = new HashMap<>();
    }

    public MLStrategy(int seed) {
        random = new RandomStrategy(seed);
        N = 5;
        storedSequence = new HashMap<>();
    }

    public MLStrategy(RoundSequence seq, int N) {
        random = new RandomStrategy();
        temp = seq;
        this.N = N;
        storedSequence = new HashMap<>();
    }

    public MLStrategy(int seed, RoundSequence seq, int N) {
        random = new RandomStrategy(seed);
        temp = seq;
        this.N = N;
        storedSequence = new HashMap<>();
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
            PatternWriter writeToFile = new PatternWriter(storedSequence);
            writeToFile.writeToFile();
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
     * @return
     */
    public Sign makePrediction() {
        if (temp.isLengthEqualN(N) && key != null) {
            if (storedSequence.containsKey(key)) {
                predictionSign = getMostFreqSeq(key);
                return predictionSign.loseTo();
            } else {
                return random.makeMove();
            }
        }
        return random.makeMove();
    }
    @Override
    public Sign getPredictionSign(){
        return predictionSign;
    }

    /**
     * Get the most frequent pattern
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
     * UNFINSHED, makes a another hashmap with a different size N, currently just takes the existing saved
     * round results and make a new hashmap. Does not add new individuals rounds to map nor predict the next
     * round
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
}

