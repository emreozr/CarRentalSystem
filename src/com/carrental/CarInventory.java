package com.carrental;

import java.util.ArrayList;
import java.util.List;

/**
 * Manages the car inventory of the system.
 *
 * <p>This class is responsible for storing car objects and providing
 * operations such as adding, removing, searching, listing, and filtering cars.</p>
 *
 * <p>Object-Oriented Concepts:
 * <ul>
 *   <li><b>Encapsulation:</b> The internal car list is private and only accessed via methods.</li>
 *   <li><b>Polymorphism:</b> The inventory stores cars using the base type {@link Car}.</li>
 *   <li><b>Type Checking:</b> Filtering uses {@code instanceof} for car-specific filters.</li>
 * </ul>
 * </p>
 */
public class CarInventory {

    /**
     * Internal storage for all cars in the system.
     * We store them as {@link Car} to support multiple car types in a single list.
     */
    private final List<Car> cars = new ArrayList<>();

    /**
     * Adds a single car to the inventory.
     *
     * <p>Validation rules:
     * <ul>
     *   <li>Car cannot be null</li>
     *   <li>Car ID must be unique</li>
     * </ul>
     * </p>
     *
     * @param car the car to add
     * @throws IllegalArgumentException if car is null or ID already exists
     */
    public void addCar(Car car) {
        // Null check to prevent NullPointerException in later operations
        if (car == null) throw new IllegalArgumentException("Araç null olamaz.");

        // Ensure unique ID: two cars cannot share the same identifier
        if (findCarById(car.getId()) != null) {
            throw new IllegalArgumentException("Bu ID zaten kullanılıyor: " + car.getId());
        }

        // Add the car into the internal list
        cars.add(car);
    }

    /**
     * Adds multiple cars at once (used for seeding initial data).
     *
     * <p>Before adding, this method checks that none of the IDs conflict with
     * existing inventory records.</p>
     *
     * @param carList list of cars to add
     * @throws IllegalArgumentException if an ID conflict occurs
     */
    public void addCars(List<Car> carList) {
        // If list is null/empty, there is nothing to add
        if (carList == null || carList.isEmpty()) return;

        // Validate ID uniqueness against current inventory
        for (Car c : carList) {
            if (c == null) continue; // skip null items safely
            if (findCarById(c.getId()) != null) {
                throw new IllegalArgumentException("Seed içinde çakışan ID var: " + c.getId());
            }
        }

        // Add all cars after validation
        cars.addAll(carList);

        // Console feedback: helps user see that seeding succeeded
        System.out.println(carList.size() + " araç envantere eklendi.");
    }

    /**
     * Removes a car from inventory by its unique ID.
     *
     * @param id car ID
     * @return true if the car was found and removed, false otherwise
     */
    public boolean removeCar(int id) {
        // Find the car first; if not found, we cannot remove it
        Car found = findCarById(id);
        if (found == null) return false;

        // Remove the found car from the list
        cars.remove(found);
        return true;
    }

    /**
     * Finds a car by its unique ID.
     *
     * @param id car ID
     * @return the car if found, otherwise null
     */
    public Car findCarById(int id) {
        // Linear search is enough for this project scale
        for (Car car : cars) {
            if (car.getId() == id) return car;
        }
        return null;
    }

    /**
     * Lists only the cars that are currently available for rental.
     *
     * <p>Uses {@link Car#isAvailable()} to check rental availability.</p>
     */
    public void listAvailableCars() {
        boolean found = false;

        // Print only available cars
        for (Car car : cars) {
            if (car.isAvailable()) {
                System.out.println(car);
                found = true;
            }
        }

        // If no cars are available, show a message to user
        if (!found) System.out.println("Müsait araç yok.");
    }

    /**
     * Lists all cars in the inventory (both available and rented).
     */
    public void listAllCars() {
        // Empty inventory check
        if (cars.isEmpty()) {
            System.out.println("Araç yok.");
            return;
        }

        // Print each car using its toString()
        for (Car car : cars) {
            System.out.println(car);
        }
    }

    // ================= FILTERING =================

    /**
     * Filters available cars by brand name (case-insensitive).
     *
     * @param brand brand name to filter
     * @return list of available cars matching the brand
     */
    public List<Car> filterAvailableByBrand(String brand) {
        List<Car> result = new ArrayList<>();

        // Add cars that are available AND brand matches
        for (Car car : cars) {
            if (car.isAvailable() && car.getBrand().equalsIgnoreCase(brand)) {
                result.add(car);
            }
        }
        return result;
    }

    /**
     * Filters available cars that are instances of {@link GasCar}.
     *
     * @return list of available gas cars
     */
    public List<Car> filterAvailableGasCars() {
        List<Car> result = new ArrayList<>();

        // instanceof checks the runtime type of each Car
        for (Car car : cars) {
            if (car.isAvailable() && car instanceof GasCar) {
                result.add(car);
            }
        }
        return result;
    }

    /**
     * Filters available cars that are instances of {@link ElectricCar}.
     *
     * @return list of available electric cars
     */
    public List<Car> filterAvailableElectricCars() {
        List<Car> result = new ArrayList<>();

        for (Car car : cars) {
            if (car.isAvailable() && car instanceof ElectricCar) {
                result.add(car);
            }
        }
        return result;
    }

    /**
     * Filters available gas cars by fuel type.
     *
     * <p>Only {@link GasCar} has fuel type, so we filter by:
     * available AND instance of GasCar AND fuelType matches.</p>
     *
     * @param fuelType fuel type to filter
     * @return list of available gas cars matching the given fuel type
     */
    public List<Car> filterAvailableByFuelType(FuelType fuelType) {
        List<Car> result = new ArrayList<>();

        for (Car car : cars) {
            // We only check fuel type for GasCar objects
            if (car.isAvailable() && car instanceof GasCar) {
                GasCar g = (GasCar) car; // safe cast after instanceof
                if (g.getFuelType() == fuelType) {
                    result.add(car);
                }
            }
        }
        return result;
    }
}
