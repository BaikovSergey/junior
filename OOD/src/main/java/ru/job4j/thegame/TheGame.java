package ru.job4j.thegame;

import ru.job4j.thegame.menu.choseturn.ChoseTurn;
import ru.job4j.thegame.menu.gamedifficult.GameDifficult;
import ru.job4j.thegame.menu.tablesize.TableSize;

/**
 * @author Sergey Baikov
 * @version $1$
 * @since 23.10.19
 */
public class TheGame {

    /**
     * Stores instance of TheGame class.
     */
    private static TheGame instance;

    /**
     * Private constructor.
     */
    private TheGame() {
    }

    /**
     * Creates one instance of TheGame class.
     * @return instance of TheGame class.
     */
    public static TheGame getInstance() {
        if (instance == null) {
            instance = new TheGame();
        }
        return instance;
    }

    Logic logic = Logic.getInstance();
    Table table = Table.getInstance();
    TableSize tableSize = new TableSize();
    GameDifficult gameDifficult = new GameDifficult();
    ChoseTurn choseTurn = new ChoseTurn();
    Ai ai = new Ai();
    IO io = new IO();

    /**
     * Stores computers turn.
     */
    private boolean computerStartsFirst = false;

    /**
     * Setter.
     * @param computerStartsFirst True/False.
     */
    public void setComputerStartsFirst(boolean computerStartsFirst) {
        this.computerStartsFirst = computerStartsFirst;
    }

    /**
     * Stores field of the game.
     */
    private String[][] field;

    /**
     * Main method of the game.
     */
    public void start() {
        init();
        boolean haveWinner = false;
        while (!haveWinner) {
            this.table.printTable(this.field);
            if (computerStartsFirst) {
                this.field = this.ai.computerMakesTurn(this.field);
                this.computerStartsFirst = false;
                if (this.logic.gameIsFinished(this.field) || this.logic.win(this.field, "O")) {
                    haveWinner = true;
                    this.table.printTable(this.field);
                    System.out.println("Computer Wins");
                }
            } else {
                this.field = this.io.playerMakesTurn(this.field);
                this.computerStartsFirst = true;
                if (this.logic.gameIsFinished(this.field) || this.logic.win(this.field, "X")) {
                    haveWinner = true;
                    this.table.printTable(this.field);
                    System.out.println("You Win");
                }
            }
        }
    }

    /**
     * Setups the game parameters: size of the table, how many figures need to win, who start first.
     */
    private void init() {
        initTableSize();
        this.field = this.table.getTable();
        if (this.field.length < 5) {
            this.logic.setNeedFiguresToWin(3);
        } else {
            initGameDifficult();
        }
        initTurn();
    }

    /**
     * Setup size of the table.
     */
    private void initTableSize() {
        tableSize.showMenu();
        tableSize.doItemAction();
    }

    /**
     * Setup how many figures need to win.
     */
    private void initGameDifficult() {
        gameDifficult.showMenu();
        gameDifficult.doItemAction();
    }

    /**
     * Setup who start first.
     */
    private void initTurn() {
        choseTurn.showMenu();
        choseTurn.doItemAction();
    }

    /**
     * Starts the game.
     * @param args game setup.
     */
    public static void main(String[] args) {
        TheGame game = TheGame.getInstance();
        game.start();
    }
}
