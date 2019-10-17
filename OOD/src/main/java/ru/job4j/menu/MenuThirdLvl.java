package ru.job4j.menu;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class MenuThirdLvl implements IMenu {

    private MenuItemsThirdLvl items = new MenuItemsThirdLvl();

    private Map<String, IItem> menuItems = new TreeMap<>();

    private List<IMenu> menuList = new ArrayList<>();

    @Override
    public void showMenu() {
        setMenuList();
        addToTree();
        for (Map.Entry<String, IItem> entry : menuItems.entrySet()) {
            entry.getValue().print();
        }
        for (IMenu menu : menuList) {
            menu.showMenu();
        }
    }

    @Override
    public void doItemAction(String item) {

    }

    private void addToTree() {
        this.items.setMenuItems();
        List<IItem> list = this.items.getMenuItems();
        for (IItem item: list) {
            this.menuItems.put(item.getName(), item);
        }
    }

    private void setMenuList() {
    }
}
