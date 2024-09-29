package Commands;

import Collection.CollectionManager;
import CoreClasses.Vehicle;

public class AddElement implements Command {

    CollectionManager collectionManager;

    public AddElement(CollectionManager collectionManager){
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(String[] args){
        if (args.length == 1){
            try {
                Vehicle vehicle = collectionManager.getReadManager().getVehicle();
                boolean flag = true;
                for (Vehicle newVehicle: collectionManager.getVehicles()){
                    if (newVehicle.equals(vehicle)){
                        flag = false;
                    }
                }
                if (flag){
                    collectionManager.add(vehicle);
                    System.out.println("The new element was successfully added to the collection.");
                } else {
                    System.out.println("This element was in the collection already.");
                }
            } catch (IllegalArgumentException e){
                System.out.println("Wrong arguments. Try to enter the arguments again. ");
            }
        } else throw new IllegalArgumentException("Wrong number of arguments. Command is incorrect. Try again.");
    }

    @Override
    public String getDescription(){
        return "add: to add new element in the collection";
    }
}
