package com.example.clubportal.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@Setter
public class PostRequest {
    private Long userId;
    private Long clubId; // Nullable for public posts
    private String caption;
    private List<String> tags;
    private boolean isPublic;
    private String imageUrl; // URL or upload logic later
}
