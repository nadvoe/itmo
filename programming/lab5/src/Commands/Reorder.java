package Commands;

import Collection.CollectionManager;

public class Reorder implements Command{

    CollectionManager collectionManager;

    public Reorder(CollectionManager collectionManager){
        this.collectionManager = collectionManager;
    }
    @Override
    public void execute(String[] args) {
        if (args.length == 1){
            collectionManager.reorder();
        } else throw new IllegalArgumentException("Wrong number of arguments. Command is incorrect. Try again.");
    }

    @Override
    public String getDescription() {
        return "reorder: to sort the collection in reverse order";
    }
}
