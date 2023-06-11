package com.patrik.blogg.service;

import com.patrik.blogg.exception.PostNotFoundException;
import com.patrik.blogg.model.Author;
import com.patrik.blogg.model.Category;
import com.patrik.blogg.model.Post;
import com.patrik.blogg.repository.AuthorRepository;
import com.patrik.blogg.repository.CategoryRepository;
import com.patrik.blogg.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private AuthorRepository authorRepository;

    public List<Post> getAllPosts(){return postRepository.findAll();}

    public Post getPostById(Long id){
        return postRepository.findById(id)
                .orElseThrow(() -> new PostNotFoundException(id));
    }

    public Post newPost(Post post) {
        Category category = categoryRepository.findById(post.getCategory().getCategoryId())
                .orElseThrow(() -> new RuntimeException("Kategori hittades inte."));
        Author author = authorRepository.findById(post.getAuthor().getAuthorId())
                .orElseThrow(() -> new RuntimeException("Author hittades inte"));

        post.setCategory(category);
        post.setAuthor(author);

        return postRepository.save(post);
    }

    public Post updatePost(Long id, Post editPost){
        return postRepository.findById(id)
                .map(post -> {
                    post.setTitle(editPost.getTitle());
                    post.setContent(editPost.getContent());
                    return postRepository.save(post);
                })
                .orElseThrow(() -> new PostNotFoundException(id));
    }

    public void deletePost(Long id){
        postRepository.findById(id).orElseThrow(() -> new PostNotFoundException(id));
        postRepository.deleteById(id);
    }


}
