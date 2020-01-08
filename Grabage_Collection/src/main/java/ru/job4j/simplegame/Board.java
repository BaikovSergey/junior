package ru.job4j.simplegame;

public class Board implements GameBoard {

    private static Board instance;

    private Board() {
    }

    public static Board getInstance() {
        if (instance == null) {
            instance = new Board();
        }
        return instance;
    }

    @Override
    public void putTokensOnStart() {
        throw new Error("Unimplemented");
    }

    @Override
    public void movePlayerToken() {
        throw new Error("Unimplemented");
    }
}
