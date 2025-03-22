package com.example.clubportal.repository;

import com.example.clubportal.entity.DirectMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DirectMessageRepository extends JpaRepository<DirectMessage, Long> {
    List<DirectMessage> findByReceiverId(Long receiverId);
}