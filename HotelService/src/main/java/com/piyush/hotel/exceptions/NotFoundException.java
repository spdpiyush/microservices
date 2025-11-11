package com.piyush.hotel.exceptions;

public class NotFoundException extends RuntimeException{

    public NotFoundException() {
        super("Resource Not Found");
    }

    public NotFoundException(String message) {
        super(message);
    }
}
