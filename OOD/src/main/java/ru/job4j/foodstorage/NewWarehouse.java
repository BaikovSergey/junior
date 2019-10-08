package ru.job4j.foodstorage;

import java.util.ArrayList;
import java.util.List;

public class NewWarehouse extends StorageDecorator{

    List<Food> newWarehouse = new ArrayList<>();

    public NewWarehouse(Storage decoratedStorage) {
        super(decoratedStorage);
    }

    @Override
    public boolean acceptFood(Food food, int percent) {
        boolean result = false;
        if (percent < 25) {
            result = true;
        }
        return result;
    }

    @Override
    public void takeFood(Food food) {
        this.newWarehouse.add(food);
    }
}
