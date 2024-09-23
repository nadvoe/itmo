package Commands;

import Asker.ReadManager;
import Client.TCPClient;
import Console.Console;
import Requests.AddRequest;
import Responses.AddResponse;

import java.io.IOException;

public class Add extends Command {
    private final Console console;
    private final TCPClient client;

    public Add (Console console, TCPClient client){
        super("add {element}", "to add element");
        this.console = console;
        this.client = client;
    }

    @Override
    public boolean apply(String[] args) throws ClassNotFoundException, IOException {
        if (!args[1].isEmpty()){
            console.println("Incorrect number of arguments.");
            return false;
        }

        var newVehicle = ReadManager.readVehicle();
        var response = (AddResponse) client.requestProcess(new AddRequest(newVehicle));
        if (response.getError() != null && !response.getError().isEmpty()){
            console.println("Something went wrong: " + response.getError());
            return false;
        }

        console.println("Vehicle with id = " + response.newId + "  was successfully added.");
        return true;
    }
}
