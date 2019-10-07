package ru.job4j.foodstorage;

import java.util.ArrayList;
import java.util.List;

public class Warehouse implements Storage {

    List<Food> warehouse = new ArrayList<>();

    @Override
    public void takeFood(Food food) {
        this.warehouse.add(food);
    }

    @Override
    public boolean acceptFood(Food food, int percent) {
        boolean result = false;
        if (percent < 25) {
            result = true;
        }
        return result;
    }
}
