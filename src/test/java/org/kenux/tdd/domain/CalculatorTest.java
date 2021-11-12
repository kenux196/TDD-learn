package org.kenux.tdd.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CalculatorTest {

    @Test
    void plus() {
        int result = Calculator.plus(1, 2);
        assertThat(result).isEqualTo(3);
        assertThat(Calculator.plus(4, 1)).isEqualTo(5);
    }
}