package Tasks;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class ParseAllTags extends DefaultHandler {

    static Set<String> tags = new HashSet<>();

    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        tags.add(qName);
    }

    public static String AllTags() {
        return Arrays.toString(tags.toArray());
    }
}

