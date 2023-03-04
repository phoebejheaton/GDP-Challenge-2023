package org.phoebe.gdp2023;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;

public class XMLReader extends Reader{
    private static final String EXTENSION = "xml";

    public XMLReader() {
        super(EXTENSION);
    }
    @Override
    public HashMap<String, Integer> read(Path x) throws IOException, XMLStreamException {
        HashMap<String,Integer> keywords = new HashMap<>();
        File f = loadFile(x);

        String toAdd = null;
        XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
        XMLEventReader reader = xmlInputFactory.createXMLEventReader(new FileInputStream(f));

        while(reader.hasNext()){
            XMLEvent nextEvent = reader.nextEvent();
            if(nextEvent.isStartElement()){
                StartElement startElement = nextEvent.asStartElement();
                if((startElement.getName().getLocalPart()).equals("item")){
                    nextEvent = reader.nextEvent();
                    if(WORDS.contains(nextEvent.toString())){
                        toAdd = nextEvent.toString();
                    }
                } else if((startElement.getName().getLocalPart()).equals("amount") && toAdd != null){
                    nextEvent = reader.nextEvent();
                    keywords.put(toAdd,
                            (keywords.getOrDefault(toAdd, 0) + Integer.parseInt(nextEvent.toString())));
                    toAdd = null;
                }

            }
        }

        reader.close();

        return keywords;
    }


}
