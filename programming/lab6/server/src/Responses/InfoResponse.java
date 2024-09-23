package Responses;

public class InfoResponse extends Response{

    public final String info;
    public InfoResponse(String info, String error){
        super("info", error);
        this.info = info;
    }
}
