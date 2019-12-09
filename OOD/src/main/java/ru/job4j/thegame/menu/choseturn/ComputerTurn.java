package ru.job4j.thegame.menu.choseturn;

import ru.job4j.thegame.TheGame;
import ru.job4j.thegame.menu.IAction;
import ru.job4j.thegame.menu.IItem;

/**
 * @author Sergey Baikov
 * @version $1$
 * @since 23.10.19
 */
public class ComputerTurn implements IItem, IAction {

    /**
     * Stores TheGame instance.
     */
    TheGame game = TheGame.getInstance();

    /**
     * Sets TheGame parameter "ComputerStartsFirst" to true.
     */
    @Override
    public void doAction() {
        this.game.setComputerStartsFirst(true);
    }

    /**
     * Prints to console.
     */
    @Override
    public void print() {
        System.out.println("2. Let computer start first");
    }
}
