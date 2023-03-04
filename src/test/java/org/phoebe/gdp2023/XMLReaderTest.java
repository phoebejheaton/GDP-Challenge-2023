package org.phoebe.gdp2023;

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

class XMLReaderTest {
    final String dataDir = "data";
    final String cyberXMLDir = "data/cyber-security.xml";

    private Path pathToCyberXML;

    private Reader xmlReader;


    HashMap<String, Integer> correctResultCyber = new HashMap<>();

    HashMap<String, Integer> correctResultAll = new HashMap<>();

    @BeforeEach
    void setUp() {
        pathToCyberXML = Paths.get(cyberXMLDir);

        xmlReader = ReaderFactory.makeReader("xml");

        correctResultCyber.put("Vulnerabilities", 5);
        correctResultCyber.put("Threat", 1);
        correctResultCyber.put("Viruses", 5);
        correctResultCyber.put("Patches", 99);
        correctResultCyber.put("Threats", 5);

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
    void read() throws XMLStreamException, IOException, ParserConfigurationException, SAXException {
        assertEquals(xmlReader.read(pathToCyberXML), correctResultCyber);

        assertEquals(FileCoordinator.fileController(dataDir),correctResultAll);
    }
}