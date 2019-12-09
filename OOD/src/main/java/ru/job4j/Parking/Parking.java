package ru.job4j.Parking;

import java.util.HashMap;
import java.util.Map;


public class Parking implements IParking{
    /**
     * Contains cars in parking.
     */
    private Map<Integer, ICar> parking = new HashMap<>();

    /**
     * Contains free car space.
     */
    private int freeCarSpace = 2;

    /**
     * Contains free truck space.
     */
    private int freeTruckSpace = 1;

    /**
     * Method parks a car. If attempt to park a truck and where is no space for it,
     * park it on free car space if available.
     * @param car Parking car.
     * @return {@code true} if car is not in parking and where is free space for it, {@code false} otherwise.
     */
    @Override
    public boolean parkCar(ICar car) {
        boolean result = false;
        int id = car.getCarId();
        int space = car.getParkingSpace();
        if (!this.parking.containsKey(id)) {
            if (space > 1) {
                if (!truckSpaceIsFull()) {
                    this.parking.put(id, car);
                    this.freeTruckSpace -= 1;
                    result = true;
                } else if (!carSpaceIsFull()) {
                    this.parking.put(id, car);
                    this.freeCarSpace -= 2;
                    result = true;
                }
            } else {
                if (!carSpaceIsFull()) {
                    this.parking.put(id, car);
                    this.freeCarSpace -= 1;
                    result = true;
                }
            }
        }
        return result;
    }

    /**
     * Method unparks a car.
     * @param car Unparking car.
     * @return {@code true} if a car is in parking, {@code false} otherwise.
     */
    @Override
    public boolean unParkCar(ICar car) {
        boolean result = false;
        int id = car.getCarId();
        int space = car.getParkingSpace();
        if (this.parking.containsKey(id)) {
            if (space > 1) {
                this.freeTruckSpace += 1;
                result = true;
            } else {
                this.freeCarSpace += 1;
                result = true;
            }
        }

        return result;
    }

    /**
     * Method checks if where is free space for cars.
     * @return {@code true} where is free space for car, {@code false} otherwise.
     */
    @Override
    public boolean carSpaceIsFull() {
        boolean result = false;
        if (this.freeCarSpace == 0) {
            result = true;
        }
        return result;
    }

    /**
     * Method checks if where is free space for trucks.
     * @return {@code true} where is free space for truck, {@code false} otherwise.
     */
    @Override
    public boolean truckSpaceIsFull() {
        boolean result = false;
        if (this.freeTruckSpace == 0) {
            result = true;
        }
        return result;
    }

    /**
     * Free car space getter.
     * @return free car space.
     */
    public int getFreeCarSpace() {
        return this.freeCarSpace;
    }

    /**
     * Free truck space getter.
     * @return free truck space.
     */
    public int getFreeTruckSpace() {
        return this.freeTruckSpace;
    }
}
