package Tasks;

import java.io.File;
import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

public class XSDValidator {
    public XSDValidator(){
    }
    public static void isValid(String xsd, String xml) {
        try {
            // зчитуємо xsd схему з файлу
            SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            File schemaFile = new File(xsd);
            Schema schema = factory.newSchema(schemaFile);

            // створюємо валідатор для даної схеми
            Validator validator = schema.newValidator();

            // зчитуємо xml документ з файлу
            File xmlFile = new File(xml);
            Source xmlSource = new StreamSource(xmlFile);

            // перевіряємо xml документ на відповідність xsd схемі
            validator.validate(xmlSource);
            System.out.println("Document is valid");
        } catch (Exception e) {
            System.out.println("Document is not valid: " + e.getMessage());
        }
    }
}
