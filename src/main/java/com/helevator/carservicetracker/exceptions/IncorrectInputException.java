package com.helevator.carservicetracker.exceptions;

/**
 * This custom exception that is used to throw the message
 * when an error occurs during the input of commands from the console
 */
public class IncorrectInputException extends RuntimeException {
    public IncorrectInputException(String errorMessage) {
        super(errorMessage);
    }
}
