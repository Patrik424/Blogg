package com.patrik.blogg.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "posts")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;
    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;

    @Column(name = "postdate")
    private LocalDate postDate;
    @ManyToOne(

    )
    @JsonBackReference(value = "author-post")
    private Author author;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonManagedReference(value = "post-postcategory")
    private List<PostCategory> postCategories = new ArrayList<>();




    @OneToMany(mappedBy = "post",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY)
    @JsonManagedReference(value = "post-comment")
    private List<Comment> comments = new ArrayList<>();



    @ManyToOne()
    @JsonBackReference(value = "category-post")
    private Category category;



    public Post() {
    }

    public Post(Long postId, String title, String content, LocalDate postDate, Author author, List<PostCategory> postCategories, List<Comment> comments, Category category) {
        this.postId = postId;
        this.title = title;
        this.content = content;
        this.postDate = postDate;
        this.author = author;
        this.postCategories = postCategories;
        this.comments = comments;
        this.category = category;
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDate getPostDate() {
        return postDate;
    }

    public void setPostDate(LocalDate postDate) {
        this.postDate = postDate;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<PostCategory> getPostCategories() {
        return postCategories;
    }

    public void setPostCategories(List<PostCategory> postCategories) {
        this.postCategories = postCategories;
    }

    @Override
    public String toString() {
        return "Post{" +
                "postId=" + postId +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", postDate=" + postDate +
                ", author=" + author +
                ", postCategories=" + postCategories +
                ", comments=" + comments +
                ", category=" + category +
                '}';
    }
}


