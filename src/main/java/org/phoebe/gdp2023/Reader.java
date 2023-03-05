package org.phoebe.gdp2023;
//Author: Phoebe Heaton
//Date: 05/03/23
//
//Description: Abstract class for file reading. Stores extension type of reader and given cyber keywords for subclasses
// to access
//
//Parameters: Class declarations are in FactoryReader
//
//Return: N/A
//

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLStreamException;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public abstract class Reader {
    protected String extensionType;

    public List<String> WORDS = new ArrayList<String>(){{
        add("Vulnerabilities");
        add("Vulnerability");
        add("Bugs");
        add("Bug");
        add("DDOS");
        add("Patch");
        add("Patches");
        add("Virus");
        add("Viruses");
        add("Threats");
        add("Threat");
    }};
    public Reader (String extensionType) {
        this.extensionType = extensionType;
    }

    public String getFileExtension() {
        return extensionType;
    }
    public abstract HashMap<String, Integer> read(Path x) throws IOException, ParserConfigurationException,
                                                                    SAXException, XMLStreamException;

    public File loadFile(Path path){
        return path.toFile();
    }
}
