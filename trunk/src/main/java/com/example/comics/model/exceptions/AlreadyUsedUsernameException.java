package com.example.comics.model.exceptions;

public class AlreadyUsedUsernameException extends Exception{
    private static final long serialVersionUID = 1L;

    public AlreadyUsedUsernameException (String message){
        super(message);
    }

    public AlreadyUsedUsernameException (Throwable cause) {
        super(cause);
    }

    public AlreadyUsedUsernameException (String message, Throwable cause) {
        super(" +++ " + message + " +++ ", cause);
    }
}
