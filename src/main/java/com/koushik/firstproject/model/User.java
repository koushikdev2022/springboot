package com.koushik.firstproject.model;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue // Do not specify GenerationType since we will manually set the UUID
    private UUID id; // UUID for the primary key

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String status;

    @Column(nullable = false, unique = true)
    private String mobile;

    // Constructors
    public User() {}

    public User(String email, String username, String password, String status, String mobile) {
        this.email = email;
        this.username = username;
        this.password = password;
        this.status = status;
        this.mobile = mobile;
    }

    // Method to generate UUID before persisting
    @PrePersist
    public void prePersist() {
        if (this.id == null) {
            this.id = UUID.randomUUID(); // Generate UUID if id is not already set
        }
    }

    // Getters and Setters

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
