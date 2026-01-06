package com.carrental;

/**
 * Represents a customer in the car rental system.
 *
 * <p>This class stores basic customer information such as identity
 * and contact details. Customer objects are immutable once created.</p>
 *
 * <p>Object-Oriented Concepts:
 * <ul>
 *   <li><b>Encapsulation:</b> Customer data is protected via private fields.</li>
 *   <li><b>Immutability:</b> Fields are final and cannot be changed after creation.</li>
 *   <li><b>Separation of Concerns:</b> Customer class only holds customer-related data.</li>
 * </ul>
 * </p>
 */
public class Customer {

    // Unique identifier for the customer
    private final int id;

    // Customer's full name
    private final String name;

    // Customer's phone number
    private final String phone;

    /**
     * Creates a new customer.
     *
     * @param id unique customer identifier
     * @param name customer's name
     * @param phone customer's phone number
     */
    public Customer(int id, String name, String phone) {
        this.id = id;
        this.name = name;
        this.phone = phone;
    }

    /**
     * @return customer ID
     */
    public int getId() {
        return id;
    }

    /**
     * @return customer's name
     */
    public String getName() {
        return name;
    }

    /**
     * @return customer's phone number
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Returns a readable representation of the customer.
     *
     * <p>Used in rental and listing outputs.</p>
     *
     * @return formatted customer information
     */
    @Override
    public String toString() {
        return "[" + id + "] " + name + " | Tel: " + phone;
    }
}
