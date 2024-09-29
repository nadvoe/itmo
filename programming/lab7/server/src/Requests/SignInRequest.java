package Requests;

import CoreClasses.User;

public class SignInRequest extends Request{
    public SignInRequest(User user){
        super("signin", user);
    }
}
