package ru.job4j.inputoutput;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Search {

    public List<File> files(String parent, List<String> exts) {
        List<File> result = new ArrayList<>();
        Queue<File> queue = new LinkedList<>();
        File file = new File(parent);
        queue.offer(file);
        while (!queue.isEmpty()) {
            File[] files = queue.poll().listFiles();
            if (files != null) {
                for (File el : files) {
                    if (el.isDirectory()) {
                        queue.offer(el);
                    }
                    if (isContains(el, exts)) {
                        result.add(el);
                    }
                }
            }
        }
        return result;
    }

    private boolean isContains(File file, List<String> exts) {
        boolean result = false;
        for (String item : exts) {
            if (file.getName().contains(item)) {
                result = true;
                break;
            }
        }
        return result;
    }

    public static void main(String[] args) throws IOException {
        final File TEMP_DIRECTORY = new File(System.getProperty("java.io.tmpdir"));
        Path tempDirPath = Paths.get(TEMP_DIRECTORY.getAbsolutePath() + "\\test");
        Files.createDirectory(tempDirPath);
        Path firstTemp = Paths.get(TEMP_DIRECTORY.getAbsolutePath() + "\\test" + "\\1.txt");
        Files.createFile(firstTemp);
        Path secondTemp = Paths.get(TEMP_DIRECTORY.getAbsolutePath() + "\\test" + "\\2.txt");
        Files.createFile(secondTemp);
        Path thirdTemp = Paths.get(TEMP_DIRECTORY.getAbsolutePath() + "\\test" + "\\3.txt");
        Files.createFile(thirdTemp);
        Path fourthTemp = Paths.get(TEMP_DIRECTORY.getAbsolutePath() + "\\test" + "\\4.docx");
        Files.createFile(fourthTemp);
    }
}
