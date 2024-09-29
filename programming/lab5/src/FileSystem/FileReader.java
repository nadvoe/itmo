package FileSystem;

import Commands.CommandManager;
import CoreClasses.FuelType;
import CoreClasses.VehicleType;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileReader {
    private Scanner scanner;

    public FileReader(String filename) throws FileNotFoundException{
        this.scanner = new Scanner(new File(filename));
    }
    public Scanner getScanner(){
        return this.scanner;
    }

    public String readName() throws IllegalArgumentException{
        String name = scanner.nextLine();
        if (name.isEmpty() || name.isBlank()){
            throw new IllegalArgumentException("");
        } else {
            return name;
        }
    }

    public int readCoordinateX(){return scanner.nextInt();}

    public long readCoordinateY(){return scanner.nextLong();}

    public long readEnginePower() throws IllegalArgumentException{
        long enginePower = scanner.nextLong();
        if (enginePower > 0){
            return enginePower;
        } else throw new IllegalArgumentException("");
    }

    public double readFuelConsumption() throws IllegalArgumentException{
        double fuelConsumption = scanner.nextDouble();
        if (fuelConsumption > 0){
            return fuelConsumption;
        } else throw new IllegalArgumentException("");
    }

    public VehicleType readVehicleType(){
        String vehicleType = scanner.next().trim().toUpperCase();
        return switch (vehicleType){
            case "BICYCLE" -> VehicleType.BICYCLE;
            case "HOVERBOARD" -> VehicleType.HOVERBOARD;
            case "SPACESHIP" -> VehicleType.SPACESHIP;
            default -> null;
        };
    }

    public FuelType readFuelType(){
        String fuelType = scanner.next().trim().toUpperCase();
        return switch (fuelType){
            case "GASOLINE" -> FuelType.GASOLINE;
            case "DIESEL" -> FuelType.DIESEL;
            case "ALCOHOL" -> FuelType.ALCOHOL;
            case "PLASMA" -> FuelType.PLASMA;
            case "ANTIMATTER" -> FuelType.ANTIMATTER;
            default -> null;
        };
    }

    public long readVehicles() throws IllegalArgumentException{
        long vehicles = scanner.nextLong();
        if (vehicles <= 0){
            throw new IllegalArgumentException("");
        } else {
            return vehicles;
        }
    }

    public String[] readCommandAndArgument() throws IllegalArgumentException{
        String[] commandAndArgument = scanner.nextLine().trim().toLowerCase().split(" ");
        String command = commandAndArgument[0].trim();
        if (CommandManager.getCommands().containsKey(command)){
            if (command.equals("update") || command.equals("remove_by_id") || command.equals("execute_script")){
                if (commandAndArgument.length == 2){
                    return new String[]{command, commandAndArgument[1].trim()};
                } else throw new IllegalArgumentException("");
            } else {
                if (commandAndArgument.length == 1){
                    return new String[]{command};
                } else throw new IllegalArgumentException("");
            }
        } else throw new IllegalArgumentException("");
    }

}