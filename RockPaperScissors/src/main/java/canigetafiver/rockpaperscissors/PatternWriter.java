package canigetafiver.rockpaperscissors;
import java.io.FileReader;
import java.io.File;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class PatternWriter {
    //SequenceHashMap patterns;
    HashMap<String,SequenceHashMap> freqPattern;
    ArrayList<String> lines = new ArrayList<>();
    public PatternWriter( HashMap<String,SequenceHashMap> freqPattern){
       // this.patterns = patterns;
        this.freqPattern = freqPattern;
    }

    public void writeToFile(){
        try {
            FileWriter fw = new FileWriter("pattern.txt");
            PrintWriter pw = new PrintWriter(fw);
            Set<String> keysOfPatterns = freqPattern.keySet();
            //Set<String> keyPatterns = patterns.getaSeqHashMap().keySet();
            for(String keys: keysOfPatterns){
                SequenceHashMap pat = freqPattern.get(keys);
                HashMap<String,Integer> thePatterns = pat.getaSeqHashMap();
                Set<String> freq = thePatterns.keySet();
                for (String freqPatterns: freq){
                    String line = freqPatterns +" "+ String.valueOf(thePatterns.get(freqPatterns));
                    lines.add(line);
                }
            }

            for(String theLine: lines){
                pw.write(theLine);
                pw.println();
            }
            pw.close();
            fw.close();

        }
        catch (IOException e) {
            System.out.println("File not found.");
            e.printStackTrace();
        }
    }
}
