package Commands;

import Collection.CollectionManager;

public class GroupCountingByType implements Command{

    CollectionManager collectionManager;
    public GroupCountingByType(CollectionManager collectionManager){
        this.collectionManager = collectionManager;
    }


    @Override
    public void execute(String[] args) {
        if (args.length == 1){
            collectionManager.groupCountingByType();
        } else throw new IllegalArgumentException("Wrong number of arguments. Command is incorrect. Try again.");
    }

    @Override
    public String getDescription() {
        return "group_counting_by_type: to group collection elements by type field values, show the amount of the groups";
    }
}