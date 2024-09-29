package Requests;

import CoreClasses.User;

public class ClearRequest extends Request{
    public ClearRequest(User user){
        super("clear", user);
    }
}
