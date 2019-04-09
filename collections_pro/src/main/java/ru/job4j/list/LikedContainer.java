package ru.job4j.list;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class LikedContainer<E> implements Iterable<E> {

    private int size;

    private Node<E> first;

    private int modCount = 0;

    public int getModCount() {
        return this.modCount;
    }

    public int getSize() {
        return this.size;
    }

    public void add(E date) {
        Node<E> newLink = new Node<>(date);
        newLink.next = this.first;
        this.first = newLink;
        this.size++;
        this.modCount++;
    }

    public E get(int index) {
        Node<E> result = this.first;
        for (int i = 0; i < index; i++) {
            result = result.next;
        }
        return result.date;
    }

    @Override
    public Iterator<E> iterator() {
        Iterator<E> it = new Iterator<E>() {
            private Node<E> current = first;
            private int expectedModCount = getModCount();
            int counter = 0;

            @Override
            public boolean hasNext() throws ConcurrentModificationException {
                boolean result = false;
                if (fastFail()) {
                    throw new ConcurrentModificationException();
                }
                if (counter < getSize()) {
                    result = true;
                    counter++;
                }
                return result;
            }

            @Override
            public E next() throws NoSuchElementException, ConcurrentModificationException {
                if (fastFail()) {
                    throw new ConcurrentModificationException();
                }
                if (!hasNext()) {
                    throw new NoSuchElementException("End of List");
                }
                E result = current.date;
                current = current.next;
                return result;
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

    /**
     * Класс предназначен для хранения данных.
     */
    private static class Node<E> {

        E date;
        Node<E> next;

        Node(E date) {
            this.date = date;
        }
    }
}
