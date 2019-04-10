package ru.job4j.inputoutput;

import javafx.scene.shape.Path;
import org.junit.Test;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class AnalizyTest {

    @Test
    public void whenLoadConfigFileThenLoadConfigToHashMap() throws IOException {
        File serverlog = data(
                String.valueOf(System.currentTimeMillis()),
                "200 10:56:01"
                + "200 10:57:01"
                + "400 10:58:01"
                + "200 10:59:01"
                + "500 11:01:02"
                + "200 11:02:02"
        );

        File result = data(
                String.valueOf(System.currentTimeMillis()),
                ""
        );
        Analizy analizy = new Analizy();
        analizy.unavailable(serverlog.getAbsolutePath(), result.getAbsolutePath());
        //assertThat( is ("hibernate.dialect"));
        serverlog.deleteOnExit();
        result.deleteOnExit();
    }

    private File data(String file, String... properties) throws IOException {
        File path = new File(
                System.getProperty("java.io.tmpdir")
                        +System.clearProperty("file.separator")
                        +file
        );
        if (!path.createNewFile()) {
            throw new IllegalStateException(String.format("File could not created %s", path.getAbsoluteFile()));
        }
        try (final PrintWriter store = new PrintWriter(path)) {
            Stream.of(properties).forEach(store::println);
        }
        return path;
    }

}