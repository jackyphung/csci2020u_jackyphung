package sample;

import java.io.*;
import java.util.*;

public class WordCounter {
    private Map<String, Integer> wordCounts;

    public WordCounter(){
        wordCounts = new TreeMap<>();
    }

    public void parseFile(File file) throws IOException {
        System.out.println("Starting parsing the file:" + file.getAbsolutePath());


        BufferedReader br = new BufferedReader(new FileReader(file));
        String line = br.readLine();

        while ((line = br.readLine())!=null){
            String[] columns = line.split(",");
            countWord(columns[5]);
        }
    }

    public Map<String, Integer> getWordCounts(){
        return wordCounts;
    }

    private void countWord(String word){
        if(wordCounts.containsKey(word)){
            int previous = wordCounts.get(word);
            wordCounts.put(word, previous+1);
        }else{
            wordCounts.put(word, 1);
        }
    }
}
