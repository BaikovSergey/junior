package ru.job4j.cache;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

/**
 * @author Sergey Baikov
 * @version $1$
 * @since 23.12.19
 */
public class CachedFiles {

    SoftRefCache<String, StringBuffer> cache = new SoftRefCache<>();

    /**
     * Attempts to get file data from cache and print it to console. If where is no file in cache, puts it there.
     * @param filePath path to file.
     * @param fileName file name.
     */
    public void printFromCache(String filePath, String fileName) {
        SoftRefCache<String, StringBuffer> cache = new SoftRefCache<>();
        StringBuffer text = getFileData(filePath, fileName);
        if (text != null) {
            System.out.println(text);
        } else {
            addToCache(filePath, fileName);
            text = cache.get(fileName).get();
            System.out.println(text);
        }
    }

    private void addToCache(String filePath, String fileName) {
        this.cache.put(fileName, getFileData(filePath, fileName));
    }

    private StringBuffer getFileData(String filePath, String fileName) {
        StringBuffer result = new StringBuffer();
        String file = filePath + File.separator + fileName;
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            reader.lines().forEach(result::append);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
