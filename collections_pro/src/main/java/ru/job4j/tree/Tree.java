package ru.job4j.tree;

import java.util.*;

public class Tree<E extends Comparable<E>> implements SimpleTree<E> {

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

    public boolean isBinary(Tree<E> tree) {
        boolean result = true;
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(root);
        Node<E> node;
        while (!data.isEmpty()) {
            node = data.poll();
            if (!node.leaves().isEmpty() && node.leaves().size() > 2) {
                result = false;
                break;
            }
        }
        return result;
    }

    @Override
    public Iterator<E> iterator() {

        Queue<Node<E>> data = new LinkedList<>();
        data.offer(root);

        Iterator<E> it = new Iterator<E>() {

            @Override
            public boolean hasNext() {
                boolean result = true;
                if (data.isEmpty()) {
                    result = false;
                }
                return result;
            }

            @Override
            public E next() throws NoSuchElementException {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                E result = null;
                Node<E> head = data.poll();
                if (head != null) {
                    if (!head.leaves().isEmpty()) {
                        for (Node<E> child : head.leaves()) {
                            data.offer(child);
                        }
                    }
                    result = head.getValue();
                }
                return result;
            }
        };
        return it;
    }
}

