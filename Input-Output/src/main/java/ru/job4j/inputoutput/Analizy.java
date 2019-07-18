package ru.job4j.inputoutput;

import java.io.*;
import java.util.*;

public class Analizy {

    private List<String> serverlog = new ArrayList<>();

    public void unavailable(String source, String target) {
        this.serverlog.clear();
        try (BufferedReader reader = new BufferedReader(new FileReader(source))) {
            reader.lines().forEach(line -> this.serverlog.add(line));
        } catch (Exception e) {
            e.printStackTrace();
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(target))) {
            List<String> arr = timeParser();
            for (String line : arr) {
                writer.write(line);
                writer.newLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private List<String> timeParser() {
        List<String> result = new ArrayList<>();
        String[] temp = {"", ";", ""};
        String[] time;
        boolean isUnavailable = false;
        for (String line : this.serverlog) {
            if (line.contains("400") & !isUnavailable || line.contains("500") & !isUnavailable) {
                time = line.split(" ");
                temp[0] = time[1];
                isUnavailable = true;
            }
            if (line.contains("200") & isUnavailable || line.contains("300") & isUnavailable) {
                time = line.split(" ");
                temp[2] = time[1];
                result.add(temp[0] + temp[1] + temp[2]);
                isUnavailable = false;
            }
        }
        return result;
    }
}