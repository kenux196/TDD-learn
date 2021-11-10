package org.kenux.tdd.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

class ExpiryDateCalculatorTest {

    @Test
    @DisplayName("만원_납부하면_한달_뒤가_만료일이_됨")
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
    @DisplayName("납부일과_한달_뒤_일자가_같지_않음")
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
    @DisplayName("첫_납부일과_만료일_일자가_다를때_만원_납부")
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
    @Test
    @DisplayName("2만 원이상 납부하면 비례해서 만료일이 구해진다.")
    void 이만원_이상_납부하면_비례해서_만료일_계산() {
        assertExpiryDate(PayData.builder()
                .billingDate(LocalDate.of(2021, 3, 1))
                .payAmount(20_000)
                .build(),
                LocalDate.of(2021, 5, 1));

        assertExpiryDate(PayData.builder()
                .billingDate(LocalDate.of(2021, 3, 1))
                .payAmount(30_000)
                .build(),
                LocalDate.of(2021, 6, 1));

        assertExpiryDate(PayData.builder()
                        .billingDate(LocalDate.of(2021, 3, 1))
                        .payAmount(50_000)
                        .build(),
                LocalDate.of(2021, 8, 1));

        assertExpiryDate(PayData.builder()
                        .billingDate(LocalDate.of(2021, 3, 1))
                        .payAmount(70_000)
                        .build(),
                LocalDate.of(2021, 10, 1));
    }

    @Test
    @DisplayName("첫 납부일과 납부일의 일자가 다를 때, 2만원 이상 남부한 경우")
    void test() {
        assertExpiryDate(
                PayData.builder()
                        .firstBillingDate(LocalDate.of(2021, 1, 31))
                        .billingDate(LocalDate.of(2021, 2, 28))
                        .payAmount(20_000)
                        .build(),
                LocalDate.of(2021,4, 30));

        assertExpiryDate(
                PayData.builder()
                        .firstBillingDate(LocalDate.of(2021, 1, 31))
                        .billingDate(LocalDate.of(2021, 2, 28))
                        .payAmount(30_000)
                        .build(),
                LocalDate.of(2021,5, 31));
    }

    private void assertExpiryDate(PayData payData, LocalDate expectedExpiryDate) {
        ExpiryDateCalculator calculator = new ExpiryDateCalculator();
        LocalDate realExpiryDate = calculator.calculateExpiryDate(payData);
        Assertions.assertThat(realExpiryDate).isEqualTo(expectedExpiryDate);
    }
}
