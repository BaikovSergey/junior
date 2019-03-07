package ru.job4j.iterator;

import java.util.Iterator;

public class EvenIterator implements Iterator<Integer> {

    private final int[] values;

    private int index = 0;

    public EvenIterator(int[] ints) {
        this.values = ints;
    }


    @Override
    public boolean hasNext() {
        boolean result = false;
        if (index < values.length) {
            for (int i = index; i < values.length; i++) {
                if (values[i] % 2 == 0) {
                    result = true;
                    break;
                }
            }
        }
        return result;
    }

    @Override
    public Integer next() {
        int result = values[index];
        if (!isEven() & hasNext()) {
            int i = closeEvenIndex();
            result = values[i];
            index = i;
                if (hasNext()) {
                    index = closeEvenIndex();
                }
        }
        return result;
    }

    public boolean isEven() {
        return values[index] % 2 == 0;
    }

    public int closeEvenIndex() {
        int result = index;
        for (int i = result; i < values.length; i++) {
            if (values[i] % 2 == 0) {
                result = i;
                break;
            }
        }
        return result;
    }
}
