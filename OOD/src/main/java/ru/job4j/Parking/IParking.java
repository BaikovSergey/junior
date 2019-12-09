package ru.job4j.Parking;

public interface IParking {

    boolean parkCar(ICar car);

    boolean unParkCar(ICar car);

    boolean carSpaceIsFull();

    boolean truckSpaceIsFull();
}
