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
        for (int i = 0; i < 12; i++) {
            map.insert(key, i);
            key++;
        }
        System.out.println(map.getSize());
        System.out.println(map.getLength());
        assertThat(map.getLength(), is(32));
    }
}