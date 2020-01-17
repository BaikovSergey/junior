package ru.job4j.simplegame;

public class Player implements GamePlayer {

    private String name;

    private Token token;

    public Player(String name, Token token) {
        this.name = name;
        this.token = token;
    }

    public String getName() {
        return name;
    }

    public Token getToken() {
        return token;
    }

    @Override
    public void makeTurn() {
        throw new Error("Unimplemented");
    }
}
