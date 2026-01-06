package com.carrental;

/**
 * Abstract base class for all car types in the system.
 *
 * <p>This class represents a generic rentable vehicle and defines
 * common properties and behaviors shared by all specific car types
 * such as GasCar, ElectricCar, and LuxuryCar.</p>
 *
 * <p>Object-Oriented Concepts:
 * <ul>
 *   <li><b>Abstraction:</b> This class is abstract and cannot be instantiated directly.</li>
 *   <li><b>Inheritance:</b> Concrete car types extend this class.</li>
 *   <li><b>Encapsulation:</b> Fields are private and accessed via methods.</li>
 *   <li><b>Polymorphism:</b> Subclasses implement their own rental fee calculation.</li>
 * </ul>
 * </p>
 */
public abstract class Car implements Rentable {

    // Unique identifier for the car (immutable after creation)
    private final int id;

    // Brand of the car (e.g., Toyota, BMW)
    private final String brand;

    // Model of the car (e.g., Corolla, A8)
    private final String model;

    // Daily rental price of the car
    private final double dailyRate;

    // Availability status of the car (true if available for rent)
    private boolean available;

    /**
     * Creates a new car instance.
     *
     * <p>When a car is created, it is marked as available by default.</p>
     *
     * @param id        unique car identifier
     * @param brand     car brand
     * @param model     car model
     * @param dailyRate daily rental price
     */
    public Car(int id, String brand, String model, double dailyRate) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.dailyRate = dailyRate;
        this.available = true; // newly created cars are available
    }

    /**
     * @return unique identifier of the car
     */
    public int getId() {
        return id;
    }

    /**
     * @return brand of the car
     */
    public String getBrand() {
        return brand;
    }

    /**
     * @return model of the car
     */
    public String getModel() {
        return model;
    }

    /**
     * @return daily rental price of the car
     */
    public double getDailyRate() {
        return dailyRate;
    }

    /**
     * Checks whether the car is currently available for rental.
     *
     * @return true if the car is available, false otherwise
     */
    @Override
    public boolean isAvailable() {
        return available;
    }

    /**
     * Marks the car as rented.
     *
     * <p>If the car is already rented, an exception is thrown to prevent
     * invalid state changes.</p>
     *
     * @throws CarNotAvailableException if the car is already rented
     */
    @Override
    public void rent() {
        // Prevent renting a car that is already in use
        if (!available) {
            throw new CarNotAvailableException("Araç şu anda müsait değil.");
        }
        available = false; // update availability state
    }

    /**
     * Marks the car as returned and makes it available again.
     *
     * <p>This method restores the car's availability after a rental
     * is completed.</p>
     *
     * @throws IllegalStateException if the car is already available
     */
    @Override
    public void returnCar() {
        // Prevent returning a car that is not currently rented
        if (available) {
            throw new IllegalStateException("Araç zaten müsait.");
        }
        available = true; // car is now available for new rentals
    }

    /**
     * Calculates the total rental fee for the given number of days.
     *
     * <p>This method is abstract and must be implemented by subclasses
     * to apply their own pricing logic (e.g., luxury surcharge,
     * electric vehicle discounts).</p>
     *
     * @param days number of rental days
     * @return total rental price
     */
    @Override
    public abstract double calculateRentalFee(int days);

    /**
     * Returns a human-readable representation of the car.
     *
     * <p>This method is commonly used when listing cars in the console UI.</p>
     *
     * @return formatted string containing car details
     */
    @Override
    public String toString() {
        return String.format(
                "[%d] %s %s | Günlük: %.2f | %s",
                id,
                brand,
                model,
                dailyRate,
                (available ? "MÜSAİT" : "KİRADA")
        );
    }
}
