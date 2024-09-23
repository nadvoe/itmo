package Responses;

public class HelpResponse extends Response{

    public final String help;
    public HelpResponse(String help, String error){
        super("help", error);
        this.help = help;
    }
}
