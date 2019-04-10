package ru.job4j.inputoutput;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {

    private void zipDirectory(String directory, String exclude, String output) {
        try (ZipOutputStream zipOut = new ZipOutputStream(new FileOutputStream(output))) {
            File folderToZip = new File(directory);
            zipFiles(folderToZip, exclude, zipOut);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void zipFiles(File folderToZip, String exclude, ZipOutputStream zos) throws IOException {
        Queue<File> queue = new LinkedList<>();
        queue.offer(folderToZip);
        while (!queue.isEmpty()) {
            File[] files = queue.poll().listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isDirectory()) {
                        queue.offer(file);
                        int rootLength = folderToZip.getPath().length() + File.separator.length();
                        zos.putNextEntry(new ZipEntry(file.getPath().substring(rootLength) + "/"));
                        zos.closeEntry();
                    } else if (!file.getName().contains(exclude)) {
                        try (FileInputStream fis = new FileInputStream(file)) {
                            int rootLength = folderToZip.getPath().length() + File.separator.length();
                            zos.putNextEntry(new ZipEntry(file.getPath().substring(rootLength)));
                            byte[] data = new byte[1024];
                            int length;
                            while ((length = fis.read(data)) >= 0) {
                                zos.write(data, 0, length);
                            }
                            zos.closeEntry();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        Zip zip = new Zip();
        Parsing parsing = new Parsing();
        zip.zipDirectory(parsing.directory(args), parsing.exclude(args), parsing.output(args));

    }
}
