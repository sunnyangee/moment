package com.example.moment.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "progress")
public class Progress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // whatever “stage” you need to track
    @Column(nullable = false)
    private String stage;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;

    public Progress() {}

    public Progress(User user, String stage) {
        this.user = user;
        this.stage = stage;
    }

    // getters & setters
    public Long getId() { return id; }
    public String getStage() { return stage; }
    public void setStage(String stage) { this.stage = stage; }
    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
}
