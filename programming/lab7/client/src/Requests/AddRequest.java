package Requests;

import CoreClasses.User;
import CoreClasses.Vehicle;

public class AddRequest extends Request{
    public final Vehicle vehicle;

    public AddRequest(Vehicle vehicle, User user) {
        super("add", user);
        this.vehicle = vehicle;
    }
}
