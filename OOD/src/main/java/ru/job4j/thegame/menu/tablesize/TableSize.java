package ru.job4j.thegame.menu.tablesize;

import ru.job4j.thegame.menu.IAction;
import ru.job4j.thegame.menu.IItem;
import ru.job4j.thegame.menu.IMenu;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @author Sergey Baikov
 * @version $1$
 * @since 23.10.19
 */
public class TableSize implements IMenu {

    /**
     * Stores menu items.
     */
    private Map<String, IItem> menuItems = new HashMap<>();

    /**
     * Stores menu items actions.
     */
    private Map<String, IAction> menuActions = new HashMap<>();

    /**
     * Adds items to maps "menuItems" and "menuActions".
     */
    private void addMenuItems() {
        this.menuItems.put("1", new StandardSize());
        this.menuItems.put("2", new CustomSize());
        this.menuActions.put("1", new StandardSize());
        this.menuActions.put("2", new CustomSize());
    }

    /**
     * Shows menu items names in console.
     */
    @Override
    public void showMenu() {
        addMenuItems();
        System.out.println("Please select size of the field.");
        for (Map.Entry<String, IItem> entry: this.menuItems.entrySet()) {
            entry.getValue().print();
        }
    }

    /**
     * Takes players input from console and performs a menu item action.
     */
    @Override
    public void doItemAction() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter: ");
        String input = scanner.nextLine();
        this.menuActions.get(input).doAction();
    }
}
