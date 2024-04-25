package tcpWork;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;

import org.w3c.dom.NodeList;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

public class ExportCardsXML {

    public static void addMoney(String serNum, double money) throws TransformerException, IOException, SAXException, ParserConfigurationException {
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc;
        try (InputStream inputStream = new FileInputStream("cards.xml")) {
            doc = dBuilder.parse(inputStream);
        }
        doc.getDocumentElement().normalize();
        NodeList cardList = doc.getElementsByTagName("metroCard");
        for (int i = 0; i < cardList.getLength(); i++) {
            Element card = (Element) cardList.item(i);
            String serNumFinded = card.getAttribute("serNum");
            if (serNumFinded.equals(serNum)){
                System.out.println(card.getChildNodes().item(2).getTextContent());
                card.getChildNodes().item(2).setTextContent(String.valueOf(Double.parseDouble(card.getChildNodes().item(2).getTextContent())  + money));
                System.out.println(card.getChildNodes().item(2).getTextContent());
            }
        }

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(new File("cards.xml"));
        transformer.transform(source, result);
    }

    public static void payMoney(String serNum, double money) throws TransformerException, IOException, SAXException, ParserConfigurationException {
        File inputFile = new File("cards.xml");
        Document doc;
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        doc = dBuilder.parse(inputFile);
        doc.getDocumentElement().normalize();
        NodeList cardList = doc.getElementsByTagName("metroCard");
        for (int i = 0; i < cardList.getLength(); i++) {
            Element card = (Element) cardList.item(i);
            String serNumFinded = card.getAttribute("serNum");
            if (serNumFinded.equals(serNum)){
                System.out.println(card.getChildNodes().item(2).getTextContent());
                card.getChildNodes().item(2).setTextContent(String.valueOf(Double.parseDouble(card.getChildNodes().item(2).getTextContent())  - money));
                System.out.println(card.getChildNodes().item(2).getTextContent());
            }
        }
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(new File("cards.xml"));
        transformer.transform(source, result);
    }

    public static void removeCard(String serNum) throws TransformerException, IOException, SAXException, ParserConfigurationException  {
        File inputFile = new File("cards.xml");
        Document doc;
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        doc = dBuilder.parse(inputFile);
        doc.getDocumentElement().normalize();
        NodeList cardList = doc.getElementsByTagName("metroCard");
        for (int i = 0; i < cardList.getLength(); i++) {
            Element card = (Element) cardList.item(i);
            String serNumFinded = card.getAttribute("serNum");
            if (serNumFinded.equals(serNum)){
                card.getParentNode().removeChild(card);
            }
        }
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(new File("cards.xml"));
        transformer.transform(source, result);

    }

    public static void add(MetroCard metroCard) {
        try {
            File inputFile = new File("cards.xml");
            Document doc;
            Element root;

            if (inputFile.exists()) {
                DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
                doc = dBuilder.parse(inputFile);
                doc.getDocumentElement().normalize();
                root = doc.getDocumentElement();
            } else {
                DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
                doc = dBuilder.newDocument();
                root = doc.createElement("metroCards");
                doc.appendChild(root);
            }

            Element card = doc.createElement("metroCard");
            card.setAttribute("serNum", metroCard.getSerNum());
            Element holder = doc.createElement("holder");
            Element surname = doc.createElement("holder-surname");
            surname.setTextContent(metroCard.getUsr().getSurName());
            Element name = doc.createElement("holder-name");
            name.setTextContent(metroCard.getUsr().getName());
            Element sex = doc.createElement("sex");
            sex.setTextContent(metroCard.getUsr().getSex());
            Element birthday = doc.createElement("birthday");
            birthday.setTextContent(metroCard.getUsr().getBirthday());
            holder.appendChild(surname);
            holder.appendChild(name);
            holder.appendChild(sex);
            holder.appendChild(birthday);
            Element college = doc.createElement("college");
            college.setTextContent(metroCard.getCollege());
            Element balance = doc.createElement("balance");
            balance.setTextContent(Double.toString(metroCard.getBalance()));
            card.appendChild(holder);
            card.appendChild(college);
            card.appendChild(balance);
            root.appendChild(card);
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("cards.xml"));
            transformer.transform(source, result);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
