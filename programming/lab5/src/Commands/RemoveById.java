package Commands;

import Collection.CollectionManager;
import CoreClasses.Vehicle;

public class RemoveById implements Command {

    CollectionManager collectionManager;

    public  RemoveById (CollectionManager collectionManager){
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
                    collectionManager.removeByID(id);
                } else {
                    System.out.println("Element with such id does not exist in the collection.");
                }
            } catch (IllegalArgumentException e){
                System.out.println("Wrong arguments. Try to enter the arguments again.");
            }
        } else throw new IllegalArgumentException("Wrong number of arguments. Command is incorrect. Try again.");
    }

    @Override
    public String getDescription() {
        return "remove_by_id id: to delete element from collection on his id";
    }
}
