package com.carrental;

/**
 * Defines fuel types for gas-powered vehicles in the system.
 *
 * <p>This enum represents the possible fuel options that can be used
 * by {@link GasCar} objects.</p>
 *
 * <p>Object-Oriented Concepts:
 * <ul>
 *   <li><b>Enum:</b> Restricts fuel type to a fixed set of valid values.</li>
 *   <li><b>Type Safety:</b> Prevents invalid or undefined fuel type usage.</li>
 *   <li><b>Readability:</b> Improves clarity in filtering and pricing logic.</li>
 * </ul>
 * </p>
 */
public enum FuelType {

    // Gasoline-powered vehicle
    BENZIN,

    // Diesel-powered vehicle
    DIZEL,

    // Liquefied petroleum gas powered vehicle
    LPG
}
