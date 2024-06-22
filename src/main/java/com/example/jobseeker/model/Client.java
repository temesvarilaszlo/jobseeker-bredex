package com.example.jobseeker.model;

import javax.persistence.*;
import java.util.UUID;

@Entity
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private UUID apiKey;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false, unique = true, length = 100)
    private String email;

    public Client() {}

    public Client(String name, String email) {
        this.name = name;
        this.email = email;
        this.apiKey = UUID.randomUUID();
    }

    public Long getId() {
        return id;
    }

    public UUID getApiKey() {
        return apiKey;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}