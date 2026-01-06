package com.carrental;

import java.time.LocalDateTime;

public class Rental {

    private final int rentalId;
    private final Car car;
    private final Customer customer;
    private final int days;
    private final double totalFee;

    private RentalStatus status;
    private final LocalDateTime createdAt;
    private LocalDateTime closedAt;

    public Rental(int rentalId, Car car, Customer customer, int days) {
        if (car == null) throw new IllegalArgumentException("Araç null olamaz.");
        if (customer == null) throw new IllegalArgumentException("Müşteri null olamaz.");
        if (days <= 0) throw new InvalidRentalPeriodException("Kiralama günü 1 veya daha fazla olmalı.");

        this.rentalId = rentalId;
        this.car = car;
        this.customer = customer;
        this.days = days;

        this.createdAt = LocalDateTime.now();
        this.closedAt = null;

        this.status = RentalStatus.CREATED;

        // kiralamayı başlat
        startRental();

        // polymorphism: her araç kendi ücretini hesaplar
        this.totalFee = car.calculateRentalFee(days);
    }

    private void startRental() {
        if (!car.isAvailable()) {
            throw new CarNotAvailableException("Araç müsait değil.");
        }
        car.rent();
        this.status = RentalStatus.ACTIVE;
    }

    public void closeRental() {
        if (status == RentalStatus.COMPLETED) {
            throw new IllegalStateException("Bu kiralama zaten tamamlanmış.");
        }
        car.returnCar();
        this.status = RentalStatus.COMPLETED;
        this.closedAt = LocalDateTime.now();
    }

    public int getRentalId() {
        return rentalId;
    }

    public Car getCar() {
        return car;
    }

    public Customer getCustomer() {
        return customer;
    }

    public int getDays() {
        return days;
    }

    public double getTotalFee() {
        return totalFee;
    }

    public RentalStatus getStatus() {
        return status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getClosedAt() {
        return closedAt;
    }

    @Override
    public String toString() {
        return "Rental #" + rentalId +
                " | Customer: " + customer +
                " | Car: " + car +
                " | Days: " + days +
                " | Fee: " + totalFee +
                " | Status: " + status +
                " | Created: " + createdAt +
                (closedAt != null ? " | Closed: " + closedAt : "");
    }
}
