import Collection.CollectionManager;
import Commands.CommandManager;
import Console.ConsoleManager;

import java.io.File;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        if (args.length > 0) {
            String filename = args[0];
            //}
            try {
                ConsoleManager consoleManager = new ConsoleManager();
                //String filename = null;
                CollectionManager collectionManager = new CollectionManager(consoleManager, filename);
                //System.out.println("Hello! Enter the collection file name:");
                //filename = consoleManager.readLine();
                File file = new File(filename);
                if (file.exists() && file.canRead() && file.canWrite()) {
                    collectionManager.setFileName(filename);
                    collectionManager.load();
                    //break;
                } else {
                    System.out.println("File doesn't exist or is not available.");
                    System.out.println("Work is not possible without collection! Bye-bye, baby.");
                    System.exit(0);
                }
                Scanner scanner = new Scanner(System.in);
                System.out.println("Please, press 'enter' to start.");
                if (scanner.hasNextLine()){
                    CommandManager commandManager = new CommandManager(collectionManager);
                    while (true) {
                        //String message = scanner.nextLine();
                        System.out.print("Enter the command: ");
                        String a = consoleManager.readLine();
                        commandManager.checkCommandExists(a);
                    }
                } else {

                }
//                CommandManager commandManager = new CommandManager(collectionManager);
//                while (true) {
//                    System.out.print("Enter the command: ");
//                    String a = consoleManager.readLine();
//                    commandManager.checkCommandExists(a);
//                }
            } catch (Exception e) {
                System.out.println("Thank you for work! Bye-bye, baby.");
                System.exit(0);
            }
        }
    }
}


