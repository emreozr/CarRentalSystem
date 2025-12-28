package com.carrental;

public class GasCar extends Car {
    private final FuelType fuelType;

    public GasCar(int id, String brand, String model, double dailyRate, FuelType fuelType) {
        super(id, brand, model, dailyRate);
        this.fuelType = fuelType;
    }

    public FuelType getFuelType() {
        return fuelType;
    }

    @Override
    public double calculateRentalFee(int days) {
        if (days <= 0) {
            throw new IllegalArgumentException("Gün sayısı 1 veya daha fazla olmalı!");
        }
        double serviceFee = 50.0;
        return days * getDailyRate() + serviceFee;
    }

    @Override
    public String toString() {
        return super.toString() + " | Yakıt: " + fuelType;
    }
}
