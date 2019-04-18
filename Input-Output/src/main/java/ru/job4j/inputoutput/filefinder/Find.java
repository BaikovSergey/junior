package ru.job4j.inputoutput.filefinder;

import com.google.common.base.Joiner;

import java.io.*;
import java.util.*;

public class Find {

    private static final String LN = System.getProperty("line.separator");

    /**
     * Method validates user input.
     * @param input
     * @return
     */
    private boolean inputValidation(String input) {
        boolean result = false;
            if (input.contains("-d")
                    && input.contains("-n")
                    || (input.contains("-m") || input.contains("-f") || input.contains("-r"))
                    && input.contains("-o")) {
                result = true;
            }
        return result;
    }

    /**
     * Method shows users menu.
     */
    private void menu() {
        Scanner scanner = new Scanner(System.in);
        String input = "";
        System.out.print(Joiner.on(LN).join(
                "Welcome to Search program.",
                "",
                "-d --directory search in",
                "-n --file to search",
                "-m --find by mask",
                "-f --full name match",
                "-r --regular expression",
                "-o --output file name (with extension)",
                "",
                "Input example: java -jar find.jar -d c:/ -n *.txt -m -o log.txt",
                "Please enter search parameters: "
        ));
        while (!input.equals("exit")) {
            input = scanner.nextLine();
            if (inputValidation(input)) {
                FindPars pars = new FindPars();
                String[] args = input.split(" ");
                for (String s : args) {
                    System.out.println(s);
                }
                findFile(pars.directory(args), pars.fileName(args), pars.output(args));
            } else {
                System.out.println("Неверный ввод");
                System.out.print("Please enter search parameters: ");
            }
        }

    }

    /**
     * Method finds file.
     */
    private void findFile(String directory, String fileName, String output) {
        System.out.println(directory);
        System.out.println(fileName);
        System.out.println(output);
        final String property = System.getProperty("java.io.tmpdir");
        final File file = new File(property + File.separator + output);
        List<String> result = search(directory, fileName);
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
        System.out.println("Search result are in file: " + output);
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
        test.menu();
    }

}
