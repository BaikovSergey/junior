package ru.job4j.thegame.menu.tablesize;

import ru.job4j.thegame.Table;
import ru.job4j.thegame.menu.IAction;
import ru.job4j.thegame.menu.IItem;

/**
 * @author Sergey Baikov
 * @version $1$
 * @since 23.10.19
 */
public class StandardSize implements IItem, IAction {

    /**
     * Sets Table parameter "setTable" to '3'.
     */
    @Override
    public void doAction() {
        Table table = Table.getInstance();
        table.setTable(3);
    }

    /**
     * Prints to console.
     */
    @Override
    public void print() {
        System.out.println("1. Standard table size e.g. 3x3");
    }
}
