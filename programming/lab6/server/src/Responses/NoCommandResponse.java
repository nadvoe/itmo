package Responses;

public class NoCommandResponse extends Response{
    public NoCommandResponse (String command){
        super(command, "There is no such command.");
    }
}
