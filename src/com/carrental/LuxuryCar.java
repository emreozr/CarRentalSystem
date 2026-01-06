package com.carrental;

/**
 * Represents a luxury (premium) car type in the rental system.
 *
 * <p>{@code LuxuryCar} extends the base {@link Car} class and applies
 * an additional premium rate to the rental price. This class is used
 * to demonstrate inheritance and polymorphism in the system.</p>
 *
 * <p>Object-Oriented Concepts:
 * <ul>
 *   <li><b>Inheritance:</b> LuxuryCar extends the abstract {@link Car} class.</li>
 *   <li><b>Polymorphism:</b> Overrides {@link #calculateRentalFee(int)} to apply
 *       luxury-specific pricing.</li>
 *   <li><b>Encapsulation:</b> Premium rate is stored as a private, immutable field.</li>
 * </ul>
 * </p>
 */
public class LuxuryCar extends Car {

    /**
     * Premium rate applied to the base rental price.
     *
     * <p>Example: 0.30 means 30% additional cost.</p>
     */
    private final double premiumRate;

    /**
     * Creates a luxury car with a default premium rate of 30%.
     *
     * @param id unique car identifier
     * @param brand car brand
     * @param model car model
     * @param dailyRate base daily rental price
     */
    public LuxuryCar(int id, String brand, String model, double dailyRate) {
        super(id, brand, model, dailyRate);
        this.premiumRate = 0.30; // default luxury premium
    }

    /**
     * Creates a luxury car with a custom premium rate.
     *
     * <p>This constructor allows different luxury levels
     * (e.g., ultra-luxury, executive class).</p>
     *
     * @param id unique car identifier
     * @param brand car brand
     * @param model car model
     * @param dailyRate base daily rental price
     * @param premiumRate additional premium rate
     */
    public LuxuryCar(int id, String brand, String model, double dailyRate, double premiumRate) {
        super(id, brand, model, dailyRate);
        this.premiumRate = premiumRate;
    }

    /**
     * Calculates the rental fee for luxury cars.
     *
     * <p>The total fee is calculated by applying the premium rate
     * on top of the base daily rental price.</p>
     *
     * @param days number of rental days
     * @return total rental price including luxury premium
     *
     * @throws InvalidRentalPeriodException if days is less than or equal to zero
     */
    @Override
    public double calculateRentalFee(int days) {
        // Validate rental duration
        if (days <= 0) {
            throw new InvalidRentalPeriodException("Kiralama günü 1 veya daha fazla olmalı.");
        }

        // Total price = base price * days * (1 + premium rate)
        return getDailyRate() * days * (1.0 + premiumRate);
    }

    /**
     * Returns a string representation of the luxury car.
     *
     * <p>Explicitly marks the car type as LUXURY in console listings.</p>
     *
     * @return formatted luxury car information
     */
    @Override
    public String toString() {
        return super.toString() + " | Tip: LUXURY";
    }
}
