package org.kenux.tdd.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

class ExpiryDateCalculatorTest {

    @Test
    void 만원_납부하면_한달_뒤가_만료일이_됨() {
        assertExpiryDate(
                LocalDate.of(2021,1,5),
                10_000,
                LocalDate.of(2021, 2, 5));
        assertExpiryDate(
                LocalDate.of(2021,3,5),
                10_000,
                LocalDate.of(2021, 4, 5));
    }

    @Test
    void 납부일과_한달_뒤_일자가_같지_않음() {
        assertExpiryDate(
                LocalDate.of(2021,1,31),
                10_000,
                LocalDate.of(2021, 2, 28));
        assertExpiryDate(
                LocalDate.of(2021,5,31),
                10_000,
                LocalDate.of(2021, 6, 30));
        assertExpiryDate(
                LocalDate.of(2020,1,31),
                10_000,
                LocalDate.of(2020, 2, 29));
    }

    private void assertExpiryDate(PayData payData, LocalDate expectedExpiryDate) {
        ExpiryDateCalculator calculator = new ExpiryDateCalculator();
        LocalDate expiryDate = calculator.calculateExpiryDate(payData);
        Assertions.assertThat(expiryDate).isEqualTo(expectedExpiryDate);
    }
}
