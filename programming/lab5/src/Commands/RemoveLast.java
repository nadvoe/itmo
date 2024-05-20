package Commands;

import Collection.CollectionManager;

public class RemoveLast implements Command{

    CollectionManager collectionManager;

    public RemoveLast(CollectionManager collectionManager){
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(String[] args) {
        if (args.length == 1){
            try{
                collectionManager.removeLast();
                System.out.println("last element was removed! :)");
            } catch (IllegalArgumentException e){
                System.out.println("Wrong arguments. Try to enter the arguments again.");
            }
        } else throw new IllegalArgumentException("Wrong number of arguments. Command is incorrect. Try again.");
    }

    @Override
    public String getDescription() {
        return "remove_last: to remove the last element of collection";
    }
}