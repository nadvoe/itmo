package Commands;

import Client.TCPClient;
import Console.Console;
import Requests.HelpRequest;
import Requests.InfoRequest;
import Responses.HelpResponse;
import Responses.InfoResponse;
import Utilities.SessionHandler;

import java.io.IOException;

public class Info extends Command {
    private final Console console;
    private final TCPClient client;

    public Info(Console console, TCPClient client) {
        super("info", "to get information about the collection.");
        this.console = console;
        this.client = client;
    }

    @Override
    public boolean apply(String[] args) throws IOException, ClassNotFoundException {
        if (SessionHandler.getCurrentUser() == null){
            console.printError("Log in or Sign in to work with collection.");
            return false;
        }

        if (!args[1].isEmpty()) {
            console.println("Incorrect number of arguments.");
            return false;
        }

        var response = (InfoResponse) client.requestProcess(new InfoRequest(SessionHandler.getCurrentUser()));
        if (response.getError() != null && !response.getError().isEmpty()) {
            console.println("Something went wrong: " + response.getError());
            return  false;
        }

        console.println(response.info);
        return true;
    }
}
