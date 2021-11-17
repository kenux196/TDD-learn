package org.kenux.tdd.mockito;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class GameGenMockTest {

    @Test
    void mockTest() {
        GameNumGen genMock = mock(GameNumGen.class); // Mockito.mock() 으로 목 객체 생성
        given(genMock.generate(GameLevel.EASY)).willReturn("123"); // BDDMockito.given 으로 stub 생성

        String num = genMock.generate(GameLevel.EASY); // mockito 의 stub 설정에 매칭되는 메서드 실행.
        Assertions.assertEquals("123", num);
    }

    @Test
    void mockThrowTest() {
        GameNumGen genMock = mock(GameNumGen.class);
        given(genMock.generate(null)).willThrow(IllegalArgumentException.class);

        Assertions.assertThrows(IllegalArgumentException.class, () -> genMock.generate(null));
    }
}
