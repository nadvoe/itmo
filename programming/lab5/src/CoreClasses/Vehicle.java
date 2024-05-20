package CoreClasses;

import Collection.IdGeneration;

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
    private Date creationDate; //can't be null, automatically generated
    private long enginePower; // > 0
    private Double fuelConsumption; //can be null, > 0
    private VehicleType type; //can be null
    private FuelType fuelType; //can be null


    public Vehicle (Long id, String name, Coordinates coordinates, Date creationDate, long enginePower, Double fuelConsumption, VehicleType type, FuelType fuelType){
        if (name == null || coordinates == null){
            throw new IllegalArgumentException("The fields can't be null.");
        }
        if (name.isEmpty()){
            throw new IllegalArgumentException("The field can't be empty.");
        }
        else{
        this.id = IdGeneration.generateID(); // > 0, unique, automatically generated
        this.name = name; //can't be null, string can't be empty
        this.coordinates = coordinates;  //can't be null
        this.creationDate = new Date(); //can't be null, automatically generated
        this.enginePower = enginePower; // > 0
        this.fuelConsumption = fuelConsumption; //can be null, >0
        this.type = type; //can be null
        this.fuelType = fuelType; //can be null
        }
    }


    public Vehicle (Long id, String name, Coordinates coordinates, LocalDateTime creationDate, long enginePower, Double fuelConsumption, VehicleType type, FuelType fuelType){
        if (name == null || coordinates == null){
            throw new IllegalArgumentException("The fields can't be null.");
        }
        if (name.isEmpty()){
            throw new IllegalArgumentException("The field can't be empty.");
        } else{
            this.id = IdGeneration.generateID(); // > 0, unique, automatically generated
            this.name = name; //can't be null, string can't be empty
            this.coordinates = coordinates;  //can't be null
            this.creationDate = new Date(); //can't be null, automatically generated
            this.enginePower = enginePower; // > 0
            this.fuelConsumption = fuelConsumption; //can be null, >0
            this.type = type; //can be null
            this.fuelType = fuelType; //can be null
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

    public Date getCreationDate(){
        return this.creationDate;
    }

    public void setCreationDate(){this.creationDate = new Date();}

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

    public static Vehicle fromArray(String[] a) {
        Long id; // > 0, unique , automatically generated
        String name; //can't be null, can't be empty
        Coordinates coordinates; //can't be null
        LocalDateTime creationDate; //can't be null, automatically generated
        Long enginePower; // > 0
        Double fuelConsumption; //can be null, > 0
        VehicleType vehicleType; //can be null
        FuelType fuelType; //can be null


        try {
            try { id = Long.parseLong(a[0]); } catch (NumberFormatException e) { id = null; }
            name = a[1];
            coordinates = new Coordinates(a[2]);
            try { creationDate = LocalDateTime.parse(a[3], DateTimeFormatter.ISO_DATE_TIME); } catch (
                    DateTimeParseException e) { creationDate = null; };
            try { enginePower = Long.parseLong(a[4]); } catch (NumberFormatException e) { enginePower = null; }
            try { fuelConsumption = (a[5].equals("null") ? null : Double.parseDouble(a[5])); } catch (NumberFormatException e) { fuelConsumption = null; }
            try { vehicleType = VehicleType.valueOf(a[6]); } catch (NullPointerException | IllegalArgumentException  e) { vehicleType = null; }
            try { fuelType = FuelType.valueOf(a[7]); } catch (NullPointerException | IllegalArgumentException  e) { fuelType = null; }
            return new Vehicle(id, name, coordinates, creationDate, enginePower, fuelConsumption, vehicleType, fuelType);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "id=" + id + ", \n" +
                " name='" + name + ", \n" +
                " coordinates=" + coordinates + ", \n" +
                " creationDate=" + creationDate + ", \n" +
                " enginePower=" + enginePower + ", \n" +
                " fuelConsumption=" + fuelConsumption + ", \n" +
                " vehicleType=" + type + ", \n" +
                " fuelType=" + fuelType + "\n" + "} \n";
    }
    public boolean validate(){
        return (getId() != null && getName() != null && getCoordinates() != null && getCreationDate() != null && getEnginePower() > 0 && (getFuelConsumption() > 0 || getFuelConsumption() == null));
    }
}