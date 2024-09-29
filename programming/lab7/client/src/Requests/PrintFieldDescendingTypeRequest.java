package Requests;

import CoreClasses.User;

public class PrintFieldDescendingTypeRequest extends Request{
    public PrintFieldDescendingTypeRequest(User user){
        super("print_field_descending_type", user);
    }
}
