package Commands;

import Collection.CollectionManager;

public class Clear implements Command{

    CollectionManager collectionManager;

    public Clear(CollectionManager collectionManager){
        this.collectionManager = collectionManager;
    }
    @Override
    public void execute(String[] args) {
        if (args.length == 1){
            collectionManager.clear();
            System.out.println("The collection was cleared.");
        } else throw new IllegalArgumentException("Wrong number of arguments. Command is incorrect. Try again.");
    }

    @Override
    public String getDescription() {
        return "clear: to clear the collection";
    }
}
