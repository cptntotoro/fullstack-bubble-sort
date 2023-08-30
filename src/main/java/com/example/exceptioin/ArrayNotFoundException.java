package com.example.exceptioin;

public class ArrayNotFoundException extends RuntimeException {
    public ArrayNotFoundException(String message) {
        super(message);
    }
}
