package com.carrental;

/**
 * Defines the contract for rentable entities in the system.
 *
 * <p>This interface represents the abstraction of a rentable object.
 * Any class that can be rented (such as {@link Car}) must implement
 * this interface and provide concrete behavior.</p>
 *
 * <p>Object-Oriented Concepts:
 * <ul>
 *   <li><b>Abstraction:</b> Defines what a rentable object can do,
 *       without specifying how it does it.</li>
 *   <li><b>Interface:</b> Enforces a common contract for all rentable types.</li>
 *   <li><b>Polymorphism:</b> Different implementations can be treated
 *       uniformly via this interface.</li>
 * </ul>
 * </p>
 */
public interface Rentable {

    /**
     * Checks whether the object is currently available for rental.
     *
     * @return true if available, false otherwise
     */
    boolean isAvailable();

    /**
     * Marks the object as rented.
     *
     * <p>Implementations should prevent renting an already rented object.</p>
     */
    void rent();

    /**
     * Marks the object as returned.
     *
     * <p>Implementations should restore availability after rental completion.</p>
     */
    void returnCar();

    /**
     * Calculates the total rental fee for the given number of days.
     *
     * <p>The pricing logic may differ depending on the implementing class.</p>
     *
     * @param days number of rental days
     * @return total rental price
     */
    double calculateRentalFee(int days);
}
