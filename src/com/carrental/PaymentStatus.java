package com.carrental;

/**
 * Represents the lifecycle states of a payment.
 *
 * <p>This enum defines the possible states a {@link Payment} can have
 * during its lifecycle in the system.</p>
 *
 * <p>Payment Lifecycle:
 * <ul>
 *   <li><b>PENDING:</b> Payment is created but not yet completed</li>
 *   <li><b>PAID:</b> Payment has been successfully completed</li>
 *   <li><b>REFUNDED:</b> Payment has been refunded after rental return</li>
 * </ul>
 * </p>
 *
 * <p>Object-Oriented Concepts:
 * <ul>
 *   <li><b>Enum:</b> Restricts payment status to a fixed set of valid states.</li>
 *   <li><b>State Management:</b> Prevents invalid or undefined payment states.</li>
 *   <li><b>Readability:</b> Makes payment flow easier to understand and maintain.</li>
 * </ul>
 * </p>
 */
public enum PaymentStatus {

    // Payment is created but not yet processed
    PENDING,

    // Payment has been successfully completed
    PAID,

    // Payment has been refunded
    REFUNDED
}
