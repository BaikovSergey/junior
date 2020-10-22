package ru.job4j.tracker.di;

import ru.job4j.tracker.ConsoleInput;

public class StartUIdi {

    private Store store;

    private ConsoleInput consoleInput;

    public StartUIdi(Store store, ConsoleInput consoleInput) {
        this.store = store;
        this.consoleInput = consoleInput;
    }

    public void add(String value) {
        store.add(value);
    }

    public void print() {
        for (String value : store.getAll()) {
            System.out.println(value);
        }
    }
}
