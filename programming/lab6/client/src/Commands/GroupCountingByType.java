package Commands;

import Client.TCPClient;
import Console.Console;
import Requests.GroupCountingByTypeRequest;
import Responses.GroupCountingByTypeResponse;

import java.io.IOException;

public class GroupCountingByType extends Command{

    private final Console console;
    private final TCPClient client;

    public GroupCountingByType(Console console, TCPClient client){
        super("group_counting_by_type", "to group collection elements by type field values, show the amount of the groups");
        this.console = console;
        this.client = client;
    }

    @Override
    public boolean apply(String[] args) throws IOException, ClassNotFoundException {
        if (!args[1].isEmpty()){
            console.println("Incorrect number of arguments.");
            return false;
        }

        var response = (GroupCountingByTypeResponse) client.requestProcess(new GroupCountingByTypeRequest());
        if (response.getError() != null && !response.getError().isEmpty()){
            console.println("Something went wrong: " + response.getError());
            return false;
        }

        console.println("The collection was successfully grouped.");
        return true;
    }


}
