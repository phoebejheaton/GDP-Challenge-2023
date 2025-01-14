package org.phoebe.gdp2023;

//Author: Phoebe Heaton
//Date: 05/03/23
//
//Description: Test class for JSONReader class. Asserts against expected results
//

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLStreamException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class JSONReaderTest {

    final String dataDir = "data";
    final String onlineJsonDir = "data/online-data.json";

    private Path pathToOnlineJson;

    private Reader jsonReader;


    HashMap<String, Integer> correctResultOnline = new HashMap<>();

    HashMap<String, Integer> correctResultAll = new HashMap<>();

    @BeforeEach
    void setUp() {
        pathToOnlineJson = Paths.get(onlineJsonDir);

        jsonReader = ReaderFactory.makeReader("json");

        correctResultOnline.put("Vulnerability", 1);
        correctResultOnline.put("Threat", 4);

        correctResultAll.put("Vulnerabilities", 11);
        correctResultAll.put("Bugs", 10);
        correctResultAll.put("Viruses", 14);
        correctResultAll.put("DDOS", 6);
        correctResultAll.put("Patches", 99);
        correctResultAll.put("Patch", 2);
        correctResultAll.put("Vulnerability", 1);
        correctResultAll.put("Threat", 8);
        correctResultAll.put("Threats", 5);
    }

    @Test
    void read() throws IOException, ParserConfigurationException, SAXException, XMLStreamException {
        assertEquals(jsonReader.read(pathToOnlineJson), correctResultOnline);

        assertEquals(FileCoordinator.fileController(dataDir),correctResultAll);
    }
}