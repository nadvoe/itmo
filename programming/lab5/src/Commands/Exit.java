package Commands;

import Collection.CollectionManager;

public class Exit implements Command {

    CollectionManager collectionManager;

    public Exit(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(String[] args) {
        if (args.length == 1) {
            collectionManager.exit();
        } else throw new IllegalArgumentException("Wrong number of arguments. Command is incorrect. Try again.");
    }

    @Override
    public String getDescription() {
        return "exit: to finish program (without saving to file)";
    }
}