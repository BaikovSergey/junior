package ru.job4j.list;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class LikedContainerTest {

    private LikedContainer<Integer> list;

    @Before
    public void beforeTest() {
        list = new LikedContainer<>();
        list.add(1);
        list.add(2);
        list.add(3);
    }

    @Test
    public void whenGetItemsThen321() {
        assertThat(list.get(0), is(3));
        assertThat(list.get(1), is(2));
        assertThat(list.get(2), is(1));
    }

    @Test
    public void whenHasNextThenTrue() {
        assertThat(list.iterator().hasNext(), is(true));
    }

    @Test
    public void whenIteratorNextThen321() {
        assertThat(list.iterator().next(), is(3));
        assertThat(list.iterator().next(), is(2));
        assertThat(list.iterator().next(), is(1));
    }
}