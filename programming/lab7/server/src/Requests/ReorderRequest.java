package Requests;

import CoreClasses.User;

public class ReorderRequest extends Request{
    public ReorderRequest(User user){
        super("reorder", user);
    }
}
