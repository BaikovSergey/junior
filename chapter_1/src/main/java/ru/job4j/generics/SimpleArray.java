package ru.job4j.generics;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleArray<T> implements Iterable<T> {

    /**
     * Current index (pointer) of array.
     */
    private int index = 0;

    /**
     * Array length.
     */
    private int arrayLength;

    /**
     * Base array.
     */
    private Object[] array;

    /**
     * Constructor.
     * @param cells number of array cells
     */
    public SimpleArray(int cells) {
        this.array = new Object[cells];
        this.arrayLength = array.length;
    }

    /**
     * Method adds new element to active cell.
     * @param model new element
     * @throws IndexOutOfBoundsException
     */
    public void add(T model) throws IndexOutOfBoundsException {
        if (this.index >= this.array.length) {
            throw new IndexOutOfBoundsException();
        }
        this.array[index++] = model;
    }

    /**
     * Method set new element on pointed index.
     * @param index points to cell of array
     * @param model new element
     * @throws IndexOutOfBoundsException
     */
    public void set(int index, T model) throws IndexOutOfBoundsException {
        if (index >= this.array.length) {
            throw new IndexOutOfBoundsException();
        }
        this.array[index] = model;
    }

    /**
     * Method removes element from pointed index. Elements to the right of pointed index will be moved to the left.
     * @param index points to cell of array
     * @throws IndexOutOfBoundsException
     */
    public void remove(int index) throws IndexOutOfBoundsException {
        if (index >= this.array.length) {
            throw new IndexOutOfBoundsException();
        }
        for (int i = index; i < this.index - 1; i++) {
            T temp = (T) array[i + 1];
            array[i] = temp;
        }
        this.index--;
        array[this.index] = null;
    }

    /**
     * Method gets element of array
     * @param index points to cell of array
     * @return element
     * @throws IndexOutOfBoundsException
     */
    public T get(int index) throws IndexOutOfBoundsException {
        if (index >= this.array.length) {
            throw new IndexOutOfBoundsException();
        }
       return (T) this.array[index];
    }

    /**
     * Iterator realisation
     * @return iterator
     */
    @Override
    public Iterator<T> iterator() {
        Iterator<T> it = new Iterator<T>() {

            private int index = 0;

            @Override
            public boolean hasNext() {
                return index < arrayLength && array[index] != null;
            }

            @Override
            public T next() throws NoSuchElementException {
                if (!hasNext()) {
                    throw new NoSuchElementException("End of List");
                }
                return (T) array[index++];
            }
        };
        return it;
    }
}
