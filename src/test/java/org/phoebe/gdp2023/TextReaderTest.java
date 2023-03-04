package org.phoebe.gdp2023;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class TextReaderTest {
    final String dataDir = "data";
    final String DDOSTxtDir = "data/DDOS-file.txt";
    final String secureTxtDir = "data/Secure-file.txt";

    private Path pathToDDOSTxt, pathToSecureTxt;

    private Reader txtReader;


    HashMap<String, Integer> correctResultSecure = new HashMap<>();
    HashMap<String, Integer> correctResultDDOS = new HashMap<>();

    HashMap<String, Integer> correctResultAll = new HashMap<>();

    @BeforeEach
    void setUp() {
        pathToDDOSTxt = Paths.get(DDOSTxtDir);
        pathToSecureTxt = Paths.get(secureTxtDir);

        txtReader = ReaderFactory.makeReader("txt");

        correctResultSecure.put("DDOS", 6);
        correctResultSecure.put("Patch", 2);
        correctResultSecure.put("Threat", 3);


        correctResultDDOS.put("Vulnerabilities", 6);
        correctResultDDOS.put("Bugs", 10);
        correctResultDDOS.put("Viruses", 9);

        correctResultAll.put("Vulnerabilities", 6);
        correctResultAll.put("Bugs", 10);
        correctResultAll.put("Viruses", 9);
        correctResultAll.put("DDOS", 6);
        correctResultAll.put("Patch", 2);
        correctResultAll.put("Threat", 3);
    }

    @Test
    void read() throws IOException {
        assertEquals(txtReader.read(pathToDDOSTxt), correctResultDDOS);
        assertEquals(txtReader.read(pathToSecureTxt), correctResultSecure);

        assertEquals(FileCoordinator.fileController(dataDir),correctResultAll);
    }
}