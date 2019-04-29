package ru.job4j.inputoutput.filefinder;

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

    private boolean isContains(String[] array, String key) {
        boolean result = false;
        if (array != null) {
            for (String s : array) {
                if (s.equals(key)) {
                    result = true;
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
        int end = findKey(input, "-");
        if (input != null) {
            result.append(input[beginning]);
            for (int i = beginning + 1; i < end - 1; i++) {
                result.append(" ").append(input[i]);
            }
        }
        return result.toString();
    }

    protected String searchKey(String[] input) {
        String result = null;
        if (isContains(input, "-m")) {
            result = "-m";
        } else if (isContains(input, "-f")) {
            result = "-f";
        } else if (isContains(input, "-r")) {
            result = "-r";
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
