package FileSystem;

import Console.ConsoleManager;
import CoreClasses.Vehicle;

import au.com.bytecode.opencsv.*;

import java.io.*;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Scanner;

public class ParserCsv {
    private final String fileName;
    private final ConsoleManager console;

    public ParserCsv(String fileName, ConsoleManager console) {
        this.fileName = fileName;
        this.console = console;
    }

    /**
     * Convert the collection in CSV-line.
     *
     * @return CSV-line
     */
    private String collection2CSV(Collection<Vehicle> collection) {
        try {
            StringWriter sw = new StringWriter();
            CSVWriter csvWriter = new CSVWriter(sw, ';');
            for (var e : collection) {
                csvWriter.writeNext(Vehicle.toArray(e));
            }
            String csv = sw.toString();
            return csv;
        } catch (Exception e) {
            console.printError("Something went wrong. Serialization error.");
            return null;
        }
    }

    /**
     * Write the collection in the file.
     *
     * @param collection collection
     */
    public void writeCollection(Collection<Vehicle> collection) {
        OutputStreamWriter writer = null;
        try {
            var csv = collection2CSV(collection);
            if (csv == null) return;
            writer = new OutputStreamWriter(new FileOutputStream(fileName));
            try {
                writer.write(csv);
                writer.flush();
                console.println("The collection was successfully saved to the file.");
            } catch (IOException e) {
                console.printError("Something went wrong. The unexpected error of saving.");
            }
        } catch (FileNotFoundException | NullPointerException e) {
            console.printError("The file is not found.");
        } finally {
            try {
                writer.close();
            } catch (IOException e) {
                console.printError("Something went wrong. The unexpected error of file closing.");
            }
        }
    }

    /**
     * Convert CSV-line to collection.
     *
     * @return collection
     */
    private LinkedList<Vehicle> CSV2collection(String s) {
        try {
            StringReader sr = new StringReader(s);
            CSVReader csvReader = new CSVReader(sr, ';');
            LinkedList<Vehicle> ds = new LinkedList<Vehicle>();
            String[] record = null;
            while ((record = csvReader.readNext()) != null) {
                Vehicle d = Vehicle.fromArray(record);
                if (d.validate())
                    ds.add(d);
            }
            csvReader.close();
            return ds;
        } catch (Exception e) {
            console.printError("Something went wrong. The deserialization error.");
            return null;
        }
    }

    /**
     * Read the collection from the file.
     *
     * @return Readed collection.
     */
    public LinkedList<Vehicle> readCollection() {
        if (fileName != null && !fileName.isEmpty()) {
            try (var fileReader = new Scanner(new File(fileName))) {
                var s = new StringBuilder("");
                while (fileReader.hasNextLine()) {
                    s.append(fileReader.nextLine());
                    s.append("\n");
                }
                LinkedList<Vehicle> collection = new LinkedList<>();
                collection.addAll(CSV2collection(s.toString()));
                if (collection != null) {
                    console.println("The collection is succesfully loaded.");
                    return collection;
                } else
                    console.printError("The required collection wasn't found in loaded file.");
            } catch (FileNotFoundException exception) {
                console.printError("The loading file wasn't found.");
            } catch (IllegalStateException exception) {
                console.printError("Something went wrong. Deserialization error. ");
                System.exit(0);
            }
        } else {
            console.printError("The argument of command line and loading file wasn't found.");
        }
        return null;
    }
}