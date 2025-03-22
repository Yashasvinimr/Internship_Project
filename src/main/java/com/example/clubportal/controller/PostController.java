package com.example.clubportal.controller;

import com.example.clubportal.entity.Post;
import com.example.clubportal.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @GetMapping("/{eventId}")
    public ResponseEntity<List<Post>> getPostsByEvent(@PathVariable Long eventId) {
        return ResponseEntity.ok(postService.getPostsByEvent(eventId));
    }

    @PostMapping
    public ResponseEntity<Post> createPost(@RequestBody Post post) {
        return ResponseEntity.ok(postService.savePost(post));
    }
}
