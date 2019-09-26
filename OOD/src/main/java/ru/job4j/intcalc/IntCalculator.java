package ru.job4j.intcalc;

import java.util.Scanner;

public class IntCalculator {

    private Operations operations;

    private InputParser parser = new InputParser();

    private double result = 0;

    private double previousResult = 0;

    public void start() {
        Scanner scanner = new Scanner(System.in);
        String input;
        System.out.println("Please enter two numbers  e.g. 1+1");
        System.out.println("Type 'p' to use previous result e.g. p+1");
        do {
            this.previousResult = this.result;
            input = scanner.nextLine();
            while (!this.parser.validate(input, this.previousResult)) {
                System.out.println("Input not valid!");
                input = scanner.nextLine();
            }

            choseStrategy(input);
            this.result = this.operations.mathOperation(this.parser.getFirst(), this.parser.getSecond());
            System.out.println("Result = " + this.result);
        } while (!input.equals("exit"));
    }

    private void setStrategy(Operations operations) {
        this.operations = operations;
    }

    private void choseStrategy(String string) {
        if (string.contains("+")) {
            setStrategy(new Add());
        }
        if (string.contains("-")) {
            setStrategy(new Division());
        }
        if (string.contains("*")) {
            setStrategy(new Multiplication());
        }
        if (string.contains("/")) {
            setStrategy(new Division());
        }
    }


    public static void main(String[] args) {
        IntCalculator calculator = new IntCalculator();
        calculator.start();
    }
}
