package ru.job4j.inputoutput;

public class Zip {

    private String directory(String dir) {
        String result = null;
        if (dir != null) {
            result = dir.substring(dir.indexOf("-d"), dir.indexOf("-e"));
        }
        return result;
    }

    private String exclude(String ex) {
        String result = null;
        return result;
    }

    private String output(String out) {
        String result = null;
        return result;
    }

    public static void main(String[] args) {
        Zip zip = new Zip();
        StringBuilder parameters = new StringBuilder();
        for (String s : args) {
            parameters.append(s);
        }
        System.out.println(zip.directory(parameters.toString()));

    }
}
