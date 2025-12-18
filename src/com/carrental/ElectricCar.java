package com.carrental;

public class ElectricCar extends Car {
    private final int rangeKm;

    public ElectricCar(int id, String brand, String model, double dailyRate, int rangeKm) {
        super(id, brand, model, dailyRate);
        this.rangeKm = rangeKm;
    }

    public int getRangeKm() { return rangeKm; }

    @Override
    public double calculateRentalFee(int days) {
        if (days <= 0) throw new IllegalArgumentException("Gün sayısı 1 veya daha fazla olmalı!");
        // Elektrikli araçlarda şarj/batarya bakım farkı daha düşük
        double batteryFee = 20.0;
        return days * getDailyRate() + batteryFee;
    }

    @Override
    public String toString() {
        return super.toString() + " | Menzil: " + rangeKm + " km";
    }
}
