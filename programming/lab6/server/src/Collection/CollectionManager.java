package Collection;

import CoreClasses.Vehicle;
import CoreClasses.VehicleType;


import java.time.LocalDateTime;
import java.util.*;

import static java.lang.Math.min;

public class CollectionManager {
    LinkedList<Vehicle> vehicles = new LinkedList<>();

    private final Map<Integer, Vehicle> map = new HashMap<>();

    private long currentId = 1 ;
    private Date date = new Date();
    private String fileName;
    private LocalDateTime lastInitTime;
    private LocalDateTime lastSaveTime;



    private ParserCsv parser = new ParserCsv(fileName);

    public CollectionManager(String fileName) {
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



    public void update() {
        Collections.sort(vehicles);
    }

    public String info() {
        String info = String.format("Collection type: " + vehicles.getClass().getSimpleName().trim() + "\n Initialization date: " + lastInitTime.toString() + "\n Save Time:" + lastSaveTime +  "\n The amount of elements: " + vehicles.size());
        return info;
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
        map.put(Math.toIntExact(vehicle.getId()), vehicle);
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
        ParserCsv parser = new ParserCsv(this.fileName);
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


    public long getFreeId(){
        while ((getVehicleById(currentId++) != null));
        return currentId;
    }

    public Vehicle getVehicleById(long id){
        return map.get((int)id);
    }

    public double getMinFuelConsumption(){
        double fuelConsumption = 999999999.9999999999999999;
        for(Vehicle a : vehicles){
            fuelConsumption = min(fuelConsumption, a.getFuelConsumption());
        }
        return fuelConsumption;
    }

    public LocalDateTime getLastInitTime() {
        return lastInitTime;
    }
    public LocalDateTime getLastSaveTime() {
        return lastSaveTime;
    }


    public void init() {
        vehicles.clear();
        vehicles = parser.readCollection(fileName);
        lastInitTime = LocalDateTime.now();
    }
}