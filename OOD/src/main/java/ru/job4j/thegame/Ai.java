package ru.job4j.thegame;

import java.util.concurrent.ThreadLocalRandom;

/**
 * @author Sergey Baikov
 * @version $1$
 * @since 23.10.19
 */
public class Ai {

    /**
     * Generates random int number.
     * @param start of numeric diapason.
     * @param end of numeric diapason.
     * @return random int number.
     */
    private int random(int start, int end) {
        return  ThreadLocalRandom.current().nextInt(start, end + 1);
    }

    /**
     * Puts "O" in random cell in the game field. If cell is occupied choose another cell.
     * @param table of the game.
     * @return field of the game.
     */
    public String[][] computerMakesTurn(String[][] table) {
        boolean successfulTurn = false;
        String[][] result = table;
        while (!successfulTurn) {
            int row = this.random(0, result.length - 1);
            int column = this.random(0, result.length - 1);
            if (!cellIsOccupied(result, row, column)) {
                result[row][column] = "O";
                successfulTurn = true;
            }
        }
        return result;
    }

    /**
     * Checks if cell in table is occupied by "X".
     * @param table game field.
     * @param row of the table.
     * @param column of the table.
     * @return true if occupied, false in other way.
     */
    private boolean cellIsOccupied(String[][] table, int row, int column) {
        boolean result = false;
        if (table[row][column].equals("X")) {
            result = true;
        }
        return result;
    }
}
