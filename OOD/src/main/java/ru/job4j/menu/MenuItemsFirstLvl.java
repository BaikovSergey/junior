package ru.job4j.menu;

import java.util.ArrayList;
import java.util.List;

public class MenuItemsFirstLvl {

    private List<IItem> menuItems = new ArrayList<>();

    public void setMenuItems() {
        this.menuItems.add(new Item1());
        this.menuItems.add(new Item2());
    }

    public List<IItem> getMenuItems() {
        return this.menuItems;
    }
}
