package org.phoebe.gdp2023;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLStreamException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Pattern;

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
    public abstract HashMap<String, Integer> read(Path x) throws IOException, ParserConfigurationException, SAXException, XMLStreamException;

    public File loadFile(Path path){
        return path.toFile();
    }
}
