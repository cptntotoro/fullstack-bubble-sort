package com.example.exceptioin;

public class ArrayIsEmptyException extends RuntimeException {
    public ArrayIsEmptyException(String message) {
        super(message);
    }
}
