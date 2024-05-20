package Collection;

import Client.ClientManager;
import Commands.CommandManager;
import Console.ConsoleManager;
import CoreClasses.Vehicle;
import CoreClasses.VehicleType;
import FileSystem.ParserCsv;

import java.time.LocalDateTime;
import java.util.*;

import static java.lang.Math.min;

public class CollectionManager {
    LinkedList<Vehicle> vehicles = new LinkedList<>();
    private Date date = new Date();
    private ClientManager clientManager = new ClientManager();

    private String fileName;

    private LocalDateTime lastInitTime;
    private LocalDateTime lastSaveTime;
    private ConsoleManager consoleManager;

    private ParserCsv parser = new ParserCsv(fileName, consoleManager);

    public CollectionManager(ConsoleManager consoleManager, String fileName) {
        this.consoleManager = consoleManager;
        this.fileName = fileName;
        this.lastInitTime = LocalDateTime.now();
        this.lastSaveTime = null;
    }

    public void setCollection(LinkedList<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }


    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void fileReading(){
        while (true) {
            try {
                ParserCsv parser = new ParserCsv(fileName, consoleManager);
                setCollection(parser.readCollection());
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("File is not found.");
            }
        }
    }

    public boolean load() {
        vehicles.clear();
        fileReading();
        lastInitTime = LocalDateTime.now();
        update();
        return true;
    }

    public ClientManager getClientManager() {
        return this.clientManager;
    }

    public LinkedList<Vehicle> getVehicles() {
        return vehicles;
    }
    public String ToString(){
        String s = "";
        for(var i : vehicles){
            s += i.toString();
        }
        return s;
    }
    public void help() {
        CommandManager.getCommands().values().forEach(command -> System.out.println(command.getDescription()));
    }

    public void update() {
        Collections.sort(vehicles);
    }

    public void info() {
        String info = String.format("Collection type: " + vehicles.getClass().getSimpleName() + "\n Initialization date: " + lastInitTime.toString() + "\n Save Time:" + lastSaveTime +  "\n The amount of elements: " + vehicles.size());
        System.out.println(info);
    }

    public void show() {
        if (!vehicles.isEmpty()) {
            System.out.println(ToString());
        } else {
            System.out.println("Collection contains no elements.");
        }
    }

    public void add(Vehicle vehicle) {
        vehicles.add(vehicle);
        update();
    }

    public void updateId(Vehicle newVehicle, long id) {
        for (Vehicle vehicle : vehicles) {
            if (vehicle.getId() == id) {
                vehicle.setName(newVehicle.getName());
                vehicle.setCoordinates(newVehicle.getCoordinates());
                vehicle.setEnginePower(newVehicle.getEnginePower());
                vehicle.setFuelConsumption(newVehicle.getFuelConsumption());
                vehicle.setType(newVehicle.getType());
                vehicle.setFuelType(newVehicle.getFuelType());
                System.out.println("The element was successfully updated.");
                break;
            }
        }
    }

    public void removeByID(long id) {
        for (Vehicle vehicle : vehicles) {
            if (vehicle.getId() == id) {
                vehicles.remove(vehicle);
                System.out.println(("The element with id " + id + " was successfully removed.").toString());
            }
        }
    }

    public void clear() {
        vehicles.clear();
    }

    public void save(){
        ParserCsv parser = new ParserCsv(this.fileName, consoleManager);
        try{
            parser.writeCollection(this.vehicles);
            lastSaveTime = LocalDateTime.now();
        } catch (NullPointerException e){
            System.out.println("Something went wrong.");
        }
    }

    public void exit() {
        System.out.println("The work is finished.");
        System.exit(0);
    }

    public void removeLast() {
        vehicles.removeLast();
    }

    public void addIfMin(Vehicle newVehicle) {
        double minFuelConsumption = 999999999.9999999999999999;
        for (Vehicle vehicle : vehicles) {
            minFuelConsumption = min(vehicle.getFuelConsumption(), minFuelConsumption);
        }
        if (newVehicle.getFuelConsumption() < minFuelConsumption) {
            add(newVehicle);
            System.out.println("The element was added to the collection as the minimal.");
        } else {
            System.out.println("The element can't be added since it is not the minimal.");
        }
    }

    public void reorder(){
        if (!vehicles.isEmpty()) {
            getVehicles().sort(Comparator.reverseOrder());
            String s = toString();
            System.out.println(s);
        } else {
            System.out.println("The collection is empty. Can't be reversed.");
        }
    }


    public void groupCountingByType() {
        if (!vehicles.isEmpty()) {
            Map<VehicleType, Integer> typeCountMap = new HashMap<>();
            for (Vehicle vehicle : vehicles) {
                VehicleType type = vehicle.getType();
                typeCountMap.put(type, typeCountMap.getOrDefault(type, 0) + 1);
            }
            System.out.println("Grouped and counted by type: ");
            typeCountMap.forEach((name, count) -> System.out.println(name + ": " + count));
        } else {
            System.out.println("Collection is empty. Can't be grouped.");
        }
    }

    public void prientFieldAscendingFuelConsumption() {
        if (!vehicles.isEmpty()){
            ArrayList<Double> ascendingFuelConsumption = new ArrayList<>();
            for (Vehicle vehicle : vehicles) {
                Double fuelConsumption = vehicle.getFuelConsumption();
                if (Collections.frequency(ascendingFuelConsumption, fuelConsumption) == 0) {
                    ascendingFuelConsumption.add(fuelConsumption);
                }
            }
            Collections.sort(ascendingFuelConsumption, Collections.reverseOrder());
            System.out.println(ascendingFuelConsumption);
        } else {
            System.out.println("The collection is empty. Try again after adding elements.");
        }

    }


    public void printFieldDescendingType() {
        if (!vehicles.isEmpty()) {
            ArrayList<VehicleType> descendingType = new ArrayList<>();
            for (Vehicle vehicle : vehicles) {
                VehicleType vehicleType = vehicle.getType();
                if (Collections.frequency(descendingType, vehicleType) == 0) {
                    descendingType.add(vehicleType);
                }
            }
            Collections.sort(descendingType, Collections.reverseOrder());
            System.out.println(descendingType);
        } else {
            System.out.println("The collection is empty. Try again after adding elements.");
        }
    }
}