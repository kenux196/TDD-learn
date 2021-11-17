package org.kenux.tdd.mockito;

public class Game {
    private final GameNumGen gameNumGen;

    public Game(GameNumGen gameNumGen) {
        this.gameNumGen = gameNumGen;
    }

    void init(GameLevel gameLevel) {
        String result = gameNumGen.generate(gameLevel);
    }
}
