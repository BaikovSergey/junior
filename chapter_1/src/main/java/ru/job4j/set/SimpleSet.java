package ru.job4j.set;

import ru.job4j.list.DinamicArray;

import java.util.Iterator;


public class SimpleSet<E> implements Iterable<E> {

    private DinamicArray<E> set;

    public SimpleSet(int cells) {
        set = new DinamicArray<>(cells);
    }

    public boolean duplicate(E model) {
        boolean result = false;
            for (E element: set) {
                if (element == model || element != null && element.equals(model)) {
                    result = true;
                    break;
                }
            }
        return result;
    }

    public void add(E model) {
        if (!duplicate(model)) {
            set.add(model);
        }
    }

    public E get(int index) {
       return set.get(index);
    }

    @Override
    public Iterator<E> iterator() {
        return set.iterator();
    }
}
