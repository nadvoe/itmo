package Commands;

import Collection.CollectionManager;

public class PrintFieldAscendingFuelConsumption implements Command{

    CollectionManager collectionManager;

    public PrintFieldAscendingFuelConsumption(CollectionManager collectionManager){
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(String[] args) {
        if (args.length == 1){
            collectionManager.prientFieldAscendingFuelConsumption();
        } else throw new IllegalArgumentException("Wrong number of arguments. Command is incorrect. Try again.");
    }

    @Override
    public String getDescription() {
        return "print_field_ascending_fuel_consumption: to show fields fuelConsumption of all elements in ascending order";
    }
}