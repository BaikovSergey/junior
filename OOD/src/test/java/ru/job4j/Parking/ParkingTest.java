package ru.job4j.Parking;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class ParkingTest {

    @Test
    public void thenWhereIsFreeParkingSpaceWhenParkACar() {
        Car car = new Car(1, 1);
        Parking parking = new Parking();
        boolean result = parking.parkCar(car);
        assertThat(result, is(true));
    }

    @Test
    public void thenWhereIsFreeTruckParkingSpaceWhenParkATruck() {
        Car truck = new Car(1, 2);
        Parking parking = new Parking();
        boolean result = parking.parkCar(truck);
        assertThat(result, is(true));
    }

    @Test
    public void thenUnParkACarWhenPlusOneFreeCarParkingSpace() {
        Car car = new Car(1, 1);
        Parking parking = new Parking();
        parking.parkCar(car);
        int space = parking.getFreeCarSpace();
        boolean result = parking.unParkCar(car);
        assertThat(result, is(true));
        assertThat(parking.getFreeCarSpace(), is(space + 1));
    }

    @Test
    public void thenUnParkATruckWhenPlusOneFreeTruckParkingSpace() {
        Car truck = new Car(1, 2);
        Parking parking = new Parking();
        parking.parkCar(truck);
        int space = parking.getFreeTruckSpace();
        boolean result = parking.unParkCar(truck);
        assertThat(result, is(true));
        assertThat(parking.getFreeTruckSpace(), is(space + 1));
    }

    @Test
    public void thenWhereIsNotFreeParkingSpaceWhenFalse() {
        Car carOne = new Car(1, 1);
        Car carTwo = new Car(2, 1);
        Car carThree = new Car(2, 1);
        Parking parking = new Parking();
        parking.parkCar(carOne);
        parking.parkCar(carTwo);
        boolean result = parking.parkCar(carThree);
        assertThat(result, is(false));
    }

    @Test
    public void thenWhereIsThatCarInParkingWhenFalse() {
        Car carOne = new Car(1, 1);
        Car carTwo = new Car(1, 1);
        Parking parking = new Parking();
        parking.parkCar(carOne);
        boolean result = parking.parkCar(carTwo);
        assertThat(result, is(false));
    }

    @Test
    public void thenWhereIsNotFreeTruckParkingSpaceWhenParkATruckOnOtherPlace() {
        Car truckOne = new Car(1, 2);
        Car truckTwo = new Car(2, 2);
        Parking parking = new Parking();
        parking.parkCar(truckOne);
        parking.parkCar(truckTwo);
        assertThat(parking.getFreeCarSpace(), is(0));
    }

    @Test
    public void thenCantParkACarWhenNoParkingPlacesLeft() {
        Car carOne = new Car(1, 1);
        Car carTwo = new Car(2, 1);
        Car carThree = new Car(3, 1);
        Parking parking = new Parking();
        parking.parkCar(carOne);
        parking.parkCar(carTwo);
        assertThat(parking.parkCar(carThree), is(false));
    }

}