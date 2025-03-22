package com.example.clubportal.repository;

import com.example.clubportal.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
    List<Event> findByIsPublicTrue(); // For Explore Page

    @Query("SELECT e FROM Event e WHERE e.club.id IN :clubIds")
    List<Event> findByClubIds(@Param("clubIds") List<Long> clubIds); // For You Page
}





