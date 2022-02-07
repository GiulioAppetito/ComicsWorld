package com.example.comics.model.exceptions;

public class InvalidPaymentException extends Exception {
    private static final long serialVersionUID = 1L;

    public InvalidPaymentException (String message){
        super(message);
    }

    public InvalidPaymentException (Throwable cause) {
        super(cause);
    }

    public InvalidPaymentException (String message, Throwable cause) {
        super(" +++ " + message + " +++ ", cause);
    }
}
