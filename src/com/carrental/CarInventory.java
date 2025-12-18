package com.carrental;

import java.util.ArrayList;
import java.util.List;

public class CarInventory {
    private final List<Car> cars = new ArrayList<>();

    public void addCar(Car car) {
        cars.add(car);
        System.out.println("Araç eklendi.");
    }

    public void removeCar(int id) {
        cars.removeIf(car -> car.getId() == id);
        System.out.println("Araç silindi.");
    }

    public Car findCarById(int id) {
        for (Car car : cars) {
            if (car.getId() == id) return car;
        }
        return null;
    }

    public void listAvailableCars() {
        boolean found = false;
        for (Car car : cars) {
            if (car.isAvailable()) {
                System.out.println(car);
                found = true;
            }
        }
        if (!found) System.out.println("Müsait araç yok.");
    }

    public void listAllCars() {
        for (Car car : cars) {
            System.out.println(car);
        }
    }
}
