package ru.job4j.list;

import ru.job4j.generics.SimpleArray;

import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class DinamicArray<E> implements Iterable<E> {

    private int index = 0;

    private int arrayLength;

    private Object[] container;

    private int modCount = 0;

    public DinamicArray(int cells) {
        this.container = new Object[cells];
        this.arrayLength = container.length;
    }

    public int getModCount() {
        return modCount;
    }

    public int getArrayLength() {
        return arrayLength;
    }

    public void add(E model) {
        if (this.index >= this.container.length) {
            this.container = Arrays.copyOf(this.container, this.arrayLength + 1);
        }
        arrayLength = container.length;
        this.container[this.index++] = model;
        this.modCount++;
    }

    public E get(int index) {
        if (index > this.index) {
            throw new IndexOutOfBoundsException();
        }
        return (E) this.container[index];
    }

    @Override
    public Iterator<E> iterator() {
        Iterator<E> it = new Iterator<E>() {

            private int expectedModCount = getModCount();
            private int index = 0;

            @Override
            public boolean hasNext() throws ConcurrentModificationException {
                if (fastFail()) {
                    throw new ConcurrentModificationException();
                }
                return index < DinamicArray.this.index && container[index] != null;
            }

            @Override
            public E next() throws NoSuchElementException, ConcurrentModificationException {
                if (fastFail()) {
                    throw new ConcurrentModificationException();
                }
                if (!hasNext()) {
                    throw new NoSuchElementException("End of List");
                }
                return (E) container[index++];
            }

            public boolean fastFail() {
                boolean result = false;
                if (expectedModCount != getModCount()) {
                    result = true;
                }
                return result;
            }
        };
        return it;
    }
}
