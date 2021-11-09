package org.kenux.tdd.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

class ExpiryDateCalculatorTest {

    @Test
    void 만원_납부하면_한달_뒤가_만료일이_됨() {
        assertExpiryDate(
                PayData.builder()
                        .billingDate(LocalDate.of(2021, 3, 1))
                        .payAmount(10_000)
                        .build(),
                LocalDate.of(2021, 4, 1));
        assertExpiryDate(
                PayData.builder()
                        .billingDate(LocalDate.of(2021, 3, 5))
                        .payAmount(10_000)
                        .build(),
                LocalDate.of(2021, 4, 5));
    }

    @Test
    void 납부일과_한달_뒤_일자가_같지_않음() {
        assertExpiryDate(
                PayData.builder()
                        .billingDate(LocalDate.of(2021, 1, 31))
                        .payAmount(10_000)
                        .build(),
                LocalDate.of(2021, 2, 28));
        assertExpiryDate(
                PayData.builder()
                        .billingDate(LocalDate.of(2021, 5, 31))
                        .payAmount(10_000)
                        .build(),
                LocalDate.of(2021, 6, 30));
        assertExpiryDate(
                PayData.builder()
                        .billingDate(LocalDate.of(2020, 1, 31))
                        .payAmount(10_000)
                        .build(),
                LocalDate.of(2020, 2, 29));
    }

    @Test
    void 첫_납부일과_만료일_일자가_다를때_만원_납부() {
        PayData payData = PayData.builder()
                .firstBillingDate(LocalDate.of(2021, 1, 31))
                .billingDate(LocalDate.of(2021, 2, 28))
                .payAmount(10_000)
                .build();

        assertExpiryDate(payData, LocalDate.of(2021, 3, 31));

        PayData payData1 = PayData.builder()
                .firstBillingDate(LocalDate.of(2021, 1, 30))
                .billingDate(LocalDate.of(2021, 2, 28))
                .payAmount((10_000))
                .build();

        assertExpiryDate(payData1, LocalDate.of(2021, 3, 30));
    }

    private void assertExpiryDate(PayData payData, LocalDate expectedExpiryDate) {
        ExpiryDateCalculator calculator = new ExpiryDateCalculator();
        LocalDate realExpiryDate = calculator.calculateExpiryDate(payData);
        Assertions.assertThat(realExpiryDate).isEqualTo(expectedExpiryDate);
    }
}
