package com.example.comics.model.exceptions;

public class FailedPaymentException extends Exception{
    private static final long serialVersionUID = 1L;

    public FailedPaymentException (String message){
        super(message);
    }

}
