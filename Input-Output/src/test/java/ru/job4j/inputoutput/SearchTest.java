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
        Path tempDirPath = Paths.get(tempDirectory.getAbsolutePath() + "\\test");
        Files.createDirectory(tempDirPath);
        Path firstTemp = Paths.get(tempDirectory.getAbsolutePath() + "\\test" + "\\1.txt");
        Files.createFile(firstTemp);
        Path secondTemp = Paths.get(tempDirectory.getAbsolutePath() + "\\test" + "\\2.txt");
        Files.createFile(secondTemp);
        Path thirdTemp = Paths.get(tempDirectory.getAbsolutePath() + "\\test" + "\\3.txt");
        Files.createFile(thirdTemp);
        Path fourthTemp = Paths.get(tempDirectory.getAbsolutePath() + "\\test" + "\\4.docx");
        Files.createFile(fourthTemp);
    }

    @After
    public void deleteTempFiles() throws IOException {

        Path firstTemp = Paths.get(tempDirectory.getAbsolutePath() + "\\test" + "\\1.txt");
        Files.delete(firstTemp);
        Path secondTemp = Paths.get(tempDirectory.getAbsolutePath() + "\\test" + "\\2.txt");
        Files.delete(secondTemp);
        Path thirdTemp = Paths.get(tempDirectory.getAbsolutePath() + "\\test" + "\\3.txt");
        Files.delete(thirdTemp);
        Path fourthTemp = Paths.get(tempDirectory.getAbsolutePath() + "\\test" + "\\4.docx");
        Files.delete(fourthTemp);
        Path tempDirPath = Paths.get(tempDirectory.getAbsolutePath() + "\\test");
        Files.delete(tempDirPath);
    }

    @Test
    public void whenFolderContainFilesThen1txt2txt3txt() {
        Search test = new Search();
        List<String> extensions = new ArrayList<>();
        extensions.add(".txt");
        extensions.add(".docx");
        List<File> result = test.files(tempDirectory.getAbsolutePath() + "\\test", extensions);
        assertThat(result.get(0).getName(), is("1.txt"));
        assertThat(result.get(1).getName(), is("2.txt"));
        assertThat(result.get(2).getName(), is("3.txt"));
        assertThat(result.get(3).getName(), is("4.docx"));
    }
}