package Responses;

public class PrintFieldDescendingTypeResponse extends Response{
    public PrintFieldDescendingTypeResponse(String descendingType,String error){
        super("print_field_descending_type", error);
    }
}
