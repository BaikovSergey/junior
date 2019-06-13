package ru.job4j.magnit;

import java.io.File;
import java.util.List;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

public class StoreXML {

    final String path = "C:\\sqlite\\db\\magnit.xml";

    public StoreXML(File file) {

    }

    public void save (Entries entries) {
        try {
            File file = new File(this.path);
            JAXBContext jaxbContext = JAXBContext.newInstance(Entries.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.marshal(entries, file);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
}
