package com.carrental;

/**
 * Abstract base class for all car types.
 * Represents a rentable vehicle in the system.
 */
public abstract class Car implements Rentable {

    private final int id;
    private final String brand;
    private final String model;
    private final double dailyRate;
    private boolean available;

    /**
     * Creates a new car.
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
        this.available = true;
    }

    public int getId() { return id; }
    public String getBrand() { return brand; }
    public String getModel() { return model; }
    public double getDailyRate() { return dailyRate; }

    /**
     * @return true if car is available for rental
     */
    @Override
    public boolean isAvailable() {
        return available;
    }

    /**
     * Marks the car as rented.
     *
     * @throws CarNotAvailableException if the car is already rented
     */
    @Override
    public void rent() {
        if (!available) {
            throw new CarNotAvailableException("Araç şu anda müsait değil.");
        }
        available = false;
    }

    /**
     * Marks the car as returned.
     */
    @Override
    public void returnCar() {
        if (available) {
            throw new IllegalStateException("Araç zaten müsait.");
        }
        available = true;
    }

    /**
     * Calculates total rental fee.
     *
     * @param days number of rental days
     * @return total price
     */
    @Override
    public abstract double calculateRentalFee(int days);

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
