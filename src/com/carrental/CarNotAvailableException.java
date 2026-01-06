package com.carrental;

/**
 * Thrown when an attempt is made to rent a car that is not available.
 *
 * <p>This exception is used to prevent invalid rental operations
 * when a car is already rented by another customer.</p>
 *
 * <p>Object-Oriented Concepts:
 * <ul>
 *   <li><b>Custom Exception:</b> Represents a domain-specific error.</li>
 *   <li><b>Encapsulation:</b> Error logic is separated from business logic.</li>
 * </ul>
 * </p>
 */
public class CarNotAvailableException extends RuntimeException {

    /**
     * Creates a new exception with a descriptive message.
     *
     * @param message error message explaining the cause
     */
    public CarNotAvailableException(String message) {
        super(message);
    }
}
