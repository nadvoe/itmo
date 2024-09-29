package Responses;

public class ShowResponse extends Response{
    public final String str;
    public ShowResponse(String str, String error){
        super("show", error);
        this.str = str;
    }
}
