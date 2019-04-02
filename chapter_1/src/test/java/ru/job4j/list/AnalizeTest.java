package ru.job4j.list;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class AnalizeTest {

    @Test
    public void whenAddOneUserThenAdded1() {
        Analize test = new Analize();
        List<Analize.User> previous = new ArrayList<>();
        previous.add(new Analize.User(1, "Bob"));
        List<Analize.User> current = new ArrayList<>();
        current.add(new Analize.User(1, "Bob"));
        current.add(new Analize.User(2,"Tom"));
        Analize.Info result = test.diff(previous, current);
        assertThat(result.getAdded(), is(1));
        assertThat(result.getChanged(), is(0));
        assertThat(result.getDeleted(), is(0));
    }

    @Test
    public void whenDelOneUserThenDel1() {
        Analize test = new Analize();
        List<Analize.User> previous = new ArrayList<>();
        previous.add(new Analize.User(1, "Bob"));
        previous.add(new Analize.User(2,"Tom"));
        List<Analize.User> current = new ArrayList<>();
        current.add(new Analize.User(1, "Bob"));
        Analize.Info result = test.diff(previous, current);
        assertThat(result.getAdded(), is(0));
        assertThat(result.getChanged(), is(0));
        assertThat(result.getDeleted(), is(1));
    }

    @Test
    public void whenChangeOneUserThenChanged1() {
        Analize test = new Analize();
        Analize.User bob = new Analize.User(1, "Bob");
        Analize.User tom = new Analize.User(1, "Tom");
        Analize.User sam = new Analize.User(1, "Sam");
        List<Analize.User> previous = new ArrayList<>();
        previous.add(bob);
        previous.add(tom);
        List<Analize.User> current = new ArrayList<>();
        current.add(bob);
        current.add(sam);
        Analize.Info result = test.diff(previous, current);
        assertThat(result.getAdded(), is(0));
        assertThat(result.getChanged(), is(1));
        assertThat(result.getDeleted(), is(0));
    }

    @Test
    public void whenOneUserWasChangedAndOneAddedThenAdded1Changed1() {
        Analize test = new Analize();
        Analize.User bob = new Analize.User(1, "Bob");
        Analize.User tom = new Analize.User(2, "Tom");
        Analize.User sam = new Analize.User(2, "Sam");
        Analize.User dan = new Analize.User(4, "Dan");
        List<Analize.User> previous = new ArrayList<>();
        previous.add(bob);
        previous.add(tom);
        List<Analize.User> current = new ArrayList<>();
        current.add(bob);
        current.add(sam);
        current.add(dan);
        Analize.Info result = test.diff(previous, current);
        assertThat(result.getAdded(), is(1));
        assertThat(result.getChanged(), is(1));
        assertThat(result.getDeleted(), is(0));
    }
}