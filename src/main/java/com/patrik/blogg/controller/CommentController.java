package com.patrik.blogg.controller;

import com.patrik.blogg.model.Comment;
import com.patrik.blogg.model.Post;
import com.patrik.blogg.model.User;
import com.patrik.blogg.repository.PostRepository;
import com.patrik.blogg.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(value = "http://localhost:3000")
public class CommentController {

    @Autowired
    private CommentService commentService;


    @GetMapping("/comment")
    public List<Comment> getAllComment() {
        List<Comment> comments = commentService.getAllComments();
        comments.forEach(comment -> comment.getUser().setUserId(comment.getUser().getUserId()));
        return comments;
    }
    @GetMapping("/comment/{id}")
    public ResponseEntity<Comment> getCommentById(@PathVariable("id") Long id){
        return new ResponseEntity<>(commentService.getCommentById(id), HttpStatus.OK);
    }

    @PostMapping("/newcomment")
    public ResponseEntity<Comment> newComment(@RequestBody Comment comment){
        return new ResponseEntity<>(commentService.newComment(comment), HttpStatus.CREATED);
    }



    @PutMapping("/editcomment/{id}")
    public ResponseEntity<Comment> updateComment(@PathVariable("id") Long id, @RequestBody Comment comment){
        return new ResponseEntity<>(commentService.updateComment(id,comment), HttpStatus.OK);
    }

    @DeleteMapping("/deletecomment/{id}")
    public ResponseEntity<String> deleteComment(@PathVariable("id") Long id){
        commentService.deleteComment(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
