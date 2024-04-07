import Tasks.ParseAlEthnicity;
import Tasks.ParseAllTags;
import Tasks.XSDValidator;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import javax.xml.parsers.*;

import java.util.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Element;

public class Main {
    public static void main(String[] args) throws SAXException, IOException, ParserConfigurationException, TransformerException {
        Scanner scanner = new Scanner(System.in);
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser saxParser = factory.newSAXParser();

        int choice;
        while (true) {
            System.out.print(
                    """
                            \n
                            1 - Tags
                            2 - Xsd validator
                            3 - Ethnicity check
                            4 - Popular names
                            5 - DOM
                            0 - Exit
                            """);
            System.out.print("Enter: ");
            choice = scanner.nextInt();
            switch (choice) {
                case 1 -> {
                    try {
                        ParseAllTags handler = new ParseAllTags();
                        saxParser.parse("Popular_Baby_Names_NY.xml", handler);
                        System.out.println("\nTags: " + ParseAllTags.AllTags());
                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }
                }
                case 2 -> {
                    XSDValidator validator = new XSDValidator();
                    validator.isValid("Popular_Baby_Names_NY.xsd", "Popular_Baby_Names_NY.xml");
                }

                case 3 -> {
                    ParseAlEthnicity handler = new ParseAlEthnicity();
                    saxParser.parse(new FileInputStream("Popular_Baby_Names_NY.xml"), handler);
                    System.out.println("\nEthnicity in Popular_Baby_Names_NY.xml: " + ParseAlEthnicity.AllEthnicity());
                }
                case 4 -> {
                    System.out.print("\nEnter ethnicity: ");
                    String ethnicity = scanner.nextLine() + scanner.nextLine();
                    System.out.print("Enter count of names: ");
                    int name_count = scanner.nextInt();

                    File inputFile = new File("Popular_Baby_Names_NY.xml");
                    DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
                    DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
                    Document doc = dBuilder.parse(inputFile);
                    doc.getDocumentElement().normalize();

                    NodeList childList = doc.getElementsByTagName("row");

                    for (int i = 0; i < childList.getLength(); i++) {
                        Element child = (Element) childList.item(i);
                        String gender = child.getElementsByTagName("gndr").item(0).getTextContent();
                        String ethcty = child.getElementsByTagName("ethcty").item(0).getTextContent();
                        String name = child.getElementsByTagName("nm").item(0).getTextContent();
                        String count = child.getElementsByTagName("cnt").item(0).getTextContent();
                        String rank = child.getElementsByTagName("rnk").item(0).getTextContent();

                        if (ethcty.equalsIgnoreCase(ethnicity)) {
                            Tasks.NameInfo nameInfoCurrent = new Tasks.NameInfo(name, gender, ethcty, count, rank);
                            Tasks.NameInfo.check(nameInfoCurrent);
                        }
                    }

                    List<Tasks.NameInfo> result = Tasks.NameInfo.names;
                    int n = result.size();
                    Tasks.NameInfo temp;
                    for (int i = 0; i < n; i++) {
                        for (int j = 1; j < (n - i); j++) {
                            if (result.get(j - 1).rank > result.get(j).rank) {
                                temp = result.get(j - 1);
                                result.set(j - 1, result.get(j));
                                result.set(j, temp);
                            }
                        }
                    }

                    DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
                    DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
                    Document new_doc = docBuilder.newDocument();

                    Element rootElement = new_doc.createElement("root");
                    new_doc.appendChild(rootElement);

                    for (int i = 0; i < name_count; i++) {
                        Element personElement = new_doc.createElement("Tasks.NameInfo");

                        Element nameElement = new_doc.createElement("name");
                        nameElement.setTextContent(result.get(i).name);
                        personElement.appendChild(nameElement);

                        Element ethctyElement = new_doc.createElement("ethcty");
                        ethctyElement.setTextContent(result.get(i).ethcty);
                        personElement.appendChild(ethctyElement);

                        Element genderElement = new_doc.createElement("gender");
                        genderElement.setTextContent(result.get(i).gender);
                        personElement.appendChild(genderElement);

                        Element countElement = new_doc.createElement("count");
                        countElement.setTextContent(result.get(i).count.toString());
                        personElement.appendChild(countElement);

                        Element rankElement = new_doc.createElement("rank");
                        rankElement.setTextContent(result.get(i).rank.toString());
                        personElement.appendChild(rankElement);

                        rootElement.appendChild(personElement);
                    }

                    TransformerFactory transformerFactory = TransformerFactory.newInstance();
                    Transformer transformer = transformerFactory.newTransformer();
                    DOMSource source = new DOMSource(new_doc);
                    StreamResult resultFile = new StreamResult(new File("Best_Baby_Names_NY.xml"));
                    transformer.transform(source, resultFile);

                    System.out.println("\nXML document created");
                }
                case 5 -> {
                    DocumentBuilderFactory Dfactory = DocumentBuilderFactory.newInstance();
                    DocumentBuilder Dbuilder = Dfactory.newDocumentBuilder();
                    Document res_doc = Dbuilder.parse(new File("Best_Baby_Names_NY.xml"));
                    Element root = res_doc.getDocumentElement();

                    NodeList nodeList = root.getElementsByTagName("Tasks.NameInfo");
                    System.out.println();
                    for (int i = 0; i < nodeList.getLength(); i++) {
                        Element child = (Element) nodeList.item(i);
                        String name = child.getElementsByTagName("name").item(0).getTextContent();
                        String gender = child.getElementsByTagName("gender").item(0).getTextContent();
                        String ethcty = child.getElementsByTagName("ethcty").item(0).getTextContent();
                        String count = child.getElementsByTagName("count").item(0).getTextContent();
                        String rank = child.getElementsByTagName("rank").item(0).getTextContent();
                        System.out.println("Name: " + name +
                                ", Gender: " + gender +
                                ", Ethnicity: " + ethcty +
                                ", Count: " + count +
                                ", Rank: " + rank);
                    }
                }
                case 0 -> {
                    System.out.println("\nEnd Of Program");
                    System.exit(1);
                }
                default -> System.out.println("\nWrong key");
            }
        }
    }
}
