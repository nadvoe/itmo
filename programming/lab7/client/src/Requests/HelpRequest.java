package Requests;

import CoreClasses.User;

public class HelpRequest extends Request{
    public HelpRequest(User user){
        super("help", user);
    }
}
