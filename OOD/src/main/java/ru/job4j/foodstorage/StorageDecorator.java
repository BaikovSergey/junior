package ru.job4j.foodstorage;

public abstract class StorageDecorator implements Storage {

    protected Storage decoratedStorage;

    public StorageDecorator(Storage decoratedStorage) {
        this.decoratedStorage = decoratedStorage;
    }

    public boolean acceptFood(Food food, int percent) {
        return this.decoratedStorage.acceptFood(food, percent);
    }

    public void takeFood(Food food) {
        this.decoratedStorage.takeFood(food);
    }
}
