package ru.job4j.inputoutput;

import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {

    private void zipFile(String directory, String exclude, String output) {
        try (ZipOutputStream out = new ZipOutputStream(new FileOutputStream(output))){
            Queue<File> queue = new LinkedList<>();
            File root = new File(directory);
            queue.offer(root);
            while (!queue.isEmpty()) {
                File[] files = queue.poll().listFiles();
                if (files != null) {
                    for (File f : files) {
                        if (f.isDirectory()) {
                            queue.offer(f);
                            out.putNextEntry(new ZipEntry(f.getPath()));
                            byte[] data = f.toString().getBytes();
                            out.write(data, 0, data.length);
                            out.closeEntry();
                        } else {
                            if (!f.getName().contains(exclude)) {
                                out.putNextEntry(new ZipEntry(f.getPath()));
                                byte[] data = f.toString().getBytes();
                                out.write(data, 0, data.length);
                                out.closeEntry();
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    private static void write(InputStream in, OutputStream out) throws IOException {
        byte[] buffer = new byte[1024];
        int len;
        while ((len = in.read(buffer)) >= 0)
            out.write(buffer, 0, len);
        in.close();
    }

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

    private String directory(String[] dir) {
        String result = null;
        if (dir != null) {
            result = dir[findKey(dir, "-d")];
        }
        return result;
    }

    private String exclude(String[] ex) {
        String result = null;
        if (ex != null) {
            result = ex[findKey(ex, "-e")];
        }
        return result;
    }

    private String output(String[] out) {
        String result = null;
        if (out != null) {
            result = out[findKey(out, "-o")];
        }
        return result;
    }

    public static void main(String[] args) {
        Zip zip = new Zip();
        final String[] param = args;
        zip.zipFile(zip.directory(param), zip.exclude(param), zip.output(param));

    }
}
