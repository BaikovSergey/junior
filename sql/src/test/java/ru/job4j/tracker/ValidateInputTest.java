package ru.job4j.tracker;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * @author Sergey Baikov
 * @version $ 1 $
 * @since 29.11.18
 */
public class ValidateInputTest {
    private final ByteArrayOutputStream mem = new ByteArrayOutputStream();
    private final PrintStream out = System.out;

    /**
     * Before выполняет метод, до запуска теста.
     */
    @Before
    public void loadMem() {
        System.setOut(new PrintStream(this.mem));
    }

    /**
     * After выполняет метод, после запуска теста.
     */
    @After
    public void loadSys() {
        System.setOut(this.out);
    }

    /**
     * Test input.
     */
    @Test
    public void whenInvalidCharacterInput() {
        ValidateInput input = new ValidateInput(
                new StubInput(new String[] {"invalid", "1"})
        );
        input.ask("Выберите пункт меню: ", new int[] {1});
        assertThat(
                this.mem.toString(),
                is(
                        String.format("Пожалуйста введите корректные данные.%n")
                )
        );
    }

    /**
     * Test input.
     */
    @Test
    public void whenInvalidNumberInput() {
        ValidateInput input = new ValidateInput(
                new StubInput(new String[] {"77", "1"})
        );
        input.ask("Выберите пункт меню: ", new int[] {1});
        assertThat(
                this.mem.toString(),
                is(
                        String.format("Пожалуйста выберите номер пункта из меню.%n")
                )
        );
    }
}