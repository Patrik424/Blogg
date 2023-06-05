package com.patrik.blogg.exception;

public class CommentNotFoundException extends RuntimeException{

    public CommentNotFoundException(Long id){
        super("No comment with this id: " + id);
    }
}
