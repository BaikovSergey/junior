package ru.job4j.Parking;

public class Car implements ICar{

    /**
     * Contains car ID.
     */
    private int carId;

    /**
     * Contains space needed to park a car.
     */
    private int parkingSpace;

    /**
     * Constructor.
     * @param carID car ID.
     * @param parkingSpace space needed to park a car.
     */
    public Car(int carID, int parkingSpace) {
        this.carId = carID;
        this.parkingSpace = parkingSpace;
    }

    /**
     * ID getter.
     * @return car ID.
     */
    @Override
    public int getCarId() {
        return carId;
    }

    /**
     * Parking space getter.
     * @return parking space.
     */
    @Override
    public int getParkingSpace() {
        return parkingSpace;
    }
}
