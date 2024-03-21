package Exceptions;

public class JewelleryException extends Exception{
    private String gift;
    public JewelleryException(String message, Throwable cause, String gift){
        super(message, cause);
        this.gift = gift;
    }
    public JewelleryException(String message){
        super(message);
    }
}


