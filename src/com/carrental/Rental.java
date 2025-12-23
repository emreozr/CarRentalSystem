package com.carrental;

import java.time.LocalDate;

public class Rental {
    private final int rentalId;
    private final Car car;
    private final Customer customer;
    private final LocalDate startDate;
    private final int days;
    private boolean active;
    private final double totalFee;

    public Rental(int rentalId, Car car, Customer customer, int days) {
        if (days <= 0) {
            throw new IllegalArgumentException("Kiralama günü 1 veya daha fazla olmalı.");
        }
        this.rentalId = rentalId;
        this.car = car;
        this.customer = customer;
        this.days = days;
        this.startDate = LocalDate.now();
        this.totalFee = car.calculateRentalFee(days);
        this.active = true;

        // araç kiralanır
        car.rent();
    }

    public int getRentalId() {
        return rentalId;
    }

    public boolean isActive() {
        return active;
    }

    public void closeRental() {
        if (!active) {
            throw new IllegalStateException("Bu kiralama zaten kapatılmış.");
        }
        active = false;
        car.returnCar();
    }

    @Override
    public String toString() {
        return "Rental #" + rentalId +
                " | Araç: " + car.getBrand() + " " + car.getModel() +
                " | Müşteri: " + customer.getName() +
                " | Gün: " + days +
                " | Ücret: " + totalFee +
                " | Durum: " + (active ? "AKTİF" : "İADE EDİLDİ");
    }
}
