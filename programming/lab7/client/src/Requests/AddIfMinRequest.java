package Requests;

import CoreClasses.User;
import CoreClasses.Vehicle;

public class AddIfMinRequest extends Request {
    public final Vehicle vehicle;

    public AddIfMinRequest(Vehicle vehicle, User user){
        super("add_if_min", user);
        this.vehicle = vehicle;
    }
}
