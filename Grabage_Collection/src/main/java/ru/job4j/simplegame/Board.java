package ru.job4j.simplegame;

import java.util.ArrayList;

public class Board implements GameBoard {

    private ArrayList<Cell> path = new ArrayList<>();

    public ArrayList<Cell> getPath() {
        return path;
    }

    public void setPath(ArrayList<Cell> path) {
        this.path = path;
    }

    @Override
    public void putCell(Cell cell) {
        throw new Error("Unimplemented");
    }
}
