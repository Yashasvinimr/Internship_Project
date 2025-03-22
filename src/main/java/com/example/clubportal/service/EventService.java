package com.example.clubportal.service;

import com.example.clubportal.entity.Event;
import com.example.clubportal.repository.EventRepository;
import com.example.clubportal.repository.MembershipRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EventService {
    private final EventRepository eventRepository;
    private final MembershipRepository membershipRepository;

    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    public List<Event> getExploreEvents() {
        return eventRepository.findByIsPublicTrue();
    }

    public List<Event> getForYouEvents(Long userId) {
        List<Long> clubIds = membershipRepository.findByUserId(userId)
                .stream()
                .map(membership -> membership.getClub().getId())
                .toList();

        return eventRepository.findByClubIds(clubIds);
    }

    public Event saveEvent(Event event) {
        return eventRepository.save(event);
    }
}
