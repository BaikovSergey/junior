package ru.job4j.thegame;

import org.junit.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * @author Sergey Baikov
 * @version $1$
 * @since ??.11.19
 */
public class LogicTest {

    @Test
    public void whenTableHasGotEmptyCellsThenFalls() {
        Logic logic = Logic.getInstance();
        String[][] table = {{"X", "O", "X"},
                            {"O", "_", "X"},
                            {"O", "_", "_"}};
        boolean result = logic.gameIsFinished(table);
        assertThat(result, is(false));
    }

    @Test
    public void whenTableHasNotGotEmptyCellsThenTrue() {
        Logic logic = Logic.getInstance();
        String[][] table = {{"X", "O", "X"},
                            {"O", "X", "X"},
                            {"O", "X", "O"}};
        boolean result = logic.gameIsFinished(table);
        assertThat(result, is(true));
    }

    @Test
    public void whenThereIsWinConditionInFirstRowThenWinTrue() {
        Logic logic = Logic.getInstance();
        logic.setNeedFiguresToWin(3);
        String[][] table = {{"X", "X", "X"},
                            {"_", "_", "_"},
                            {"_", "_", "_"}};
        boolean result = logic.win(table, "X");
        assertThat(result, is(true));
    }

    @Test
    public void whenThereIsWinConditionInSecondRowThenWinTrue() {
        Logic logic = Logic.getInstance();
        logic.setNeedFiguresToWin(3);
        String[][] table = {{"_", "_", "_"},
                            {"X", "X", "X"},
                            {"_", "_", "_"}};
        boolean result = logic.win(table, "X");
        assertThat(result, is(true));
    }

    @Test
    public void whenThereIsWinConditionInThirdRowThenWinTrue() {
        Logic logic = Logic.getInstance();
        logic.setNeedFiguresToWin(3);
        String[][] table = {{"_", "_", "_"},
                            {"_", "_", "_"},
                            {"X", "X", "X"}};
        boolean result = logic.win(table, "X");
        assertThat(result, is(true));
    }

    @Test
    public void whenThereIsWinConditionInFirstColumnThenWinTrue() {
        Logic logic = Logic.getInstance();
        logic.setNeedFiguresToWin(3);
        String[][] table = {{"X", "_", "_"},
                            {"X", "_", "_"},
                            {"X", "_", "_"}};
        boolean result = logic.win(table, "X");
        assertThat(result, is(true));
    }

    @Test
    public void whenThereIsWinConditionInSecondColumnWinTrue() {
        Logic logic = Logic.getInstance();
        logic.setNeedFiguresToWin(3);
        String[][] table = {{"_", "X", "_"},
                            {"_", "X", "_"},
                            {"_", "X", "_"}};
        boolean result = logic.win(table, "X");
        assertThat(result, is(true));
    }

    @Test
    public void whenThereIsWinConditionInThirdColumnThenWinTrue() {
        Logic logic = Logic.getInstance();
        logic.setNeedFiguresToWin(3);
        String[][] table = {{"_", "_", "X"},
                            {"_", "_", "X"},
                            {"_", "_", "X"}};
        boolean result = logic.win(table, "X");
        assertThat(result, is(true));
    }

    @Test
    public void whenThereIsWinConditionInFirstDiagonalWinTrue() {
        Logic logic = Logic.getInstance();
        logic.setNeedFiguresToWin(3);
        String[][] table = {{"X", "_", "_"},
                            {"_", "X", "_"},
                            {"_", "_", "X"}};
        boolean result = logic.win(table, "X");
        assertThat(result, is(true));
    }

    @Test
    public void whenThereIsWinConditionInSecondDiagonalThenWinTrue() {
        Logic logic = Logic.getInstance();
        logic.setNeedFiguresToWin(3);
        String[][] table = {{"_", "_", "X"},
                            {"_", "X", "_"},
                            {"X", "_", "_"}};
        boolean result = logic.win(table, "X");
        assertThat(result, is(true));
    }

    @Test
    public void whenThereIsNoWinConditionInFieldThenWinTrue() {
        Logic logic = Logic.getInstance();
        logic.setNeedFiguresToWin(3);
        String[][] table = {{"_", "_", "_"},
                            {"_", "X", "_"},
                            {"_", "_", "_"}};
        boolean result = logic.win(table, "X");
        assertThat(result, is(false));
    }
}