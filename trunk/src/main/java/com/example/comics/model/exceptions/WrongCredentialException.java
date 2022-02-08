package com.example.comics.model.exceptions;

public class WrongCredentialException extends Exception {
    private static final long serialVersionUID = 1L;

    public WrongCredentialException (String message){
        super(message);
    }

    public WrongCredentialException (Throwable cause) {
        super(cause);
    }

    public WrongCredentialException (String message, Throwable cause) {
        super(" +++ " + message + " +++ ", cause);
    }
}
