package com.carrental;

import java.time.LocalDateTime;

public class Payment {
    private final int paymentId;
    private final int rentalId;
    private final double amount;
    private final String method; // CASH / CARD
    private final LocalDateTime paidAt;

    public Payment(int paymentId, int rentalId, double amount, String method) {
        this.paymentId = paymentId;
        this.rentalId = rentalId;
        this.amount = amount;
        this.method = method;
        this.paidAt = LocalDateTime.now();
    }

    public int getPaymentId() {
        return paymentId;
    }

    public int getRentalId() {
        return rentalId;
    }

    public double getAmount() {
        return amount;
    }

    public String getMethod() {
        return method;
    }

    public LocalDateTime getPaidAt() {
        return paidAt;
    }

    @Override
    public String toString() {
        return "Payment #" + paymentId +
                " | Rental: " + rentalId +
                " | Tutar: " + amount +
                " | Yöntem: " + method +
                " | Tarih: " + paidAt;
    }
}
