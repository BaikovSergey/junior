package ru.job4j.interactcalculator;

import org.junit.Test;
import ru.job4j.intcalc.InputParser;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class InputParserTest {

    @Test
    public void whenParse1and1Then1and1() {
        ru.job4j.intcalc.InputParser parser = new ru.job4j.intcalc.InputParser();
        boolean result = parser.validate("1+1", 0);
        List<Double> arr = new ArrayList<>();
        arr.add(parser.getFirst());
        arr.add(parser.getSecond());
        assertThat(result, is(true));
        assertThat(arr.get(0), is(1d));
        assertThat(arr.get(1), is(1d));
    }

    @Test
    public void whenParsePand1Then2and1() {
        ru.job4j.intcalc.InputParser parser = new InputParser();
        boolean result = parser.validate("p+1", 2d);
        List<Double> arr = new ArrayList<>();
        arr.add(parser.getFirst());
        arr.add(parser.getSecond());
        assertThat(result, is(true));
        assertThat(arr.get(0), is(2d));
        assertThat(arr.get(1), is(1d));
    }
}