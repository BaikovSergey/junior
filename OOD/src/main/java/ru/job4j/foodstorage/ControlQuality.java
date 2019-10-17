package ru.job4j.foodstorage;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ControlQuality {

    private Storage storage;
    
    private StorageRestore storageRestore;

    public void setStorage(Storage storage) {
        this.storage = storage;
    }

    public int getPercent(String createDate, String expireDate) {
        int result = 0;
        if (createDate != null && expireDate != null) {
        long first = LocalDate.parse(createDate).toEpochSecond(LocalTime.now(), ZoneOffset.UTC);
        long second = LocalDate.parse(expireDate).toEpochSecond(LocalTime.now(), ZoneOffset.UTC);
        long current = System.currentTimeMillis() / 1000;
        result = (int) (((current - first) * 100) / (second - first));
        }
        return result;
    }

    public void foodStorage(List<Food> food) {
        ControlQuality control = new ControlQuality();
        for(Food f: food) {
            int percent = control.getPercent(f.getCreateDate(), f.getExpireDate());
            if(storage.acceptFood(f, percent)) {
                storage.takeFood(f);
            }
        }
    }

    public void resort() {
        List<Food> foodList = new ArrayList<>();
        List<StorageRestore> storages = Arrays.asList(new Trash(), new Warehouse(), new Shop(),
                new LowTemperetureWarehouse(storage), new NewWarehouse(storage));
        for (StorageRestore storageRestore : storages) {
            foodList.addAll(storageRestore.takeBackFood());
        }
        this.foodStorage(foodList);
    }

    public static void main(String[] args) {
        ControlQuality test = new ControlQuality();
        Apples apple = new Apples("apple", "2020-01-01", "2019-08-01", 10, 0);
        List<Food> food = new ArrayList<>();
        food.add(apple);
        test.foodStorage(food);
        Warehouse warehouse = new Warehouse();
        System.out.println(warehouse.warehouse.size());
    }

}
