package ru.job4j.list;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class DinamicArrayTest {

    private DinamicArray<Integer> list;

    @Before
    public void beforeTest() {
        list = new DinamicArray<>(1);
        list.add(1);
    }

    @Test
    public void whenAddNewElementThenLengthIsTwo() {
        list.add(2);
        assertThat(list.getArrayLength(), is(2));
    }
}