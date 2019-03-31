package ru.job4j.map;

import org.hamcrest.core.IsNull;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class SimpleHashMapTest {

    private SimpleHashMap<Integer, Integer> map;

    @Before
    public void beforeTest() {
        map = new SimpleHashMap<>();
    }


    @Test
    public void whenAddNewElementThenThisElementInMap() {
        map.insert(0, 1);
        assertThat(map.get(0), is(1));
    }

    @Test
    public void whenDeleteElementFromMapThenNoSuchElement() {
        map.insert(1,1);
        map.delete(1);
        assertThat(map.get(1), is(IsNull.nullValue()));
    }

    @Test
    public void getElementAfterGrow() {
        System.out.println(map.getSize());
        System.out.println(map.getLength());
        int key = 0;
        for (int i = 0; i < 13; i++) {
            map.insert(key, i);
            key++;
        }
        System.out.println(map.getSize());
        System.out.println(map.getLength());
        assertThat(map.getLength(), is(32));
    }

    @Test
    public void whenMapWasGrownThenElementsStillAccessed() {
        int key = 0;
        for (int i = 0; i < 20; i++) {
            map.insert(key, i);
            key++;
        }
        assertThat(map.get(2), is(2));
        assertThat(map.get(5), is(5));
        assertThat(map.get(19), is(19));
    }
}