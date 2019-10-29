package ru.job4j.template;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.HashMap;

import static org.hamcrest.Matchers.is;

public class TemplateTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void whenNameAndSubjectThenIAmSergeiWhoAreYou() {
        Template template = new Template();
        String text = "I am ${name}, who are ${subject}?";
        HashMap<String, String> data = new HashMap<>() {{
            put("name", "Sergei");
            put("subject", "you");
        }};
        String result = template.generate(text, data);
        String expect = "I am Sergei, who are you?";
        Assert.assertThat(result, is(expect));
    }

    @Test
    public void whenSosThenHelpAaaAaaAaa() {
        Template template = new Template();
        String text = "Help, ${sos}, ${sos}, ${sos}!";
        HashMap<String, String> data = new HashMap<>() {{
            put("sos", "Aaa");
        }};
        String result = template.generate(text, data);
        String expect = "Help, Aaa, Aaa, Aaa!";
        Assert.assertThat(result, is(expect));
    }

    @Test
    public void whenNoSuchKeyThenException() throws NoSuchKeyException {
        this.thrown.expect(NoSuchKeyException.class);
        this.thrown.expectMessage("No such key in table");
        Template template = new Template();
        String text = "Help, ${sos}, ${sos}, ${sos}!";
        HashMap<String, String> data = new HashMap<>() {{
            put("tag", "Zzz");
        }};
        String result = template.generate(text, data);
        String expect = "Help, Aaa, Aaa, Aaa!";
        Assert.assertThat(result, is(expect));
    }

    @Test
    public void whenMoreKeysInMapThenException() throws NoSuchKeyException {
        this.thrown.expect(NoSuchKeyException.class);
        this.thrown.expectMessage("No such key in table");
        Template template = new Template();
        String text = "I am ${name}, who are ${subject}?";
        HashMap<String, String> data = new HashMap<>() {{
            put("name", "Sergei");
            put("subject", "you");
            put("sos", "Aaa");
        }};
        String result = template.generate(text, data);
        String expect = "I am Sergei, who are you?";
        Assert.assertThat(result, is(expect));
    }

}