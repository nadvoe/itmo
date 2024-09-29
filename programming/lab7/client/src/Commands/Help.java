package Commands;

import Client.TCPClient;
import Console.Console;
import Requests.GroupCountingByTypeRequest;
import Requests.HelpRequest;
import Responses.GroupCountingByTypeResponse;
import Responses.HelpResponse;
import Utilities.SessionHandler;

import java.io.IOException;

public class Help extends Command {
    private final Console console;
    private final TCPClient client;

    public Help(Console console, TCPClient client) {
        super("help", "to get commands' descriptions.");
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

        var response = (HelpResponse) client.requestProcess(new HelpRequest(SessionHandler.getCurrentUser()));
        if (response.getError() != null && !response.getError().isEmpty()) {
            console.println("Something went wrong: " + response.getError());
            return false;
        }

        console.println(response.help);
        return true;
    }
}
