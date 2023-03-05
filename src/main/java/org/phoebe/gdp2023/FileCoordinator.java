package org.phoebe.gdp2023;
//Author: Phoebe Heaton
//Date: 05/03/23
//
//Description: Class responsible for directory parsing to find relevant files, calling readers for those files, and
// returning complete data
//
//Parameters: Class entry point is fileController, taking the data directory from Main
//
//Return: Class exit point is also fileController, passing the final data back to Main
//



import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLStreamException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileCoordinator {
    private static final Map<String, Integer> KeywordCollection = new HashMap<>();

    //Description: Condenses similar map keys (ie singular and plural) into same K,V pair
    //
    //Parameters: Str, Int HashMap from fileController
    //
    //Return: Reduced Str, Int HashMap
    //
    public static Map<String, Integer> mapReducer(Map<String, Integer> map){

        String[][] duplicates = {{"Vulnerabilities", "Vulnerability"}, {"Threats", "Threat"}, {"Bugs", "Bug"},
                                {"Patches", "Patch"}, {"Viruses", "Virus"}};

        for(String[] pair: duplicates){
            if(map.containsKey(pair[0]) && map.containsKey(pair[1])) {
                map.replace(pair[0], (map.get(pair[1]) + map.get(pair[0])));
                map.remove(pair[1]);
            }
        }

        return map;
    }

    //Description: Iterates over found files, collects returned data into one hashmap
    //
    //Parameters: Directory of files to search
    //
    //Return: Condensed complete hashmap
    //
    public static Map<String, Integer> fileController(String dir) throws IOException {
        List<Path> paths = listFiles(dir);

        paths.forEach(x -> {
                Reader reader = getReader(x);
                if(reader != null) {
                    try {
                        Map<String, Integer> keywordTemp;
                        keywordTemp = reader.read(x);
                        keywordTemp.forEach((key, value) -> KeywordCollection.merge(key, value, Integer::sum));
                    } catch (IOException | XMLStreamException | ParserConfigurationException | SAXException e) {
                        throw new RuntimeException(e);
                    }
                } else {
                    throw new IllegalArgumentException();
                }
            });

        return mapReducer(KeywordCollection);
    }

    //Description: Requests reader from ReaderFactory for given file type
    //
    //Parameters: Path to file
    //
    //Return: Reader object
    //
    public static Reader getReader(Path path){
        String[] extension = path.toString().split("[.]");
        return ReaderFactory.makeReader(extension[extension.length-1]);
    }

    //Description: Walks through given directory and extracts files
    //
    //Parameters: Directory name
    //
    //Return: list of paths to files in given directory
    //
    public static List<Path> listFiles(String dir) throws IOException {
        Path path = Paths.get(dir);
        List<Path> result;
        try (Stream<Path> walk = Files.walk(path)){
            result = walk.filter(Files::isRegularFile)
                    .collect(Collectors.toList());
        } catch (IOException e){
            throw new IOException();
        }
        return result;
    }
}
