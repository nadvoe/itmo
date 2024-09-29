package Requests;

import CoreClasses.User;

public class ShowRequest extends Request{
    public ShowRequest(User user){
        super("show", user);
    }
}
