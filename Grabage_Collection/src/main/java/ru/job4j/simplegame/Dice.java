package ru.job4j.simplegame;

public class Dice implements GameDice {

    private int rolledNumber = -1;

    public int getRolledNumber() {
        return rolledNumber;
    }

    @Override
    public int rollADice() {
        return this.rolledNumber = -1;
    }
}
