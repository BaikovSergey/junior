package ru.job4j.parser;

import org.junit.Test;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class DBSQLRuParserSQLTest {

    @Test
    public void testConnection() {
        PostgreSQL PostgreSQL = new PostgreSQL();
        assertThat(PostgreSQL.connectToDB(), is(true));
    }

    @Test
    public void connectToDB() {
    }

    @Test
    public void insertVacancy() {
    }

    @Test
    public void selectFirstVacancy() {
    }

    @Test
    public void checkVacancyInDB() {
    }

    @Test
    public void createTable() {
    }

    @Test
    public void getDateFromLastVacancy() {
    }
}