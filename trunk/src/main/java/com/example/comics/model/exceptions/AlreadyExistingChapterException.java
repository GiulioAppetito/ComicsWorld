package com.example.comics.model.exceptions;

public class AlreadyExistingChapterException extends Exception {
    private static final long serialVersionUID = 1L;

    public AlreadyExistingChapterException (String message){
        super(message);
    }

    public AlreadyExistingChapterException (Throwable cause) {
        super(cause);
    }

    public AlreadyExistingChapterException (String message, Throwable cause) {
        super(" +++ " + message + " +++ ", cause);
    }
}
