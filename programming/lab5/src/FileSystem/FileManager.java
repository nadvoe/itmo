package FileSystem;

import CoreClasses.Vehicle;

import java.util.LinkedList;

public abstract class FileManager {
    private String filename;

    public FileManager() {
    }

    public FileManager(String fileName) {
        this.filename = filename;
    }

    public String getFilename() {
        return this.filename;
    }

    public void setFilename() {
        this.filename = filename;
    }

    protected abstract void saveToCsv(LinkedList<Vehicle> vehicles) throws Exception;

    public abstract LinkedList<Vehicle> loadFromCsv() throws Exception;
}