package com.patrik.blogg.exception;

public class AuthorNotFoundException extends RuntimeException{

    public AuthorNotFoundException(Long id){
        super("No author with that Id: " + id);
    }
}
