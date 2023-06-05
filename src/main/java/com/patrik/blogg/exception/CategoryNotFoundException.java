package com.patrik.blogg.exception;

public class CategoryNotFoundException extends RuntimeException{

    public CategoryNotFoundException(Long id){
        super("No Category with this id: " + id);
    }
}
