package com.example.moment.entity;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;
    private String nickname;

    private int enabled;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "user_items", // 중간 테이블 이름
        joinColumns = @JoinColumn(name = "user_id"), // User 테이블의 외래 키
        inverseJoinColumns = @JoinColumn(name = "item_id") // Item 테이블의 외래 키
    )
    private Set<Item> items = new HashSet<>();

    // 기본 생성자 (JPA용)
    public User() {}

    // 생성자 추가
    public User(String username, String password, String nickname) {
        this.username = username;
        this.password = password;
        this.nickname = nickname;
    }

    // Getter 및 Setter 추가
    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getUsername() { return username; }

    public int getEnabled() {
        return enabled;
    }

    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }

    public void setPassword(String password) { this.password = password; }

    public String getNickname() { return nickname; }

    public void setNickname(String nickname) { this.nickname = nickname; }

    public Set<Item> getItems() { return items; }

    public void setItems(Set<Item> items) { this.items = items; }

    
}
