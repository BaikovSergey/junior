package ru.job4j.inputoutput.filefinder;

import java.util.StringJoiner;

public class FindPars {

    private int findKey(String[] array, String key) {
        int result = 0;
        if (array != null) {
            for (int i = 0; i < array.length; i++) {
                if (array[i].contains(key)) {
                    result = i + 1;
                    break;
                }
            }
        }
        return result;
    }

    protected String directory(String[] input) {
        StringBuilder result = new StringBuilder();
        int beginning = findKey(input, "-d");
        int end = findKey(input, "-n");
        if (input != null) {
            result.append(input[beginning]);
            for (int i = beginning + 1; i < end - 1; i++) {
                result.append(" ").append(input[i]);
            }
        }
        return result.toString();
    }

    protected String fileName(String[] input) {
        StringBuilder result = new StringBuilder();
        int beginning = findKey(input, "-n");
        int end = findKey(input, "-o");
        if (input != null) {
            result.append(input[beginning]);
            for (int i = beginning + 1; i < end - 1; i++) {
                result.append(" ").append(input[i]);
            }
        }
        return result.toString();
    }

    protected String mask(String[] input) {
        String result = null;
        if (input != null) {
            result = input[findKey(input, "-m")];
        }
        return result;
    }

    protected String fullName(String[] input) {
        String result = null;
        if (input != null) {
            result = input[findKey(input, "-f")];
        }
        return result;
    }

    protected String regularExp(String[] input) {
        String result = null;
        if (input != null) {
            result = input[findKey(input, "-r")];
        }
        return result;
    }

    protected String output(String[] input) {
        StringBuilder result = new StringBuilder();
        int beginning = findKey(input, "-o");
        if (input != null) {
            result.append(input[beginning]);
            for (int i = beginning + 1; i < input.length; i++) {
                result.append(" ").append(input[i]);
            }
        }
        return result.toString();
    }
}
