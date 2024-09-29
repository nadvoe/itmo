package Responses;

public class ReorderResponse extends Response{
    public ReorderResponse(String error){
        super("reorder", error);
    }
}
