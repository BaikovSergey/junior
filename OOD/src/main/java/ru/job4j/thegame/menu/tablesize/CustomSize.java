package ru.job4j.thegame.menu.tablesize;

import ru.job4j.thegame.IO;
import ru.job4j.thegame.Table;
import ru.job4j.thegame.menu.IAction;
import ru.job4j.thegame.menu.IItem;

/**
 * @author Sergey Baikov
 * @version $1$
 * @since 23.10.19
 */
public class CustomSize implements IItem, IAction {

    /**
     * Takes player input and sets Table parameter "setTable" to user input.
     */
    @Override
    public void doAction() {
        Table table = Table.getInstance();
        IO input = new IO();
        table.setTable(input.setCustomSize());
    }

    /**
     * Prints to console.
     */
    @Override
    public void print() {
        System.out.println("2. Custom table size e.g. 5x5, 10x10");
    }
}
