package Commands;

import Collection.CollectionManager;
import CoreClasses.Vehicle;

public class AddIfMin implements Command{

    CollectionManager collectionManager;

    public AddIfMin (CollectionManager collectionManager){
        this.collectionManager = collectionManager;
    }
    @Override
    public void execute(String[] args) {
        if (args.length == 1){
            try {
                Vehicle vehicle = collectionManager.getClientManager().getVehicle();
                collectionManager.addIfMin(vehicle);
            } catch (IllegalArgumentException e){
                System.out.println("Wrong arguments. Try to enter the arguments again.");
            }
        } else throw new IllegalArgumentException("Wrong number of arguments. Command is incorrect. Try again.");
    }

    @Override
    public String getDescription() {
        return "add_if_min {element}: to add new element in collection, if its value is smaller than smallest element's value in the collection";
    }
}