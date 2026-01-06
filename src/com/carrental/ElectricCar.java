package com.carrental;

/**
 * Represents an electric car in the rental system.
 *
 * <p>{@code ElectricCar} is a concrete implementation of {@link Car}
 * that adds electric-specific attributes such as driving range
 * and custom rental pricing logic.</p>
 *
 * <p>Object-Oriented Concepts:
 * <ul>
 *   <li><b>Inheritance:</b> Extends the abstract {@link Car} class.</li>
 *   <li><b>Polymorphism:</b> Overrides {@link #calculateRentalFee(int)} with
 *       electric vehicle pricing rules.</li>
 *   <li><b>Encapsulation:</b> Electric-specific data is stored in private fields.</li>
 * </ul>
 * </p>
 */
public class ElectricCar extends Car {

    // Maximum driving range of the electric car in kilometers
    private final int rangeKm;

    /**
     * Creates a new electric car.
     *
     * @param id unique car identifier
     * @param brand car brand
     * @param model car model
     * @param dailyRate daily rental price
     * @param rangeKm maximum driving range in kilometers
     */
    public ElectricCar(int id, String brand, String model, double dailyRate, int rangeKm) {
        super(id, brand, model, dailyRate);
        this.rangeKm = rangeKm;
    }

    /**
     * @return maximum driving range in kilometers
     */
    public int getRangeKm() {
        return rangeKm;
    }

    /**
     * Calculates rental fee for electric cars.
     *
     * <p>Electric vehicles have lower maintenance costs compared to gas cars,
     * therefore a smaller fixed battery maintenance fee is applied.</p>
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

        // Fixed battery maintenance fee for electric cars
        double batteryFee = 20.0;

        // Total price = daily rate * days + battery fee
        return days * getDailyRate() + batteryFee;
    }

    /**
     * Returns a string representation of the electric car.
     *
     * <p>Includes driving range information in addition to base car details.</p>
     *
     * @return formatted electric car information
     */
    @Override
    public String toString() {
        return super.toString() + " | Menzil: " + rangeKm + " km";
    }
}
