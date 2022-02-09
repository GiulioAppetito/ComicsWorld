package com.example.comics.model.exceptions;

public class FailedRegistrationException extends Exception{
    private static final long serialVersionUID = 1L;

    public FailedRegistrationException (String message){
        super(message);
    }

    public FailedRegistrationException (Throwable cause) {
        super(cause);
    }

    public FailedRegistrationException (String message, Throwable cause) {
        super(" +++ " + message + " +++ ", cause);
    }
}