package com.demo.ChatApplication.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)  // Let Hibernate handle UUID generation
    private UUID id;
    private String firstName;
    private String lastName;
    private String mobileNumber;
    private String address;

    private String email;            // To uniquely identify the user for login
    private String username;         // Optional: Alternative to email for login
    private String password;         // Encrypted password for authentication
    private Boolean isActive;        // To track if the user account is active
    private String role;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Friend> friends; // List of friendships owned by the user
}