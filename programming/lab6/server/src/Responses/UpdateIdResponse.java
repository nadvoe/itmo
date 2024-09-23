package Responses;

public class UpdateIdResponse extends Response{
    public UpdateIdResponse(String error){
        super("update", error);
    }
}
