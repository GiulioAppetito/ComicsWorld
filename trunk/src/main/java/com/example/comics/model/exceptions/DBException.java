package com.example.comics.model.exceptions;

public class DBException extends Exception{
    private static final long serialVersionUID = 1L;

    public DBException (String message){
        super(message);
    }

    public DBException (Throwable cause) {
        super(cause);
    }

    public DBException (String message, Throwable cause) {
        super(" +++ " + message + " +++ ", cause);
    }
}
