package org.kenux.tdd.mockito;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class AnyMatcherTest {

    @Test
    void aniMatchTest() {
        GameNumGen genMock = mock(GameNumGen.class);
        given(genMock.generate(any())).willReturn("456");

        String num = genMock.generate(GameLevel.NORMAL);
        assertEquals("456", num);

        String num2 = genMock.generate(GameLevel.HARD);
        assertEquals("456", num2);
    }

    @Test
    void mixAnyAndEqTest() {
        List<String> mockList = mock(List.class);

        given(mockList.set(anyInt(), eq("123"))).willReturn("456");

        String old = mockList.set(5, "123");
        assertEquals("456", old);
    }
}
