package org.phoebe.gdp2023;

import java.io.*;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;

public class TextReader extends Reader{
    private static final String EXTENSION = "txt";

    public TextReader() {
        super(EXTENSION);
    }
    @Override
    public HashMap<String, Integer> read(Path x) throws IOException {
        HashMap<String,Integer> keywords = new HashMap<>();

        File f = loadFile(x);
        Scanner scanner = new Scanner(f);
        scanner.useDelimiter("\\.");

        String curr;

        while(scanner.hasNext()){
            curr = scanner.next();

            String previousWord = null;
            for(String word: curr.split(" ")){
                if (WORDS.contains(word) && previousWord != null) {
                    keywords.put(word,
                            (keywords.getOrDefault(word, 0) + Integer.parseInt(previousWord.trim())));
                }
                previousWord = word;
            }
        }
        scanner.close();

        return keywords;
    }
}



