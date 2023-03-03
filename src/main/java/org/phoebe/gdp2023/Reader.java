package org.phoebe.gdp2023;

import java.nio.file.Path;
import java.util.HashMap;

public abstract class Reader {
    protected String extensionType;

    public Reader (String extensionType) {
        this.extensionType = extensionType;
    }

    public String getFileExtension() {
        return extensionType;
    }
    public abstract HashMap<String, Integer> read(Path x);
}
