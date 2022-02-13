package com.example.comics.model.exceptions;

public class AlreadyExistingSeriesException extends Exception {
    private static final long serialVersionUID = 1L;

    public AlreadyExistingSeriesException (String message){
        super(message);
    }

    public AlreadyExistingSeriesException (String message, Throwable cause) {
        super(" +++ " + message + " +++ ", cause);
    }
}
