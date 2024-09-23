package Client;

import Commands.*;
import Console.Console;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class ClientRunner {

    private final Console console;
    private final TCPClient client;

    private final Map<String, Command> commands;
    private final List<String> scriptStack = new ArrayList<>();
    private int lengthRecursion = -1;

    public ClientRunner(TCPClient client, Console console) {
        this.client = client;
        this.console = console;
        this.commands = new HashMap<>() {{
            put("help", new Help(console, client));
            put("info", new Info(console, client));
            put("show", new Show(console, client));
            put("add", new Add(console, client));
            put("update", new UpdateId(console, client));
            put("remove_by_id", new RemoveById(console, client));
            put("clear", new Clear(console, client));
            put("execute_script", new ExecuteScript(console));
            put("exit", new Exit(console));
            put("remove_last", new RemoveLast(console, client));
            put("add_if_min", new AddIfMin(console, client));
            put("reorder", new Reorder(console, client));
            put("group_counting_by_type", new GroupCountingByType(console, client));
            put("print_field_ascending_fuel_consumption", new PrintFieldAscendingFuelConsumption(console, client));
            put("print_field_descending_type", new PrintFieldDescendingType(console, client));
        }};
    }

    public void interactiveMode(){
        try {
            String[] userCommand = {"", ""};

            do {
                userCommand = (console.readln().trim() + " ").split(" ", 2);
                userCommand[1] = userCommand[1].trim();

                applyCommand(userCommand);
            } while (!userCommand[0].equals("exit"));

        } catch (NoSuchElementException e) {
            console.println("The client input was not founded.");
        }  catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void scriptMode (String argument) throws IOException {
        int a = 0;
        String[] userCommand = {"",""};
        File file = new File(argument);

        if (!file.exists()){
            console.println(argument);
            console.printError("Such file does not exist.");
            if (!Files.isReadable(Paths.get(argument))){
                console.printError("File is not readable.");
            }
        }

        scriptStack.add(argument);
        try (InputStreamReader scriptReader = new InputStreamReader(new FileInputStream(argument))){
            PushbackReader reader = new PushbackReader(scriptReader);

            if(!scriptReader.ready()) throw new NoSuchElementException();
            console.selectFileReader(reader);
            do {
                userCommand = (console.filereadln().trim() + " ").split(" ", 2);
                userCommand[1] = userCommand[1].trim();
                while (console.isCanReadln(reader) && userCommand[0].isEmpty()){
                    userCommand = (console.filereadln().trim() + " ").split(" ", 2);
                    userCommand[1] = userCommand[1].trim();
                }

                var needApply = true;
                if (userCommand[0].equals("execute_script")){
                    needApply = checkRecursion(userCommand[1], reader);
                }

                if (needApply){
                    applyCommand(userCommand);
                }

                if (userCommand[0].equals("execute_script")) console.selectFileReader(reader);
                a = reader.read();
                reader.unread(a);
            } while (console.isCanReadln(reader));
            console.selectConsoleReader();
        } catch (FileNotFoundException exception) {
            console.printError("File not founded.");
        } catch (NoSuchElementException exception) {
            console.printError("File is empty.");
        } catch (IllegalStateException | IOException e) {
            console.printError("Error.");
            System.exit(0);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            scriptStack.remove(scriptStack.size() - 1);
        }
    }

    public void applyCommand(String[] userCommand) throws IOException, ClassNotFoundException {
        var command = commands.get(userCommand[0]);

        if (command == null) {
            console.printError("Command '"+ userCommand[0] + "' was not founded. Type 'help' to see available commands.");
            return;
        }

        switch (userCommand[0]){
            case "exit" -> {
                commands.get("exit").apply(userCommand);
            }
            case "execute_script" -> {
                scriptMode(userCommand[1]);
            }
            default -> {
                command.apply(userCommand);
            }
        }
    }

    private boolean checkRecursion(String argument, PushbackReader scriptReader) {
        var recStart = -1;
        var i = 0;
        for (String script : scriptStack) {
            i++;
            if (argument.equals(script)) {
                if (recStart < 0) recStart = i;
                if (lengthRecursion < 0) {
                    console.selectConsoleReader();
                    console.println("The are recursion! Select the  (0..500)");
                    while (lengthRecursion < 0 || lengthRecursion > 500) {
                        try { console.println("> "); lengthRecursion = Integer.parseInt(console.readln().trim()); } catch (NumberFormatException e) { console.println("The depth is not identified."); }
                    }
                    console.selectFileReader(scriptReader);
                }
                if (i > recStart + lengthRecursion || i > 500)
                    return false;
            }
        }
        return true;
    }

}
