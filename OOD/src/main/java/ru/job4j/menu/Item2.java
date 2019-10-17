package ru.job4j.menu;

public class Item2 implements IItem, IAction {

    private String name = "--Задача 2";

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void print() {
        System.out.println(this.name);
    }

    @Override
    public void doAction() {
        System.out.println("Выполняется действие пункта 'Задача 2'");
    }
}
