package Commands;

import Collection.CollectionManager;

public class PrintFieldDescendingType implements Command{

    CollectionManager collectionManager;

    public PrintFieldDescendingType(CollectionManager collectionManager){
            this.collectionManager = collectionManager;
    }

    @Override
    public void execute(String[] args) {
        if (args.length == 1){
            collectionManager.printFieldDescendingType();
        } else throw new IllegalArgumentException("Wrong number of arguments. Command is incorrect. Try again.");
    }

    @Override
    public String getDescription() {
        return "print_field_descending_type: to show fields type of all elements in descending order";
    }
}