package Requests;

import CoreClasses.Vehicle;

public class AddRequest extends Request{
    public final Vehicle vehicle;

    public AddRequest(Vehicle vehicle) {
        super("add");
        this.vehicle = vehicle;
    }
}
