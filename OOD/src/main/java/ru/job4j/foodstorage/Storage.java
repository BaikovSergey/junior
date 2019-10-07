package ru.job4j.foodstorage;

import java.util.List;

public interface Storage {

    boolean acceptFood(Food food, int percent);

    void takeFood(Food food);
}
