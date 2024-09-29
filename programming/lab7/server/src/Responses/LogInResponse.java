package Responses;

import CoreClasses.User;

public class LogInResponse extends Response{
    public final User user;

    public LogInResponse(User user, String error){
        super("log_in", error);
        this.user = user;
    }
}
