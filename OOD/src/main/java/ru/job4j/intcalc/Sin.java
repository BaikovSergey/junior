package ru.job4j.intcalc;

public class Sin implements EngOperation {

    @Override
    public double mathOperation(double f) {
        return Math.sin(f);
    }
}
