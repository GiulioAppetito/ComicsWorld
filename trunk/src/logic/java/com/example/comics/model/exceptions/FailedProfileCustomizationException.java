package com.example.comics.model.exceptions;

public class FailedProfileCustomizationException extends Exception{
    private static final long serialVersionUID = 1L;

    public FailedProfileCustomizationException (String message){
        super(message);
    }

    public FailedProfileCustomizationException (String message, Throwable cause) {
        super(" +++ " + message + " +++ ", cause);
    }
}
