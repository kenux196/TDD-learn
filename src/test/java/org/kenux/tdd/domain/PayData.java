package org.kenux.tdd.domain;

import java.time.LocalDate;

public class PayData {
    private LocalDate billingDate;
    private int paymentAmount;

    public PayData() {
    }

    public PayData(LocalDate billingDate, int paymentAmount) {
        this.billingDate = billingDate;
        this.paymentAmount = paymentAmount;
    }

    public LocalDate getBillingDate() {
        return billingDate;
    }

    public int getPaymentAmount() {
        return paymentAmount;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private final PayData data = new PayData();

        public Builder billingDate(LocalDate billingDate) {
            data.billingDate = billingDate;
            return this;
        }

        public Builder payAmount(int payAmount) {
            data.paymentAmount = payAmount;
            return this;
        }

        public PayData build() {
            return data;
        }
    }
}