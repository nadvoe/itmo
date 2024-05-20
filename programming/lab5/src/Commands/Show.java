package Commands;

import Collection.CollectionManager;

public class Show implements Command{

    CollectionManager collectionManager;

    public Show (CollectionManager collectionManager){
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(String[] args) {
        if (args.length == 1){
            collectionManager.show();
        } else throw new IllegalArgumentException("Wrong number of the arguments. Command is incorrect. Try again.");
    }

    @Override
    public String getDescription() {
        return "show: output the collection elements in a string view to the standard output stream";
    }
}
