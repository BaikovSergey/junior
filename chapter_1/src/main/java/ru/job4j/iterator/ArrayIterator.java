package ru.job4j.iterator;

import java.util.Iterator;

public class ArrayIterator implements Iterator<Integer> {

    public ArrayIterator(int[][] ints) {
    }

    @Override
    public boolean hasNext() {
        return false;
    }

    @Override
    public Integer next() {
        return null;
    }
}
