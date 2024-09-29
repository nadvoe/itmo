package CoreClasses;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Date;

public class Vehicle implements Serializable, Comparable<Vehicle> {

    private long id; // > 0, unique , automatically generated
    private String name; //can't be null, can't be empty
    private Coordinates coordinates; //can't be null
    private LocalDateTime creationDate; //can't be null, automatically generated
    private long enginePower; // > 0
    private Double fuelConsumption; //can be null, > 0
    private VehicleType type; //can be null
    private FuelType fuelType; //can be null

    private int creatorId;


    public Vehicle (Long id, String name, Coordinates coordinates, LocalDateTime creationDate, long enginePower, Double fuelConsumption, VehicleType type, FuelType fuelType, int creatorId){
        if (name == null || coordinates == null){
            throw new IllegalArgumentException("The fields can't be null.");
        }
        if (name.isEmpty()){
            throw new IllegalArgumentException("The field can't be empty.");
        }
        else{
            this.id = 0; // > 0, unique, automatically generated
            this.name = name; //can't be null, string can't be empty
            this.coordinates = coordinates;  //can't be null
            this.creationDate = LocalDateTime.now(); //can't be null, automatically generated
            this.enginePower = enginePower; // > 0
            this.fuelConsumption = fuelConsumption; //can be null, >0
            this.type = type; //can be null
            this.fuelType = fuelType; //can be null\
            this.creatorId = creatorId;

        }
    }


    public Vehicle (long id, String name, Coordinates coordinates, LocalDateTime creationDate, long enginePower, double fuelConsumption, VehicleType type, FuelType fuelType, int creatorId){
        if (name == null || coordinates == null){
            throw new IllegalArgumentException("The fields can't be null.");
        }
        if (name.isEmpty()){
            throw new IllegalArgumentException("The field can't be empty.");
        } else{
            this.id = id; // > 0, unique, automatically generated
            this.name = name; //can't be null, string can't be empty
            this.coordinates = coordinates;  //can't be null
            this.creationDate = creationDate; //can't be null, automatically generated
            this.enginePower = enginePower; // > 0
            this.fuelConsumption = fuelConsumption; //can be null, >0
            this.type = type; //can be null
            this.fuelType = fuelType; //can be null
            this.creatorId = creatorId;
        }
    }

    public Long getId(){
        return this.id;
    }

    public void setId(long id){
        this.id = id;
    }

    public String getName(){
        return this.name;
    }

    public void setName(String name){
        this.name = name;
    }

    public Coordinates getCoordinates(){
        return this.coordinates;
    }

    public void setCoordinates(Coordinates coordinates){
        this.coordinates = coordinates;
    }

    public LocalDateTime getCreationDate(){
        return this.creationDate;
    }

    public void setCreationDate(){this.creationDate = LocalDateTime.now();}

    public Long getEnginePower(){
        return this.enginePower;
    }

    public void setEnginePower(long enginePower){
        this.enginePower = enginePower;
    }

    public Double getFuelConsumption(){return this.fuelConsumption;}

    public void setFuelConsumption(Double fuelConsumption){
        this.fuelConsumption = fuelConsumption;
    }

    public VehicleType getType(){ return this.type;}

    public void setType(VehicleType type){
        this.type = type;
    }

    public FuelType getFuelType(){
        return this.fuelType;
    }

    public void setFuelType(FuelType fuelType){
        this.fuelType = fuelType;
    }

    @Override
    public int compareTo(Vehicle vehicle) {
        if (enginePower < vehicle.getEnginePower()){
            return 1;
        } else if (enginePower > vehicle.getEnginePower()){
            return -1;
        } else {
            return 0;
        }
    }

    public static String[] toArray(Vehicle e) {
        var list = new ArrayList<String>();
        list.add(e.getId().toString());
        list.add(e.getName());
        list.add(e.getCoordinates().toString());
        list.add(e.getCreationDate().toString());
        list.add(e.getEnginePower() == null ? "null" : e.getEnginePower().toString());
        list.add(e.getFuelConsumption().toString());
        list.add(e.getType().toString());
        list.add(e.getFuelType() == null ? "null" : e.getFuelType().toString());
        return list.toArray(new String[0]);
    }


    @Override
    public String toString() {
        return "Vehicle{" +
                "id =" + id + ", \n" +
                " name ='" + name + ", \n" +
                " coordinates = " + coordinates + ", \n" +
                " creationDate = " + creationDate + ", \n" +
                " enginePower = " + enginePower + ", \n" +
                " fuelConsumption = " + fuelConsumption + ", \n" +
                " vehicleType = " + type + ", \n" +
                " fuelType = " + fuelType + "\n" +
                " creatorId = " + creatorId +"} \n";
    }
    public boolean validate(){
        return (getId() != null && getName() != null && getCoordinates() != null && getCreationDate() != null && getEnginePower() > 0 && (getFuelConsumption() > 0 || getFuelConsumption() == null));
    }

    public int getCreatorId() {
        return creatorId;
    }
}