package org.phoebe.gdp2023;

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
    private static Map<String, Integer> KeywordCollection = new HashMap<>();

    public static Map<String,Integer> fileController(String dir) throws IOException {

        Path path = Paths.get(dir);
        List<Path> paths = listFiles(path);

        paths.forEach(x -> {
            String[] extension = x.toString().split("[.]");
            Reader reader = ReaderFactory.makeReader(extension[extension.length-1]);
            System.out.println(x);
            System.out.println(reader.getFileExtension());
            //KeywordCollection.putAll(reader.read(x));
        });

        return KeywordCollection;
    }

    //copied from https://mkyong.com/java/java-files-walk-examples/#list-all-files
    public static List<Path> listFiles(Path path) throws IOException{
        List<Path> result;
        try (Stream<Path> walk = Files.walk(path)){
            result = walk.filter(Files::isRegularFile)
                    .collect(Collectors.toList());
        }
        return result;
    }
}
