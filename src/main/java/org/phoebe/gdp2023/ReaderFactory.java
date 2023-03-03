package org.phoebe.gdp2023;

public class ReaderFactory {
    public static Reader makeReader(String extension){
        if(extension.equals("json")){
            return new JSONReader();
        } else if(extension.equals("xml")){
            return new XMLReader();
        } else if(extension.equals("txt")){
            return new TextReader();
        } else return null;
    }
}
