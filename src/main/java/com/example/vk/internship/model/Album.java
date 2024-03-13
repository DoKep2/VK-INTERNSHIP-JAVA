package com.example.vk.internship.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Album {
    private Long userId;
    @Id
    private Long id;
    private String title;

    public Album() {
    }

    public Album(Long id, Long userId, String title) {
        this.id = id;
        this.userId = userId;
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
