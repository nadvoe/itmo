package Commands;

import Console.Console;
import Utilities.SessionHandler;

public class ExecuteScript extends Command{

    private final Console console;

    public ExecuteScript(Console console){
        super("execute_script <file_name>", "to execute script in file");
        this.console = console;
    }

    @Override
    public boolean apply(String[] arguments){
        if (SessionHandler.getCurrentUser() == null){
            console.printError("Log in or Sign in to work with collection.");
            return false;
        }
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
