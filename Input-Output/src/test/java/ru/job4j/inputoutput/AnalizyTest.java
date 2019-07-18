package ru.job4j.inputoutput;


import org.junit.Before;
import org.junit.Test;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class AnalizyTest {

    final File tempDirectory = new File(System.getProperty("java.io.tmpdir"));

    @Before
    public void preaper() throws IOException{
        File unavailable = new File(tempDirectory + File.separator + "unavailable.txt");
        unavailable.createNewFile();
        FileWriter writer = new FileWriter(unavailable);
        writer.write("200 10:56:01");
        writer.write(System.lineSeparator());
        writer.write("500 10:57:01");
        writer.write(System.lineSeparator());
        writer.write("400 10:58:01");
        writer.write(System.lineSeparator());
        writer.write("200 10:59:01");
        writer.write(System.lineSeparator());
        writer.write("500 11:01:02");
        writer.write(System.lineSeparator());
        writer.write("200 11:02:02");
        writer.write(System.lineSeparator());
        writer.close();
        unavailable.deleteOnExit();
        File result = new File(tempDirectory + File.separator + "result.txt");
        result.createNewFile();
        result.deleteOnExit();
    }

    @Test
    public void whenLoadLogFileThenFileWithServerDownTime() {
        List<String> result = new ArrayList<>();
        Analizy test = new Analizy();
        test.unavailable(tempDirectory + File.separator + "unavailable.txt",
                tempDirectory + File.separator + "result.txt");
        try (BufferedReader reader = new BufferedReader(new FileReader(tempDirectory + File.separator + "result.txt"))){
            reader.lines().forEach(result::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertThat(result.get(0), is("10:57:01;10:59:01"));
        assertThat(result.get(1), is("11:01:02;11:02:02"));
    }
}