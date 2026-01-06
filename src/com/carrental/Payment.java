package com.carrental;

import java.time.LocalDateTime;

public class Payment {

    private final int paymentId;
    private final int rentalId;
    private final double amount;
    private final PaymentMethod method;
    private final LocalDateTime paidAt;
    private PaymentStatus status;

    public Payment(int paymentId, int rentalId, double amount, PaymentMethod method) {
        this.paymentId = paymentId;
        this.rentalId = rentalId;
        this.amount = amount;
        this.method = method;
        this.paidAt = LocalDateTime.now();
        this.status = PaymentStatus.PAID; // kiralama anında ödendi varsayıyoruz
    }

    public int getRentalId() {
        return rentalId;
    }

    public double getAmount() {
        return amount;
    }

    public PaymentMethod getMethod() {
        return method;
    }

    public LocalDateTime getPaidAt() {
        return paidAt;
    }

    public PaymentStatus getStatus() {
        return status;
    }

    public void refund() {
        if (status == PaymentStatus.REFUNDED) {
            throw new IllegalStateException("Ödeme zaten iade edilmiş.");
        }
        status = PaymentStatus.REFUNDED;
    }

    @Override
    public String toString() {
        return "Payment #" + paymentId +
                " | Rental: " + rentalId +
                " | Tutar: " + amount +
                " | Yöntem: " + method +
                " | Durum: " + status +
                " | Tarih: " + paidAt;
    }
}
