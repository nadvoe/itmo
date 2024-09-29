package Requests;

import CoreClasses.User;

public class PrintFieldAscendingFuelConsumptionRequest extends Request{
    public PrintFieldAscendingFuelConsumptionRequest(User user){
        super("print_field_ascending_fuel_consumption", user);
    }
}
