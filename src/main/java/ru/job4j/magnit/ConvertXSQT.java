package ru.job4j.magnit;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.*;
import java.net.URISyntaxException;

/**
 * Класс конвертации XML-файла в HTML-файл с помощью XSLT.
 *
 */
public class ConvertXSQT {

    /**
     *
     * @param source XML-Файл-источник
     * @param dest HTML-файл-результирующий
     * @param scheme XSL-схема
     */
    public static void convert(File source, File dest, File scheme) throws IOException, URISyntaxException, TransformerException, XMLStreamException {
        TransformerFactory factory = TransformerFactory.newInstance();
        Transformer transformer = factory.newTransformer(new StreamSource(scheme));
        transformer.transform(new StreamSource(source), new StreamResult(dest));
        stax(source);
    }


    /**
     * Метод парсит XML-файл, считает сумму значений и выводит результат в консоль.
     * @param source XML-файл
     */
    public static void stax(File source) throws FileNotFoundException, XMLStreamException {
        XMLInputFactory factory = XMLInputFactory.newInstance();
        XMLStreamReader parser = factory.createXMLStreamReader(new FileInputStream(source));
        int sum = 0;
        String ch = "";
        while (parser.hasNext()) {
            int event = parser.next();
            if (event == XMLStreamConstants.CHARACTERS) {
                ch = parser.getText();
                if (!ch.contains(System.lineSeparator())) {
                    sum = sum + Integer.parseInt(ch);
                }
            }
        }
        System.out.println("Сумма значений равна: " + sum);
    }
}
