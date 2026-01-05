package com.carrental;

import java.time.LocalDateTime;

/**
 * Represents a payment made for a rental.
 */
public class Payment {

    private final int paymentId;
    private final int rentalId;
    private final double amount;
    private final PaymentMethod method;
    private final LocalDateTime paidAt;

    /**
     * Creates a payment record.
     *
     * @param paymentId unique payment id
     * @param rentalId  related rental id
     * @param amount    payment amount
     * @param method    payment method
     */
    public Payment(int paymentId, int rentalId, double amount, PaymentMethod method) {
        this.paymentId = paymentId;
        this.rentalId = rentalId;
        this.amount = amount;
        this.method = method;
        this.paidAt = LocalDateTime.now();
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
