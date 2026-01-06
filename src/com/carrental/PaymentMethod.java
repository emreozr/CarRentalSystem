package com.carrental;

/**
 * Defines available payment methods in the car rental system.
 *
 * <p>This enum restricts payment options to a predefined set of values,
 * ensuring type safety and preventing invalid payment method usage.</p>
 *
 * <p>Object-Oriented Concepts:
 * <ul>
 *   <li><b>Enum:</b> Represents a fixed set of constant values.</li>
 *   <li><b>Type Safety:</b> Prevents invalid payment method inputs.</li>
 *   <li><b>Readability:</b> Makes payment logic more expressive and clear.</li>
 * </ul>
 * </p>
 */
public enum PaymentMethod {

    // Cash payment made directly during rental
    CASH,

    // Payment made using a debit or credit card
    CARD,

    // Bank transfer payment method
    TRANSFER,

    // Mobile or digital wallet payment (e.g., mobile apps)
    MOBILE_PAY
}
