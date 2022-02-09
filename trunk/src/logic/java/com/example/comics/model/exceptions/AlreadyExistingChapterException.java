package com.example.comics.model.exceptions;

public class AlreadyExistingChapterException extends Exception {
    private static final long serialVersionUID = 1L;

    public AlreadyExistingChapterException (String message){
        super(message);
    }

}
