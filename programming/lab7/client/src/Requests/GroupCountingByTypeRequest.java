package Requests;

import CoreClasses.User;

public class GroupCountingByTypeRequest extends Request {
    public GroupCountingByTypeRequest(User user){
        super("group_counting_by_type", user);
    }
}
