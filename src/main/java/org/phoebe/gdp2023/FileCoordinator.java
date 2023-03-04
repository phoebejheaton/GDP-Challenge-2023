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

    public static Map<String, Integer> fileController(String dir) throws IOException {

        Path path = Paths.get(dir);
        List<Path> paths = listFiles(path);


        try {
            paths.forEach(x -> {
                Reader reader = getReader(x);
                if (reader.getFileExtension() != "xml") {
                    try {
                        Map<String, Integer> keywordTemp;
                        keywordTemp = reader.read(x);
                        keywordTemp.forEach((key, value) -> KeywordCollection.merge(key,value, Integer::sum));
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
        } catch (Exception e) {
            System.out.println(e);
        }

        return KeywordCollection;
    }

    public static String getExtension(Path path){
        String[] extension = path.toString().split("[.]");
        Reader reader = ReaderFactory.makeReader(extension[extension.length-1]);
        return reader.getFileExtension();
    }

    public static Reader getReader(Path path){
        String[] extension = path.toString().split("[.]");
        Reader reader = ReaderFactory.makeReader(extension[extension.length-1]);
        return reader;
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
