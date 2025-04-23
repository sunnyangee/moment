package com.example.moment.entity;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "item")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    @Column(name = "is_taken")
    private Boolean isTaken = false;

    @Column(name = "is_limited", nullable = false)
    private Boolean isLimited = false;

    @Column(name = "max_quantity")
    private Integer maxQuantity;

    @ManyToMany(mappedBy = "items")
    private Set<User> users = new HashSet<>();

    protected Item() {}

    public Item(String name, Boolean isLimited, Integer maxQuantity) {
        this.name = name;
        this.isLimited = isLimited;
        this.maxQuantity = maxQuantity;
    }

    // --- 기존 필드 getter/setter ---
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Boolean getIsTaken() { return isTaken; }
    public void setIsTaken(Boolean isTaken) { this.isTaken = isTaken; }

    public Set<User> getUsers() { return users; }
    public void setUsers(Set<User> users) { this.users = users; }

    // --- isLimited 필드의 JavaBean 규칙 getter/setter ---
    public Boolean getIsLimited() {
        return isLimited;
    }
    public void setIsLimited(Boolean isLimited) {
        this.isLimited = isLimited;
    }

    // --- maxQuantity 필드 getter/setter ---
    public Integer getMaxQuantity() {
        return maxQuantity;
    }
    public void setMaxQuantity(Integer maxQuantity) {
        this.maxQuantity = maxQuantity;
    }
}
