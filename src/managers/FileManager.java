package managers;

import au.com.bytecode.opencsv.*;

import java.io.StringWriter;
import java.io.StringReader;
import java.io.OutputStreamWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.File;
import java.lang.NullPointerException;
import java.util.*;

import utility.Console;
import models.LabWork;

/**
 * Использует файл для сохранения и загрузки коллекции.
 */
public class FileManager {
    private final String fileName;
    private final Console console;

    public FileManager(String fileName, Console console) {
        this.fileName = fileName;
        this.console = console;
    }

    /**
     * Преобразует коллекцию в CSV-строку.
     * @param collection
     * @return CSV-строка
     */
    private String collection2CSV(Collection<LabWork> collection) {
        try {
            StringWriter sw = new StringWriter();
            CSVWriter csvWriter = new CSVWriter(sw, ';');
            for (var e : collection) {
                csvWriter.writeNext(LabWork.toArray(e));
            }
            String csv = sw.toString();
            return csv;
        } catch (Exception e) {
            console.printError("Ошибка сериализации");
            return null;
        }
    }

    /**
     * Записывает коллекцию в файл.
     * @param collection коллекция
     */
    public void writeCollection(Collection<LabWork> collection, ArrayDeque<String> logStack) { //    public void writeCollection(Collection<LabWork> collection, Collection<LabWork> collectionDie, ArrayDeque<String> logStack) {
        OutputStreamWriter writer = null, writer2 = null;
        try {
            var csv = collection2CSV(collection);
            if (csv == null) return;
            writer = new OutputStreamWriter(new FileOutputStream(fileName));
            writer2 = new OutputStreamWriter(new FileOutputStream(fileName+"_log.txt"));
            try {
                writer.write(csv);
                writer.flush();
                for (var line : logStack)
                    writer2.write(line+"\r\n");
                writer2.flush();
                console.println("Коллекция успешна сохранена в файл!");
            } catch (IOException e) {
                console.printError("Неожиданная ошибка сохранения");
            }
        } catch (FileNotFoundException | NullPointerException e) {
            console.printError("Файл не найден");
        } finally {
            try {
                writer.close();
                writer2.close();
            } catch(IOException e) {
                console.printError("Ошибка закрытия файла");
            }
        }
    }

    /**
     * Преобразует CSV-строку в коллекцию.
     * @param s CSV-строка
     * @return коллекция
     */
    private Vector<LabWork> CSV2collection(String s) {
        try {
            StringReader sr = new StringReader(s);
            CSVReader csvReader = new CSVReader(sr, ';');
            Vector<LabWork> ds = new Vector<LabWork>();
            String[] record = null;
            while ((record = csvReader.readNext()) != null) {
                LabWork d = LabWork.fromArray(record);
                if (d.validate())
                    ds.add(d);
                else
                    console.printError("Файл с колекцией содержит не действительные данные");
            }
            csvReader.close();
            return ds;
        } catch (Exception e) {
            console.printError("Ошибка десериализации");
            return null;
        }
    }

    /**???
     * Считывает коллекцию из файл.
     * @return Считанная коллекция
     */
    public void readCollection(Collection<LabWork> collection, ArrayDeque<String> logStack) {
        if (fileName != null && !fileName.isEmpty()) {
            try (var fileReader = new Scanner(new File(fileName));
                 var fileReader2 = new Scanner(new File(fileName+"_log.txt"))) {
                var s = new StringBuilder("");
                while (fileReader.hasNextLine()) {
                    s.append(fileReader.nextLine());
                    s.append("\n");
                }
                var tmpStack = new ArrayDeque<String>();
                while (fileReader2.hasNextLine()) {
                    tmpStack.push(fileReader2.nextLine());
                }
                for (var e : tmpStack)
                    logStack.push(e);
                collection.clear();
                for (var e: CSV2collection(s.toString()))
                    collection.add(e);

            } catch (FileNotFoundException exception) {
                console.printError("Загрузочный файл не найден!");
            } catch (IllegalStateException exception) {
                console.printError("Непредвиденная ошибка!");
                System.exit(0);
            }
        } else {
            console.printError("Аргумент командной строки с загрузочным файлом не найден!");
        }
        collection = new Vector<LabWork>();
    }
}