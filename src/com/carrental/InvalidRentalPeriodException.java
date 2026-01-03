package com.carrental;

public class InvalidRentalPeriodException extends RuntimeException {
    public InvalidRentalPeriodException(String message) {
        super(message);
    }
}
