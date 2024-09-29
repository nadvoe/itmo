package Asker;

import CoreClasses.*;
import Console.Console;
import Utilities.SessionHandler;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;

import static java.lang.System.console;

public class ReadManager {
    static Console console = new Console();


    public static String readName(){
        if(console.getFileReader() != null){
            return console.filereadln();
        }
        System.out.println("Enter the name of the vehicle: ");
        String name =  console.readLine();
        while (true){
            if (name.isBlank() || name.isEmpty()){
                System.out.println("The name can't be empty. Enter the name again: ");
                name = console.readLine();
            } else {return name;}
        }
    }

    public static Integer readCoordinateX(){
        if(console.getFileReader() != null){
            return Integer.parseInt(console.filereadln());
        }
        System.out.println("Enter the coordinate X: ");
        while (true){
            try{
                return console.readInt();
            } catch (NumberFormatException e){
                System.out.println("Incorrect coordinate. Enter the coordinate X again: ");
            }
        }
    }

    public static Long readCoordinateY(){
        if(console.getFileReader() != null){
            return (long) Integer.parseInt(console.filereadln());
        }
        System.out.println("Enter the coordinate Y: ");
        while(true){
            try{
                return console.readLong();
            } catch (NumberFormatException e){
                System.out.println("Incorrect coordinate. Enter the coordinate Y again: ");
            }
        }
    }

    public static long readEnginePower() {
        if(console.getFileReader() != null){
            return Integer.parseInt(console.filereadln());
        }
        while (true) {
            System.out.println("Enter engine power: ");
            try {
                var a =  console.readLine();
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

    public static Double readFuelConsumption(){
        if(console.getFileReader() != null){
            return Double.parseDouble(console.filereadln());
        }
        System.out.println("Enter fuel consumption: ");
            while (true){
                try{
                    var a =  console.readLine();
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

    public static VehicleType readVehicleType(){
        if(console.getFileReader() != null){
            String s = console.filereadln();
            return VehicleType.valueOf(s);
        }
        System.out.println("Enter 1 of the vehicle types: " + Arrays.toString(VehicleType.values()));
        while (true){
            try{
                System.out.println("\n Enter the vehicle type: ");
                String s = console.toValidatedMessage();
                if(s == null){
                    return null;
                }
                return VehicleType.valueOf(s);
            } catch (IllegalArgumentException e){
                System.out.println("Incorrect vehicle type. Enter the vehicle type again: ");
            }
        }
    }

    public static FuelType readFuelType(){
        if(console.getFileReader() != null){
            String s = console.filereadln();
            return FuelType.valueOf(s);
        }
        System.out.println("Enter 1 of the fuel type: " + Arrays.toString(FuelType.values()));
        while (true){
            try {
                System.out.println("\n Enter the fuel type: ");
                String s = console.toValidatedMessage();
                if(s == null){
                    return null;
                }
                return FuelType.valueOf(s);
            } catch (IllegalArgumentException e){
                System.out.println("Incorrect fuel type. Enter the fuel type: ");
            }
        }
    }

    public static Vehicle readVehicle(){
        String name  = readName();
        Integer x = readCoordinateX();
        Long y = readCoordinateY();
        long enginePower = readEnginePower();
        Double fuelConsumption = readFuelConsumption();
        VehicleType type = readVehicleType();
        FuelType fuelType= readFuelType();
        return new Vehicle ( 0, name, new Coordinates(x,y), LocalDateTime.now(), enginePower, fuelConsumption, type, fuelType, SessionHandler.getCurrentUser().getId());
    }

    public static class AskBreak extends Exception {
    }

    public static User askUser(Console console) throws IOException, AskBreak {
        boolean scrM;
        scrM = console.getFileReader() != null;
        String name;
        if (!scrM) {
            while (true) {
                console.println("Enter your name (size < 30 symbols, can't be null): ");
                name = console.readln().trim();
                if (name.equals("exit")) throw new AskBreak();
                if (!(name.isEmpty()) && (name.length() <= 30)) {
                    break;
                } else {
                    console.println("Your name should be less than 30 symbols and not null !!! Try again.");
                }
            }
        } else {
            name = console.filereadln();
            if (name == null || name.trim().isEmpty()) {
                throw new IOException("Invalid name in file (null or empty)");
            }
            name = name.trim();
        }
        String psw1;
        if(!scrM) {
            console.println("Enter password (we won't tell it anyone, really)");
            psw1 = askPassword(console);
        }
        else{
            psw1 = console.filereadln().trim();
        }
        return new User(1, name, psw1, null);
    }

//    public static User askUser(Console console) throws IOException, AskBreak {
//        boolean scrM;
//        scrM = console.getFileReader() != null;
//        String name;
//        if (!scrM) {
//            while (true) {
//                console.println("Enter your name (size < 30 symbols, can't be null): ");
//                name = console.readln().trim();
//                if(name.equals("exit")) throw new AskBreak();
//                if (!(name.isEmpty()) && (name.length() <= 30)) {
//                    break;
//                }
//                else{
//                    console.println("Your name should be less than 30 symbols and not null !!! Try again. ");
//                }
//            }
//        } else {
//            name = console.filereadln().trim();
//        }
//        String psw1;
//        if(!scrM) {
//            console.println("Enter password (we won't tell it anyone, really)");
//            psw1 = askPassword(console);
//        }
//        else{
//            psw1 = console.filereadln().trim();
//        }
//        return new User(0, name, psw1, null);
//    }

    protected static String askPassword(Console console) throws IOException {
        if(console() != null){
            return new String(console().readPassword()); }
        else return console.readln().trim();
    }

}
