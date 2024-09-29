package Commands;

import Collection.CollectionManager;

public class Save implements Command{

    CollectionManager collectionManager;

    public Save(CollectionManager collectionManager){
        this.collectionManager = collectionManager;
    }
    @Override
    public void execute(String[] args) {
        if (args.length == 1){
            collectionManager.save();
        } else throw new IllegalArgumentException("Wrong number of arguments. Command is incorrect. Try again.");
    }

    @Override
    public String getDescription() {
        return "save: to save the collection to file";
    }
}
