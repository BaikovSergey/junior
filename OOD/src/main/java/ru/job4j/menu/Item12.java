package ru.job4j.menu;

public class Item12 implements IItem, IAction {

    private String name = "--Задача 1.2";

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void print() {
        System.out.println(this.name);
    }

    @Override
    public void doAction() {
        System.out.println("Выполняется действие пункта 'Задача 1.2'");
    }
}
