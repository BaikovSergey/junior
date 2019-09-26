package ru.job4j.intcalc;

public class Division implements Operations {

    /**
     * Method div.
     * @param first First value.
     * @param second Second value.
     */
    @Override
    public double mathOperation(double first, double second) {
        return first / second;
    }
}
