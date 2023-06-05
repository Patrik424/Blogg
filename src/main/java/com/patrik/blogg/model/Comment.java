package com.patrik.blogg.model;

import com.fasterxml.jackson.annotation.JsonBackReference;


import javax.persistence.*;

@Entity
@Table(name = "comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;

    @Column(name = "content")
    private String content;

    @ManyToOne(

    )
    private User user;

    @ManyToOne()
    @JsonBackReference(value = "post-comment")
    private Post post;

    public Comment() {
    }

    public Comment(Long commentId, String content, User user, Post post) {
        this.commentId = commentId;
        this.content = content;
        this.user = user;
        this.post = post;
    }

    public Long getCommentId() {
        return commentId;
    }

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public Comment(Long commentId, String content) {
        this.commentId = commentId;
        this.content = content;
    }
}
