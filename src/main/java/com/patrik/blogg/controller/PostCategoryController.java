package com.patrik.blogg.controller;

import com.patrik.blogg.model.Category;
import com.patrik.blogg.model.Post;
import com.patrik.blogg.model.PostCategory;
import com.patrik.blogg.service.CategoryService;
import com.patrik.blogg.service.PostCategoryService;
import com.patrik.blogg.service.PostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/post-categories")
public class PostCategoryController {

    private final PostCategoryService postCategoryService;
    private final PostService postService;
    private final CategoryService categoryService;

    public PostCategoryController(PostCategoryService postCategoryService, PostService postService, CategoryService categoryService) {
        this.postCategoryService = postCategoryService;
        this.postService = postService;
        this.categoryService = categoryService;
    }

    @PostMapping
    public ResponseEntity<PostCategory> createPostCategory(
            @RequestParam Long postId,
            @RequestParam Long categoryId
    ) {

        Post post = new Post();
        post.setPostId(postId);
        Category category = new Category();
        category.setCategoryId(categoryId);

        PostCategory createdPostCategory = postCategoryService.createPostCategory(post, category);
        return ResponseEntity.ok(createdPostCategory);
    }
}
