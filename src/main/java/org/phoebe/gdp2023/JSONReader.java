package org.phoebe.gdp2023;
//Author: Phoebe Heaton
//Date: 05/03/23
//
//Description: Subclass of abstract Reader, specialised for reading JSON files.
//
//Parameters: File path
//
//Return: Data for Cyber keywords found at given file path
//

import com.google.gson.Gson;

import java.io.*;
import java.nio.file.Path;
import java.util.HashMap;

public class JSONReader extends Reader{

    private static final String EXTENSION = "json";

    public JSONReader() {
        super(EXTENSION);
    }

    //Description: Parses JSON files into hashmap on JSON array name, array content stored in value. If key in hashmap
    // is a given keyword, value is parsed for amount of ID's within the array. Key and # of IDs are stored in returned
    // hashmap
    //
    //Parameters: Path to file
    //
    //Return: Str, Int Hashmap of amount of found cyber keywords
    //
    @Override
    public HashMap<String, Integer> read(Path x) throws FileNotFoundException {
        try {
            HashMap<String, Integer> keywords = new HashMap<>();

            File f = loadFile(x);
            Gson gson = new Gson();
            BufferedReader reader = new BufferedReader(new FileReader(f));

            HashMap json = gson.fromJson(reader, HashMap.class);

            for (Object k : json.keySet()) {
                if (WORDS.contains(k.toString())) {
                    String[] words = json.get(k).toString().split(" ");
                    int count = 0;
                    for (String w : words) {
                        if (w.contains("id=")) count++;
                    }
                    keywords.put(k.toString(),
                            (keywords.getOrDefault(k.toString(), 0) + count));
                }
            }

            return keywords;
        } catch (FileNotFoundException e){
            throw new FileNotFoundException();
        }
    }

}
