package Requests;

import CoreClasses.User;
import CoreClasses.Vehicle;

public class ClearRequest extends Request{
    public ClearRequest(User user){
        super("clear", user);
    }
}
