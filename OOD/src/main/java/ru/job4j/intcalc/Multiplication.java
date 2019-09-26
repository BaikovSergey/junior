package ru.job4j.intcalc;

public class Multiplication implements Operations {

    /**
     * Method multiple.
     * @param first First value.
     * @param second Second value.
     */
    @Override
    public double mathOperation(double first, double second) {
        return first * second;
    }
}
