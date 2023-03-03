package org.phoebe.gdp2023;

import java.nio.file.Path;
import java.util.HashMap;

public class JSONReader extends Reader{

    private static final String EXTENSION = "json";

    public JSONReader() {
        super(EXTENSION);
    }
    @Override
    public HashMap<String, Integer> read(Path x) {
        return null;
    }

}
