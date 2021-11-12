package org.kenux.tdd.domain;

import org.junit.jupiter.api.*;

@DisplayName("LifeCycleTest 입니다.")
public class LifeCycleTest {
    public LifeCycleTest() {
        System.out.println("new LifeCycleTest");
    }

    @BeforeAll
    static void globalInit() {
        System.out.println("globalInit");
    }

    @BeforeEach
    void init() {
        System.out.println("init");
    }

    @Test
    @DisplayName("첫 번째 테스트입니다.")
    void test1() {
        System.out.println("run test1");
    }

    @Test
    @DisplayName("두 번째 테스트입니다.")
    void test2() {
        System.out.println("run test2");
    }

    @Test
    @DisplayName("세 번째 테스트 입니다. 이 테스트는 실행하지 않습니다.")
    @Disabled
    void test3() {
        System.out.println("run test3 : This text must not showed");
    }

    @AfterEach
    void tearDown() {
        System.out.println("tearDown");
    }

    @AfterAll
    static void globalTearDown() {
        System.out.println("globalTearDown");
    }
}