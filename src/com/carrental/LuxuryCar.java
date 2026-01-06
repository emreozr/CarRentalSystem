package com.carrental;

/**
 * LuxuryCar is a premium car type with higher rental pricing.
 * Demonstrates inheritance and polymorphism.
 */
public class LuxuryCar extends Car {

    private final double premiumRate; // 0.30 = %30 ekstra

    public LuxuryCar(int id, String brand, String model, double dailyRate) {
        super(id, brand, model, dailyRate);
        this.premiumRate = 0.30;
    }

    public LuxuryCar(int id, String brand, String model, double dailyRate, double premiumRate) {
        super(id, brand, model, dailyRate);
        this.premiumRate = premiumRate;
    }

    @Override
    public double calculateRentalFee(int days) {
        if (days <= 0) {
            throw new InvalidRentalPeriodException("Kiralama günü 1 veya daha fazla olmalı.");
        }
        return getDailyRate() * days * (1.0 + premiumRate);
    }

    @Override
    public String toString() {
        return super.toString() + " | Tip: LUXURY";
    }
}
