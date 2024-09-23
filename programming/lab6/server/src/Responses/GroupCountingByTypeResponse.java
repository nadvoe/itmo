package Responses;

public class GroupCountingByTypeResponse extends Response{
    public GroupCountingByTypeResponse(String groupedTypes, String error){
        super("group_counting_by_type", error);
    }
}
