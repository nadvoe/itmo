package Responses;

public class PrintFieldAscendingFuelConsumptionResponse extends Response{

    public final String str;
    public PrintFieldAscendingFuelConsumptionResponse(String str, String error){
        super("print_field_ascending_fuel_consumption", error);
        this.str = str;
    }
}
