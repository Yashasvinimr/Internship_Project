package com.example.clubportal.controller;

import com.example.clubportal.dto.CommentRequest;
import com.example.clubportal.entity.Comment;
import com.example.clubportal.service.CommentService;
import com.example.clubportal.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/comments")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @Autowired
    private PostService postService;
    @PostMapping("/comments")
    public ResponseEntity<Comment> createComment(@RequestBody CommentRequest commentRequest) throws IOException {
        return ResponseEntity.ok(commentService.createComment(commentRequest));
    }

    @GetMapping("/{postId}")
    public ResponseEntity<List<Comment>> getCommentsByPost(@PathVariable Long postId) {
        return ResponseEntity.ok(commentService.getCommentsByPost(postId));
    }

    @PostMapping("/{postId}/like")
    public ResponseEntity<String> likePost(@PathVariable Long postId, @RequestParam Long userId) {
        postService.toggleLike(postId, userId);
        return ResponseEntity.ok("Post liked/unliked successfully!");
    }
    @PostMapping("/{commentId}/reply")
    public ResponseEntity<Comment> replyToComment(@PathVariable Long commentId, @RequestBody CommentRequest request) {
        return ResponseEntity.ok(commentService.replyToComment(commentId, request));
    }


}
