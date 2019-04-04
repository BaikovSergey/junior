package inputoutput;

import java.io.*;
import java.util.StringJoiner;

public class Analizy {
    public void unavailable(String source, String target) {
        try (BufferedReader read = new BufferedReader(new FileReader(source))) {
            read.lines().forEach(line -> {
                String[] code = line.split(" ", 1);
                String[] timeInterval = new String[2];
                if (code[0].contains("400") || code[0].contains("500")) {

                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try (PrintWriter out = new PrintWriter(new FileOutputStream("unavailable.csv"))) {
            out.println("15:01:30;15:02:32");
            out.println("15:10:30;23:12:32");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}