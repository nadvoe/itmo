package Requests;

import CoreClasses.User;
import CoreClasses.Vehicle;

public class UpdateIdRequest extends Request{
    public final Vehicle vehicle;
    public final long id;

    public UpdateIdRequest(Vehicle vehicle, long id, User user){
        super("update", user);
        this.vehicle = vehicle;
        this.id = id;
    }
}
