package com.joseoliveros.tdd.stringcalculator;

public class InvalidNumberException extends RuntimeException {
    public InvalidNumberException(String message) {
        super(message);
    }
}
