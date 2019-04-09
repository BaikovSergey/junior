package ru.job4j.generics;

import org.hamcrest.core.IsNull;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * @author Sergey Baikov
 * @version $ 1 $
 * @since 12.03.19
 */
public class SimpleArrayTest {

    /**
     * Test add
     */
    @Test
    public void thenAddNewObjectWhenThisObject() {
        SimpleArray<Integer> array = new SimpleArray<>(1);
        array.add(5);
        assertThat(array.get(0), is(5));
    }

    /**
     * Test set
     */
    @Test
    public void thenSetEightInIndexZeroWhenEightInIndexZero() {
        SimpleArray<Integer> array = new SimpleArray<>(1);
        array.add(5);
        assertThat(array.get(0), is(5));
        array.set(0, 8);
        assertThat(array.get(0), is(8));
    }

    /**
     * Test remove
     */
    @Test
    public void thenRemoveFirstObjectWhen234Null() {
        SimpleArray<Integer> array = new SimpleArray<>(4);
        array.add(1);
        array.add(2);
        array.add(3);
        array.add(4);
        array.remove(0);
        assertThat(array.get(0), is(2));
        assertThat(array.get(1), is(3));
        assertThat(array.get(2), is(4));
        assertThat(array.get(3), is(IsNull.nullValue()));
    }

    /**
     * Test remove
     */
    @Test
    public void thenRemoveSecondObjectWhen134Null() {
        SimpleArray<Integer> array = new SimpleArray<>(4);
        array.add(1);
        array.add(2);
        array.add(3);
        array.add(4);
        array.remove(1);
        assertThat(array.get(0), is(1));
        assertThat(array.get(1), is(3));
        assertThat(array.get(2), is(4));
        assertThat(array.get(3), is(IsNull.nullValue()));
    }

    /**
     * Test remove
     */
    @Test
    public void thenRemoveLastObjectWhen234Null() {
        SimpleArray<Integer> array = new SimpleArray<>(4);
        array.add(1);
        array.add(2);
        array.add(3);
        array.add(4);
        array.remove(3);
        assertThat(array.get(0), is(1));
        assertThat(array.get(1), is(2));
        assertThat(array.get(2), is(3));
        assertThat(array.get(3), is(IsNull.nullValue()));
    }

    /**
     * Test get
     */
    @Test
    public void thenGetObjectFromIndexOneWhenThisObject() {
        SimpleArray<Integer> array = new SimpleArray<>(1);
        array.add(7);
        assertThat(array.get(0), is(7));
    }

    /**
     * Test iterator
     */
    @Test
    public void thenIterate() {
        SimpleArray<Integer> array = new SimpleArray<>(2);
        array.add(1);
        array.add(2);
        assertThat(array.iterator().hasNext(), is(true));
        assertThat(array.iterator().next(), is(1));
    }

}