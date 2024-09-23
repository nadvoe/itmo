package Requests;

import CoreClasses.Vehicle;

public class UpdateIdRequest extends Request{
    public final Vehicle vehicle;
    public final long id;

    public UpdateIdRequest(Vehicle vehicle, long id){
        super("update");
        this.vehicle = vehicle;
        this.id = id;
    }
}
