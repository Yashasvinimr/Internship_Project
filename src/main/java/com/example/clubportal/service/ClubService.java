package com.example.clubportal.service;

import com.example.clubportal.entity.Club;
import com.example.clubportal.entity.User;
import com.example.clubportal.exceptions.ClubNotFoundException;
import com.example.clubportal.repository.ClubRepository;
import com.example.clubportal.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ClubService {
    private final ClubRepository clubRepository;

    @Autowired
    private UserRepository userRepository;
    // ✅ Get all clubs
    public List<Club> getAllClubs() {
        return clubRepository.findAll();
    }

    // ✅ Get club by ID with better exception handling
    public Club getClubById(Long id) {
        return clubRepository.findById(id)
                .orElseThrow(() -> new ClubNotFoundException("Club with ID " + id + " not found"));
    }

    // ✅ Save club with unique name validation
    public Club saveClub(Club club) {
        if (clubRepository.findByName(club.getName()) != null) {
            throw new IllegalArgumentException("Club with name '" + club.getName() + "' already exists");
        }
        return clubRepository.save(club);
    }

    // ✅ Delete a club by ID
    public void deleteClub(Long id) {
        if (!clubRepository.existsById(id)) {
            throw new ClubNotFoundException("Club with ID " + id + " not found");
        }
        clubRepository.deleteById(id);
    }


    @Transactional
    public void addMemberToClub(Long clubId, Long userId) {
        Club club = clubRepository.findById(clubId)
                .orElseThrow(() -> new RuntimeException("Club not found"));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        club.getMembers().add(user); // Add user to the club's members list
        clubRepository.save(club);  // Save the updated club
    }


}
