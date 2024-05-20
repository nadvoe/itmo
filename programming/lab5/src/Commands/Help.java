package Commands;

import Collection.CollectionManager;

public class Help implements Command {
    CollectionManager collectionManager;

    public Help (CollectionManager collectionManager){
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(String[] args){
        if(args.length == 1){
            collectionManager.help();
        } else throw new IllegalArgumentException("Wrong number of arguments. Command is incorrect. Try again.");
    }

    @Override
    public String getDescription(){
        return "help: to get command description";
    }
}

