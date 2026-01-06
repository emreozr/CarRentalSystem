package com.carrental;

/**
 * Thrown when an invalid rental period is provided.
 *
 * <p>This exception ensures that rental duration rules
 * (e.g., minimum of 1 day) are enforced consistently.</p>
 *
 * <p>Object-Oriented Concepts:
 * <ul>
 *   <li><b>Custom Exception:</b> Used to signal invalid domain input.</li>
 *   <li><b>Validation:</b> Protects the system from illegal rental states.</li>
 * </ul>
 * </p>
 */
public class InvalidRentalPeriodException extends RuntimeException {

    /**
     * Creates a new exception with a descriptive message.
     *
     * @param message error message explaining the cause
     */
    public InvalidRentalPeriodException(String message) {
        super(message);
    }
}
