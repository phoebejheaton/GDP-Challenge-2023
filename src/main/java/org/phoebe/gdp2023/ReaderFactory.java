package org.phoebe.gdp2023;
//Author: Phoebe Heaton
//Date: 05/03/23
//
//Description: Simple Factory class following factory design pattern. Hides Reader selection from other classes based
// on given file type/extension
//
//Parameters: String of file extension
//
//Return: Subclass object of Reader class for relevant file type, null if not allowed
//

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
