package org.kenux.tdd.chapter8;

public class AuthService {
    private String authKey = "someKey";

    public int authenticate(String id, String pw) {
        boolean authorized = AuthUtil.authorize(authKey);
        if (authorized) {
            return AuthUtil.authentication(id, pw);
        } else {
            return -1;
        }
    }
}
