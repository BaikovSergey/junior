package ru.job4j.foodstorage;

import java.util.ArrayList;
import java.util.List;

public class Shop implements Storage, StorageRestore {

    List<Food> shop = new ArrayList<>();

    @Override
    public void takeFood(Food food) {
        this.shop.add(food);
    }

    @Override
    public boolean acceptFood(Food food, int percent) {
        boolean result = false;
        if (percent >= 25 && percent <= 75) {
            result = true;
        }
        return result;
    }

    @Override
    public List<Food> takeBackFood() {
        List<Food> list = new ArrayList<>(this.shop);
        this.shop.clear();
        return list;
    }
}
