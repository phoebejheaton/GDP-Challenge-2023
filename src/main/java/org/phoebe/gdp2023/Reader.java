package org.phoebe.gdp2023;

import java.util.HashMap;

public class Reader {
    protected String extensionType = "txt";
    public String getFileExtension() {
        return this.extensionType;
    }
    public HashMap<String, Integer> read() {
        HashMap<String, Integer> Keywords = new HashMap<String, Integer>();

        Keywords.put("Viruses", 0);
        Keywords.put("Bugs", 0);
        Keywords.put("DDOS", 0);
        Keywords.put("Patches", 0);
        Keywords.put("Vulnerabilities", 0);
        Keywords.put("Threat", 0);

        return Keywords;
    }
}
