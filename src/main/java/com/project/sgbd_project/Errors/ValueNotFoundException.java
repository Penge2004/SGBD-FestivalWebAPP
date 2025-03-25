package com.project.sgbd_project.Errors;

/**
 * A custom exception that is being thrown when a Value is not found (as its name suggests)
 * */
public class ValueNotFoundException extends RuntimeException {
    public ValueNotFoundException(String message) {
        super(message);
    }
}
