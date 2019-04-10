package ru.job4j.inputoutput;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class ChatBot {

    private List<String> list = new ArrayList<>();
    private List<String> emptylist = List.of("");
    private List<String> workinglist = list;

    private void setup() {
        list.clear();
        try (BufferedReader read = new BufferedReader(new FileReader("ChatBotPhrases.txt"))) {
            read.lines().forEach(line -> list.add(line));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void start() {
        final String property = System.getProperty("java.io.tmpdir");
        final File file = new File(property + File.separator + "chatBotLog.txt");
        try (FileOutputStream fos = new FileOutputStream(file);
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(fos))) {
            Scanner input = new Scanner(System.in);
            String phrase = "";
            while (!phrase.equals("закончить")) {
                System.out.print("Введите слово или фразу: ");
                phrase = input.nextLine();
                if (phrase.equals("")) {
                    continue;
                }
                if (phrase.equals("стоп")) {
                    workinglist = emptylist;
                } else if (phrase.equals("продолжить")) {
                    workinglist = list;
                }
                writer.write("Вы: " + phrase);
                writer.newLine();
                writer.write("Бот: "
                + workinglist.get(ThreadLocalRandom.current().nextInt(0, workinglist.size())));
                writer.newLine();
                System.out.println(workinglist.get(ThreadLocalRandom.current().nextInt(0, workinglist.size())));
            }
            System.out.println("Завершение работы.");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        ChatBot bot = new ChatBot();
        bot.setup();
        bot.start();
    }
}
