package com.example.clubportal.controller;

import com.example.clubportal.entity.DirectMessage;
import com.example.clubportal.service.DirectMessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/messages")
@RequiredArgsConstructor
public class DirectMessageController {
    private final DirectMessageService directMessageService;

    @GetMapping("/{userId}")
    public ResponseEntity<List<DirectMessage>> getUserMessages(@PathVariable Long userId) {
        return ResponseEntity.ok(directMessageService.getMessagesForUser(userId));
    }

    @PostMapping
    public ResponseEntity<DirectMessage> sendMessage(@RequestBody DirectMessage message) {
        return ResponseEntity.ok(directMessageService.sendMessage(message));
    }
}
