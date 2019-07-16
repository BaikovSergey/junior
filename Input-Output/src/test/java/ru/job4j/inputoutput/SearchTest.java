package ru.job4j.inputoutput;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class SearchTest {

    final File tempDirectory = new File(System.getProperty("java.io.tmpdir"));

    @Before
    public void createTempFiles() throws IOException {
        File testDir = new File(tempDirectory + File.separator + "test");
        boolean test = testDir.mkdir();
        testDir.deleteOnExit();
        File first = new File(tempDirectory + File.separator + "test" + File.separator + "1.txt");
        first.createNewFile();
        first.setReadable(true);
        first.deleteOnExit();
        File second = new File(tempDirectory + File.separator + "test" + File.separator + "2.txt");
        second.createNewFile();
        second.setReadable(true);
        second.deleteOnExit();
        File third = new File(tempDirectory + File.separator + "test" + File.separator + "3.txt");
        third.createNewFile();
        third.setReadable(true);
        third.deleteOnExit();
        File fourth = new File(tempDirectory + File.separator + "test" + File.separator + "4.docx");
        fourth.createNewFile();
        fourth.setReadable(true);
        fourth.deleteOnExit();

    }

    @Test
    public void whenFolderContainFilesThen1txt2txt3txt4docx() {
        Search test = new Search();
        List<String> extensions = new ArrayList<>();
        extensions.add(".txt");
        extensions.add(".docx");
        List<File> result = test.files(tempDirectory.getAbsolutePath() + File.separator + "test", extensions);
        assertThat(result.get(0).getName(), is("1.txt"));
        assertThat(result.get(1).getName(), is("2.txt"));
        assertThat(result.get(2).getName(), is("3.txt"));
        assertThat(result.get(3).getName(), is("4.docx"));
    }
}