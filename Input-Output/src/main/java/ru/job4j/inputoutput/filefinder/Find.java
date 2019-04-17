package ru.job4j.inputoutput.filefinder;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Find {

    public void menu() {
        System.out.println("");
    }

    /**
     * Method finds file.
     */
    public void findFile() {
        final String property = System.getProperty("java.io.tmpdir");
        final File file = new File(property + File.separator + "result.txt");
        List<String> result = search("C:\\Temp", "test");
        try (FileOutputStream fos = new FileOutputStream(file);
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(fos))) {
                if (result != null) {
                    for (String string : result) {
                        writer.write(string);
                        writer.newLine();
                    }
                }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Method searches for file and put matches to List
     * @param directory
     * @param toFind
     * @return
     */
    private List<String> search(String directory, String toFind) {
        List<String> result = new ArrayList<>();
        File folder = new File(directory);
        Queue<File> queue = new LinkedList<>();
        queue.offer(folder);
        while (!queue.isEmpty()) {
            File[] files = queue.poll().listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isDirectory()) {
                        queue.offer(file);

                    } else if (file.getName().contains(toFind)) {
                        result.add(file.getName());
                    }
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Find test = new Find();
        test.findFile();
    }

}
