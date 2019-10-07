package ru.job4j.intcalc;

public class Log implements EngOperation {

    @Override
    public double mathOperation(double f) {
        return Math.log(f);
    }
}
