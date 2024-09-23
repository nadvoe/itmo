package Responses;

public class PrintFieldDescendingTypeResponse extends Response{
    public final String str;
    public PrintFieldDescendingTypeResponse(String str, String error){
        super("print_field_descending_type", error);
        this.str = str;
    }
}
