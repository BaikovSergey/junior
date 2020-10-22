package ru.job4j.tracker.di;

import ru.job4j.tracker.ConsoleInput;

public class Main {
    public static void main(String[] args) {
        Context context = new Context();
        context.reg(Store.class);
        context.reg(ConsoleInput.class);
        context.reg(StartUIdi.class);

        StartUIdi ui = context.get(StartUIdi.class);

        ui.add("Petr Arsentev");
        ui.add("Ivan ivanov");
        ui.print();
    }
}
