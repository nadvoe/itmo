package Commands;

import Client.TCPClient;
import Console.Console;
import Requests.ClearRequest;
import Responses.ClearResponse;

import java.io.IOException;

public class Clear extends Command{

    private final Console console;
    private final TCPClient client;

    public Clear(Console console, TCPClient client){
        super("clear", "to clear the collection");
        this.console = console;
        this.client = client;
    }

    @Override
    public boolean apply(String[] args) throws IOException, ClassNotFoundException {
        if (!args[1].isEmpty()){
            console.println("Incorrect number of arguments.");
            return false;
        }

        var response = (ClearResponse) client.requestProcess( new ClearRequest());
        if (response.getError() != null && !response.getError().isEmpty()){
            console.println("Something went wrong: " + response.getError());
            return false;
        }

        console.println("The collection was successfully cleared.");
        return true;
    }
}
