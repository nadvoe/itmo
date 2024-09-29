package FileSystem;

import CoreClasses.Vehicle;
import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.CSVWriter;

import java.io.*;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Scanner;

public class ParserCsv {
    private final String fileName;


    public ParserCsv(String fileName) {
        this.fileName = fileName;
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
            System.err.println("Something went wrong: " + e.getMessage());
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
            } catch (IOException e) {
                System.err.println("Something went wrong. The unexpected error of saving: " + e);
            }
        } catch (FileNotFoundException | NullPointerException e) {
            System.err.println("The file is not found: " + e );
        } finally {
            try {
                writer.close();
            } catch (IOException e) {
                System.err.println("Something went wrong. The unexpected error of file closing: " + e);
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
            System.err.println("Something went wrong. The deserialization error: " + e);
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
                    return collection;
                } else
                    System.err.println("The required collection wasn't found in loaded file.");
            } catch (FileNotFoundException e) {
                System.err.println("The loading file wasn't found: " + e);
            } catch (IllegalStateException e) {
                System.err.println("Something went wrong. Deserialization error: " + e);
                System.exit(0);
            }
        } else {
            System.err.println("The argument of command line and loading file wasn't found.");
        }
        return null;
    }
}