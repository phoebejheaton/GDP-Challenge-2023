package org.phoebe.gdp2023;

import java.nio.file.Path;
import java.util.HashMap;

public class XMLReader extends Reader{
    private static final String EXTENSION = "xml";

    public XMLReader() {
        super(EXTENSION);
    }
    @Override
    public HashMap<String, Integer> read(Path x) {
        return null;
    }


}
