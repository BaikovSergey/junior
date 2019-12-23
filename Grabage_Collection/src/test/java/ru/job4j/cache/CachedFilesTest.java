package ru.job4j.cache;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.*;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class CachedFilesTest {

    final File tempDirectory = new File(System.getProperty("java.io.tmpdir"));

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream errOut = System.err;

    @Before
    public void createTempFiles() throws IOException {
        File testDir = new File(tempDirectory + File.separator + "test");
        testDir.mkdir();
        testDir.deleteOnExit();
        File file = new File(tempDirectory + File.separator + "test" + File.separator + "Names.txt");
        file.createNewFile();
        file.setReadable(true);
        file.deleteOnExit();

        try (BufferedWriter reader = new BufferedWriter(new FileWriter(file))) {
            reader.write("Test Name");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @After
    public void restoreStreams() {
        System.setOut(new PrintStream(originalOut));
        System.setErr(new PrintStream(errOut));
    }

    @Test
    public void whenPrintFromCacheThenTestNames() {
        CachedFiles cachedFiles = new CachedFiles();
        String expected = "Test Name";
        cachedFiles.printFromCache(tempDirectory.getAbsolutePath() + File.separator + "test", "Names.txt");
        assertThat(outContent.toString().trim(), is(expected));
    }
}