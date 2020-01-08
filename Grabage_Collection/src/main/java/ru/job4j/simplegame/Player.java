package ru.job4j.simplegame;

public class Player implements GamePlayer {

    private String name;

    public String getName() {
        return name;
    }

    @Override
    public void makeATurn() {
        throw new Error("Unimplemented");
    }
}
