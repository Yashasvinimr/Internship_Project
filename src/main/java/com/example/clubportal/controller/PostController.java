package com.example.clubportal.controller;

import com.example.clubportal.dto.PostRequest;
import com.example.clubportal.entity.Post;
import com.example.clubportal.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/posts")
public class PostController {
    @Autowired
    private PostService postService;

    @PostMapping("/")
    public ResponseEntity<Post> createPost(@RequestBody PostRequest postRequest) {
        Post post = postService.createPost(postRequest);
        return ResponseEntity.ok(post);
    }

    @GetMapping("/public")
    public ResponseEntity<List<Post>> getAllPublicPosts() {
        return ResponseEntity.ok(postService.getAllPublicPosts());
    }

    @GetMapping("/club/{clubId}")
    public ResponseEntity<List<Post>> getClubPosts(@PathVariable Long clubId) {
        return ResponseEntity.ok(postService.getClubPosts(clubId));
    }

    @GetMapping("/tags/{tag}")
    public ResponseEntity<List<Post>> getPostsByTag(@PathVariable String tag) {
        return ResponseEntity.ok(postService.getPostsByTag(tag));
    }
}
