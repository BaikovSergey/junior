package ru.job4j.menu;

import java.util.ArrayList;
import java.util.List;

public class MenuItemsSecondLvl {

    private List<IItem> menuItems = new ArrayList<>();

    public void setMenuItems() {
        this.menuItems.add(new Item11());
        this.menuItems.add(new Item12());
    }

    public List<IItem> getMenuItems() {
        return this.menuItems;
    }
}
