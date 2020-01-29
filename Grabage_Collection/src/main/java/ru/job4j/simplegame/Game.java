package ru.job4j.simplegame;

public class Game implements BoardGame {

    @Override
    public void init() {
        throw new Error("Unimplemented");
    }

    @Override
    public void start() {
        throw new Error("Unimplemented");
    }

    public static void main(String[] args) {
        Game game = new Game();
        game.init();
        game.start();
    }
}
