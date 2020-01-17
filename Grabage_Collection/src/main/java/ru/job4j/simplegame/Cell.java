package ru.job4j.simplegame;

import java.util.ArrayList;

public class Cell {

    private Event event;

    private String name;

    private ArrayList<Cell> children = new ArrayList<>();

    public Cell(Event event, String name, ArrayList<Cell> children) {
        this.event = event;
        this.name = name;
        this.children = children;
    }

    public Event getEvent() {
        return event;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Cell> getChildren() {
        return children;
    }
}
