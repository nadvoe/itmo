package Commands;

import Console.Console;

public class Exit extends Command{

    private final Console console;

    public Exit(Console console){
        super("exit", "to exit");
        this.console = console;
    }

    @Override
    public boolean apply(String[] args){
        if (!args[1].isEmpty()) {
            console.println("How to use : '" + getName() + "'");
            return false;
        }

        console.println("░░░░█▀▀▀█▀▀▀█▀▀▀▀▄░░░░░░░░░░\n" +
                        "░░▄▀▀░░░░░░░░░░░░░█▄▄░░░░░░░\n" +
                        "░█░░░░░░░░░░░░░░░░▀░▀█░░░░░░\n" +
                        "░█▀░░░░░░░░░░░░░░░░░░░█░░░░░\n" +
                        "░█▄░▄░░░▄█▄▄█░▄░▄▄░░░▄████▄░\n" +
                        "░░██▀▀▀▀▀▄▄░█░█▀▀░▀██▀░░▀██▄\n" +
                        "░░██░██████░█▄█░██░██░░░░███\n" +
                        "░░██░███████▄█▄███░██░░░░██▀\n" +
                        "░░██░█████████████░██░░░▄██░\n" +
                        "░░██░█████████████░██░▄██▀░░\n" +
                        "░░██░█████████████░██▀▀▀░░░░\n" +
                        "░░██░█████████████░██░░░░░░░\n" +
                        "░░██░████████████▀░█▀░░░░░░░\n" +
                        "░░░▀█▄▄▄▄▄▄▄▄▄▄▄▄▀▀░░░░░░░░░".indent(1));

        console.println("До свидания (на допсе)...");
        return true;
    }
}