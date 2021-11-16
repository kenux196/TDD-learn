package org.kenux.tdd.domain;

import java.time.LocalDateTime;

public class AutoDebitInfo {

    private String userId;
    private String cardNumber;
    private LocalDateTime updatedTime;


    public AutoDebitInfo(String userId, String cardNumber, LocalDateTime updatedTime) {
        this.userId = userId;
        this.cardNumber = cardNumber;
        this.updatedTime = updatedTime;
    }


    public String getUserId() {
        return userId;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public LocalDateTime getUpdatedTime() {
        return updatedTime;
    }

    public void changeCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }
}
