package Commands;

import Client.TCPClient;
import Console.Console;
import Requests.HelpRequest;
import Requests.PrintFieldAscendingFuelConsumptionRequest;
import Responses.HelpResponse;
import Responses.PrintFieldAscendingFuelConsumptionResponse;

import java.io.IOException;

public class PrintFieldAscendingFuelConsumption extends Command{

    private final Console console;
    private final TCPClient client;

    public PrintFieldAscendingFuelConsumption(Console console, TCPClient client) {
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

        var response = (PrintFieldAscendingFuelConsumptionResponse) client.requestProcess(new PrintFieldAscendingFuelConsumptionRequest());
        if (response.getError() != null && !response.getError().isEmpty()) {
            console.println("Something went wrong: " + response.getError());
            return false;
        }

        console.println(response.str);
        return true;
    }
}
