package com.patrik.blogg.controller;

import com.patrik.blogg.model.Category;
import com.patrik.blogg.model.Comment;
import com.patrik.blogg.model.Post;
import com.patrik.blogg.repository.CategoryRepository;
import com.patrik.blogg.repository.CommentRepository;
import com.patrik.blogg.repository.PostRepository;
import com.patrik.blogg.service.CategoryService;
import com.patrik.blogg.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(value = "http://localhost:3000")
public class PostController {

    private final PostService postService;
    private final CategoryService categoryService;
    @Autowired
    private final CommentRepository commentRepository;

    private final PostRepository postRepository;


    public PostController(PostService postService, CategoryService categoryService, CommentRepository commentRepository, PostRepository postRepository) {
        this.postService = postService;
        this.categoryService = categoryService;
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
    }


    @GetMapping("/post")
    public List<Post> getAllPosts() {
        return postService.getAllPosts();
    }

    @GetMapping("/post/{id}")
    public ResponseEntity<Post> getPostById(@PathVariable("id") Long id) {
        Post post = postService.getPostById(id);
        Category category = categoryService.getCategoryById(post.getCategory().getCategoryId());
        post.getCategory().setName(category.getName());

        return new ResponseEntity<>(post, HttpStatus.OK);
    }

    @PostMapping("/newpost")
    public ResponseEntity<Post> newPost(@RequestBody Post post) {
        Category category = categoryService.getCategoryById(post.getCategory().getCategoryId());
        post.setCategory(category);

        return new ResponseEntity<>(postService.newPost(post), HttpStatus.CREATED);
    }

    @PutMapping("/editpost/{id}")
    public ResponseEntity<Post> updatePost(@PathVariable("id") Long id, @RequestBody Post post) {
        return new ResponseEntity<>(postService.updatePost(id, post), HttpStatus.OK);
    }

    @DeleteMapping("/deletepost/{id}")
    public ResponseEntity<String> deletePost(@PathVariable("id") Long id) {
        postService.deletePost(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/post/comments/{id}")
    public List<Comment> postComment(@PathVariable("id") Long id) {
        return commentRepository.findCommentByPost_PostId(id);
    }

    @GetMapping("/postbycategory/{id}")
    public List<Post> postCategory(@PathVariable("id") Long categoryId){
        return postRepository.findPostByCategory_CategoryId(categoryId);
    }

}


