package org.kenux.tdd.chapter8;

import java.time.LocalDate;

public class Subscription {
    public boolean isFinished(LocalDate now) {
        return true;
    }

    public CustomerGrade getGrade() {
        return CustomerGrade.GOLD;
    }

    public int getProductId() {
        return 1;
    }
}
