package Commands;

import Asker.ReadManager;
import Client.TCPClient;
import Console.Console;
import Requests.AddIfMinRequest;
import Requests.AddRequest;
import Responses.AddIfMinResponse;
import Responses.AddResponse;

import java.io.IOException;

public class AddIfMin extends Command{
    private final Console console;
    private final TCPClient client;

    public AddIfMin(Console console, TCPClient client){
        super("add_if_min {element}", "to add new element in the collection, if its value is smaller than smallest element's value in the collection");
        this.console = console;
        this.client = client;
    }

    @Override
    public boolean apply(String[] args) throws IOException, ClassNotFoundException {
        if (!args[1].isEmpty()){
            console.println("Incorrect number of arguments.");
            return false;
        }

        var newVehicle = ReadManager.readVehicle();
        var response = (AddIfMinResponse) client.requestProcess(new AddIfMinRequest(newVehicle));
        if (response.getError() != null && !response.getError().isEmpty()){
            console.println("Something went wrong: " + response.getError());
            return false;
        }

        console.println("Vehicle with id = " + response.newId + " was successfully added.");
        return true;
    }
}
