package com.carrental;

public class GasCar extends Car {
    private final String fuelType; // Benzin/Dizel/LPG

    public GasCar(int id, String brand, String model, double dailyRate, String fuelType) {
        super(id, brand, model, dailyRate);
        this.fuelType = fuelType;
    }

    public String getFuelType() { return fuelType; }

    @Override
    public double calculateRentalFee(int days) {
        if (days <= 0) throw new IllegalArgumentException("Gün sayısı 1 veya daha fazla olmalı!");
        // örnek: yakıtlı araçlarda küçük servis ücreti
        double serviceFee = 50.0;
        return days * getDailyRate() + serviceFee;
    }

    @Override
    public String toString() {
        return super.toString() + " | Yakıt: " + fuelType;
    }
}
