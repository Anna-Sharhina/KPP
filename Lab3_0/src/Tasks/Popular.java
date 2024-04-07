package Tasks;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Popular extends DefaultHandler {
    private List<NameInfo> nameList = new ArrayList<>();
    private String currentElement;
    private Map<String, String> attributes;
    int current = 0;

    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        this.currentElement = qName;
    }

    public void endElement(String uri, String localName, String qName) throws SAXException {
        System.out.println();
        current++;
    }

    public void endDocument() {
        for (NameInfo nameInfo : nameList) {
            System.out.println(nameInfo);
        }
        System.out.println("END");
    }

    public void characters(char[] ch, int start, int length) throws SAXException {
        if (currentElement.equalsIgnoreCase("row")) {
            String text = new String(ch, start, length);
            System.out.println(current + " " + text);
        }
    }

    public void parse(String fileName, String ethnicity, int count) {
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            saxParser.parse(fileName, this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
