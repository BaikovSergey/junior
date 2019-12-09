package ru.job4j.thegame;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Sergey Baikov
 * @version $1$
 * @since 23.10.19
 */
public class Logic {

    /**
     * Stores instance of Logic class.
     */
    private static Logic instance;

    /**
     * Private constructor.
     */
    private Logic() {

    }

    /**
     * Creates one instance of Logic class.
     * @return instance of Logic class.
     */
    public static Logic getInstance() {
        if (instance == null) {
            instance = new Logic();
        }
        return instance;
    }

    /**
     * Stores how many figures is needed to collect straight to win the game.
     */
    private int needFiguresToWin;

    /**
     * Getter.
     * @return needFiguresToWin.
     */
    public int getNeedFiguresToWin() {
        return needFiguresToWin;
    }

    /**
     * Checks if game field has not got free cells.
     * @param table game field.
     * @return true is table has free cells, false otherwise.
     */
    public boolean gameIsFinished(String[][] table) {
        boolean result = true;
        for (String[] arr: table) {
            for (String string: arr) {
                if (string.equals("_")) {
                    result = false;
                    break;
                }
            }
        }
        return result;
    }

    /**
     * Setter.
     * @param needStraightToWin number of figures needed to win the game.
     */
    public void setNeedFiguresToWin(int needStraightToWin) {
        this.needFiguresToWin = needStraightToWin;
    }

    /**
     * Checks if there is win combination of figures on the game field.
     * @param table game field.
     * @param figure type of figure "X" or "O".
     * @return true if there is win combination of figures on the game field, false otherwise.
     */
    public boolean win(String[][] table, String figure) {
        boolean result = false;
        if (winCondition(table, getNeedFiguresToWin(), figure)) {
            result = true;
        }
        return result;
    }

    /**
     * Checks if there is win condition in all rows, columns or diagonals.
     * @param table game field.
     * @param needStraightToWin number of figures needed to win the game.
     * @param figure type of figure "X" or "O".
     * @return true if there is win condition in any row, column or diagonal, false otherwise.
     */
    private boolean winCondition(String[][] table, int needStraightToWin, String figure) {
        boolean result = false;
            if (winConditionInRows(table, needStraightToWin, figure)
            || winConditionInColumns(table, needStraightToWin, figure)
            || winConditionInDiagonals(table, needStraightToWin, figure)) {
                result = true;
            }
        return result;
    }

    /**
     * Checks if there is win condition in all rows.
     * @param table game field.
     * @param needStraightToWin number of figures needed to win the game.
     * @param figure type of figure "X" or "O".
     * @return true if there is win condition in any row, false otherwise.
     */
    private boolean winConditionInRows(String[][] table, int needStraightToWin, String figure) {
        boolean result = false;
        for (String[] arr: table) {
            if (neededFiguresInArray(arr, needStraightToWin, figure)) {
                if (winConditionInArray(arr, needStraightToWin, figure)) {
                    result = true;
                    break;
                }
            }
        }
        return result;
    }

    /**
     * Checks if there is needed number of figures in String array.
     * @param arr String array.
     * @param needStraightToWin number of figures needed to win the game.
     * @param figure type of figure "X" or "O".
     * @return true if there is needed number of figures in array, false otherwise.
     */
    private boolean neededFiguresInArray(String[] arr, int needStraightToWin, String figure) {
        boolean result = false;
        int counter = 0;
        for (String s: arr) {
            if (s.equals(figure)) {
                counter++;
            }
        }
        if (counter == needStraightToWin) {
            result = true;
        }
        return result;
    }

    /**
     * Checks if there is needed number of figures of one type in array followed one by one.
     * @param arr String array.
     * @param needStraightToWin number of figures needed to win the game.
     * @param figure type of figure "X" or "O".
     * @return true if there is needed number of figures of one type in array followed one by one, false otherwise.
     */
    private boolean winConditionInArray(String[] arr, int needStraightToWin, String figure) {
        boolean result = false;
        int counter = 0;
        for (int i = 0; i <= arr.length - needStraightToWin; i++) {
            for (int j = i; j < needStraightToWin + i; j++) {
                if (arr[j].equals(figure)) {
                    counter++;
                }
            }
            if (counter == needStraightToWin) {
                result = true;
                break;
            }
            counter = 0;
        }
        return result;
    }

