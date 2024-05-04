package xml;

import mybeans.DataSheet;
import java.io.File;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class SAXRead {

    public static DataSheet parseXMLToDataSheet(String path) {
        try {
            File inputFile = new File(path);
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            DataHandler dataHandler = new DataHandler();
            saxParser.parse(inputFile, dataHandler);
            return dataHandler.getDataSheet();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
