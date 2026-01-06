package com.carrental;

/**
 * Represents the lifecycle states of a rental transaction.
 *
 * <p>This enum defines the possible states a {@link Rental} object
 * can be in during its lifetime.</p>
 *
 * <p>Rental Lifecycle:
 * <ul>
 *   <li><b>CREATED:</b> Rental is created but not yet active</li>
 *   <li><b>ACTIVE:</b> Car is currently rented and in use</li>
 *   <li><b>COMPLETED:</b> Rental is finished and the car is returned</li>
 * </ul>
 * </p>
 *
 * <p>Object-Oriented Concepts:
 * <ul>
 *   <li><b>Enum:</b> Restricts rental status to a fixed set of valid states.</li>
 *   <li><b>State Management:</b> Prevents invalid rental state transitions.</li>
 *   <li><b>Readability:</b> Makes rental workflow easier to understand.</li>
 * </ul>
 * </p>
 */
public enum RentalStatus {

    // Rental object has been created but is not yet active
    CREATED,

    // Rental is active and the car is currently in use
    ACTIVE,

    // Rental has been completed and the car has been returned
    COMPLETED
}
