package ru.job4j.thegame;

/**
 * @author Sergey Baikov
 * @version $1$
 * @since 23.10.19
 */
public class Table {

    /**
     * Stores instance of Table class.
     */
    private static Table instance;

    /**
     * Private constructor.
     */
    private Table() {
    }

    /**
     * Creates one instance of Table class.
     * @return instance of Table class.
     */
    public static Table getInstance() {
        if (instance == null) {
            instance = new Table();
        }
        return instance;
    }

    /**
     * Stores table.
     */
    private String[][] table;

    /**
     * Getter.
     * @return table.
     */
    public String[][] getTable() {
        return table;
    }

    /**
     * Creates a square table and sets all cells to "_".
     * @param size of the table.
     */
    public void setTable(int size) {
        this.table = new String[size][size];
        for (int i = 0; i < this.table.length; i++) {
            for (int j = 0; j < this.table[i].length; j++) {
                this.table[i][j] = "_";
            }
        }
    }

    /**
     * Prints table to console in formatted way.
     * @param arr table.
     */
    public void printTable(String[][] arr) {
        System.out.println();
        for (String[] anArr : arr) {
            printArray(anArr);
            System.out.println();
        }
    }

    /**
     * Prints one dimension array.
     * @param arr one dimension array.
     */
    private void printArray(String[] arr) {
        for (String string: arr) {
            System.out.print(" " + string + " ");
        }
    }
}
