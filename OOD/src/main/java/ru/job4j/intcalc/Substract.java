package ru.job4j.intcalc;

public class Substract implements Operations {

    /**
     * Method subtract.
     * @param first First value.
     * @param second Second value.
     */
    @Override
    public double mathOperation(double first, double second) {
        return first - second;
    }
}
