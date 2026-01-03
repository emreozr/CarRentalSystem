package com.carrental;

public class RentalAlreadyClosedException extends RuntimeException {
    public RentalAlreadyClosedException(String message) {
        super(message);
    }
}
