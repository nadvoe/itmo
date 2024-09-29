package Commands;

import Collection.CollectionManager;
import Collection.IdGeneration;
import Collection.Validation;
import CoreClasses.Coordinates;
import CoreClasses.FuelType;
import CoreClasses.Vehicle;
import CoreClasses.VehicleType;
import FileSystem.FileReader;
import FileSystem.FileStack;

import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.NoSuchElementException;

import static java.lang.Long.parseLong;

public class ExecuteScript implements Command {

    private final CollectionManager collectionManager;

    private final HashMap<String, Command> commands;

    public ExecuteScript(CollectionManager collectionManager){
        this.collectionManager = collectionManager;
        this.commands = CommandManager.getCommands();
    }

    Validation validation = new Validation();

    @Override
    public void execute(String[] args) {
        if (args.length == 2) {
            String filename = args[1];
            if (!FileStack.getFileStack().contains(filename)){
                FileStack.addFile(filename);
                try {
                    FileReader fileReader = new FileReader(filename);
                    while (fileReader.getScanner().hasNextLine()){
                        try {
                            String[] commandAndArgument = fileReader.readCommandAndArgument();
                            String command = commandAndArgument[0];
                            if (commands.containsKey(command)){
                                if (command.equals("add") || command.equals("add_if_min") || command.equals("update")) {
                                    String name = fileReader.readName();
                                    Integer x = fileReader.readCoordinateX();
                                    Long y = fileReader.readCoordinateY();
                                    long enginePower = fileReader.readEnginePower();
                                    Double fuelConsumption = fileReader.readFuelConsumption();
                                    VehicleType type = fileReader.readVehicleType();
                                    FuelType fuelType = fileReader.readFuelType();
                                    Vehicle vehicle = validation.getValidatedModel(new Vehicle(IdGeneration.generateID(), name, new Coordinates(x, y), LocalDateTime.now(), enginePower, fuelConsumption, type, fuelType));
                                    if(!vehicle.validate()){

                                    }
                                    if (vehicle != null) {
                                        switch (command) {
                                            case "add" -> {
                                                collectionManager.add(vehicle);
                                                System.out.println("The vehicle was successfully added to the collection.");
                                            }
                                            case "add_if_min" -> collectionManager.addIfMin(vehicle);
                                            case "remove_by_id" -> collectionManager.removeByID(vehicle.getId());
                                            case "update" -> {
                                                collectionManager.updateId(vehicle, parseLong(commandAndArgument[1]));
                                                System.out.println("The vehicle was updated.");
                                            }
                                        }
                                    }
                                } else {
                                    commands.get(command).execute(commandAndArgument);
                                }
                            }
                        } catch (IllegalArgumentException | NoSuchElementException ignored){}
                    }
                } catch (FileNotFoundException e){
                    System.out.println("File is not found.");
                } finally {
                    FileStack.removeFile();
                }
            }
        }
    }

    @Override
    public String getDescription() {
        return "execute_script file_name: to read and execute a script from defined file.";
    }
}
