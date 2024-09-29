package Responses;

import CoreClasses.User;

public class SignInResponse extends Response {
    public final User user;

    public SignInResponse(User user, String error) {
        super("sign_in", error);
        this.user = user;
    }
}
