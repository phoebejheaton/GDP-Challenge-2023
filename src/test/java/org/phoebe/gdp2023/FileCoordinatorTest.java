package org.phoebe.gdp2023;
//Author: Phoebe Heaton
//Date: 05/03/23
//
//Description: Test class for FileCoordinator class. Asserts against expected results
//

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;



@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class FileCoordinatorTest {
    final String dataDir = "data";
    final String cyberXMLDir = "data/cyber-security.xml";
    final String DDOSTxtDir = "data/DDOS-file.txt";
    final String onlineJSONDir = "data/online-data.json";
    final String secureTxtDir = "data/Secure-file.txt";


    private List<Path> paths;
    private Path pathToCyberXML, pathToDDOSTxt, pathToOnlineJSON, pathToSecureTxt;

    @BeforeAll
    void pathSetUp() throws IOException {
        paths = FileCoordinator.listFiles(dataDir);
        pathToCyberXML = Paths.get(cyberXMLDir);
        pathToDDOSTxt = Paths.get(DDOSTxtDir);
        pathToOnlineJSON = Paths.get(onlineJSONDir);
        pathToSecureTxt = Paths.get(secureTxtDir);
    }

    @Test
    void fileController() {
        Assertions.assertEquals(paths.toArray()[0], pathToCyberXML);
        Assertions.assertEquals(paths.toArray()[1], pathToDDOSTxt);
        Assertions.assertEquals(paths.toArray()[2], pathToOnlineJSON);
        Assertions.assertEquals(paths.toArray()[3], pathToSecureTxt);
    }

    @Test
    void getExtension() {
        Assertions.assertEquals(FileCoordinator.getReader((Path) paths.toArray()[0]).getFileExtension(), "xml");
        Assertions.assertEquals(FileCoordinator.getReader((Path) paths.toArray()[1]).getFileExtension(), "txt");
        Assertions.assertEquals(FileCoordinator.getReader((Path) paths.toArray()[2]).getFileExtension(), "json");
        Assertions.assertEquals(FileCoordinator.getReader((Path) paths.toArray()[3]).getFileExtension(), "txt");
    }

}