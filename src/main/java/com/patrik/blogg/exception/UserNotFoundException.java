package com.patrik.blogg.exception;

public class UserNotFoundException extends RuntimeException{

    public UserNotFoundException(Long id){
        super("No user with that id: " + id);
    }
}
