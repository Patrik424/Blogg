package com.patrik.blogg.service;

import com.patrik.blogg.model.Category;
import com.patrik.blogg.model.Post;
import com.patrik.blogg.model.PostCategory;
import com.patrik.blogg.repository.PostCategoryRepository;
import org.springframework.stereotype.Service;

@Service
public class PostCategoryService {

    private final PostCategoryRepository postCategoryRepository;

    public PostCategoryService(PostCategoryRepository postCategoryRepository) {
        this.postCategoryRepository = postCategoryRepository;
    }

    public PostCategory createPostCategory(Post post, Category category) {
        PostCategory postCategory = new PostCategory(post, category);
        return postCategoryRepository.save(postCategory);
    }
}
