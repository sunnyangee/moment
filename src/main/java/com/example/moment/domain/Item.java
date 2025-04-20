package com.example.moment.domain;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;
import com.example.moment.entity.User;

@Entity
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Boolean isTaken = false;

    @ManyToMany(mappedBy = "items")  // User 엔티티의 items 필드를 참조
    private Set<User> users = new HashSet<>();

    public Item() {}

    public Item(String name) {
        this.name = name;
    }

    // Getter 및 Setter 추가
    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public Boolean getIsTaken() { return isTaken; }

    public void setIsTaken(Boolean isTaken) { this.isTaken = isTaken; }

    public Set<User> getUsers() { return users; }

    public void setUsers(Set<User> users) { this.users = users; }
}
