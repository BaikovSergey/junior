package ru.job4j.inputoutput;

public class Parsing {

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

    protected String directory(String[] dir) {
        String result = null;
        if (dir != null) {
            result = dir[findKey(dir, "-d")];
        }
        return result;
    }

    protected String exclude(String[] ex) {
        String result = null;
        if (ex != null) {
            result = ex[findKey(ex, "-e")];
        }
        return result;
    }

    protected String output(String[] out) {
        String result = null;
        if (out != null) {
            result = out[findKey(out, "-o")];
        }
        return result;
    }
}
