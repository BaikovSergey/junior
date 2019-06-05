package ru.job4j.tracker;

import static org.junit.Assert.*;
import org.junit.Test;

import static org.hamcrest.Matchers.is;

public class TrackerSQLTest {
    @Test
    public void checkConnection() {
        TrackerSQL sql = new TrackerSQL();
        assertThat(sql.init(), is(true));
    }

}