package Responses;

import Requests.GroupCountingByTypeRequest;

public class GroupCountingByTypeResponse extends Response{
    public GroupCountingByTypeResponse(String error){
        super("group_counting_by_type", error);
    }
}
