package com.example.clubportal.service;

import com.example.clubportal.entity.Club;
import com.example.clubportal.entity.Event;
import com.example.clubportal.repository.ClubRepository;
import com.example.clubportal.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EventService {
    private final EventRepository eventRepository;
    private final ClubRepository clubRepository;

    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    public List<Event> getExploreEvents() {
        return eventRepository.findByVisibility(Event.Visibility.PUBLIC);
    }

    public Event createEvent(Long clubId, Event event) {
        Optional<Club> club = clubRepository.findById(clubId);
        if (club.isEmpty()) {
            throw new IllegalArgumentException("Club not found");
        }
        event.setClub(club.get());
        return eventRepository.save(event);
    }
}
