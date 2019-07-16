package ru.job4j.inputoutput;


import org.junit.Test;
import java.io.*;
import java.util.stream.Stream;
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
        serverlog.deleteOnExit();
        result.deleteOnExit();
    }

    private File data(String file, String... properties) throws IOException {
        File path = new File(
                System.getProperty("java.io.tmpdir")
                        + System.clearProperty("file.separator")
                        + file
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