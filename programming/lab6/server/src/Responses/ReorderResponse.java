package Responses;

public class ReorderResponse extends Response{
    public ReorderResponse(String s, String error){
        super("reorder", error);
    }
}
