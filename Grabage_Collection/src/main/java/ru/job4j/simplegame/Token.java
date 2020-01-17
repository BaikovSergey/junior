package ru.job4j.simplegame;

public class Token {

    private String color;

    private Player owner;

    private String name;

    private String shape;

    public Token(String color, Player owner, String name, String shape) {
        this.color = color;
        this.owner = owner;
        this.name = name;
        this.shape = shape;
    }

    public String getColor() {
        return color;
    }

    public Player getOwner() {
        return owner;
    }

    public String getName() {
        return name;
    }

    public String getShape() {
        return shape;
    }
}
