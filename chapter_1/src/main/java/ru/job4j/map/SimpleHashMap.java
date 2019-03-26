package ru.job4j.map;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleHashMap<K, V> implements Iterable<V> {

    /**
     * Array
     */
    private Node[] container;

    /**
     * Constructor
     */
    public SimpleHashMap() {
        this.container = new Node[16];
    }

    /**
     * Current map size.
     */
    private int size = 0;


    /**
     *
     */
    private final double LOAD_FACTOR = 0.75d;

    /**
     *
     */
    private double threshold() {
        return this.container.length * this.LOAD_FACTOR;
    }



    /**
     * Method determinate in witch cell new element will be put
     * @param h object hashCode
     * @return index in array
     */
    private int indexFor(int h, int length)
    {
        return h & (length - 1);
    }

    /**
     * Method calculates object's hashCode
     * @param key key
     * @return hashCode
     */
    private int hash(Object key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }

    /**
     * Method puts new element in to map
     * @param key object's key
     * @param value object's value
     * @return true/false
     */
    public boolean insert(K key, V value) {
        if (size >= threshold()) {
            this.container = Arrays.copyOf(this.container, this.container.length * 2);
        }
        boolean result = false;
        int hash = indexFor(hash(key), this.container.length);
        if (hash == 0 && container[0] == null) {
            container[0] = new Node(key, value);
            size++;
            result = true;
        } else {
            if (container[hash] == null) {
                container[hash] = new Node(key, value);
                size++;
                result = true;
            }
        }
        return result;
    }

    /**
     * Method gets element from map
     * @param key object's key
     * @return element
     */
    public V get(K key) {
        V result = null;
        int hash = indexFor(hash(key), this.container.length);
        if (hash < this.container.length) {
            result = (V) this.container[hash];
        }
        return result;
    }

    /**
     * Method delet's element from map
     * @param key object's key
     * @return true/false
     */
    public boolean delete(K key) {
        boolean result = false;
        int hash = indexFor(hash(key), this.container.length);
        if (hash < this.container.length) {
            container[hash] = null;
            result = true;
        }
        return result;
    }


    public int getSize() {
        return size;
    }

    public int getLength() {
        return this.container.length;
    }

    @Override
    public Iterator<V> iterator() {
        Iterator<V> it = new Iterator<V>() {

            private int index = 0;

            @Override
            public boolean hasNext() {
                return index < SimpleHashMap.this.size;
            }

            @Override
            public V next() throws NoSuchElementException {
                if (!hasNext()) {
                    throw new NoSuchElementException("End of List");
                }
                return (V) container[index++];
            }
        };
        return it;
    }

    private static class Node<K, V> {
        K key;
        V value;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

}
