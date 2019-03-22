package ru.job4j.tree;

import java.util.*;

public class Tree<E extends Comparable<E>> implements Iterable<E>, SimpleTree<E> {

    /**
     * Root of the tree
     */
    private Node<E> root;

    /**
     * Constructor
     * @param value value
     */
    public Tree(E value) {
        this.root = new Node<>(value);
    }

    @Override
    public boolean add(E parent, E child) {
        boolean result = false;
        Optional<Node<E>> node = findBy(parent);
        if (!duplicate(child) && !node.equals(Optional.empty())) {
            Node<E> leaf = new Node<>(child);
            node.get().add(leaf);
        }
        return result;
    }

    @Override
    public Optional<Node<E>> findBy(E value) {
        Optional<Node<E>> rsl = Optional.empty();
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> el = data.poll();
            if (el.eqValue(value)) {
                rsl = Optional.of(el);
                break;
            }
            for (Node<E> child : el.leaves()) {
                data.offer(child);
            }
        }
        return rsl;
    }

    public boolean duplicate(E value) {
        boolean result = false;
        Optional<Node<E>> node = findBy(value);
        if (!node.equals(Optional.empty())) {
            result = true;
        }
        return result;
    }

    @Override
    public Iterator<E> iterator() {
        Iterator<E> it = new Iterator<E>() {

            int counter = 0;

            @Override
            public boolean hasNext() throws ConcurrentModificationException {
                return true;
            }

            @Override
            public E next() throws NoSuchElementException {
                if (!hasNext()) {
                    throw new NoSuchElementException("End of List");
                }
                return null;
            }

        };
        return it;
    }
}
