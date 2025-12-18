package com.carrental;

public abstract class Car implements Rentable {
    private final int id;
    private final String brand;
    private final String model;
    private final double dailyRate;
    private boolean available;

    public Car(int id, String brand, String model, double dailyRate) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.dailyRate = dailyRate;
        this.available = true; // başta müsait
    }

    public int getId() { return id; }
    public String getBrand() { return brand; }
    public String getModel() { return model; }
    public double getDailyRate() { return dailyRate; }

    @Override
    public boolean isAvailable() { return available; }

    @Override
    public void rent() {
        if (!available) throw new IllegalStateException("Araç zaten kirada!");
        available = false;
    }

    @Override
    public void returnCar() {
        if (available) throw new IllegalStateException("Araç zaten müsait görünüyor!");
        available = true;
    }

    @Override
    public abstract double calculateRentalFee(int days);

    @Override
    public String toString() {
        return String.format("[%d] %s %s | Günlük: %.2f | %s",
                id, brand, model, dailyRate, (available ? "MÜSAİT" : "KİRADA"));
    }
}
