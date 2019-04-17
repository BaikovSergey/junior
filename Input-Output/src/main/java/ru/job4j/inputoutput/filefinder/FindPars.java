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

    protected String directory(String[] dir) {
        String result = null;
        if (dir != null) {
            result = dir[findKey(dir, "-d")];
        }
        return result;
    }

    protected String fileName(String[] out) {
        String result = null;
        if (out != null) {
            result = out[findKey(out, "-n")];
        }
        return result;
    }

    protected String mask(String[] out) {
        String result = null;
        if (out != null) {
            result = out[findKey(out, "-m")];
        }
        return result;
    }

    protected String fullName(String[] out) {
        String result = null;
        if (out != null) {
            result = out[findKey(out, "-f")];
        }
        return result;
    }

    protected String regularExp(String[] out) {
        String result = null;
        if (out != null) {
            result = out[findKey(out, "-r")];
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
