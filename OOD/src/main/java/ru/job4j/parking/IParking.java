package ru.job4j.parking;

public interface IParking {

    boolean parkCar(ICar car);

    boolean unParkCar(ICar car);

    boolean carSpaceIsFull();

    boolean truckSpaceIsFull();
}
