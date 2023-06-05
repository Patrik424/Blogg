package com.patrik.blogg.service;

import com.patrik.blogg.exception.CommentNotFoundException;
import com.patrik.blogg.model.Comment;
import com.patrik.blogg.model.Post;
import com.patrik.blogg.model.User;
import com.patrik.blogg.repository.CommentRepository;
import com.patrik.blogg.repository.PostRepository;
import com.patrik.blogg.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private UserRepository userRepository;

    public Comment newComment(Comment comment){
        User user = userRepository.findById(comment.getUser().getUserId()).orElseThrow(() -> new RuntimeException("User was wrong"));
        Post post = postRepository.findById(comment.getPost().getPostId()).orElseThrow(() -> new RuntimeException("Post was wrong"));
        comment.setUser(user);
        comment.setPost(post);
        return commentRepository.save(comment);
    }


    public List<Comment> getAllComments() {
        List<Comment> comments = commentRepository.findAll();
        for (Comment comment : comments) {
            Long userId = comment.getUser().getUserId();
            comment.getUser().setUserId(userId);
    }
        return comments;
}

    public Comment getCommentById(Long id){
        return commentRepository.findById(id)
                .orElseThrow(() -> new CommentNotFoundException(id));
    }

    public Comment updateComment(Long id, Comment editComment){

        return commentRepository.findById(id)
                .map(comment -> {
                    comment.setContent(editComment.getContent());
                    return commentRepository.save(comment);
                })
                .orElseThrow(() -> new CommentNotFoundException(id));
    }

    public void deleteComment(Long id){
        commentRepository.findById(id).orElseThrow(() -> new CommentNotFoundException(id));
        commentRepository.deleteById(id);
    }
}
