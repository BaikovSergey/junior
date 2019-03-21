package ru.job4j.map;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleHashMap<K, V> implements Iterable<V> {

    /**
     * Current map size.
     */
    private int size = 0;

    /**
     * Length of array
     */
    private int length;

    /**
     *
     */
    private final double loadFactor = 0.75;

    /**
     *
     */
    private double threshold = length * loadFactor;

    /**
     * Array
     */
    private Object[] container;

    /**
     * Constructor
     */
    public SimpleHashMap() {
        this.container = new Object[16];
        this.length = 16;
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
        if (size >= threshold) {
            this.container = Arrays.copyOf(this.container, this.length * 2);
            this.length = this.container.length;
        }
        boolean result = false;
        int hash = indexFor(hash(key), this.length);
        if (hash == 0 && container[0] == null) {
            container[0] = value;
            result = true;
        } else {
            if (container[hash] == null) {
                container[hash] = value;
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
        int hash = indexFor(hash(key), this.length);
        if (hash < this.length) {
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
        int hash = indexFor(hash(key), this.length);
        if (hash < this.length) {
            container[hash] = null;
            result = true;
        }
        return result;
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

}
