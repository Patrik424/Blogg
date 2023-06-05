package com.patrik.blogg.exception;

public class PostNotFoundException extends RuntimeException{

    public PostNotFoundException(Long id){
        super("No post with id: " + id);
    }
}
