package ru.job4j.intcalc;

import java.util.ArrayList;
import java.util.List;

public class InputParser {

    private List<Double> input = new ArrayList<>();

    public double getFirst() {
        return this.input.get(0);
    }

    public double getSecond() {
        return this.input.get(1);
    }

    public boolean validate(String string, double previousResult) {
        this.input.clear();
        boolean result = false;
        String[] input;
        input = string.split("[-+*/]");
        if (string.matches("(\\d)(\\W)(\\d)") || string.matches("(p)(\\W)(\\d)")
            || string.matches("(\\d)(\\W)(p)") || string.matches("(p)(\\W)(p)")) {
            result = true;
            for (String s: input) {
                if (s.equals("p")) {
                    this.input.add(previousResult);
                } else {
                    this.input.add(Double.parseDouble(s));
                }
            }
        }
        return result;
    }
}
