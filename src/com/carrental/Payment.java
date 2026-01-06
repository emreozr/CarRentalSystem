package com.carrental;

import java.time.LocalDateTime;

/**
 * Represents a payment transaction in the car rental system.
 *
 * <p>A {@code Payment} object is associated with a specific rental
 * and records how much was paid, when it was paid, and by which method.</p>
 *
 * <p>Payment Lifecycle:
 * <ul>
 *   <li><b>PAID:</b> Payment successfully completed</li>
 *   <li><b>REFUNDED:</b> Payment refunded after rental return</li>
 * </ul>
 * </p>
 *
 * <p>Object-Oriented Concepts:
 * <ul>
 *   <li><b>Encapsulation:</b> Payment data is protected via private fields.</li>
 *   <li><b>State Management:</b> PaymentStatus prevents invalid refund operations.</li>
 *   <li><b>Enum Usage:</b> PaymentMethod and PaymentStatus define allowed values.</li>
 * </ul>
 * </p>
 */
public class Payment {

    // Unique identifier for the payment transaction
    private final int paymentId;

    // ID of the related rental transaction
    private final int rentalId;

    // Paid amount for the rental
    private final double amount;

    // Payment method used (e.g., CASH, CREDIT_CARD)
    private final PaymentMethod method;

    // Timestamp when the payment was made
    private final LocalDateTime paidAt;

    // Current payment status (PAID or REFUNDED)
    private PaymentStatus status;

    /**
     * Creates a new payment record.
     *
     * <p>Payments are assumed to be completed at the time of rental creation,
     * therefore the initial status is set to {@link PaymentStatus#PAID}.</p>
     *
     * @param paymentId unique payment identifier
     * @param rentalId related rental ID
     * @param amount payment amount
     * @param method payment method
     */
    public Payment(int paymentId, int rentalId, double amount, PaymentMethod method) {
        this.paymentId = paymentId;
        this.rentalId = rentalId;
        this.amount = amount;
        this.method = method;

        // Payment time is recorded automatically
        this.paidAt = LocalDateTime.now();

        // Initial payment state
        this.status = PaymentStatus.PAID;
    }

    /**
     * @return rental ID associated with this payment
     */
    public int getRentalId() {
        return rentalId;
    }

    /**
     * @return payment amount
     */
    public double getAmount() {
        return amount;
    }

    /**
     * @return payment method
     */
    public PaymentMethod getMethod() {
        return method;
    }

    /**
     * @return payment timestamp
     */
    public LocalDateTime getPaidAt() {
        return paidAt;
    }

    /**
     * @return current payment status
     */
    public PaymentStatus getStatus() {
        return status;
    }

    /**
     * Refunds the payment.
     *
     * <p>This method ensures that a payment cannot be refunded more than once
     * by checking the current {@link PaymentStatus}.</p>
     *
     * @throws IllegalStateException if the payment is already refunded
     */
    public void refund() {
        // Prevent double refund
        if (status == PaymentStatus.REFUNDED) {
            throw new IllegalStateException("Ödeme zaten iade edilmiş.");
        }

        // Update payment state
        status = PaymentStatus.REFUNDED;
    }

    /**
     * Returns a readable string representation of the payment.
     *
     * <p>Used for console output and payment listings.</p>
     *
     * @return formatted payment details
     */
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
