package com.carrental;

import java.time.LocalDate;

/**
 * Represents a rental transaction between a customer and a car.
 */
public class Rental {

    private final int rentalId;
    private final Car car;
    private final Customer customer;
    private final LocalDate startDate;
    private final int days;
    private RentalStatus status;
    private final double totalFee;

    /**
     * Creates a new rental.
     *
     * @param rentalId unique rental identifier
     * @param car      rented car
     * @param customer renting customer
     * @param days     rental duration in days
     */
    public Rental(int rentalId, Car car, Customer customer, int days) {
        if (days <= 0) {
            throw new InvalidRentalPeriodException("Kiralama günü 1 veya daha fazla olmalı.");
        }
        this.rentalId = rentalId;
        this.car = car;
        this.customer = customer;
        this.days = days;
        this.startDate = LocalDate.now();
        this.totalFee = car.calculateRentalFee(days);
        this.status = RentalStatus.ACTIVE;

        car.rent();
    }

    public int getRentalId() {
        return rentalId;
    }

    public RentalStatus getStatus() {
        return status;
    }

    public boolean isActive() {
        return status == RentalStatus.ACTIVE;
    }

    /**
     * @return total rental fee
     */
    public double getTotalFee() {
        return totalFee;
    }

    /**
     * Closes the rental and returns the car.
     */
    public void closeRental() {
        if (status == RentalStatus.RETURNED) {
            throw new RentalAlreadyClosedException("Bu kiralama zaten kapatılmış.");
        }
        status = RentalStatus.RETURNED;
        car.returnCar();
    }

    @Override
    public String toString() {
        return "Rental #" + rentalId +
                " | Araç: " + car.getBrand() + " " + car.getModel() +
                " | Müşteri: " + customer.getName() +
                " | Gün: " + days +
                " | Ücret: " + totalFee +
                " | Durum: " + status;
    }
}
