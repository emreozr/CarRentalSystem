package com.carrental;

/**
 * Represents a gas-powered car in the rental system.
 *
 * <p>{@code GasCar} is a concrete implementation of {@link Car} that
 * adds fuel-specific behavior and pricing logic.</p>
 *
 * <p>Object-Oriented Concepts:
 * <ul>
 *   <li><b>Inheritance:</b> Extends the abstract {@link Car} base class.</li>
 *   <li><b>Polymorphism:</b> Overrides {@link #calculateRentalFee(int)} with
 *       gas-specific pricing logic.</li>
 *   <li><b>Encapsulation:</b> Fuel type is stored as a private field.</li>
 * </ul>
 * </p>
 */
public class GasCar extends Car {

    // Type of fuel used by the car (e.g., PETROL, DIESEL)
    private final FuelType fuelType;

    /**
     * Creates a new gas-powered car.
     *
     * @param id unique car identifier
     * @param brand car brand
     * @param model car model
     * @param dailyRate daily rental price
     * @param fuelType fuel type of the car
     */
    public GasCar(int id, String brand, String model, double dailyRate, FuelType fuelType) {
        super(id, brand, model, dailyRate);
        this.fuelType = fuelType;
    }

    /**
     * @return fuel type of the gas car
     */
    public FuelType getFuelType() {
        return fuelType;
    }

    /**
     * Calculates rental fee for gas-powered cars.
     *
     * <p>In addition to the daily rental price, a fixed service fee
     * is applied to gas cars.</p>
     *
     * @param days number of rental days
     * @return total rental price
     *
     * @throws IllegalArgumentException if days is less than or equal to zero
     */
    @Override
    public double calculateRentalFee(int days) {
        // Validate rental period
        if (days <= 0) {
            throw new IllegalArgumentException("Gün sayısı 1 veya daha fazla olmalı!");
        }

        // Fixed service fee applied to gas cars
        double serviceFee = 50.0;

        // Total price = daily rate * days + service fee
        return days * getDailyRate() + serviceFee;
    }

    /**
     * Returns a string representation of the gas car.
     *
     * <p>Includes fuel type information in addition to base car details.</p>
     *
     * @return formatted gas car information
     */
    @Override
    public String toString() {
        return super.toString() + " | Yakıt: " + fuelType;
    }
}
