package ru.job4j.thegame.menu.gamedifficult;

import ru.job4j.thegame.Logic;
import ru.job4j.thegame.menu.IAction;
import ru.job4j.thegame.menu.IItem;

/**
 * @author Sergey Baikov
 * @version $1$
 * @since 23.10.19
 */
public class HardDifficult implements IItem, IAction {

    /**
     * Stores Logic instance.
     */
    Logic logic = Logic.getInstance();

    /**
     * Sets Logic parameter "NeedFiguresToWin" to '5'.
     */
    @Override
    public void doAction() {
        this.logic.setNeedFiguresToWin(5);
    }

    /**
     * Prints to console.
     */
    @Override
    public void print() {
        System.out.println("2. Hard difficult - get 5 figures straight");
    }
}
