package ru.job4j.tracker;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.function.Consumer;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class StartUITest {

    private final ByteArrayOutputStream out = new ByteArrayOutputStream();
    private final PrintStream stdout = new PrintStream(out);
    private final Consumer<String> output = new Consumer<String>() {


        @Override
        public void accept(String s) {
            stdout.println(s);
        }
    };
    private Tracker tracker;
    private Item first;

    /**
     * Before выполняет метод, до запуска теста.
     */
    @Before
    public void loadOutput() {
        tracker = new Tracker();
        first = tracker.add(new Item("test name", "desc"));
        System.out.println("execute before method");
        System.setOut(new PrintStream(this.out));

    }

    /**
     * After выполняет метод, после запуска теста.
     */
    @After
    public void backOutput() {
        System.setOut(this.stdout);
        System.out.println("execute after method");
    }

    /**
     * Test createItem.
     */
    @Test
    public void whenUserAddItemThenTrackerHasNewItemWithSameName() {
        Input input = new StubInput(new String[]{"0", "test name", "desc", "6"});
        new StartUI(new ValidateInput(input), tracker, output).init();
        assertThat(tracker.findAll().get(0).getName(), is("test name"));
    }

    /**
     * Test findAllItems.
     */
    @Test
    public void whenTwoItemsInTrackerThenLengthIsTwo() {
        Item second = tracker.add(new Item("test name2", "desc2"));
        Input input = new StubInput(new String[]{"1", "6"});
        new StartUI(input, tracker, output).init();
        assertThat(tracker.findAll().size(), is(2));
    }

    /**
     * Test replaceItem.
     */
    @Test
    public void whenUserReplaceItemThenTrackerHasThatItem() {
        Input input = new StubInput(new String[]{"2", first.getId(), "test name two", "change", "6"});
        new StartUI(input, tracker, output).init();
        assertThat(tracker.findAll().get(0).getName(), is("test name two"));
    }

    /**
     * Test deleteItem.
     */
    @Test
    public void whenDeleteItemThenOnlyOneItemLeft() {
        Item second = tracker.add(new Item("test name2", "desc2"));
        Input input = new StubInput(new String[]{"3", first.getId(), "6"});
        new StartUI(input, tracker, output).init();
        assertThat(tracker.findAll().get(0), is(second));
    }

    /**
     * Test findItemById.
     */
    @Test
    public void whenFindByIdThenReturnItemWithThatId() {
        Input input = new StubInput(new String[]{"4", first.getId(), "6"});
        new StartUI(input, tracker, output).init();
        assertThat(tracker.findAll().get(0).getName(), is("test name"));
    }

    /**
     * Test findItemByName.
     */
    @Test
    public void whenFindByNameThenReturnItemWithThatName() {
        Input input = new StubInput(new String[]{"5", first.getName(), "6"});
        new StartUI(input, tracker, output).init();
        assertThat(tracker.findAll().get(0).getName(), is("test name"));
    }

    /**
     * Test showMenu.
     */
    @Test
    public void whenShowMenuThenPrintMenu() {
        String id = tracker.findAll().get(0).getId();
        Input input = new StubInput(new String[]{"1", "6"});
        new StartUI(input, tracker, output).init();
        StringBuilder menu = new StringBuilder()
                .append("Меню.")
                .append(System.lineSeparator())
                .append("0 : Добавить новую заявку.")
                .append(System.lineSeparator())
                .append("1 : Показать все заявки.")
                .append(System.lineSeparator())
                .append("2 : Заменить заявку.")
                .append(System.lineSeparator())
                .append("3 : Удалить заявку.")
                .append(System.lineSeparator())
                .append("4 : Найти заявку по Id.")
                .append(System.lineSeparator())
                .append("5 : Найти заявку по имени.")
                .append(System.lineSeparator())
                .append("6 : Завершить работу программы.");



        assertThat(
                new String(this.out.toByteArray()),
                is(new StringBuilder()
                        .append(menu)
                        .append(System.lineSeparator())
                        .append("------------ Вывод всех заявок --------------")
                        .append(System.lineSeparator())
                        .append("Имя заявки: test name")
                        .append(System.lineSeparator())
                        .append("Id заявки: ")
                        .append(id)
                        .append(System.lineSeparator())
                        .append("Описание заявки: desc")
                        .append(System.lineSeparator())
                        .append(System.lineSeparator())
                        .append(System.lineSeparator())
                        .append(menu)
                        .append(System.lineSeparator())
                        .append("------------ Завершение работы программы --------------")
                        .append(System.lineSeparator())
                        .toString()

                )
        );
    }
}
