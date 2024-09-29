package Requests;

import CoreClasses.User;

public class InfoRequest extends Request{
    public InfoRequest(User user){
        super("info", user);
    }
}
