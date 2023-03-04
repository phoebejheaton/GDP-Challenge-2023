package org.phoebe.gdp2023;

import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static com.sun.org.apache.xerces.internal.util.PropertyState.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;


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
        //paths = FileCoordinator.fileController(dataDir);
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
        Assertions.assertEquals(FileCoordinator.getExtension((Path) paths.toArray()[0]), "xml");
        Assertions.assertEquals(FileCoordinator.getExtension((Path) paths.toArray()[1]), "txt");
        Assertions.assertEquals(FileCoordinator.getExtension((Path) paths.toArray()[2]), "json");
        Assertions.assertEquals(FileCoordinator.getExtension((Path) paths.toArray()[3]), "txt");

    }

}