package Commands;

import Collection.CollectionManager;

public class Info implements Command{
    CollectionManager collectionManager;

    public Info (CollectionManager collectionManager){
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(String[] args){
        if (args.length == 1){
            collectionManager.info();
        } else throw new IllegalArgumentException("Wrong number of arguments. Command is incorrect. Try again.");
    }

    @Override
    public String getDescription(){
        return "info: to get information about collection";
    }
}
