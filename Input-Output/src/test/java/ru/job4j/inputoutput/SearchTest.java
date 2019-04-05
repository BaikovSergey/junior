package ru.job4j.inputoutput;

import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class SearchTest {

    @Test
    public void whenFolderContainFilesThen1txt2txt3txt() {
        Search test = new Search();
        List<String> extensions = new ArrayList<>();
        extensions.add(".txt");
        extensions.add(".docx");
        File file = new File("test");
        System.out.println(file.getAbsolutePath());
        List<File> result = test.files(file.getAbsolutePath(), extensions);
        assertThat(result.get(0).getName(), is("1.txt"));
        assertThat(result.get(1).getName(), is("2.txt"));
        assertThat(result.get(2).getName(), is("3.txt"));
        assertThat(result.get(3).getName(), is("4.docx"));
    }
}