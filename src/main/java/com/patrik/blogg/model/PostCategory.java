package com.patrik.blogg.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity
@Table(name = "post_category")
public class PostCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "post_id")
    @JsonBackReference(value = "post-postcategory")
    private Post post;

    @ManyToOne
    @JoinColumn(name = "category_id")
    @JsonBackReference(value = "category-postcategory")
    private Category category;

    public PostCategory() {
    }

    public PostCategory(Post post, Category category) {
        this.post = post;
        this.category = category;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "PostCategory{" +
                "id=" + id +
                ", post=" + post +
                ", category=" + category +
                '}';
    }
}




