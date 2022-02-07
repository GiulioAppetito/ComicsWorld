package com.example.comics.model.exceptions;

public class MalformedEmailException extends Exception{
    private static final long serialVersionUID = 1L;

    public MalformedEmailException (String message){
        super(message);
    }

    public MalformedEmailException (Throwable cause) {
        super(cause);
    }

    public MalformedEmailException (String message, Throwable cause) {
        super(" +++ " + message + " +++ ", cause);
    }
}
