package Commands;

import Collection.CollectionManager;
import CoreClasses.Vehicle;

public class UpdateId implements Command {

    CollectionManager collectionManager;

    public UpdateId(CollectionManager collectionManager){
        this.collectionManager = collectionManager;
    }
    @Override
    public void execute(String[] args) {
        if (args.length == 2){
            try {
                long id = Long.parseLong(args[1]);
                boolean flag = false;
                for (Vehicle vehicle : collectionManager.getVehicles()){
                    if (vehicle.getId() == id){
                        flag = true;
                        break;
                    }
                }
                if (flag){
                    Vehicle vehicle = collectionManager.getClientManager().getVehicle();
                    collectionManager.updateId(vehicle, id);
                } else {
                    System.out.println("Element with such id does not exist in the collection.");
                }
            } catch (IllegalArgumentException e){
                System.out.println("Wrong arguments. Try to enter the arguments again.");
            }
        } else throw  new IllegalArgumentException("Wrong number of arguments. Command is incorrect. Try again.");
    }

    @Override
    public String getDescription() {
        return "update id {element}: to update the fields of the element where id equals to the entered id";
    }
}
