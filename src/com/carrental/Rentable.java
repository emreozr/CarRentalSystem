package com.carrental;

public interface Rentable {
    boolean isAvailable();
    void rent();
    void returnCar();
    double calculateRentalFee(int days);
}
