package xml;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import mybeans.Data;
import mybeans.DataSheet;

public class DataSheetToXML {

    public static void parseDataSheetToXML(DataSheet dataSheet, String path) throws IOException {
        try {
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.newDocument();

            Element datasheetElement = document.createElement("datasheet");
            document.appendChild(datasheetElement);

            List<Data> dataList = dataSheet.getDataArrayList();

            for (Data data : dataList) {
                Element dataElement = document.createElement("data");
                datasheetElement.appendChild(dataElement);
                System.out.println(data.getDate());
                dataElement.setAttribute("date", data.getDate());

                Element xElement = document.createElement("x");
                System.out.println(data.getX());
                xElement.appendChild(document.createTextNode(Double.toString(data.getX())));
                dataElement.appendChild(xElement);

                Element yElement = document.createElement("y");
                System.out.println(data.getY());
                yElement.appendChild(document.createTextNode(Double.toString(data.getY())));
                dataElement.appendChild(yElement);
            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource domSource = new DOMSource(document);
            FileOutputStream fileOutputStream = new FileOutputStream(path);
            StreamResult streamResult = new StreamResult(fileOutputStream);

            transformer.transform(domSource, streamResult);

        } catch (ParserConfigurationException | TransformerConfigurationException e) {
            throw new IOException(e);
        } catch (TransformerException e) {
            throw new IOException("Error saving to file ", e);
        }
    }
}
