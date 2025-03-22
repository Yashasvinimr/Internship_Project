package com.example.clubportal.service;

import com.example.clubportal.entity.DirectMessage;
import com.example.clubportal.repository.DirectMessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DirectMessageService {
    private final DirectMessageRepository directMessageRepository;

    public List<DirectMessage> getMessagesForUser(Long userId) {
        return directMessageRepository.findByReceiverId(userId);
    }

    public DirectMessage sendMessage(DirectMessage message) {
        return directMessageRepository.save(message);
    }
}
