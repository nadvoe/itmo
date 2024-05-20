package Commands;

import Collection.CollectionManager;

import java.util.HashMap;
import java.util.Scanner;

public class CommandManager {
    private boolean isWorking = true;

    private static HashMap<String, Command> commands = new HashMap<>();

    private String filename;

    public CommandManager (CollectionManager collectionManager){
        commands.put("help", new Help(collectionManager));
        commands.put("info", new Info(collectionManager));
        commands.put("show", new Show(collectionManager));
        commands.put("add", new AddElement(collectionManager));
        commands.put("update", new UpdateId(collectionManager));
        commands.put("remove_by_id", new RemoveById(collectionManager));
        commands.put("clear", new Clear(collectionManager));
        commands.put("save", new Save(collectionManager));
        commands.put("execute_script", new ExecuteScript(collectionManager));
        commands.put("exit", new Exit(collectionManager));
        commands.put("remove_last", new RemoveLast(collectionManager));
        commands.put("add_if_min", new AddIfMin(collectionManager));
        commands.put("reorder", new Reorder(collectionManager));
        commands.put("group_counting_by_type", new GroupCountingByType(collectionManager));
        commands.put("print_field_ascending_fuel_consumption", new PrintFieldAscendingFuelConsumption(collectionManager));
        commands.put("print_field_descenfing_type", new PrintFieldDescendingType(collectionManager));
    }

    public void setFilename(String filename){
        this.filename = filename;
    }

    public static HashMap<String, Command> getCommands(){
        return commands;
    }

    public boolean getIsWorking(){
        return this.isWorking;
    }

    public void checkCommandExists(String a){
        Scanner scanner = new Scanner(System.in);
        try{
            System.out.flush();
            String[] args = a.split(" ");
            if (commands.containsKey(args[0])){
                try{
                    commands.get(args[0]).execute(args);
                } catch (IllegalArgumentException e){
                    System.out.println("Something went wrong. " + e.getMessage() + " Try again.");
                }
            } else {
                System.out.println("Command \"" + args[0] + "\" is not found.");
            }
        } catch (Exception e){
            System.out.println("Something went wrong. " + e.getMessage());
        }
    }
}
