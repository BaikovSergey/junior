package ru.job4j.list;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class StackTest {

    @Test
    public void whenPush123ThenPoll321() {
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        assertThat(stack.poll(), is(3));
        assertThat(stack.poll(), is(2));
        assertThat(stack.poll(), is(1));
    }
}