package ru.job4j.set;


import org.hamcrest.core.IsNull;
import org.junit.Before;
import org.junit.Test;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;


public class SimpleSetTest {

    private SimpleSet<Integer> set;

    @Before
    public void beforeTest() {
        set = new SimpleSet<>(2);
        set.add(1);
        set.add(null);
        set.add(2);
    }

    @Test
    public void whenAddOneAndOneThenOne() {
        assertThat(set.get(0), is(1));
        assertThat(set.get(1), is(2));
    }

}