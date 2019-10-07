package ru.job4j.intcalc;

public class Cos implements EngOperation {

    @Override
    public double mathOperation(double f) {
        return Math.cos(f);
    }
}
