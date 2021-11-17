package org.kenux.tdd.service;

public class SpyEmailNotifier implements EmailNotifier {
    private boolean called;
    private String email;

    public boolean isCalled() {
        return this.called;
    }

    public String getEmail() {
        return this.email;
    }

    @Override
    public void sendRegisterEmail(String email) {
        this.called = true;
        this.email = email;
    }
}
