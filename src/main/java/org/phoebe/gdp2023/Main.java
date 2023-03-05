package org.phoebe.gdp2023;
//Author: Phoebe Heaton
//Date: 05/03/23
//This is my submission for the GDP-Challenge-2023 for Raytheon Technologies UK.
//
//Description: This solution to the challenge implements a plug-in style architecture with a simple Factory pattern to
// facilitate the different file types. The project is developed in OO Java code, developed in IntelliJ with Maven. All
// requirements and dependencies can be seen in the pom.xml. Tests are located in src/test/java/org/phoebe/gdp2023 and
// are implemented with JUnit5.
//
// Assumptions: I have made the following assumptions in this project:
//  - The number associated with the cyber keywords was the amount to record, ie "2 Vulnerabilities come along to help
//    the fight." in DDOS-file.txt resulted in 2 Vulnerabilities being recorded, rather than just the amount the given
//    keyword occurs in each file.
//  - For text files, there is no more than 1 keyword/value pair per sentence.
//  - For text files, there will always be a number preceding the keyword.
//  - For XML files, there will always be an amount tag per keyword.
//  - For JSON files, there will always be an ID tag per keyword.
//
//Requirements:  The project requires the command line argument of the path to the data directory from the source root,
// in this instance it is simply 'data' (no speech marks).
//
//Return: The project outputs to the command line the total amount of cyber-security items found. With the given files,
// the current output is below.
//
//        All cyber-security items found:
//        Bugs: 10
//        Threats: 13
//        Viruses: 14
//        DDOS: 6
//        Vulnerabilities: 12
//        Patches: 101
//


import java.io.IOException;
import java.util.Map;

public class Main {

    public static void main(String[] args) throws IOException {
        run(args[0]);
    }

    public static void run(String dir) throws IOException {
        Map<String, Integer> finalVals = FileCoordinator.fileController(dir);

        System.out.println("All cyber-security items found: ");
        finalVals.forEach((key, value) -> {
            System.out.println(key + ": " + value);
        });
    }



}
