package ru.job4j.foodstorage;

import java.util.ArrayList;
import java.util.List;

public class Trash implements Storage, StorageRestore {

    List<Food> trash = new ArrayList<>();

    @Override
    public void takeFood(Food food) {
        this.trash.add(food);
    }

    @Override
    public boolean acceptFood(Food food, int percent) {
        boolean result = false;
        if (percent > 75 && percent < 100) {
            result = true;
        }
        return result;
    }

    @Override
    public List<Food> takeBackFood() {
        List<Food> list = new ArrayList<>(this.trash);
        this.trash.clear();
        return list;
    }
}
