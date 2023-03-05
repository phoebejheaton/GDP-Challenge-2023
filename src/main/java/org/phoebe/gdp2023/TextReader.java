package org.phoebe.gdp2023;
//Author: Phoebe Heaton
//Date: 05/03/23
//
//Description: Subclass of abstract Reader, specialised for reading text files.
//
//Parameters: File path
//
//Return: Data for Cyber keywords found at given file path
//

import java.io.*;
import java.nio.file.Path;
import java.util.*;

public class TextReader extends Reader{
    private static final String EXTENSION = "txt";

    public TextReader() {
        super(EXTENSION);
    }


    //Description: Parses text files line-by-line using Scanner and delimitter of ".". Each line checked for presence
    // of given keywords, if found, stored in hashmap Keywords with given amount (word before).
    //
    //Parameters: File path
    //
    //Return: Str, Int Hashmap of amount of found cyber keywords
    //
    @Override
    public HashMap<String, Integer> read(Path x) throws FileNotFoundException {
        try {
            HashMap<String, Integer> keywords = new HashMap<>();

            File f = loadFile(x);
            Scanner scanner = new Scanner(f);
            scanner.useDelimiter("\\.");

            String curr;

            while (scanner.hasNext()) {
                curr = scanner.next();

                String previousWord = null;
                for (String word : curr.split(" ")) {
                    if (WORDS.contains(word) && previousWord != null) {
                        keywords.put(word,
                                (keywords.getOrDefault(word, 0) + Integer.parseInt(previousWord.trim())));
                    }
                    previousWord = word;
                }
            }
            scanner.close();

            return keywords;
        } catch (IOException e){
            throw new FileNotFoundException();
        }
    }
}



