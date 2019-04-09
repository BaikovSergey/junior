package ru.job4j.inputoutput;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {

    private void zipFile(String directory, String exclude, String output) {
        try (ZipOutputStream out = new ZipOutputStream(new FileOutputStream(output))){
            File source = new File(directory);
            File[] list = source.listFiles();
            if (list != null) {
                for (File f : list) {
                    if (!f.getName().contains(exclude)) {
                        FileInputStream input = new FileInputStream(f);
                        byte[] buffer = new byte[input.available()];
                        out.putNextEntry(new ZipEntry(f.getPath()));
                        input.read(buffer);
                        out.write(buffer);
                        out.closeEntry();
                    }
                }
            }
        } catch (Exception e) {
            e.getStackTrace();
        }
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
