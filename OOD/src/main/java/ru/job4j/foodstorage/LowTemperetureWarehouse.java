package ru.job4j.foodstorage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LowTemperetureWarehouse extends StorageDecorator {

    List<Food> lowTemperatureWarehouse = new ArrayList<>();

    List<String> vegetables = Arrays.asList("Морковь", "Свекла", "Катофель");

    public LowTemperetureWarehouse(Storage decoratedStorage) {
        super(decoratedStorage);
    }

    @Override
    public boolean acceptFood(Food food, int percent) {
        boolean result = false;
        if (percent > 75 && percent < 100 && food.getReproduce() && isVegetable(food.getName())) {
            result = true;
        }
        return result;
    }

    @Override
    public void takeFood(Food food) {
        this.lowTemperatureWarehouse.add(food);
    }

    private boolean isVegetable(String name) {
        boolean result = false;
        if (this.vegetables.contains(name)) {
            result = true;
        }
        return result;
    }
}
