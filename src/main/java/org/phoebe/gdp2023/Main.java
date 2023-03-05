package org.phoebe.gdp2023;

import java.io.IOException;
import java.util.Map;

public class Main {

    public static void main(String[] args) throws IOException {
        run(args[0]);
    }

    public static void run(String dir) throws IOException {
        Map<String, Integer> finalVals = FileCoordinator.fileController(dir);

        System.out.println("All cyber security items found: ");
        finalVals.forEach((key, value) -> {
            System.out.println(key + ": " + value);
        });
    }



}
