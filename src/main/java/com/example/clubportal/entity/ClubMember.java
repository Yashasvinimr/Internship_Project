package com.example.clubportal.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "club_members")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClubMember {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "club_id", nullable = false)
    private Club club;

    @Enumerated(EnumType.STRING)
    private Role role = Role.MEMBER;

    @Temporal(TemporalType.TIMESTAMP)
    private Date joinedAt = new Date();

    public enum Role {
        MEMBER,
        COORDINATOR
    }
}
