package Requests;

import CoreClasses.Vehicle;

public class AddIfMinRequest extends Request {
    public final Vehicle vehicle;

    public AddIfMinRequest(Vehicle vehicle){
        super("add_if_min");
        this.vehicle = vehicle;
    }
}
