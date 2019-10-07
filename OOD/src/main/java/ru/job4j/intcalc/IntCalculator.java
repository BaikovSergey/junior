package ru.job4j.intcalc;
import org.apache.commons.collections.map.HashedMap;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class IntCalculator {

    public IntCalculator() {
        this.operation.put("+", new Add());
        this.operation.put("-", new Substract());
        this.operation.put("*", new Multiplication());
        this.operation.put("/", new Division());
        this.engOperation.put("sin", new Sin());
        this.engOperation.put("cos", new Cos());
        this.engOperation.put("log", new Log());
    }

    private Map<String, Operations> operation = new HashMap<>();

    private Map<String, EngOperation> engOperation = new HashMap<>();

    private InputParser parser = new InputParser();

    private double result = 0;

    private double previousResult = 0;

    public void start() {
        Scanner scanner = new Scanner(System.in);
        String input;
        System.out.println("Hello, you can use: + - * / cos sin log");
        System.out.println("Please enter two numbers  e.g. 1+1 or cos5");
        System.out.println("Type 'p' to use previous result e.g. p+1");
        do {
            this.previousResult = this.result;
            input = scanner.nextLine();
            while (!this.parser.validate(input, this.previousResult)) {
                System.out.println("Input not valid!");
                input = scanner.nextLine();
            }
            if (input.contains("cos") || input.contains("sin") || input.contains("log")) {
                this.result = operatin(this.parser.getString(), this.parser.getFirst());
            } else {
                this.result = operation(this.parser.getString(), this.parser.getFirst(), this.parser.getSecond());
            }

            System.out.println("Result = " + this.result);
        } while (!input.equals("exit"));
    }

    private double operation(String op, double f, double s) {
        return this.operation.get(op).mathOperation(f, s);
    }

    private double operatin(String op, double f) {
        return this.engOperation.get(op).mathOperation(f);
    }


    public static void main(String[] args) {
        IntCalculator calculator = new IntCalculator();
        calculator.start();
    }
}
