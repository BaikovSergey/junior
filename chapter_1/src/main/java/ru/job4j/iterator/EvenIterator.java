package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author Sergey Baikov
 * @version $ 1 $
 * @since 09.03.19
 */
public class EvenIterator implements Iterator<Integer> {

    /**
     * Contains array.
     */
    private final int[] values;

    /**
     * Contains array current index.
     */
    private int index = 0;

    /**
     * Constructor.
     * @param ints input array.
     */
    public EvenIterator(int[] ints) {
        this.values = ints;
    }

    /**
     * Returns {@code true} if iteration has more even numbers.
     * @return {@code true} if iteration has more even numbers
     */
    @Override
    public boolean hasNext() {
        boolean result = false;
        for (int i = index; i < values.length; i++) {
            if (values[i] % 2 == 0) {
                index = i;
                result = true;
                break;
            }
        }
        return result;
    }

    /**
     * Returns the next even number in the iteration.
     * @return the next even number in the iteration
     * @throws NoSuchElementException if iteration has no even numbers
     */
    @Override
    public Integer next() throws NoSuchElementException {
        int result;
        if (!hasNext()) {
            throw  new NoSuchElementException ("End of list");
        }
        result = values[index++];
        return result;
    }
}
