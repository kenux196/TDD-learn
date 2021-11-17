package org.kenux.tdd.mockito;

import org.junit.jupiter.api.Test;

import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.mock;

class GameTest {

    @Test
    void init() {
        GameNumGen genMock = mock(GameNumGen.class);
        Game game = new Game(genMock);
        game.init(GameLevel.EASY);

        then(genMock).should().generate(GameLevel.EASY);
    }
}
