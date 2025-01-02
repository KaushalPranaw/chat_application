package com.demo.ChatApplication.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "friends")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Friend {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)  // Let Hibernate handle UUID generation
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user; // The user who owns the friendship

    @ManyToOne
    @JoinColumn(name = "friend_id", nullable = false)
    private User friend; // The friend in the relationship

    private String status; // pending, accepted, blocked
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
}
