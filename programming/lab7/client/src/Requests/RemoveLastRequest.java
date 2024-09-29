package Requests;

import CoreClasses.User;

public class RemoveLastRequest extends Request {
    public RemoveLastRequest(User user){
        super("remove_last", user);
    }
}
