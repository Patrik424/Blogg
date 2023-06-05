package com.patrik.blogg.controller;

import com.patrik.blogg.model.Author;
import com.patrik.blogg.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(value = "http://localhost:3000")
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @GetMapping("/author")
    public List<Author> getAllBloggers(){return authorService.getAllAuthors();}

    @PostMapping("/newauthor")
    public ResponseEntity<Author> newBlogger(@RequestBody Author author){
        return new ResponseEntity<Author>(authorService.newAuthor(author), HttpStatus.CREATED);
    }

    @GetMapping("/author/{id}")
    public ResponseEntity<Author> getBloggerById(@PathVariable("id") Long id){
        return new ResponseEntity<Author>(authorService.getAuthorById(id), HttpStatus.OK);
    }

    @PutMapping("/editauthor/{id}")
    public ResponseEntity<Author> updateBlogger(@PathVariable("id") Long id, @RequestBody Author author){

        return new ResponseEntity<>(authorService.updateAuthor(id, author), HttpStatus.OK);
    }

    @DeleteMapping("/deleteauthor/{id}")
    public ResponseEntity<String> deleteBlogger(@PathVariable("id") Long id){
        authorService.deleteAuthor(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
