package Responses;

public class AddIfMinResponse extends Response {
    public final int newId;

    public AddIfMinResponse(int newId, String error) {
        super("add_if_min", error);
        this.newId = newId;
    }
}
