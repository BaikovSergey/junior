package ru.job4j.map;

import org.hamcrest.core.IsNull;
import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;

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
        int key = 0;
        for (int i = 0; i < 13; i++) {
            map.insert(key, i);
            key++;
        }
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

    @Test
    public void iteratorTest() {
        map.insert(1, 1);
        map.insert(5, 2);
        map.insert(10, 3);
        Iterator<Integer> it = map.iterator();
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(1));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(2));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(3));
        assertThat(it.hasNext(), is(false));
    }
}