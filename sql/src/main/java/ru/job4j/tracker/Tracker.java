package ru.job4j.tracker;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @version $2$
 * @since 12.11.18
 */
public class Tracker implements ITracker {

    /**
     * Список для хранение заявок.
     */
    private final List<Item> items = new ArrayList<>();

    /**
     * Метод реализаущий добавление заявки в хранилище
     * @param item новая заявка
     */
    @Override
    public Item add(Item item) {
        item.setId(this.generateId());
        this.items.add(item);
        return item;
    }

    /**
     * Метод генерирует уникальный ключ для заявки.
     * Так как у заявки нет уникальности полей, имени и описание. Для идентификации нам нужен уникальный ключ.
     * @return Уникальный ключ.
     */
    private int generateId() {
        return  (int)(Math.random() * 100);
    }

    /**
     * Метод заменяет ячейку в списке this.items на новую заявку.
     * @param id id заявки, которую необходимо заменить.
     * @param item новая заявка.
     */
    @Override
    public boolean replace(int id, Item item) {
        boolean result = false;
        Item find = items
                .stream()
                .filter(Objects::nonNull)
                .filter(s -> s.getId()==(id))
                .findAny()
                .orElse(null);
        if (find != null) {
            int itemId = find.getId();
            item.setId(itemId);
            this.items.set(this.items.indexOf(find), item);
            result = true;
        }
        return result;
    }

    /**
     * Метод удаляет ячейку в списке this.items.
     * @param id id.
     */
    @Override
    public boolean delete(int id) {
        boolean result = false;
        Item find = items
                .stream()
                .filter(Objects::nonNull)
                .filter(s -> s.getId()==(id))
                .findAny()
                .orElse(null);
        if (find != null) {
            this.items.remove(find);
            result = true;
        }
        return result;
    }

    /**
     * Метод возвращает копию списка в виде массива this.items без null элементов.
     * @return массив.
     */
    @Override
    public List<Item> findAll() {
        return this.items;
    }

    /**
     * Метод проверяет в цикле все элементы списка this.items,
     * сравнивая name (используя метод getName класса Item) с аргументом метода String key.
     * Элементы, у которых совпадает name, копирует в результирующий список и возвращает его.
     * @return список элементов.
     */
    @Override
    public List<Item> findByName(String key) {
        ArrayList<Item> finds = new ArrayList<>();
        items
                .stream()
                .filter(Objects::nonNull)
                .filter(s -> s.getName().equals(key))
                .forEach(finds :: add);
        return finds;
    }

    /**
     * Метод проверяет в цикле все элементы списка this.items,
     * сравнивая id с аргументом String id и возвращает найденный Item.
     * Если Item не найден - возвращает null.
     * @return искомую заявку.
     */
    @Override
    public Item findById(int id) {
        return items
                .stream()
                .filter(Objects::nonNull)
                .filter(s -> s.getId()==(id))
                .findAny()
                .orElse(null);
    }
}