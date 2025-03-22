package com.example.clubportal.service;

import com.example.clubportal.entity.Post;
import com.example.clubportal.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;

    public List<Post> getPostsByEvent(Long eventId) {
        return postRepository.findByEventId(eventId);
    }

    public Post savePost(Post post) {
        return postRepository.save(post);
    }
}
