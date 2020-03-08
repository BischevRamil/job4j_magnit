package ru.job4j.magnit;

import javax.xml.bind.JAXBException;
import javax.xml.stream.XMLStreamException;
import javax.xml.transform.TransformerException;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

/**
 * @author Bischev Ramil
 * @since 2020-02-27
 * В программе производятся манипуляции с данными.
 * Генерируюся целочисленные значения, которые заносятся в базу данных SQLite3.
 * Затем эти значения выводятся из БД, составляется xml файл из них с помощью технологии JAXB.
 * XML файл конвертируется в HTML  с помощью технлогии XSLT.
 */
public class Magnit {

    private static File xmlFile = new File(System.getProperty("java.io.tmpdir") + "/magnit.xml");
    private static File htmlFile = new File(System.getProperty("java.io.tmpdir") + "/magnitConvert.html");
    private static File xslScheme = new File("src/main/java/ru/job4j/magnit/magnit.xsl");

    public static void main(String[] args) throws JAXBException, IOException, XMLStreamException, TransformerException, URISyntaxException {
        Config config = new Config();
        StoreSQL storeSQL = new StoreSQL(config);
        storeSQL.generate(100);
        StoreXML.convertToXML(storeSQL.load(), xmlFile);
        ConvertXSQT.convert(xmlFile, htmlFile, xslScheme);
    }
}
