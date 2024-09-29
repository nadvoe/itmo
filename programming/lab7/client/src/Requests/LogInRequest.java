package Requests;

import CoreClasses.User;

public class LogInRequest extends Request{
    public LogInRequest(User user){
        super("login", user);
    }
}
