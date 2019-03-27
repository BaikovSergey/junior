package ru.job4j.map;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class IsArraysEqualsTest {

    @Test
    public void whenArraysIsEqualThenTrue() {
        IsArraysEquals test = new IsArraysEquals();
        char[] first = {'q', 'w', 'e', 'r', 't', 'y'};
        char[] second = {'y', 't', 'r', 'e', 'w', 'q'};
        assertThat(test.isEquals(first, second), is(true));
    }

    @Test
    public void whenArraysIsNotEqualThenFalse() {
        IsArraysEquals test = new IsArraysEquals();
        char[] first = {'q', 'w', 'e', 'r', 't', 'y'};
        char[] second = {'a', 't', 'r', 'e', 'w', 'q'};
        assertThat(test.isEquals(first, second), is(false));
    }
}