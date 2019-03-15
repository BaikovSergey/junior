package ru.job4j.list;

import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class LikedContainerTest {

    private LikedContainer<Integer> list;
    private Iterator<Integer> it;

    @Before
    public void beforeTest() {
        list = new LikedContainer<>();
        list.add(1);
        list.add(2);
        list.add(3);
        it = list.iterator();
    }

    @Test
    public void whenGetItemsThen321() {
        assertThat(list.get(0), is(3));
        assertThat(list.get(1), is(2));
        assertThat(list.get(2), is(1));
    }

    @Test
    public void whenHasNextThenTrue() {
        assertThat(it.hasNext(), is(true));
    }

    @Test
    public void whenIteratorNextThen321() {
        assertThat(it.next(), is(3));
        assertThat(it.next(), is(2));
        assertThat(it.next(), is(1));
    }
}