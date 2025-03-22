package com.example.clubportal.service;

import com.example.clubportal.entity.Membership;
import com.example.clubportal.repository.MembershipRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MembershipService {
    private final MembershipRepository membershipRepository;

    public List<Membership> getUserMemberships(Long userId) {
        return membershipRepository.findByUserId(userId);
    }

    public Membership saveMembership(Membership membership) {
        return membershipRepository.save(membership);
    }
}
