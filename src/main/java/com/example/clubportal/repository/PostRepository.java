package com.example.clubportal.repository;

import com.example.clubportal.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByIsPublicTrue();
    List<Post> findByClubId(Long clubId);
    List<Post> findByTagsContaining(String tag);
}
