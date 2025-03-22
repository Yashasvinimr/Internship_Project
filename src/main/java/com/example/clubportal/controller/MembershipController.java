package com.example.clubportal.controller;

import com.example.clubportal.entity.Membership;
import com.example.clubportal.service.MembershipService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/memberships")
@RequiredArgsConstructor
public class MembershipController {
    private final MembershipService membershipService;

    @GetMapping("/{userId}")
    public ResponseEntity<List<Membership>> getUserMemberships(@PathVariable Long userId) {
        return ResponseEntity.ok(membershipService.getUserMemberships(userId));
    }

    @PostMapping
    public ResponseEntity<Membership> joinClub(@RequestBody Membership membership) {
        return ResponseEntity.ok(membershipService.saveMembership(membership));
    }
}

