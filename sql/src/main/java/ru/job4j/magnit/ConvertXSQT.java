package ru.job4j.magnit;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

public class ConvertXSQT {

    public void convert() {
        try {
            TransformerFactory factory = TransformerFactory.newInstance();
            Source xslt = new StreamSource(new File("C:\\sqlite\\db\\xsl.xsl"));
            Transformer transformer = factory.newTransformer(xslt);
            Source xml = new StreamSource(new File("C:\\sqlite\\db\\magnit.xml"));
            transformer.transform(xml, new StreamResult(new File("C:\\sqlite\\db\\magnitXSL.xml")));
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }
}
