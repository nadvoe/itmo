package Responses;

import java.io.Serializable;

public class Response implements Serializable {
    private final String name;
    private final String error;

    public Response(String name, String error) {
        this.name = name;
        this.error = error;
    }

    public String getName() {
        return name;
    }

    public String getError() {
        return error;
    }
}
