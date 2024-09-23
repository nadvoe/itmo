package Commands;

import Client.TCPClient;
import Console.Console;
import Requests.PrintFieldAscendingFuelConsumptionRequest;
import Requests.RemoveByIdRequest;
import Responses.PrintFieldAscendingFuelConsumptionResponse;
import Responses.RemoveByIdResponse;

import java.io.IOException;

public class RemoveById extends Command{

    private final Console console;
    private final TCPClient client;

    public RemoveById (Console console, TCPClient client) {
        super("remove_by_id {id}", "to delete element from collection on his id");
        this.console = console;
        this.client = client;
    }

    @Override
    public boolean apply(String[] args) throws IOException, ClassNotFoundException {
        if (args[1].isEmpty()) {
            console.println("Incorrect number of arguments.");
            return false;
        }

        long id;
        try { id = Long.parseLong(args[1].trim()); } catch (NumberFormatException e) {
            console.println("Wrong id format.");
            return false;
        }

        var response = (RemoveByIdResponse) client.requestProcess(new RemoveByIdRequest(id));
        if (response.getError() != null && !response.getError().isEmpty()) {
            console.println("Something went wrong: " + response.getError());
            return false;
        }

        console.println("The element was successfully deleted");
        return true;
    }
}
