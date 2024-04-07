package Tasks;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class ParseAlEthnicity extends DefaultHandler {

    boolean tagFound = false;
    static Set<String> ethnicity = new HashSet<>();

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes)
            throws SAXException {
        if (qName.equalsIgnoreCase("ethcty")) {
            tagFound = true;
        }
    }

    @Override
    public void characters(char ch[], int start, int length) throws SAXException {
        if (tagFound) {
            ethnicity.add(new String(ch, start, length));
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.equalsIgnoreCase("ethcty")) {
            tagFound = false;
        }
    }

    public static String AllEthnicity() {
        return Arrays.toString(ethnicity.toArray());
    }
}
