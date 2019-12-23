package ru.job4j.cache;

import java.lang.ref.SoftReference;
import java.util.*;

/**
 * @author Sergey Baikov
 * @version $1$
 * @since 23.12.19
 */
public class SoftRefCache<K, V> {

   private HashMap<K, SoftReference<V>> cache = new HashMap<>();

    /**
     * Puts new entry to "cache".
     * @param key the key.
     * @param value the value to put.
     */
    public void put(K key, V value) {
        this.cache.put(key, new SoftReference<>(value));
    }

    /**
     * Returns the value to which the specified key is mapped,
     * or {@code null} if this map contains no mapping for the key.
     * @param key the key.
     * @return the value.
     */
    public SoftReference<V> get(K key) {
        return this.cache.get(key);
    }
}
