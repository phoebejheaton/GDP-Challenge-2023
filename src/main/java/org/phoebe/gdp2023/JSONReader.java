package org.phoebe.gdp2023;

import com.google.gson.Gson;

import java.io.*;
import java.nio.file.Path;
import java.util.HashMap;

public class JSONReader extends Reader{

    private static final String EXTENSION = "json";

    public JSONReader() {
        super(EXTENSION);
    }
    @Override
    public HashMap<String, Integer> read(Path x) throws IOException{
        HashMap<String,Integer> keywords = new HashMap<>();

        File f = loadFile(x);
        Gson gson = new Gson();
        BufferedReader reader = new BufferedReader(new FileReader(f));

        HashMap json = gson.fromJson(reader, HashMap.class);

        for(Object k: json.keySet()){
            if(WORDS.contains(k.toString())){
                String[] words = json.get(k).toString().split(" ");
                int count = 0;
                for(String w: words){
                    if(w.contains("id=")) count++;
                }
                keywords.put(k.toString(),
                        (keywords.getOrDefault(k.toString(), 0) + count));
            }
        }

        return keywords;
    }

}
