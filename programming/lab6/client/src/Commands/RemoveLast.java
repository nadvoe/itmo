package Commands;

import Client.TCPClient;
import Console.Console;
import Requests.RemoveByIdRequest;
import Requests.RemoveLastRequest;
import Responses.RemoveByIdResponse;
import Responses.RemoveLastResponse;

import java.io.IOException;

public class RemoveLast extends Command{

    private final Console console;
    private final TCPClient client;

    public RemoveLast (Console console, TCPClient client) {
        super("remove_last", "to remove the last element from collection");
        this.console = console;
        this.client = client;
    }

    @Override
    public boolean apply(String[] args) throws IOException, ClassNotFoundException {
        if (!args[1].isEmpty()) {
            console.println("Incorrect number of arguments");
            return false;
        }

        var response = (RemoveLastResponse) client.requestProcess(new RemoveLastRequest());
        if (response.getError() != null && !response.getError().isEmpty()) {
            console.println("Something went wrong: " + response.getError());
            return false;
        }

        console.println("The last element was successfully deleted");
        return true;
    }
}
