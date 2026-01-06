package com.carrental;

/**
 * Thrown when an operation is attempted on a rental
 * that has already been completed.
 *
 * <p>This exception is used to prevent invalid state transitions,
 * such as trying to close or refund a rental more than once.</p>
 *
 * <p>Object-Oriented Concepts:
 * <ul>
 *   <li><b>Custom Exception:</b> Represents a domain-specific business rule.</li>
 *   <li><b>State Protection:</b> Prevents illegal operations on completed rentals.</li>
 * </ul>
 * </p>
 */
public class RentalAlreadyClosedException extends RuntimeException {

    /**
     * Creates a new exception with a descriptive message.
     *
     * @param message error message explaining the cause
     */
    public RentalAlreadyClosedException(String message) {
        super(message);
    }
}
