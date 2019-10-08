package ru.job4j.foodstorage;

import java.util.ArrayList;
import java.util.List;

public class Reproduction extends StorageDecorator {

    List<Food> reproducedWarehouse = new ArrayList<>();

    public Reproduction(Storage decoratedStorage) {
        super(decoratedStorage);
    }

    @Override
    public boolean acceptFood(Food food, int percent) {
        boolean result = false;
        if (percent > 75 && percent < 100 && food.getReproduce()) {
            result = true;
        }
        return result;
    }

    @Override
    public void takeFood(Food food) {
        this.reproducedWarehouse.add(food);
    }
}
