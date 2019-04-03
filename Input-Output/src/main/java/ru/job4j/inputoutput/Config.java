package ru.job4j.inputoutput;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.StringJoiner;

public class Config {
    private final String path;

    private final Map<String, String> values = new LinkedHashMap<>();

    private static final String SP = "=";

    public Config(final String path) {
        this.path = path;
    }

    public Config load() {
        this.values.clear();
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines().forEach(line -> {
                if (line.contains(SP)) {
                    int pos = line.indexOf(SP);
                    this.values.put(line.substring(0, pos), line.substring(pos + 1));
                } else {
                    this.values.put(line, "");
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this;
    }

    public String value(String key) {
        return this.values.get(key);
    }

    @Override
    public String toString() {
        StringJoiner out = new StringJoiner(System.lineSeparator());
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines().forEach(out::add);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return out.toString();
    }

    public static void main(String[] args) {
        System.out.println(new Config("app.properties"));
        System.out.println(new File("app.properties").getAbsolutePath());

    }
}