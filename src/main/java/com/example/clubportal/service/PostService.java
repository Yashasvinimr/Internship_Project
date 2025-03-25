package com.example.clubportal.service;

import com.example.clubportal.dto.PostRequest;
import com.example.clubportal.entity.Post;
import com.example.clubportal.entity.User;
import com.example.clubportal.entity.Club;
import com.example.clubportal.repository.PostRepository;
import com.example.clubportal.repository.UserRepository;
import com.example.clubportal.repository.ClubRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class PostService {
    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ClubRepository clubRepository;


    public Post createPost(PostRequest postRequest) {
        User user = userRepository.findById(postRequest.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Club club = (postRequest.getClubId() != null)
                ? clubRepository.findById(postRequest.getClubId()).orElse(null)
                : null;

        Post post = new Post();
        post.setUser(user);
        post.setClub(club);
        post.setCaption(postRequest.getCaption());
        post.setTags(postRequest.getTags());
        post.setPublic(postRequest.isPublic());
        post.setImageUrl(postRequest.getImageUrl()); // Handle file upload separately

        return postRepository.save(post);
    }


    public List<Post> getAllPublicPosts() {
        return postRepository.findByIsPublicTrue();
    }

    public List<Post> getClubPosts(Long clubId) {
        return postRepository.findByClubId(clubId);
    }

    public List<Post> getPostsByTag(String tag) {
        return postRepository.findByTagsContaining(tag);
    }
}
