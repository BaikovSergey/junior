package ru.job4j.tracker;


import java.util.List;
import java.util.function.Consumer;

/**
 * @version $Id$
 * @since 19.11.18
 */
public class StartUI {

    private final Consumer<String> output;

    /**
     * Константа для выхода из цикла.
     */
    private boolean exit = false;

    /**
     * метод для выхода из цикла.
     */
    public void exit() {
        this.exit = true;
    }

    /**
     * Получение данных от пользователя.
     */
    private final Input input;

    /**
     * Хранилище заявок.
     */
    private final Tracker tracker;

    /**
     * Конструтор инициализирующий поля.
     *
     * @param input   ввод данных.
     * @param tracker хранилище заявок.
     */
    public StartUI(Input input, Tracker tracker, Consumer<String> output) {
        this.input = input;
        this.tracker = tracker;
        this.output = output;

    }

    /**
     * Основой цикл программы.
     */
    public void init() {
        MenuTracker menu = new MenuTracker(this.input, this.tracker, this.output);
        int[] range = new int[]{0, 1, 2, 3, 4, 5, 6};
        menu.fillActions(this);
        do {
            menu.show();
            menu.select(input.ask("Выберите пункт меню: ", range));
        } while (!this.exit);
    }

    /**
     * Метод реализует добавленяи новый заявки в хранилище.
     */
    private void createItem() {
        output.accept("------------ Добавление новой заявки --------------");
        String name = this.input.ask("Введите имя заявки :");
        String desc = this.input.ask("Введите описание заявки :");
        Item item = new Item(name, desc);
        this.tracker.add(item);
        output.accept("------------ Новая заявка с Id : " + item.getId() + "-----------");
    }

    /**
     * Метод реализует вывод всех заявок.
     */
    private void findAllItems() {
        output.accept("------------ Вывод всех заявок --------------");
        List<Item> founds = tracker.findAll();
        for (Item found : founds) {
            System.out.println(found.toString());
            System.out.println(System.lineSeparator());
        }
    }

    /**
     * Метод реализует замену заявки.
     */
    private void replaceItem() {
        output.accept("------------ Замена заявки --------------");
        String id = this.input.ask("Введите Id заявки, котрую хотите заменить: ");
        String name = this.input.ask("Введите имя новой заявки: ");
        String desc = this.input.ask("Введите описание заявки :");
        Item item = new Item(name, desc);
        if (tracker.replace(Integer.parseInt(id), item)) {
            output.accept("------------ Заявка успешно заменена -----------");
            output.accept("Имя заявки: " + name);
            output.accept("Id заявки: " + id);
        } else {
            output.accept("------------ Ошибка. Заявка с Id: " + id + " не найдена -----------");
        }
    }

    /**
     * Метод реализует добавленяи новый заявки в хранилище.
     */
    private void deleteItem() {
        output.accept("------------ Удаление заявки --------------");
        String id = this.input.ask("Введите Id заявки :");
        if (tracker.delete(Integer.parseInt(id))) {
            output.accept("------------ Заявка с Id: " + id + " удалена -----------");
        } else {
            output.accept("------------ Ошибка. Заявка с Id: " + id + " не найдена -----------");
        }
    }

    /**
     * Метод реализует поиск заявки по Id.
     */
    private void findItemById() {
        output.accept("------------ Поиск заявки по Id --------------");
        String id = this.input.ask("Введите Id заявки :");
        Item founds = tracker.findById(Integer.parseInt(id));
        if (founds != null && founds.getId().equals(id)) {
            output.accept("------------ Заявка с Id: " + id + " -----------");
            output.accept(founds.toString());
        } else {
            output.accept("------------ Ошибка. Заявка с Id: " + id + " не найдена -----------");
        }
    }

    /**
     * Метод реализует поиск заявки по Name.
     */
    private void findItemByName() {
        output.accept("------------ Поиск заявки по имени --------------");
        String name = input.ask("Введите имя заявки :");
        System.out.println("------------ Заявки с именем: " + name + " -----------");
        for (int i = 0; i < tracker.findByName(name).size(); i++) {
            output.accept("Id заявки: " + tracker.findByName(name).get(i));
            output.accept("Описание заявки: " + tracker.findByName(name).get(i));
        }
    }

    /**
     * Метод выводит на консоль пункты меню.
     */
    private void showMenu() {
        System.out.println(new StringBuilder()
                .append("Меню.")
                .append(System.lineSeparator())
                .append("0. Добавление новой заявки.")
                .append(System.lineSeparator())
                .append("1. Показать все заявки.")
                .append(System.lineSeparator())
                .append("2. Замена заявки.")
                .append(System.lineSeparator())
                .append("3. Удаление заявки.")
                .append(System.lineSeparator())
                .append("4. Поиск заявки по Id.")
                .append(System.lineSeparator())
                .append("5. Поиск заявки по имени.")
                .append(System.lineSeparator())
                .append("6. Завершение работы программы.")
                .append(System.lineSeparator()));

    }

    /**
     * Запускт программы.
     *
     * @param args args
     */
    public static void main(String[] args) {
        new StartUI(
                new ValidateInput(
                        new ConsoleInput()
                ),
                new Tracker(), new Consumer<String>() {
            @Override
            public void accept(String s) {

            }
        }
        ).init();
    }
}