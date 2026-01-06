package com.carrental;

import java.time.LocalDateTime;

/**
 * Represents a rental transaction in the system.
 *
 * <p>A {@code Rental} connects a {@link Customer} with a {@link Car}
 * for a specific period of time. It also tracks the rental lifecycle
 * using {@link RentalStatus}.</p>
 *
 * <p>Rental Lifecycle:
 * <ul>
 *   <li><b>CREATED:</b> Rental object is created</li>
 *   <li><b>ACTIVE:</b> Car is rented and in use</li>
 *   <li><b>COMPLETED:</b> Car is returned and rental is closed</li>
 * </ul>
 * </p>
 *
 * <p>Object-Oriented Concepts:
 * <ul>
 *   <li><b>Encapsulation:</b> Rental data is protected via private fields.</li>
 *   <li><b>State Management:</b> Rental status prevents invalid transitions.</li>
 *   <li><b>Polymorphism:</b> Rental fee is calculated by the specific car type.</li>
 * </ul>
 * </p>
 */
public class Rental {

    // Unique identifier for the rental transaction
    private final int rentalId;

    // The rented car
    private final Car car;

    // Customer who rented the car
    private final Customer customer;

    // Number of rental days
    private final int days;

    // Total calculated rental fee (immutable after creation)
    private final double totalFee;

    // Current lifecycle status of the rental
    private RentalStatus status;

    // Timestamp when the rental was created
    private final LocalDateTime createdAt;

    // Timestamp when the rental was closed (null until completed)
    private LocalDateTime closedAt;

    /**
     * Creates a new rental transaction.
     *
     * <p>This constructor validates inputs, initializes the rental,
     * starts the rental process, and calculates the total rental fee.</p>
     *
     * @param rentalId unique rental identifier
     * @param car rented car
     * @param customer renting customer
     * @param days number of rental days
     *
     * @throws IllegalArgumentException if car or customer is null
     * @throws InvalidRentalPeriodException if days is less than or equal to zero
     * @throws CarNotAvailableException if the car is not available
     */
    public Rental(int rentalId, Car car, Customer customer, int days) {
        // Input validation
        if (car == null) throw new IllegalArgumentException("Araç null olamaz.");
        if (customer == null) throw new IllegalArgumentException("Müşteri null olamaz.");
        if (days <= 0) throw new InvalidRentalPeriodException("Kiralama günü 1 veya daha fazla olmalı.");

        this.rentalId = rentalId;
        this.car = car;
        this.customer = customer;
        this.days = days;

        // Set creation timestamp
        this.createdAt = LocalDateTime.now();
        this.closedAt = null;

        // Initial rental state
        this.status = RentalStatus.CREATED;

        // Start the rental lifecycle
        startRental();

        // Polymorphism: each car calculates its own rental fee
        this.totalFee = car.calculateRentalFee(days);
    }

    /**
     * Starts the rental by renting the car and updating the status.
     *
     * <p>This method ensures that a car cannot be rented if it is already
     * in use by another rental.</p>
     *
     * @throws CarNotAvailableException if the car is not available
     */
    private void startRental() {
        // Check availability before renting
        if (!car.isAvailable()) {
            throw new CarNotAvailableException("Araç müsait değil.");
        }

        // Change car state to rented
        car.rent();

        // Update rental lifecycle state
        this.status = RentalStatus.ACTIVE;
    }

    /**
     * Closes the rental transaction.
     *
     * <p>This method returns the car, updates the rental status,
     * and records the closing timestamp.</p>
     *
     * @throws IllegalStateException if the rental is already completed
     */
    public void closeRental() {
        // Prevent closing an already completed rental
        if (status == RentalStatus.COMPLETED) {
            throw new IllegalStateException("Bu kiralama zaten tamamlanmış.");
        }

        // Return the rented car
        car.returnCar();

        // Update lifecycle state and closing time
        this.status = RentalStatus.COMPLETED;
        this.closedAt = LocalDateTime.now();
    }

    /**
     * @return rental ID
     */
    public int getRentalId() {
        return rentalId;
    }

    /**
     * @return rented car
     */
    public Car getCar() {
        return car;
    }

    /**
     * @return customer who rented the car
     */
    public Customer getCustomer() {
        return customer;
    }

    /**
     * @return number of rental days
     */
    public int getDays() {
        return days;
    }

    /**
     * @return total rental fee
     */
    public double getTotalFee() {
        return totalFee;
    }

    /**
     * @return current rental status
     */
    public RentalStatus getStatus() {
        return status;
    }

    /**
     * @return rental creation timestamp
     */
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    /**
     * @return rental closing timestamp, or null if not closed
     */
    public LocalDateTime getClosedAt() {
        return closedAt;
    }

    /**
     * Returns a detailed string representation of the rental.
     *
     * <p>This output is used in console listings and reports.</p>
     *
     * @return formatted rental information
     */
    @Override
    public String toString() {
        return "Rental #" + rentalId +
                " | Customer: " + customer +
                " | Car: " + car +
                " | Days: " + days +
                " | Fee: " + totalFee +
                " | Status: " + status +
                " | Created: " + createdAt +
                (closedAt != null ? " | Closed: " + closedAt : "");
    }
}