    /**
     * Checks if there is win condition in all columns.
     * @param table game field.
     * @param needStraightToWin number of figures needed to win the game.
     * @param figure type of figure "X" or "O".
     * @return true if there is win condition in any column, false otherwise.
     */
    private boolean winConditionInColumns(String[][] table, int needStraightToWin, String figure) {
        boolean result = false;
        String[] array = new String[table.length];
        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table.length; j++) {
                array[j] = table[j][i];
            }
            if (neededFiguresInArray(array, needStraightToWin, figure)) {
                if (winConditionInArray(array, needStraightToWin, figure)) {
                    result = true;
                    break;
                }
            }
        }
        return result;
    }

    /**
     * Checks if there is win condition in all diagonals.
     * @param table game field.
     * @param needStraightToWin number of figures needed to win the game.
     * @param figure type of figure "X" or "O".
     * @return true if there is win condition in any diagonal, false otherwise.
     */
    private boolean winConditionInDiagonals(String[][] table, int needStraightToWin, String figure) {
        boolean result = false;
        if (winConditionInLeftDiagonal(table, needStraightToWin, figure)
        || winConditionInRightDiagonal(table, needStraightToWin, figure)) {
            result = true;
        }
        return result;
    }

    /**
     * Checks if there is win condition in left diagonals (starts from upper left corner of the table).
     * @param table game field.
     * @param needStraightToWin number of figures needed to win the game.
     * @param figure type of figure "X" or "O".
     * @return true if there is win condition in any left diagonal, false otherwise.
     */
    private boolean winConditionInLeftDiagonal(String[][] table, int needStraightToWin, String figure) {
        boolean result = false;
        List<String> list;
        int row = 0;
        int column = table.length - needStraightToWin;
        for (int i = 0; i < 1 + (2 * (table.length - needStraightToWin)); i++) {
                list = wayThroughLeftDiagonal(table, row, column);
                if (column != 0) {
                    column--;
                } else {
                    row++;
                }
                String[] array = list.toArray(new String[0]);
                if (neededFiguresInArray(array, needStraightToWin, figure)) {
                    if (winConditionInArray(array, needStraightToWin, figure)) {
                        result = true;
                        break;
                    }
                }
        }
        return result;
    }

    /**
     * Checks if there is win condition in right diagonals (starts from upper right corner of the table).
     * @param table game field.
     * @param needStraightToWin number of figures needed to win the game.
     * @param figure type of figure "X" or "O".
     * @return true if there is win condition in any right diagonal, false otherwise.
     */
    private boolean winConditionInRightDiagonal(String[][] table, int needStraightToWin, String figure) {
        boolean result = false;
        List<String> list;
        int row = 0;
        int column = needStraightToWin - 1;
        for (int i = 0; i < 1 + (2 * (table.length - needStraightToWin)); i++) {
                list = wayThroughRightDiagonal(table, row, column);
                if (column != table.length - 1) {
                    column++;
                } else {
                    row++;
                }
                String[] array = list.toArray(new String[0]);
                if (neededFiguresInArray(array, needStraightToWin, figure)) {
                    if (winConditionInArray(array, needStraightToWin, figure)) {
                        result = true;
                        break;
                    }
                }
        }
        return result;
    }

    /**
     * Creates one dimensional array from right diagonal of the table.
     * @param table game field.
     * @param row start position if row.
     * @param column start position if column.
     * @return List of figures.
     */
    private List<String> wayThroughRightDiagonal(String[][] table, int row, int column) {
        ArrayList<String> result = new ArrayList<>();
        int tableRow = row;
        int tableColumn = column;
        for (int i = 0; i <= Math.abs(column - row); i++) {
            result.add(table[tableRow][tableColumn]);
            tableRow++;
            tableColumn--;
        }
        return result;
    }

    /**
     * Creates one dimensional array from left diagonal of the table.
     * @param table game field.
     * @param row start position if row.
     * @param column start position if column.
     * @return List of figures.
     */
    private List<String> wayThroughLeftDiagonal(String[][] table, int row, int column) {
        ArrayList<String> result = new ArrayList<>();
        int tableRow = row;
        int tableColumn = column;
        for (int i = 0; i < table.length - Math.abs(column - row); i++) {
            result.add(table[tableRow][tableColumn]);
            tableRow++;
            tableColumn++;
        }
        return result;
    }
}
