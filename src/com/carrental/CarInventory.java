package com.carrental;

import java.util.ArrayList;
import java.util.List;

public class CarInventory {
    private final List<Car> cars = new ArrayList<>();

    public void addCar(Car car) {
        cars.add(car);
    }

    public void addCars(List<Car> carList) {
        cars.addAll(carList);
        System.out.println(carList.size() + " araç envantere eklendi.");
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
        if (cars.isEmpty()) {
            System.out.println("Araç yok.");
            return;
        }
        for (Car car : cars) {
            System.out.println(car);
        }
    }

    // ---------------- FILTERING (varsa önceki committen kaldıysa kalsın) ----------------

    public List<Car> filterAvailableByBrand(String brand) {
        List<Car> result = new ArrayList<>();
        for (Car car : cars) {
            if (car.isAvailable() && car.getBrand().equalsIgnoreCase(brand)) {
                result.add(car);
            }
        }
        return result;
    }

    public List<Car> filterAvailableGasCars() {
        List<Car> result = new ArrayList<>();
        for (Car car : cars) {
            if (car.isAvailable() && car instanceof GasCar) {
                result.add(car);
            }
        }
        return result;
    }

    public List<Car> filterAvailableElectricCars() {
        List<Car> result = new ArrayList<>();
        for (Car car : cars) {
            if (car.isAvailable() && car instanceof ElectricCar) {
                result.add(car);
            }
        }
        return result;
    }

    public List<Car> filterAvailableByFuelType(FuelType fuelType) {
        List<Car> result = new ArrayList<>();
        for (Car car : cars) {
            if (car.isAvailable() && car instanceof GasCar) {
                GasCar g = (GasCar) car;
                if (g.getFuelType() == fuelType) {
                    result.add(car);
                }
            }
        }
        return result;
    }
}
