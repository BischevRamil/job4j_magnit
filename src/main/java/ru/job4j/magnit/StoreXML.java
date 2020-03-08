package ru.job4j.magnit;


import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.util.List;

/**
 * Класс конвертации List в XML файл с помощью JAXB.
 */
public class StoreXML {

    public static void convertToXML(List<Entry> entryList, File file) throws JAXBException {
        Entries entries = new Entries();
        entries.setEntries(entryList);
        JAXBContext jaxbContext = JAXBContext.newInstance(Entries.class);
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        jaxbMarshaller.marshal(entries, file);
    }
}
