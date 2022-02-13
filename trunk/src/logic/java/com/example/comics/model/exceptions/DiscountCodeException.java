package com.example.comics.model.exceptions;

public class DiscountCodeException extends Exception{
    private static final long serialVersionUID = 5L;

    public DiscountCodeException(String message){
        super(message);
    }

    public DiscountCodeException (String message, Throwable cause) {
        super(" +++ " + message + " +++ ", cause);
    }

}
