package Commands;

import Console.Console;

public class ExecuteScript extends Command{

    private final Console console;

    public ExecuteScript(Console console){
        super("execute_script <file_name>", "to execute script in file");
        this.console = console;
    }

    @Override
    public boolean apply(String[] arguments){
        if (arguments[1].isEmpty()){
            console.println("Incorrect number of arguments.");
            console.println("How to use: '" + getName() + "'");
            return false;
        } else {
            console.println("Executing the script ' " + arguments[1] + " ' ");
            return true;
        }
    }

}
