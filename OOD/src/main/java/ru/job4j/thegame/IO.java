package ru.job4j.thegame;

import java.util.Scanner;

/**
 * @author Sergey Baikov
 * @version $1$
 * @since 23.10.19
 */
public class IO {

    /**
     * Offers a player to choose field size through console.
     * @return length of one size of the game filed (e.g 5 -> field 5x5).
     */
    public int setCustomSize() {
        int result = 0;
        Scanner scanner = new Scanner(System.in);
        String userInput;
            do {
                System.out.println("Please enter positive number > 3");
                userInput = scanner.nextLine();
            } while (!validInput(userInput));
            int size = Integer.parseInt(userInput);
            if (size > 0) {
                result = size;
            }
        return result;
    }

    /**
     * Offers a player to choose a cell to put figure "X".
     * @param table game field.
     * @return renewed game field.
     */
    public String[][] playerMakesTurn(String[][] table) {
        boolean successfulTurn = false;
        Scanner scanner = new Scanner(System.in);
        String[][] result = table;
        int maxRange = table.length - 1;
        int row;
        int column;
        while (!successfulTurn) {
            System.out.println("Enter row from " + "0 " + "to " + maxRange);
            row = scanner.nextInt();
            System.out.println("Enter column from " + "0 " + "to " + maxRange);
            column = scanner.nextInt();
            if (result[row][column].equals("_")) {
                result[row][column] = "X";
                successfulTurn = true;
            }
        }
        return result;
    }

    /**
     * Checks if players input is valid (input is int number).
     * @param input players input.
     * @return true if input is valid, false otherwise.
     */
    private boolean validInput(String input) {
        boolean result = false;
        if (input != null && (input.matches("\\d") || input.matches("\\d+\\d"))) {
            result = true;
        }
        return result;
    }
}
