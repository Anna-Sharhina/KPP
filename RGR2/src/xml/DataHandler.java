package xml;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import mybeans.Data;
import mybeans.DataSheet;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

public class DataHandler extends DefaultHandler {

    private final SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
    private String currentDate;
    private double currentX;
    private double currentY;
    private final List<Data> dataList = new ArrayList<>();
    private String currentElement;

    public void startElement(String uri, String localName, String qName, Attributes attributes){
        if (qName.equalsIgnoreCase("data")) {
            String dateString = attributes.getValue("date");
            try {
                currentDate = dateString;
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (qName.equalsIgnoreCase("x")) {
            currentElement = "x";
        } else if (qName.equalsIgnoreCase("y")) {
            currentElement = "y";
        }
    }

    public void characters(char[] ch, int start, int length){
        if (currentElement != null) {
            String value = new String(ch, start, length);
            if (currentElement.equalsIgnoreCase("x")) {
                currentX = Double.parseDouble(value);
            } else if (currentElement.equalsIgnoreCase("y")) {
                currentY = Double.parseDouble(value);
            }
            currentElement = null;
        }
    }

    public void endElement(String uri, String localName, String qName){
        if (qName.equalsIgnoreCase("data")) {
            Data data = new Data(currentX, currentY, currentDate);
            dataList.add(data);
        }
    }

    public DataSheet getDataSheet() {
        DataSheet dataSheet = new DataSheet();
        for (Data data : dataList) {
            dataSheet.addData(data);
        }
        return dataSheet;
    }
}