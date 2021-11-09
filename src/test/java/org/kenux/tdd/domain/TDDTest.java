package org.kenux.tdd.domain;

import org.junit.jupiter.api.Test;
import org.kenux.tdd.domain.Dollar;
import org.kenux.tdd.domain.Franc;
import org.kenux.tdd.domain.Money;

import static org.assertj.core.api.Assertions.assertThat;


class TDDTest {

    @Test
    void testMultiplication() {
        Dollar five = new Dollar(5, "USD");
        assertThat(new Dollar(10, "USD")).isEqualTo(five.times(2));
        assertThat(new Dollar(15, "USD")).isEqualTo(five.times(3));
    }

    @Test
    void testEquality() {
        assertThat(Money.dollar(5)).isEqualTo(new Dollar(5,"USD"));
        assertThat(Money.dollar(5)).isNotEqualTo(new Dollar(6, "USD"));
        assertThat(new Franc(5,"CHF")).isEqualTo(new Franc(5, "CHF"));
        assertThat(new Franc(5, "CHF")).isNotEqualTo(new Franc(6,"CHF"));
        assertThat(new Franc(5,"CHF")).isNotEqualTo(Money.dollar(6));
    }

    @Test
    void testFrancMultiplication() {
//        Franc five = new Franc(5);
//        assertThat(new Franc(10)).isEqualTo(five.times(2));
//        assertThat(new Franc(15)).isEqualTo(five.times(3));
        Money five = Money.dollar(5);
        assertThat(Money.dollar(10)).isEqualTo(five.times(2));
        assertThat(Money.dollar(15)).isEqualTo(five.times(3));
    }

    @Test
    void testCurrency() {
        assertThat("USD").isEqualTo(Money.dollar(1).currency());
        assertThat("CHF").isEqualTo(Money.franc(1).currency());
    }

    @Test
    void testDifferentClassEquality() {
        assertThat(new Money(10, "CHF")).isEqualTo(new Franc(10, "CHF"));
    }
}
