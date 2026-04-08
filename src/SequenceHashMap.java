import java.util.HashMap;
import java.util.Set;

public class SequenceHashMap {
    private final HashMap<String, Integer> aSeqHashMap;

    public SequenceHashMap(String key){
        aSeqHashMap = new HashMap<>();
        aSeqHashMap.put(key, 1);
    }
    /**
     * Add counter for sequence for first occurence of sequence
     * @param key
     */
    public void addHashMapSequence(String key){
        aSeqHashMap.put(key, 1);
    }
    /**
     * Check if sequence not occured yet, if not add new occerence to hashmap
     * if it had occured, update frequency count
     * @param key the sequence
     */
    public void addFreq(String key){
        if(aSeqHashMap.containsKey(key)){
            int frequency = aSeqHashMap.get(key) + 1;
            aSeqHashMap.replace(key, frequency);
        }
        else{
            addHashMapSequence(key);
        }
    }
    /**
     * Check if sequence had happened
     */
    public boolean hasKey(String key){
        return aSeqHashMap.containsKey(key);
    }

    public String getMostFreqSequence(){
        int max=-1;
        String maxSeq = "";
        Set<String> keyList = aSeqHashMap.keySet();
        for(String keys: keyList){
            if(max < aSeqHashMap.get(keys)){
                max = aSeqHashMap.get(keys);
                maxSeq = keys;
            }       
        }
        return maxSeq;
    }
    
    public void printSequenceFrequency(){
        Set<String> stringofpattern = aSeqHashMap.keySet();
        for(String str: stringofpattern){
            System.out.print(str + ":" +aSeqHashMap.get(str)+", ");
        }
        System.out.println("");
    }
    public static void main(String[] args) {
        Player com1 = new Computer(new RandomStrategy());
        Player com2 = new Computer(new RandomStrategy());
        RoundSequence rsTest = new RoundSequence(com1, com2);
        com1.makeChoice();
        com2.makeChoice();
        rsTest.updateRoundSequence();
        rsTest.print();
        int count =0;
        
        while(count<20){
            com1.makeChoice();
            com2.makeChoice();
            rsTest.updateRoundSequence();
            count+=1;
        }
        rsTest.print();
        


    }

    public HashMap<String, Integer> getaSeqHashMap() {
        return aSeqHashMap;
    }


}
