package com.example.comics.model.exceptions;

public class IncompleteReviewException extends Exception {
    private static final long serialVersionUID = 1L;

    public IncompleteReviewException (String message){
        super(message);
    }

    public IncompleteReviewException (String message, Throwable cause) {
        super(" +++ " + message + " +++ ", cause);
    }

}
