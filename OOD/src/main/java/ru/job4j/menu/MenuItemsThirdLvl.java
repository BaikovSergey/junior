package ru.job4j.menu;

import java.util.ArrayList;
import java.util.List;

public class MenuItemsThirdLvl {

    private List<IItem> menuItems = new ArrayList<>();

    public void setMenuItems() {
        this.menuItems.add(new Item111());
    }

    public List<IItem> getMenuItems() {
        return this.menuItems;
    }
}

