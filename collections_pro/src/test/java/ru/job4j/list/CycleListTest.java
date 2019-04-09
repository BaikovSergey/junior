package ru.job4j.list;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;


public class CycleListTest {

    private CycleList.Node first;
    private CycleList.Node two;
    private CycleList.Node third;
    private CycleList.Node four;

    @Before
    public void beforeTest() {
        first = new CycleList.Node<>(1);
        two = new CycleList.Node<>(2);
        third = new CycleList.Node<>(3);
        four = new CycleList.Node<>(4);

        first.next = two;
        two.next = third;
        third.next = four;
        four.next = first;
    }

    @Test
    public void whenHasCycleThenTrue() {
        CycleList test = new CycleList();
        assertThat(test.hasCycle2(first), is(true));
    }
}