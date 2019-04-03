package ru.job4j.inputoutput;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class ConfigTest {

    @Test
    public void whenLoadConfigFileThenLoadConfigToHashMap() throws IOException {
        File path = data(
                String.valueOf(System.currentTimeMillis()),
                "hibernate.dialect=hibernate.dialect"
        );
        Config config = new Config(path.getAbsolutePath()).load();
        assertThat(config.value("hibernate.dialect"), is ("hibernate.dialect"));

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