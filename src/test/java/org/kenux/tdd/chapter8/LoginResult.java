package org.kenux.tdd.chapter8;

public class LoginResult {
    public static LoginResult badAuthKey() {
        return new LoginResult();
    }

    public static LoginResult fail(int resp) {
        return new LoginResult();
    }

    public static LoginResult authenticated(Customer c) {
        return new LoginResult();
    }
}
