package com.example.clubportal.repository;

import com.example.clubportal.entity.Club;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClubRepository extends JpaRepository<Club, Long> {
    @EntityGraph(attributePaths = "members")
    Club findByName(String name); // Fetch club by name

}
