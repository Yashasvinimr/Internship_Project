package com.example.clubportal.repository;

import com.example.clubportal.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByEventId(Long eventId); // Fetch posts for an event

}