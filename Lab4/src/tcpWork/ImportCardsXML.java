package tcpWork;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import java.io.File;
import java.util.ArrayList;

public class ImportCardsXML {

    public static ArrayList<MetroCard> read() {
        ArrayList<MetroCard> cards = new ArrayList<>();

        try {
            File inputFile = new File("cards.xml");

            if (inputFile.exists()) {
                DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
                Document doc = dBuilder.parse(inputFile);
                doc.getDocumentElement().normalize();
                NodeList nodeList = doc.getElementsByTagName("metroCard");

                for (int i = 0; i < nodeList.getLength(); i++) {
                    Element cardElement = (Element) nodeList.item(i);
                    String serNum = cardElement.getAttribute("serNum");
                    Element holderElement = (Element) cardElement.getElementsByTagName("holder").item(0);
                    String surname = holderElement.getElementsByTagName("holder-surname").item(0).getTextContent();
                    String name = holderElement.getElementsByTagName("holder-name").item(0).getTextContent();
                    String sex = holderElement.getElementsByTagName("sex").item(0).getTextContent();
                    String birthday = holderElement.getElementsByTagName("birthday").item(0).getTextContent();
                    String colledge = cardElement.getElementsByTagName("colledge").item(0).getTextContent();
                    double balance = Double.parseDouble(cardElement.getElementsByTagName("balance").item(0).getTextContent());
                    User holder = new User(surname, name, sex, birthday);
                    MetroCard card = new MetroCard(holder, serNum , colledge, balance);
                    cards.add(card);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return cards;
    }
}
