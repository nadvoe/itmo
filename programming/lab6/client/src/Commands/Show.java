package Commands;

import Client.TCPClient;
import Console.Console;
import Requests.ShowRequest;
import Responses.ShowResponse;

import java.io.IOException;

public class Show extends Command{

    private final Console console;
    private final TCPClient client;

    public Show(Console console, TCPClient client) {
        super("show", "to show the collection");
        this.console = console;
        this.client = client;
    }

    @Override
    public boolean apply(String[] args) throws IOException, ClassNotFoundException {
        if (!args[1].isEmpty()) {
            console.println("Incorrect number of arguments.");
            return false;
        }

        var response = (ShowResponse) client.requestProcess(new ShowRequest());
        if (response.getError() != null && !response.getError().isEmpty()) {
            console.println("Something went wrong: " + response.getError());
            return  false;
        }

        console.println(response.str);
        return true;
    }
}
