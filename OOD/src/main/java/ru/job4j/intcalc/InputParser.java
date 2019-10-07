package ru.job4j.intcalc;

import java.util.ArrayList;
import java.util.List;

public class InputParser {

    private List<String> strings = new ArrayList<>();

    private List<Double> doubles = new ArrayList<>();

    public double getFirst() {
        return this.doubles.get(0);
    }

    public double getSecond() {
        return this.doubles.get(1);
    }

    public String getString() {
        return this.strings.get(0);
    }

    public boolean validate(String string, double previousResult) {
        this.doubles.clear();
        this.strings.clear();
        boolean result = false;
        String[] input;
        if (string.matches("(\\d)(\\W)(\\d)") || string.matches("(p)(\\W)(\\d)")
            || string.matches("(\\d)(\\W)(p)") || string.matches("(p)(\\W)(p)")) {
            input = string.split("[-+*/]");
            result = true;
            for (String s: input) {
                if (s.equals("p")) {
                    this.doubles.add(previousResult);
                } else {
                    this.doubles.add(Double.parseDouble(s));
                }
            }
            this.strings.add(getSymbol(string.split("")));
        } else if (string.contains("sin") || string.contains("cos") || string.contains("log")){
            result = true;
            this.doubles.add(Double.parseDouble(string.substring(3)));
            this.strings.add(string.substring(0, 3));
        }
        return result;
    }

    private String getSymbol(String[] arr) {
        String result = "";
        for (String s: arr) {
            if (s.matches("\\D")) {
                result = s;
                break;
            }
        }
        return result;
    }
}
