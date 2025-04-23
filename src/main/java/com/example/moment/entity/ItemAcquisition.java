package com.example.moment.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "item_acquisition", uniqueConstraints = @UniqueConstraint(
    name = "uniq_user_item", columnNames = {"username", "item_key"}
))
public class ItemAcquisition {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "item_key", nullable = false)
    private String itemKey;

    @Column(nullable = false)
    private String username;

    @Column(name = "acquired_at", nullable = false)
    private LocalDateTime acquiredAt = LocalDateTime.now();

    /**
     * JPA용 기본 생성자 (public으로 변경)
     */
    public ItemAcquisition() {
    }

    /**
     * 편리한 생성자
     */
    public ItemAcquisition(String itemKey, String username) {
        this.itemKey = itemKey;
        this.username = username;
        this.acquiredAt = LocalDateTime.now();
    }

    // --- getters & setters ---
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getItemKey() { return itemKey; }
    public void setItemKey(String itemKey) { this.itemKey = itemKey; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public LocalDateTime getAcquiredAt() { return acquiredAt; }
    public void setAcquiredAt(LocalDateTime acquiredAt) { this.acquiredAt = acquiredAt; }
}

