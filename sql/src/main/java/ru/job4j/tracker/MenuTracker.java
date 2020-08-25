package ru.job4j.tracker;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class MenuTracker {

    /**
     * хранит ссылку на объект.
     */
    private Input input;

    /**
     * хранит ссылку на объект.
     */
    private Tracker tracker;

    /**
     * хранит количестов пунктов меню.
     */
    private int position = 0;

    private StartUI ui;

    /**
     * хранит ссылку на массив типа UserAction.
     */
    private ArrayList<UserAction> actions = new ArrayList<>();

    private final Consumer<String> output;

    /**
     * Конструктор.
     *
     * @param input   объект типа Input
     * @param tracker объект типа Tracker
     */
    public MenuTracker(Input input, Tracker tracker, Consumer<String> output) {
        this.input = input;
        this.tracker = tracker;
        this.output = output;
    }

    public int getActionsLenght() {
        return this.actions.size();
    }

    /**
     * Метод заполняет массив.
     */
    public void fillActions(StartUI ui) {
        this.actions.add(new AddItem(0, "Добавить новую заявку."));
        this.actions.add(new ShowItems(1, "Показать все заявки."));
        this.actions.add(new MenuTracker.EditItem(2, "Заменить заявку."));
        this.actions.add(new MenuTracker.DeleteItem(3, "Удалить заявку."));
        this.actions.add(new FindItemById(4, "Найти заявку по Id."));
        this.actions.add(new FindItemsByName(5, "Найти заявку по имени."));
        this.actions.add(new ExitProgram(6, "Завершить работу программы.", ui));
    }

    /**
     * Метод в зависимости от указанного ключа, выполняет соотвествующие действие.
     *
     * @param key ключ операции
     */
    public void select(int key) {
        this.actions.get(key).execute(this.input, this.tracker);
    }

    /**
     * Метод выводит на экран меню.
     */
    public void show() {
        System.out.println(new StringBuilder()
                .append("Меню."));
        for (UserAction action : this.actions) {
            if (action != null) {
                output.accept(action.info());
            }
        }
    }
    public class AddItem extends BaseAction {

        public AddItem(int key, String name) {
            super(key, name);
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            output.accept("------------ Добавление новой заявки --------------");
            String name = input.ask("Введите имя заявки :");
            String desc = input.ask("Введите описание заявки :");
            Item item = new Item(name, desc);
            tracker.add(item);
            output.accept("------------ Новая заявка с Id : " + item.getId() + "-----------");
        }
    }

    public class ShowItems extends BaseAction {

        public ShowItems(int key, String name) {
            super(key, name);
        }

            @Override
            public void execute(Input input, Tracker tracker) {
                output.accept("------------ Вывод всех заявок --------------");
                List<Item> founds = tracker.findAll();
                for (int i = 0; i < founds.size(); i++) {
                    output.accept(founds.get(i).toString());
                    output.accept(System.lineSeparator());
                }
            }
    }

    public class EditItem extends BaseAction {

        public EditItem(int key, String name) {
            super(key, name);
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            output.accept("------------ Замена заявки --------------");
            String id = input.ask("Введите Id заявки, котрую хотите заменить: ");
            String name = input.ask("Введите имя новой заявки: ");
            String desc = input.ask("Введите описание заявки :");
            Item item = new Item(name, desc);
            if (tracker.replace(Integer.parseInt(id), item)) {
                output.accept("------------ Заявка успешно заменена -----------");
                output.accept("Имя заявки: " + name);
                output.accept("Id заявки: " + id);
            } else {
                output.accept("------------ Ошибка. Заявка с Id: " + id + " не найдена -----------");
            }
        }
    }

    public class DeleteItem extends BaseAction {

        public DeleteItem(int key, String name) {
            super(key, name);
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            output.accept("------------ Удаление заявки --------------");
            String id = input.ask("Введите Id заявки :");
            if (tracker.delete(Integer.parseInt(id))) {
                output.accept("------------ Заявка с Id: " + id + " удалена -----------");
            } else {
                output.accept("------------ Ошибка. Заявка с Id: " + id + " не найдена -----------");
            }
        }
    }

    public class FindItemById extends BaseAction {

        public FindItemById(int key, String name) {
            super(key, name);
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            output.accept("------------ Поиск заявки по Id --------------");
            String id = input.ask("Введите Id заявки :");
            Item finds = tracker.findById(Integer.parseInt(id));
            if (finds != null && finds.getId().equals(id)) {
                output.accept("------------ Заявка с Id: " + id + " -----------");
                System.out.println(finds.toString());
            } else {
                output.accept("------------ Ошибка. Заявка с Id: " + id + " не найдена -----------");
            }
        }
    }

    public class FindItemsByName extends BaseAction {

        public FindItemsByName(int key, String name) {
            super(key, name);
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            output.accept("------------ Поиск заявки по имени --------------");
            String name = input.ask("Введите имя заявки :");
            output.accept("------------ Заявки с именем: " + name + " -----------");
            for (int i = 0; i < tracker.findByName(name).size(); i++) {
                output.accept("Id заявки: " + tracker.findByName(name).get(i));
                output.accept("Описание заявки: " + tracker.findByName(name).get(i));
            }
        }
    }

    public class ExitProgram extends BaseAction {
        private StartUI startUI;

        public ExitProgram(int key, String name, StartUI startUI) {
            super(key, name);
            this.startUI = startUI;
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            output.accept("------------ Завершение работы программы --------------");
            startUI.exit();
        }
    }
}
