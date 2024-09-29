package Responses;

public class AddResponse extends Response {
    public final long newId;

    public AddResponse (int newId, String error){
        super("add", error);
        this.newId = newId;
    }
}
