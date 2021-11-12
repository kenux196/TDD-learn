package org.kenux.tdd.domain;

import java.time.LocalDate;
import java.time.YearMonth;

public class ExpiryDateCalculator {

    public LocalDate calculateExpiryDate(PayData payData) {
        int addedMonth = payData.getPaymentAmount() == 100_000 ? 12 : payData.getPaymentAmount() / 10_000;
        if (payData.getFirstBillingDate() != null) {
            return expiryDAteUsingFirstBillingDate(payData, addedMonth);
        } else {
            return payData.getBillingDate().plusMonths(addedMonth);
        }
    }

    private LocalDate expiryDAteUsingFirstBillingDate(PayData payData, int addedMonth) {
        LocalDate candidateExp = payData.getBillingDate().plusMonths(addedMonth);
        if (!isSameDayOfMonth(payData.getFirstBillingDate(), candidateExp)) {
            final int dayLengthOfCandidateMonth = lastDayOfMonth(candidateExp);
            final int dayOfFirstBilling = payData.getFirstBillingDate().getDayOfMonth();
            if (dayLengthOfCandidateMonth < dayOfFirstBilling) {
                return candidateExp.withDayOfMonth(dayLengthOfCandidateMonth);
            }
            return candidateExp.withDayOfMonth(dayOfFirstBilling);
        } else {
            return candidateExp;
        }
    }

    private boolean isSameDayOfMonth(LocalDate date1, LocalDate date2) {
        return date1.getDayOfMonth() == date2.getDayOfMonth();
    }

    private int lastDayOfMonth(LocalDate date) {
        return YearMonth.from(date).lengthOfMonth();
    }
}