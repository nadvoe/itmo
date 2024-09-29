package Requests;

import CoreClasses.User;

public class RemoveByIdRequest extends Request{
    public final long id;

    public RemoveByIdRequest(long id, User user){
        super("remove_by_id", user);
        this.id = id;
    }

}
