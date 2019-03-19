package ru.job4j.set;


import org.hamcrest.core.IsNull;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;


public class SimpleSetTest {

    private SimpleSet<Integer> set;

    @Before
    public void beforeTest() {
        set = new SimpleSet<>(1);

    }

    @Test
    @Ignore
    public void whenAddOneAndOneThenOne() {
        assertThat(set.get(0), is(1));
        assertThat(set.get(1), is(IsNull.nullValue()));
        assertThat(set.get(2), is(2));
    }

    @Test
    public void name() {
        set.add(3);
        set.add(1);
        set.add(1);
        set.add(null);
        set.add(null);
        set.add(2);
        set.add(null);
        for (Integer i: set
             ) {
            System.out.println(i);
        }
    }

}