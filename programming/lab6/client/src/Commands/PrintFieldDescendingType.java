package Commands;

import Client.TCPClient;
import Console.Console;
import Requests.PrintFieldAscendingFuelConsumptionRequest;
import Requests.PrintFieldDescendingTypeRequest;
import Responses.PrintFieldAscendingFuelConsumptionResponse;
import Responses.PrintFieldDescendingTypeResponse;

import java.io.IOException;

public class PrintFieldDescendingType extends Command{
    private final Console console;
    private final TCPClient client;

    public PrintFieldDescendingType(Console console, TCPClient client) {
        super("print_field_ascending_fuel_consumption", "to show fields fuelConsumption of all elements in ascending order");
        this.console = console;
        this.client = client;
    }

    @Override
    public boolean apply(String[] args) throws IOException, ClassNotFoundException {
        if (!args[1].isEmpty()) {
            console.println("Incorrect number of arguments.");
            return false;
        }

        var response = (PrintFieldDescendingTypeResponse) client.requestProcess(new PrintFieldDescendingTypeRequest());
        if (response.getError() != null && !response.getError().isEmpty()) {
            console.println("Something went wrong: " + response.getError());
            return false;
        }

        console.println(response.str);
        return true;
    }
}
