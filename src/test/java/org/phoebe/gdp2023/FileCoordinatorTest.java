package org.phoebe.gdp2023;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.Assert.assertEquals;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class FileCoordinatorTest {
    final String dataDir = "data";
    final String cyberXMLDir = "data/cyber-security.xml";
    final String DDOSTxtDir = "data/DDOS-file.txt";
    final String onlineJSONDir = "data/online-data.json";
    final String secureTxtDir = "data/Secure-file.txt";


    private List<Path> paths;
    private Path pathToCyberXML, pathToDDOSTxt, pathToOnlineTxt, pathToSecureTxt;

    @BeforeAll
    void pathSetUp() throws IOException {
        paths = FileCoordinator.fileController(dataDir);
        pathToCyberXML = Paths.get(cyberXMLDir);
        pathToDDOSTxt = Paths.get(DDOSTxtDir);
        pathToOnlineTxt = Paths.get(onlineJSONDir);
        pathToSecureTxt = Paths.get(secureTxtDir);
    }

    @Test
    void fileController() {
        assertEquals(paths.toArray()[0], pathToCyberXML);
        assertEquals(paths.toArray()[1], pathToDDOSTxt);
        assertEquals(paths.toArray()[2], pathToOnlineTxt);
        assertEquals(paths.toArray()[3], pathToSecureTxt);
    }

    @Test
    void getExtension() {
        assertEquals(FileCoordinator.getExtension((Path) paths.toArray()[0]), "xml");
        assertEquals(FileCoordinator.getExtension((Path) paths.toArray()[1]), "txt");
        assertEquals(FileCoordinator.getExtension((Path) paths.toArray()[2]), "json");
        assertEquals(FileCoordinator.getExtension((Path) paths.toArray()[3]), "txt");

    }

}