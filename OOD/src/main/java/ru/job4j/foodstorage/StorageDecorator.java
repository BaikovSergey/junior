package ru.job4j.foodstorage;

import java.util.List;

public abstract class StorageDecorator implements Storage {

    protected Storage decoratedStorage;

    protected StorageRestore storageRestore;

    public StorageDecorator(Storage decoratedStorage) {
        this.decoratedStorage = decoratedStorage;
    }

    public boolean acceptFood(Food food, int percent) {
        return this.decoratedStorage.acceptFood(food, percent);
    }

    public void takeFood(Food food) {
        this.decoratedStorage.takeFood(food);
    }

    public List<Food> takeBackFood() {
        return this.storageRestore.takeBackFood();
    }
}
