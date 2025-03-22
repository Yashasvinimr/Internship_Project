package com.example.clubportal.controller;

import com.example.clubportal.entity.Club;
import com.example.clubportal.service.ClubService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clubs")
@RequiredArgsConstructor
public class ClubController {
    private final ClubService clubService;

    // ✅ Get all clubs
    @GetMapping
    public ResponseEntity<List<Club>> getAllClubs() {
        return ResponseEntity.ok(clubService.getAllClubs());
    }

    // ✅ Get club by ID
    @GetMapping("/{id}")
    public ResponseEntity<Club> getClubById(@PathVariable Long id) {
        return ResponseEntity.ok(clubService.getClubById(id));
    }

    // ✅ Create a new club
    @PostMapping
    public ResponseEntity<Club> createClub(@RequestBody Club club) {
        return ResponseEntity.ok(clubService.saveClub(club));
    }

    // ✅ Delete a club
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteClub(@PathVariable Long id) {
        clubService.deleteClub(id);
        return ResponseEntity.ok("Club deleted successfully.");
    }


        @PostMapping("/{clubId}/join/{userId}")
        public ResponseEntity<String> joinClub(@PathVariable Long clubId, @PathVariable Long userId) {
            clubService.addMemberToClub(clubId, userId);
            return ResponseEntity.ok("User joined the club successfully!");
        }
    }


