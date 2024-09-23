package Commands;

import Client.TCPClient;
import Console.Console;
import Requests.ReorderRequest;
import Responses.ReorderResponse;

import java.io.IOException;

public class Reorder extends Command{

    private final Console console;
    private final TCPClient client;

    public Reorder(Console console, TCPClient client) {
        super("reorder", "to sort the collection in reverse order");
        this.console = console;
        this.client = client;
    }

    @Override
    public boolean apply(String[] args) throws IOException, ClassNotFoundException {
        if (!args[1].isEmpty()) {
            console.println("Incorrect number of arguments.");
            return false;
        }

        var response = (ReorderResponse) client.requestProcess(new ReorderRequest());
        if (response.getError() != null && !response.getError().isEmpty()) {
            console.println("Something went wrong: " + response.getError());
            return  false;
        }

        console.println("The collection was successfully reordered.");
        return true;
    }
}
