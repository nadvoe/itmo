package Requests;

public class RemoveByIdRequest extends Request{
    public final long id;

    public RemoveByIdRequest(long id){
        super("remove_by_id");
        this.id = id;
    }

}
