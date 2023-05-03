package ru.sayron.server.utility;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import ru.sayron.common.data.Organization;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.TimeZone;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class FileManager {
    private String envVariable;

    public FileManager(String envVariable) {
        this.envVariable = envVariable;
    }

    /**
     * Writes collection to a file.
     *
     * @param collection Collection to write.
     */
    public void writeCollection(Collection<?> collection) {
        try (BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(System.getenv().get(envVariable)))) {
            XmlMapper xmlMapper = new XmlMapper();
            xmlMapper.registerModule(new JavaTimeModule());
            xmlMapper.enable(SerializationFeature.INDENT_OUTPUT);
            xmlMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
            dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
            xmlMapper.setDateFormat(dateFormat);
            xmlMapper.writeValue(outputStream, collection);
        } catch (FileNotFoundException exception) {
            Console.printerror("Файл для записи не найден!");
        } catch (IOException exception) {
            Console.printerror("Ошибка записи в файл!");
        }
    }

    /**
     * Reads collection from a file.
     *
     * @return Readed collection.
     */
    public TreeSet<Organization> readCollection() {
        if (System.getenv().get(envVariable) != null) {
            try (BufferedReader collectionFileReader = new BufferedReader(new FileReader(new File(System.getenv().get(envVariable))))) {
                XmlMapper xmlMapper = new XmlMapper();
                xmlMapper.registerModule(new JavaTimeModule());
                String xml = collectionFileReader.lines().collect(Collectors.joining());
                return xmlMapper.readValue(xml, new TypeReference<TreeSet<Organization>>() {});
            } catch (FileNotFoundException exception) {
                Console.printerror("Загрузочный файл не найден!");
            } catch (IOException exception) {
                Console.printerror("Ошибка чтения загрузочного файла!");
            }
        } else Console.printerror("Системная переменная с загрузочным файлом не найдена!");
        return new TreeSet<Organization>();
    }


    @Override
    public String toString() {
        String string = "FileManager (класс для работы с загрузочным файлом)";
        return string;
    }
}
