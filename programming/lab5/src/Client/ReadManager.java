package Client;

import Console.ConsoleManager;
import CoreClasses.FuelType;
import CoreClasses.VehicleType;

import java.util.Arrays;

public class ReadManager {
    ConsoleManager consoleManager = new ConsoleManager();


    public String readName(){
        System.out.println("Enter the name of the vehicle: ");
        String name =  consoleManager.readLine();
        while (true){
            if (name.isBlank() || name.isEmpty()){
                System.out.println("The name can't be empty. Enter the name again: ");
                name = consoleManager.readLine();
            } else {return name;}
        }
    }

    public Integer readCoordinateX(){
        System.out.println("Enter the coordinate X: ");
        while (true){
            try{
                return consoleManager.readInt();
            } catch (NumberFormatException e){
                System.out.println("Incorrect coordinate. Enter the coordinate X again: ");
            }
        }
    }

    public Long readCoordinateY(){
        System.out.println("Enter the coordinate Y: ");
        while(true){
            try{
                return consoleManager.readLong();
            } catch (NumberFormatException e){
                System.out.println("Incorrect coordinate. Enter the coordinate Y again: ");
            }
        }
    }

    public long readEnginePower() {
        while (true) {
            System.out.println("Enter engine power: ");
            try {
                var a =  consoleManager.readLine();
                if(!a.isEmpty()){
                    long enginePower = Long.parseLong(a);
                    if (enginePower > 0){
                        return enginePower;
                    } else{
                        System.out.println("The engine power can't be equal 0 or less. Enter the engine power again: ");
                    }
                }
            } catch (NumberFormatException e) {
                System.out.println("Incorrect number. Enter the engine power again: ");
            }
        }
    }

    public Double readFuelConsumption(){
        System.out.println("Enter fuel consumption: ");
            while (true){
                try{
                    var a =  consoleManager.readLine();
                    if(a.isEmpty()){
                        return null;
                    }
                    Double fuelConsumption = Double.parseDouble(a);
                    if (fuelConsumption > 0 & !fuelConsumption.toString().isBlank()){
                        return fuelConsumption;
                    }else{
                        System.out.println("The fuel consumption can't be null or less than 0. Enter the fuel consumption again: ");
                    }
                } catch (NumberFormatException e){
                    System.out.println("Incorrect number. Enter the fuel consumption again: ");
                }
            }

    }

    public VehicleType readVehicleType(){
        System.out.println("Enter 1 of the vehicle types: " + Arrays.toString(VehicleType.values()));
        while (true){
            try{
                System.out.println("\n Enter the vehicle type: ");
                String s = consoleManager.toValidatedMessage();
                if(s == null){
                    return null;
                }
                return VehicleType.valueOf(s);
            } catch (IllegalArgumentException e){
                System.out.println("Incorrect vehicle type. Enter the vehicle type again: ");
            }
        }
    }

    public FuelType readFuelType(){
        System.out.println("Enter 1 of the fuel type: " + Arrays.toString(FuelType.values()));
        while (true){
            try {
                System.out.println("\n Enter the fuel type: ");
                String s = consoleManager.toValidatedMessage();
                if(s == null){
                    return null;
                }
                return FuelType.valueOf(s);
            } catch (IllegalArgumentException e){
                System.out.println("Incorrect fuel type. Enter the fuel type: ");
            }
        }
    }
}
