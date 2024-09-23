package Commands;

import Asker.ReadManager;
import Client.TCPClient;
import Console.Console;
import Requests.UpdateIdRequest;
import Responses.UpdateIdResponse;

import java.io.IOException;

public class UpdateId extends Command{

    private final Console console;
    private final TCPClient client;

    public UpdateId(Console console, TCPClient client) {
        super("update id {element}", "to update the fields of the element where id equals to the entered id");
        this.console = console;
        this.client = client;
    }

    @Override
    public boolean apply(String[] args) throws IOException, ClassNotFoundException {
        if (args[0].isEmpty() || args[1].isEmpty() || !args[2].isEmpty()){
            console.printError("Incorrect number of arguments");
            return false;
        }

        long id;
        try { id = Long.parseLong(args[1].trim()); } catch (NumberFormatException e) {
            console.printError("Wrong id format");
            return false;
        }

        var newVehicle = ReadManager.readVehicle();
        var response = (UpdateIdResponse) client.requestProcess(new UpdateIdRequest(newVehicle, id));
        if (response.getError() != null && !response.getError().isEmpty()) {
            console.println(response.getError());
            return false;
        }

        console.println("The element was updated.");
        return true;
    }
}